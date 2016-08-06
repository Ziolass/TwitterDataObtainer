package utils;

import datamodel.ConfigModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.File;

/**
 * Created by Michau on 16.05.2016.
 */
public class ConfigReader {
    public ConfigModel readConfig() {
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("config.txt"));

            JSONObject jsonObject = (JSONObject) obj;
            String consumerKey = (String) jsonObject.get("consumerKey");
            String consumerSecret = (String) jsonObject.get("consumerSecret");
            String accessToken = (String) jsonObject.get("accessToken");
            String accessTokenSecret = (String) jsonObject.get("accessTokenSecret");
            double SWLongitude = Double.parseDouble((String) jsonObject.get("boundingBox_SouthWest_Longitude"));
            double SWLatitude = Double.parseDouble((String) jsonObject.get("boundingBox_SouthWest_Latitude"));
            double NELongitude = Double.parseDouble((String) jsonObject.get("boundingBox_NorthEast_Longitude"));
            double NELatitude = Double.parseDouble((String) jsonObject.get("boundingBox_NorthEast_Latitude"));

            return new ConfigModel
                    (consumerKey, consumerSecret, accessToken, accessTokenSecret,
                            SWLongitude, SWLatitude, NELongitude, NELatitude);


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Nie udalo sie odnalezc pliku");
        }
        return null;
    }

}
