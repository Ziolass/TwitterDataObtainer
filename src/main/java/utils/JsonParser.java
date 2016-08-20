package utils;
import datamodel.TweetModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.sql.PreparedStatement;

/**
 * Created by Michau on 17.05.2016.
 */
public class JsonParser
{
    private JSONParser parser;

    public JsonParser() {
        parser = new JSONParser();
    }

    public TweetModel processTweet(String input) {

        try
        {
            Object obj = parser.parse(input);
            JSONObject jsonObject = (JSONObject) obj;
            String creation_time = (String) jsonObject.get("created_at");
            String id = (String) jsonObject.get("id_str");
            String text = (String) jsonObject.get("text");

            return new TweetModel(id,text,creation_time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
