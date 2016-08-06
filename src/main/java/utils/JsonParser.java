package utils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

/**
 * Created by Michau on 17.05.2016.
 */
public class JsonParser
{
    private JSONParser parser;

    public JsonParser() {
        parser = new JSONParser();
    }

    public String processTweet(String input) {

        try
        {
            Object obj = parser.parse(input);
            JSONObject jsonObject = (JSONObject) obj;
            String creation_time = (String) jsonObject.get("created_at");
            String id = (String) jsonObject.get("id_str");
            String text = (String) jsonObject.get("text");
            String formatted_text = tweetTextFormat(text);

            return creation_time+"<!przerwa!>"+id+"!<przerwa>!"+text;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String tweetTextFormat(String input) {
        return input;
    }
}
