package initializer;

import datamodel.ConfigModel;
import twitter.TwitterClientImpl;
import utils.ConfigReader;
import utils.input.InputParametersHandler;
import utils.input.InputParametersHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Michau on 03.08.2016.
 */
public class ApplicationInitializer {
    private static final Logger log = Logger.getLogger(InputParametersHandler.class.getName());

    public static void main(String[] args) {
        log.log(Level.INFO,"Began streaming");
        ConfigReader configReader;
        ConfigModel configModel;

        InputParametersHandler inputParametersHandler = new InputParametersHandler(args);
        InputParametersHolder inputParametersHolder = inputParametersHandler.getInputParameters();


        configReader = new ConfigReader();
        configModel = configReader.readConfig();

        TwitterClientImpl twitterClient = TwitterClientImpl.getInstance();
        twitterClient.prepareForStreaming(inputParametersHolder, configModel);

        twitterClient.startStreaming();

    }

}
