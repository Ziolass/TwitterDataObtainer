package utils.input;

import com.google.common.collect.Lists;

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


    public static void main(String[] args){
        String a = "pl";
        System.out.println(InputParametersParser.parseInputValues(a));
    }
}
