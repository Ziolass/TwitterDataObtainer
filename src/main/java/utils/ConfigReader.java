package utils;

import datamodel.ConfigModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

/**
 * Created by Michau on 16.05.2016.
 */
public class ConfigReader {

    public ConfigModel readConfig() {
        JSONParser parser = new JSONParser();

        try (FileReader fileReader = new FileReader("config.txt")){

            Object obj = parser.parse(fileReader);

            JSONObject jsonObject = (JSONObject) obj;
            String consumerKey = (String) jsonObject.get("consumerKey");
            String consumerSecret = (String) jsonObject.get("consumerSecret");
            String accessToken = (String) jsonObject.get("accessToken");
            String accessTokenSecret = (String) jsonObject.get("accessTokenSecret");

            double SWLongitude = Double.parseDouble((String) jsonObject.get("boundingBox_SouthWest_Longitude"));
            double SWLatitude = Double.parseDouble((String) jsonObject.get("boundingBox_SouthWest_Latitude"));
            double NELongitude = Double.parseDouble((String) jsonObject.get("boundingBox_NorthEast_Longitude"));
            double NELatitude = Double.parseDouble((String) jsonObject.get("boundingBox_NorthEast_Latitude"));

            String postgres_host = (String) jsonObject.get("postgres_host");
            String postgres_dbName = (String) jsonObject.get("postgres_dbName");
            String postgres_port = (String) jsonObject.get("postgres_port");
            String postgres_user = (String) jsonObject.get("postgres_user");
            String postgres_password = (String) jsonObject.get("postgres_password");
            return new ConfigModel
                            (consumerKey,
                            consumerSecret,
                            accessToken,
                            accessTokenSecret,
                            SWLongitude,
                            SWLatitude,
                            NELongitude,
                            NELatitude,
                            postgres_host,
                            postgres_dbName,
                            postgres_port,
                            postgres_user,
                            postgres_password);


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Nie udalo sie odnalezc pliku");
        }
        return null;
    }

}
