package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TwitterControllerIntTest {

    TwitterController twitterController;

    @Before
    public void setup() throws Exception {
        String CONSUMER_KEY = System.getenv("consumerKey");
        String CONSUMER_SECRET = System.getenv("consumerSecret");
        String ACCESS_TOKEN = System.getenv("accessToken");
        String TOKEN_SECRET = System.getenv("tokenSecret");

        TwitterHttpHelper helper = new TwitterHttpHelper(CONSUMER_KEY,CONSUMER_SECRET,ACCESS_TOKEN,TOKEN_SECRET);
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

        // Call showTweet without fields arg
        String[] showArgs = new String[]{"show", postTweet.getIdString()};
        Tweet showTweet = twitterController.showTweet(showArgs);

        assertEquals(showTweet.getText(),postTweet.getText());
        assertEquals(showTweet.getCoordinates().getCoordinates().get(0), postTweet.getCoordinates().getCoordinates().get(0));
        assertEquals(showTweet.getCoordinates().getCoordinates().get(1), postTweet.getCoordinates().getCoordinates().get(1));

        String[] fieldsArgs = new String[]{"show", postTweet.getIdString(), text};
        showTweet= twitterController.showTweet(fieldsArgs);
        assertNotNull(showTweet.getText());
    }

    @Test
    public void deleteTweet() {
        String text="Sample tweet text" + java.time.LocalTime.now();
        Double lat = 1d;
        Double lon = -1d;
        String coords = lat + ":" + lon;

        String[] args = new String[]{"post", text, coords};
        Tweet postTweet= twitterController.postTweet(args);

        String[] deleteArgs = new String[]{"delete", postTweet.getIdString()};
        List<Tweet> deletedTweets= twitterController.deleteTweet(deleteArgs);
        assertEquals(text, deletedTweets.get(0).getText());
    }
}
