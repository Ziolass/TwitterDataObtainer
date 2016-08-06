package initializer;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.event.Event;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import datamodel.ConfigModel;
import twitter.TwitterClientImpl;
import utils.ConfigReader;
import utils.JsonParser;
import utils.input.InputParametersHandler;
import utils.input.InputParametersHolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Michau on 03.08.2016.
 */
public class ApplicationInitializer {

    private static void Init(){

    }
    public static void main(String[] args) {
        ConfigReader configReader;
        ConfigModel configModel;
        StringBuilder stringBuilder;

        InputParametersHandler inputParametersHandler = new InputParametersHandler(args);
        InputParametersHolder inputParametersHolder = inputParametersHandler.getInputParameters();

        configReader = new ConfigReader();
        configModel = configReader.readConfig();

        TwitterClientImpl twitterClient = TwitterClientImpl.getInstance();
        twitterClient.prepareForStreaming(inputParametersHolder, configModel);

        twitterClient.startStreaming();

    }

}
