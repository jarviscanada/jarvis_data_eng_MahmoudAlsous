package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.JsonUtil;
import ca.jrvs.apps.twitter.dao.helper.TweetUtil;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {

    @Mock
    HttpHelper mockHelper;

    @InjectMocks
    TwitterDao dao;

    @Test
    public void showTweet() throws Exception {
        //test failed request
        lenient().when(mockHelper.httpGet(isNotNull())).thenThrow(new RuntimeException("mock"));
        try {
            dao.findById("1507842133453062147");
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }

        //Test request with spyDao
        String tweetJsonStr = "{\n"
                + "   \"created_at\":\"Sat Mar 26 18:09:00 +0000 2022\",\n"
                + "   \"id\":1507842133453062147,\n"
                + "   \"id_str\":\"1507842133453062147\",\n"
                + "   \"text\":\"test with loc223\",\n"
                + "   \"entities\":{\n"
                + "      \"hashtags\":[],"
                + "      \"user_mentions\":[]"
                + "   },\n"
                + "   \"coordinates\":null,"
                + "   \"retweet_count\":0,\n"
                + "   \"favorite_count\":0,\n"
                + "   \"favorited\":false,\n"
                + "   \"retweeted\":false\n"
                + "}";

        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = spy(dao);
        Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
        //Mock the parseResponseBody method
        doReturn(expectedTweet).when(spyDao).parseResponseBody(any(),anyInt());
        Tweet tweet = spyDao.findById("1507842133453062147");
        assertNotNull(tweet);
        assertEquals("1507842133453062147", tweet.getIdString());
        assertNotNull(tweet.getText());
    }

    @Test
    public void postTweet() throws Exception {
        String hashTag = "#abc";
        String text = "@someone some text" + hashTag + " " + System.currentTimeMillis();
        Double lon = -1d;
        Double lat = 1d;
        when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
        try {
            dao.create(TweetUtil.buildTweet(text, lon, lat));
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }

        String tweetJsonStr = "{\n"
                + "   \"created_at\":\"Sat Mar 26 18:09:00 +0000 2022\",\n"
                + "   \"id\":1507842133453062147,\n"
                + "   \"id_str\":\"1507842133453062147\",\n"
                + "   \"text\":\"test with loc223\",\n"
                + "   \"entities\":{\n"
                + "      \"hashtags\":[],"
                + "      \"user_mentions\":[]"
                + "   },\n"
                + "   \"coordinates\":null,"
                + "   \"retweet_count\":0,\n"
                + "   \"favorite_count\":0,\n"
                + "   \"favorited\":false,\n"
                + "   \"retweeted\":false\n"
                + "}";


        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = spy(dao);
        Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
        doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
        Tweet tweet = spyDao.create(TweetUtil.buildTweet(text, lon, lat));
        System.out.println(tweet.getText());
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
    }

    @Test
    public void deleteTweet() throws Exception {
        // Test failed request.
        when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
        try {
            dao.deleteById("1507842133453062147");
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }

        String tweetJsonStr = "{\n"
                + "   \"created_at\":\"Sat Mar 26 18:09:00 +0000 2022\",\n"
                + "   \"id\":1507842133453062147,\n"
                + "   \"id_str\":\"1507842133453062147\",\n"
                + "   \"text\":\"test with loc223\",\n"
                + "   \"entities\":{\n"
                + "      \"hashtags\":[],"
                + "      \"user_mentions\":[]"
                + "   },\n"
                + "   \"coordinates\":null,"
                + "   \"retweet_count\":0,\n"
                + "   \"favorite_count\":0,\n"
                + "   \"favorited\":false,\n"
                + "   \"retweeted\":false\n"
                + "}";

        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = spy(dao);
        Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
        doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
        Tweet tweet = spyDao.deleteById("1507842133453062147");
        assertNotNull(tweet);
        assertEquals("1507842133453062147", tweet.getIdString());
        assertNotNull(tweet.getText());
    }
}
