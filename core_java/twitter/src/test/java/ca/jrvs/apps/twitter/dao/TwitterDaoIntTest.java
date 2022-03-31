package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.JsonUtil;
import ca.jrvs.apps.twitter.dao.helper.TweetUtil;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoIntTest {

    private TwitterDao twitterDAO;

    private static String id;
    private static String text;

    @Before
    public void setup() {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        System.out.println(consumerKey + " | " + consumerSecret + " | "
                + accessToken + " | " + tokenSecret);
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
                accessToken, tokenSecret);
        twitterDAO = new TwitterDao(httpHelper);
    }

    @Test
    public void create() throws Exception {
        String hashTag = "#abc";
        String text = "@someone some text " + hashTag + " " + System.currentTimeMillis();
        Double lon = -1d;
        Double lat = 1d;
        Tweet postTweet = TweetUtil.buildTweet(text, lon, lat);
        System.out.println(JsonUtil.toPrettyJson(postTweet));

        Tweet tweet = twitterDAO.create(postTweet);
        System.out.println(JsonUtil.toPrettyJson(tweet));
        TwitterDaoIntTest.id = tweet.getId_str();
        TwitterDaoIntTest.text = tweet.getText();

        Assert.assertEquals(text, tweet.getText());
        Assert.assertNotNull(tweet.getCoordinates());
        Assert.assertEquals(2, tweet.getCoordinates().getCoordinates().size());
        Assert.assertEquals(lon, tweet.getCoordinates().getCoordinates().get(0));
        Assert.assertEquals(lat, tweet.getCoordinates().getCoordinates().get(1));
    }

    @Test
    public void findById() throws Exception {
        System.out.println(TwitterDaoIntTest.id);
        Tweet tweet = twitterDAO.findById(TwitterDaoIntTest.id);
        System.out.println(JsonUtil.toPrettyJson(tweet));
        Double expectedLong = -1d;
        Double expectedLat = 1d;
        Assert.assertEquals(TwitterDaoIntTest.text, tweet.getText());

        Assert.assertNotNull(tweet.getCoordinates());
        Assert.assertEquals(2, tweet.getCoordinates().getCoordinates().size());
        Assert.assertEquals(expectedLong, tweet.getCoordinates().getCoordinates().get(0));
        Assert.assertEquals(expectedLat, tweet.getCoordinates().getCoordinates().get(1));
    }

    @Test
    public void deleteById() throws Exception {
        Tweet tweet = twitterDAO.deleteById(TwitterDaoIntTest.id);
        System.out.println(JsonUtil.toPrettyJson(tweet));
        Double expectedLong = -1d;
        Double expectedLat = 1d;
        Assert.assertEquals(TwitterDaoIntTest.text, tweet.getText());

        Assert.assertNotNull(tweet.getCoordinates());
        Assert.assertEquals(2, tweet.getCoordinates().getCoordinates().size());
        Assert.assertEquals(expectedLong, tweet.getCoordinates().getCoordinates().get(0));
        Assert.assertEquals(expectedLat, tweet.getCoordinates().getCoordinates().get(1));
    }
}