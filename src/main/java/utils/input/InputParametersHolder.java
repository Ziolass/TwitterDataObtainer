package utils.input;

import java.util.List;

/**
 * Created by Michau on 03.08.2016.
 */
public class InputParametersHolder {
    private final List<String> languages;
    private final List<String> keywords;
    private final int duration;

    public List<String> getLanguages() {
        return languages;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public int getDuration() {
        return duration;
    }


    public InputParametersHolder(List<String> languages, List<String> keywords, int duration) {
        this.languages = languages;
        this.keywords = keywords;
        this.duration = duration;

    }
}
