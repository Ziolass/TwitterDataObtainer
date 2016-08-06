package database;

/**
 * Created by Michau on 02.08.2016.
 */
public class PostGreConnector implements DatabaseConnector{
    private static PostGreConnector ourInstance = new PostGreConnector();

    public static PostGreConnector getInstance() {
        return ourInstance;
    }

    private PostGreConnector() {
    }
}
