package twitter;

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
import database.DatabaseConnector;
import database.PostGreConnector;
import datamodel.ConfigModel;
import datamodel.SingletonConfigModel;
import datamodel.TweetModel;
import utils.JsonParser;
import utils.input.InputParametersHolder;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Michau on 06.08.2016.
 */
public class TwitterClientImpl {
    private static TwitterClientImpl ourInstance = new TwitterClientImpl();

    private BlockingQueue<String> msgQueue;
    private BlockingQueue<Event> eventQueue;
    private Hosts hosebirdHosts;
    private StatusesFilterEndpoint hosebirdEndpoint;
    private Authentication hosebirdAuth;
    private Client hosebirdClient;
    private int streamingSecondsDuration;

    public static TwitterClientImpl getInstance() {
        return ourInstance;
    }

    private TwitterClientImpl() {

        msgQueue = new LinkedBlockingQueue<String>(100000);
        eventQueue = new LinkedBlockingQueue<Event>(1000);
        hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        hosebirdEndpoint = new StatusesFilterEndpoint();

    }

    public void prepareForStreaming(InputParametersHolder inputParametersHolder) {

        this.streamingSecondsDuration = inputParametersHolder.getDuration()*60;
        hosebirdEndpoint.trackTerms(inputParametersHolder.getKeywords());
        hosebirdEndpoint.languages(inputParametersHolder.getLanguages());
        SingletonConfigModel singletonConfigModel = SingletonConfigModel.getInstance();

        hosebirdAuth = new OAuth1(singletonConfigModel.getConsumerKey(),
                singletonConfigModel.getConsumerSecret(),
                singletonConfigModel.getAccessToken(),
                singletonConfigModel.getAccessTokenSecret());

        ClientBuilder builder = new ClientBuilder()
                .name("Bachelors_first_try")                              // optional: mainly for the logs
                .hosts(hosebirdHosts)
                .authentication(hosebirdAuth)
                .endpoint(hosebirdEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue))
                .eventMessageQueue(eventQueue);                          // optional: use this if you want to process client events

        hosebirdClient = builder.build();
    }

    public void startStreaming() {
        DatabaseConnector databaseConnector = PostGreConnector.getInstance();
        TweetModel tweetModel = null;
        long end = System.currentTimeMillis() + (streamingSecondsDuration*1000);

        hosebirdClient.connect();
        JsonParser parser = new JsonParser();

        while (!hosebirdClient.isDone()) {
            if(System.currentTimeMillis() >= end){
                break;
            }
            try {
                String msg = msgQueue.take();
                tweetModel = parser.processTweet(msg.toString());
                databaseConnector.insertRecord(tweetModel);

            } catch (Exception e) {
                e.printStackTrace();
                try {
                    hosebirdClient.stop();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

    }


}
