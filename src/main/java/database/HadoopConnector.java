package database;

/**
 * Created by Michau on 02.08.2016.
 */
public class HadoopConnector implements DatabaseConnector{
    private static HadoopConnector ourInstance = new HadoopConnector();

    public static HadoopConnector getInstance() {
        return ourInstance;
    }

    private HadoopConnector() {
    }
}
