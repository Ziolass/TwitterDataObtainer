package initializer;

import datamodel.ConfigModel;
import twitter.TwitterClientImpl;
import utils.ConfigReader;
import utils.input.InputParametersHandler;
import utils.input.InputParametersHolder;

/**
 * Created by Michau on 03.08.2016.
 */
public class ApplicationInitializer {

    public static void main(String[] args) {

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
