package database;
import com.sun.javafx.binding.StringFormatter;
import datamodel.ConfigModel;
import datamodel.SingletonConfigModel;
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
import java.util.logging.Level;
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
    private int counter=0;
    private int globalCounter=0;
    private static final Logger log = Logger.getLogger(PostGreConnector.class.getName());


    public Connection getConnection() {
        return connection;
    }

    private PostGreConnector() {
        SingletonConfigModel singletonConfigModel = SingletonConfigModel.getInstance();
        try {
           Class.forName("org.postgresql.Driver");

            String connection_details = "jdbc:postgresql://"+
                    singletonConfigModel.getPostgres_host() + ":" +
                    singletonConfigModel.getPostgres_port() + "/"+
                    singletonConfigModel.getPostgres_dbName();

            connection = DriverManager.getConnection(
                    connection_details,
                    singletonConfigModel.getPostgres_user(),
                    singletonConfigModel.getPostgres_password());

            preparedStatement = connection.prepareStatement("INSERT INTO tweets VALUES (?,?,?)");

        }catch (ClassNotFoundException cEx){
        //
                System.err.println(cEx.getClass().getName()+": "+cEx.getMessage());

        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
    }
    public void insertRecord(TweetModel tweetModel) throws  Exception{

        try {
            preparedStatement.setString(2,tweetModel.getText());
            preparedStatement.setString(1,tweetModel.getId());

            preparedStatement.setTimestamp(3,getTwitterDate(tweetModel.getCreation_time()));

            preparedStatement.addBatch();

            counter++;
            if(counter==10){
                int[] result = preparedStatement.executeBatch();
                globalCounter += result.length;
                log.log(Level.INFO,"Already added "+globalCounter+" records");
                counter=0;
            }

        }catch (SQLException sqlE){
            sqlE.printStackTrace();
            sqlE.getNextException().printStackTrace();
            preparedStatement.clearParameters();
        }catch (ParseException e){
            preparedStatement.clearParameters();
            e.printStackTrace();
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
        final String TWITTER = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(TWITTER, Locale.ENGLISH);
        sf.setLenient(true);
        Date date;
        try {
             date = sf.parse(stringDate);
        }catch (NullPointerException e){
            throw new ParseException("Null exception",0);
        }
        Timestamp timestamp = new Timestamp(date.getTime());
        return  timestamp;
    }

}
