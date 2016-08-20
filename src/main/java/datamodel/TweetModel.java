package datamodel;

/**
 * Created by Michau on 10.08.2016.
 */
public class TweetModel {
    private final String creation_time;
    private final String id;
    private final String text;

    public String getCreation_time() {
        return creation_time;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public TweetModel(String id, String text, String creation_time){
        this.creation_time = creation_time;
        this.id = id;
        this.text = text;
    }

}

