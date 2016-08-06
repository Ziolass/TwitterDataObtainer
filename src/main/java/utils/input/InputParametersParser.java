package utils.input;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Michau on 03.08.2016.
 */
public class InputParametersParser <T>{
    public static List<String> parseInputValues(String inputParameter)
    {
        String[] values = inputParameter.split("|");
        return Arrays.asList(values);

    }
}
