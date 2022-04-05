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
    }

    @Test
    public void postTweet() {
        String text = "Testing tweet for CLI at " + java.time.LocalTime.now();
        Double lat = 1d;
        Double lon = -1d;
        Tweet postTweet = TweetUtil.buildTweet(text,lon,lat);
        Tweet response = twitterService.postTweet(postTweet);
        assertEquals(postTweet.getText(),response.getText());
        assertEquals(postTweet.getCoordinates().getCoordinates(), response.getCoordinates().getCoordinates());
    }

    @Test
    public void showTweet() {
        String text = "Testing tweet for CLI at " + java.time.LocalTime.now();
        Double lat = 1d;
        Double lon = -1d;
        Tweet postTweet = TweetUtil.buildTweet(text,lon,lat);
        Tweet response = twitterService.postTweet(postTweet);
        Tweet showTweet = twitterService.showTweet(response.getId_str(), new String[]{"text"});
        assertEquals(response.getText(), showTweet.getText());

    }

    @Test
    public void deleteTweets() {
        String text = "Testing tweet for CLI at " + java.time.LocalTime.now();
        Double lat = 1d;
        Double lon = -1d;
        Tweet postTweet = TweetUtil.buildTweet(text,lon,lat);
        Tweet response = twitterService.postTweet(postTweet);
        List<Tweet> deletedTweets = twitterService.deleteTweets(new String[]{response.getId_str()});
        assertEquals(deletedTweets.get(0).getText(), response.getText());
    }
}
