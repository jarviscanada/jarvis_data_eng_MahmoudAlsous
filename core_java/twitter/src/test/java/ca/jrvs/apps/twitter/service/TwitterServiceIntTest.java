package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.JsonUtil;
import ca.jrvs.apps.twitter.dao.helper.TweetUtil;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TwitterServiceIntTest {

    private TwitterService twitterService;

    // Add tweet IDs which is later used to test delete method
    private static Map<Integer, String> idMap;

    @Before
    public void setup() {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        System.out.println(consumerKey + " | " + consumerSecret + " | " + accessToken + " | " + tokenSecret);
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        TwitterDao twitterDAO = new TwitterDao(httpHelper);
        twitterService = new TwitterService(twitterDAO);
        idMap = new HashMap<>();
    }

    @Test
    public void PostTweetUsingTweetUtil() {
        String text = "Sample text";
        Double lon = 1.0;
        Double lat = -1.0;
        Tweet tweet = twitterService.postTweet(TweetUtil.buildTweet(text, lon, lat));
        assertNotNull(tweet);
        assertNotNull(tweet.getIdString());
        assertEquals(text, tweet.getText());
        assertEquals(lon, tweet.getCoordinates().getCoordinates().get(0));
        assertEquals(lat, tweet.getCoordinates().getCoordinates().get(1));
        idMap.put(1, tweet.getIdString());
    }

    @Test
    public void PostTweetWithoutTweetUtil() {
        String text = "Sample manual text";
        Tweet tweet = new Tweet();
        tweet.setText(text);
        Tweet test = twitterService.postTweet(tweet);
        assertNotNull(test);
        assertNotNull(test.getIdString());
        assertNull(test.getCoordinates().getCoordinates());
        assertEquals(text, test.getText());
        idMap.put(2, test.getIdString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void PostTweetWithInvalidText() {
        Double lon = 1.0;
        Double lat = -1.0;
        twitterService.postTweet(TweetUtil.buildTweet("", lon, lat));
    }

    @Test(expected = IllegalArgumentException.class)
    public void PostTweetWithLargeText() {
        String largeText = "I will use a project description from my profile to exceed the 140 character "
                + "constraint. The JDBC app is a Java Database Connectivity (JDBC) application that allows "
                + "users to establish a connection between a Java app and a PostgreSQL database. The application "
                + "can perform multiple tasks directly to the database using a CRUD methodology (Create, Read, Update, "
                + "and Delete). This application was implemented with Maven, SQL, Java, Data Access Objects, "
                + "Data Transfer Objects, JDBC, Docker, and PostgreSQL. A JDBC Executer class was implemented with "
                + "a main method to manually test the application and Docker was used to contain the program.";

        Double lon = 1.0;
        Double lat = -1.0;
        twitterService.postTweet(TweetUtil.buildTweet(largeText, lon, lat));
    }

    @Test(expected = IllegalArgumentException.class)
    public void PostTweetWithInvalidLongitude() {
        Double lon = 1000.0;
        Double lat = -1.0;
        twitterService.postTweet(TweetUtil.buildTweet("", lon, lat));
    }

    @Test(expected = IllegalArgumentException.class)
    public void PostTweetWithInvalidLatitude() {
        Double lon = 1.0;
        Double lat = -1000.0;
        twitterService.postTweet(TweetUtil.buildTweet("", lon, lat));
    }

    @Test
    public void ShowTweet() throws Exception {
        String[] fields = {"id", "text"};
        Tweet tweet = twitterService.showTweet(idMap.get(1), fields);

        assertNotNull(tweet);
        assertNotNull(tweet.getId());
        assertNotNull(tweet.getText());

        assertNull(tweet.getCreated_at());
        assertNull(tweet.getIdString());
        assertEquals(idMap.get(1), tweet.getIdString());
        assertNull(tweet.getEntities());
        assertNull(tweet.getCoordinates());
        assertNull(tweet.getRetweet_count());
        assertNull(tweet.getFavorite_count());
        assertNull(tweet.getFavorited());
        assertNull(tweet.getRetweeted());

        System.out.println(JsonUtil.toPrettyJson(tweet));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ShowTweetWithInvalidId() {
        String[] fields = {};
        twitterService.showTweet("IncorrectId", fields);
    }

    @Test
    public void DeleteTweet() {
        String[] ids = idMap.values().toArray(new String[0]);
        List<Tweet> deleted = twitterService.deleteTweets(ids);

        assertNotNull(deleted.get(0));
        assertEquals(idMap.get(1), deleted.get(0).getIdString());
        assertNotNull(deleted.get(0).getText());

        System.out.println(deleted.get(0).getText());

        assertNotNull(deleted.get(1));
        assertEquals(idMap.get(2), deleted.get(1).getIdString());
        assertNotNull(deleted.get(1).getText());

        System.out.println(deleted.get(1).getText());
    }

    @Test(expected = IllegalArgumentException.class)
    public void DeleteTweetsWithInvalidFields() {
        String[] idsArray = {"IncorrectId", "1234"};
        twitterService.deleteTweets(idsArray);
    }
}
