package utils.input;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Michau on 03.08.2016.
 */
public class InputParametersParser {
    public static List<String> parseInputValues(String inputParameter)
    {
        String[] values = inputParameter.split("_");
        return Arrays.asList(values);
    }

}
