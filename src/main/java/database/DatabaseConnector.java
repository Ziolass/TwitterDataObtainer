package database;


import java.sql.Connection;

/**
 * Created by Michau on 02.08.2016.
 */
public interface DatabaseConnector {
    public Connection getConnection();
}
