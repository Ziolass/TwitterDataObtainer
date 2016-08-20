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
import datamodel.TweetModel;
import javafx.scene.chart.PieChart;
import utils.JsonParser;
import utils.input.InputParametersHolder;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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

    public static TwitterClientImpl getInstance() {
        return ourInstance;
    }

    private TwitterClientImpl() {

        msgQueue = new LinkedBlockingQueue<String>(100000);
        eventQueue = new LinkedBlockingQueue<Event>(1000);
        hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        hosebirdEndpoint = new StatusesFilterEndpoint();

    }
    public static Date getTwitterDate(String date) throws ParseException{

        final String TWITTER="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(TWITTER);
        sf.setLenient(true);
        return sf.parse(date);
    }

    public void prepareForStreaming(InputParametersHolder inputParametersHolder, ConfigModel configModel) {

       // hosebirdEndpoint.trackTerms(inputParametersHolder.getKeywords());
        //hosebirdEndpoint.languages(inputParametersHolder.getLanguages());

        hosebirdEndpoint.trackTerms(Arrays.asList("a"));
        hosebirdEndpoint.languages(Arrays.asList("pl"));

        hosebirdAuth = new OAuth1(configModel.getConsumerKey(),
                configModel.getConsumerSecret(),
                configModel.getAccessToken(),
                configModel.getAccessTokenSecret());

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
        StringBuilder stringBuilder = new StringBuilder();
        Thread writer = new Thread();
        DatabaseConnector databaseConnector = PostGreConnector.getInstance();
        Connection connection = databaseConnector.getConnection();
        PreparedStatement preparedStatement = null;
        TweetModel tweetModel = null;

        hosebirdClient.connect();
        int i = 0;
        JsonParser parser = new JsonParser();
        String statement="INSERT INTO TWEETS VALUES (?,?,?)";

        while (!hosebirdClient.isDone()) {
            try {
                preparedStatement = connection.prepareStatement(statement);
                String msg = msgQueue.take();
                tweetModel = parser.processTweet(msg.toString());

                preparedStatement.setString(1,tweetModel.getId());
                preparedStatement.setString(2,tweetModel.getText());
                new Timestamp(Long.getLong(tweetModel.getCreation_time()));
                preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                //System.out.println(i);

                System.out.println(parser.processTweet(msg.toString()));
//                stringBuilder.append(parser.processTweet(msg.toString()) + "\n");
                i++;
                if (i == 100000) {
                    hosebirdClient.isDone();
                }

            } catch (Exception e) {
                e.printStackTrace();
                try {
                    hosebirdClient.stop();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
//
//        String date = new SimpleDateFormat("dd_MM").format(new Date());
//        File file = new File("dump" + date + ".txt");
//        BufferedWriter writer = null;
//        try {
//            writer = new BufferedWriter(new FileWriter(file));
//            writer.write(stringBuilder.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (writer != null) try {
//                writer.close();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
    }

// Attempts to establish a connection.


}
