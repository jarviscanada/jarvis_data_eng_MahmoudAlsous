package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TwitterControllerIntTest {

    TwitterController twitterController;

    @Before
    public void setup() throws Exception {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");

        TwitterHttpHelper helper = new TwitterHttpHelper(consumerKey,consumerSecret,accessToken,tokenSecret);
        TwitterDao dao = new TwitterDao(helper);
        Service service = new TwitterService(dao);
        twitterController = new TwitterController(service);
    }

    @Test
    public void postTweet() {
        String text="Sample tweet text" + java.time.LocalTime.now();
        Double lat = 1d;
        Double lon = -1d;
        String coords = lat + ":" + lon;

        String[] args = new String[]{"post",text,coords};
        Tweet postTweet = twitterController.postTweet(args);

        assertEquals(text,postTweet.getText());
        assertEquals(lon, postTweet.getCoordinates().getCoordinates().get(0));
        assertEquals(lat, postTweet.getCoordinates().getCoordinates().get(1));
    }

    @Test
    public void showTweet() {
        String text="Sample tweet text" + java.time.LocalTime.now();
        Double lat = 1d;
        Double lon = -1d;
        String coords = lat + ":" + lon;

        String[] args = new String[]{"post",text,coords};
        Tweet postTweet= twitterController.postTweet(args);

        String[] fieldsArgs = new String[]{"show", postTweet.getId_str(), "text"};
        Tweet showTweet = twitterController.showTweet(fieldsArgs);
        assertEquals(postTweet.getText(), showTweet.getText());
    }

    @Test
    public void deleteTweet() {
        String text="Sample tweet text" + java.time.LocalTime.now();
        Double lat = 1d;
        Double lon = -1d;
        String coords = lat + ":" + lon;

        String[] args = new String[]{"post", text, coords};
        Tweet postTweet= twitterController.postTweet(args);

        String[] deleteArgs = new String[]{"delete", postTweet.getId_str()};
        List<Tweet> deletedTweets= twitterController.deleteTweet(deleteArgs);
        assertEquals(text, deletedTweets.get(0).getText());
    }
}
