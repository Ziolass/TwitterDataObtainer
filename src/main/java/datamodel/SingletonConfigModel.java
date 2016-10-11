package datamodel;

import com.twitter.hbc.httpclient.Connection;
import org.xml.sax.SAXException;
import utils.ConfigReader;

import java.io.IOException;

/**
 * Created by Michau on 09/10/16.
 */
public class SingletonConfigModel {

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

    public double getNELatitude() {
        return NELatitude;
    }

    public void setNELatitude(double NELatitude) {
        this.NELatitude = NELatitude;
    }

    public double getNELongitude() {
        return NELongitude;
    }

    public void setNELongitude(double NELongitude) {
        this.NELongitude = NELongitude;
    }

    public double getSWLatitude() {
        return SWLatitude;
    }

    public void setSWLatitude(double SWLatitude) {
        this.SWLatitude = SWLatitude;
    }

    public double getSWLongitude() {
        return SWLongitude;
    }

    public void setSWLongitude(double SWLongitude) {
        this.SWLongitude = SWLongitude;
    }

    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }

    public void setAccessTokenSecret(String accessTokenSecret) {
        this.accessTokenSecret = accessTokenSecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    private String postgres_port;
    private String postgres_user;
    private String postgres_password;

    private static final SingletonConfigModel ourInstance = new SingletonConfigModel();

    public static SingletonConfigModel getInstance() {
        return ourInstance;
    }

    private SingletonConfigModel() {

        ConfigReader configReader = new ConfigReader();
        ConfigModel configModel = configReader.readConfig();

        this.consumerKey = configModel.getConsumerKey();
        this.consumerSecret = configModel.getConsumerSecret();
        this.accessToken = configModel.getAccessToken();
        this.accessTokenSecret = configModel.getAccessTokenSecret();
        this.SWLongitude = configModel.getSWLongitude();
        this.SWLatitude = configModel.getSWLatitude();
        this.NELongitude = configModel.getNELongitude();
        this.NELatitude = configModel.getNELatitude();
        this.postgres_host = configModel.getPostgres_host();
        this.postgres_dbName = configModel.getPostgres_dbName();
        this.postgres_port = configModel.getPostgres_port();
        this.postgres_user = configModel.getPostgres_user();
        this.postgres_password = configModel.getPostgres_password();

    }
}


