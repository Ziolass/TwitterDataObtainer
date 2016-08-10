package utils.input;

import java.util.List;

/**
 * Created by Michau on 03.08.2016.
 */
public class InputParametersHolder {
    private final List<String> languages;
    private final List<String> keywords;

    public List<String> getLanguages() {
        return languages;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public InputParametersHolder(List<String> languages, List<String> keywords) {
        this.languages = languages;
        this.keywords = keywords;


    }
}
