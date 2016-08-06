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

    public ConfigModel(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret, double SWLongitude, double SWLatitude, double NELongitude, double NELatitude) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;
        this.SWLongitude = SWLongitude;
        this.SWLatitude = SWLatitude;
        this.NELongitude = NELongitude;
        this.NELatitude = NELatitude;
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
}

