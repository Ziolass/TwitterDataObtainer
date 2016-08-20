package database;

import java.sql.*;

/**
 * Created by Michau on 02.08.2016.
 */
public class PostGreConnector implements DatabaseConnector{
    private static PostGreConnector ourInstance = new PostGreConnector();

    public static PostGreConnector getInstance() {
        return ourInstance;
    }
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    private PostGreConnector() {
        try {
            Class.forName("org.postgresql.Driver");

        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bachelor",
                        "bachelor", "bachelor");

        }catch (ClassNotFoundException cEx){
            cEx.printStackTrace();
        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }

    }
    public String insertRecord(String message, int id){
        PreparedStatement preparedStatement = null;

        ResultSet resultSet =null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO tweets VALUES (?,?)");
            preparedStatement.setString(1,message);
            preparedStatement.setInt(2,id);
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException sqlE){
            sqlE.printStackTrace();
        }
        return resultSet.toString();

    }
}
