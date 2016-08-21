package database;

import datamodel.TweetModel;

import java.sql.Connection;
import java.util.logging.Logger;

/**
 * Created by Michau on 02.08.2016.
 */
public class HadoopConnector implements DatabaseConnector{
    private static HadoopConnector ourInstance = new HadoopConnector();
    private static final Logger log = Logger.getLogger(HadoopConnector.class.getName());

    public static HadoopConnector getInstance() {
        return ourInstance;
    }

    private HadoopConnector() {
    }

    public Connection getConnection() {
        return null;
    }

    public void insertRecord(TweetModel tweetModel) throws Exception{

    }

    public void fillUpBatch() {

    }
}
