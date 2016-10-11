package datamodel;

/**
 * Created by Michau on 17.05.2016.
 */
public class ConfigModel {
    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String accessTokenSecret;
    private double SWLongitude;
    private double SWLatitude;
    private double NELongitude;
    private  double NELatitude;
    private String postgres_host;
    private String postgres_dbName;
    private String postgres_port;
    private String postgres_user;
    private String postgres_password;

    public ConfigModel(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret, double SWLongitude, double SWLatitude, double NELongitude, double NELatitude, String postgres_host, String postgres_dbName, String postgres_port, String postgres_user, String postgres_password) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;
        this.SWLongitude = SWLongitude;
        this.SWLatitude = SWLatitude;
        this.NELongitude = NELongitude;
        this.NELatitude = NELatitude;
        this.postgres_host = postgres_host;
        this.postgres_dbName = postgres_dbName;
        this.postgres_port = postgres_port;
        this.postgres_user = postgres_user;
        this.postgres_password = postgres_password;
    }

    @Override
    public String toString() {
        return "ConfigModel{" +
                "consumerKey='" + consumerKey + '\'' +
                ", consumerSecret='" + consumerSecret + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", accessTokenSecret='" + accessTokenSecret + '\'' +
                ", SWLongitude=" + SWLongitude +
                ", SWLatitude=" + SWLatitude +
                ", NELongitude=" + NELongitude +
                ", NELatitude=" + NELatitude +
                '}';
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }

    public void setAccessTokenSecret(String accessTokenSecret) {
        this.accessTokenSecret = accessTokenSecret;
    }

    public double getSWLongitude() {
        return SWLongitude;
    }

    public void setSWLongitude(double SWLongitude) {
        this.SWLongitude = SWLongitude;
    }

    public double getSWLatitude() {
        return SWLatitude;
    }

    public void setSWLatitude(double SWLatitude) {
        this.SWLatitude = SWLatitude;
    }

    public double getNELongitude() {
        return NELongitude;
    }

    public void setNELongitude(double NELongitude) {
        this.NELongitude = NELongitude;
    }

    public double getNELatitude() {
        return NELatitude;
    }

    public void setNELatitude(double NELatitude) {
        this.NELatitude = NELatitude;
    }

    public String getPostgres_password() {
        return postgres_password;
    }

    public void setPostgres_password(String postgres_password) {
        this.postgres_password = postgres_password;
    }

    public String getPostgres_user() {
        return postgres_user;
    }

    public void setPostgres_user(String postgres_user) {
        this.postgres_user = postgres_user;
    }

    public String getPostgres_port() {
        return postgres_port;
    }

    public void setPostgres_port(String postgres_port) {
        this.postgres_port = postgres_port;
    }

    public String getPostgres_dbName() {
        return postgres_dbName;
    }

    public void setPostgres_dbName(String postgres_dbName) {
        this.postgres_dbName = postgres_dbName;
    }

    public String getPostgres_host() {
        return postgres_host;
    }

    public void setPostgres_host(String postgres_host) {
        this.postgres_host = postgres_host;
    }
}
