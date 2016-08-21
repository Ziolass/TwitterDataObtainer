package database;


import datamodel.TweetModel;

import java.sql.Connection;
import java.util.logging.Logger;

/**
 * Created by Michau on 02.08.2016.
 */
public interface DatabaseConnector {
    Connection getConnection();
    Logger log = Logger.getLogger(DatabaseConnector.class.getName());


    void insertRecord(TweetModel tweetModel) throws Exception;

    void fillUpBatch();
}