package database;
import datamodel.TweetModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * Created by Michau on 02.08.2016.
 */
public class PostGreConnector implements DatabaseConnector{
    private static PostGreConnector ourInstance;

    public static PostGreConnector getInstance() {
        if(ourInstance ==null) {
            ourInstance = new PostGreConnector();
        }
        return ourInstance;
    }
    private Connection connection;
    private PreparedStatement preparedStatement;
    private int counter;
    private static final Logger log = Logger.getLogger(PostGreConnector.class.getName());


    public Connection getConnection() {
        return connection;
    }

    private PostGreConnector() {
        try {
           Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bachelor",
                        "bachelor", "bachelor");

            preparedStatement = connection.prepareStatement("INSERT INTO tweets VALUES (?,?,?);");

        }catch (ClassNotFoundException cEx){
        //
                System.err.println(cEx.getClass().getName()+": "+cEx.getMessage());

        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
        counter = 0;
        System.out.println(counter );

    }
    public void insertRecord(TweetModel tweetModel) throws  Exception{

        try {
            System.out.println(tweetModel.getText());
            preparedStatement.setString(1,tweetModel.getText());
            preparedStatement.setString(2,tweetModel.getId());

            preparedStatement.setTimestamp(3,getTwitterDate(tweetModel.getCreation_time()));

            preparedStatement.addBatch();
            counter++;
            if(counter==10){
                int[] result = preparedStatement.executeBatch();
                counter=0;
            }

        }catch (SQLException sqlE){
            sqlE.printStackTrace();
        }

    }

    public void fillUpBatch() {

        try {
            if (counter!= 0){
                preparedStatement.executeBatch();
            }
        }catch (SQLException sqlE){
            sqlE.printStackTrace();
        }
    }
    public static Timestamp getTwitterDate(String stringDate) throws ParseException
    {
        //TODO
        final String TWITTER = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(TWITTER, Locale.ENGLISH);
        sf.setLenient(true);

        Date date = sf.parse(stringDate);
        Timestamp timestamp = new Timestamp(date.getTime());
        return  timestamp;
    }

}
