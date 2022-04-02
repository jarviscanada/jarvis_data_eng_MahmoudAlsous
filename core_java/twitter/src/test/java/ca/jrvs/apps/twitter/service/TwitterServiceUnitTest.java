package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.helper.TweetUtil;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {
    @Mock
    CrdDao dao;

    @InjectMocks
    TwitterService service;

    @Test
    public void postTweet() {
        Tweet test = new Tweet();
        test.setText("Sample text");
        when(dao.create(any())).thenReturn(test);
        Tweet tweet = service.postTweet(TweetUtil.buildTweet("Sample text", 30.0, 0.0));
        assertEquals("Sample text", tweet.getText());
    }

    @Test
    public void showTweet() {
        String[] fields = {"id", "text"};
        Tweet test = new Tweet();
        test.setIdString("1010");
        test.setText("Sample text");
        when(dao.findById(any())).thenReturn(test);
        Tweet tweet = service.showTweet(test.getIdString(), fields);
        assertEquals("Sample text", tweet.getText());
    }

    @Test
    public void deleteTweets() {
        Tweet test = new Tweet();
        test.setText("Sample text");
        String[] ids = {"1234"};
        when(dao.deleteById(any())).thenReturn(test);
        List<Tweet> tweets = service.deleteTweets(ids);
        assertEquals("Sample text", tweets.get(0).getText());
    }
}
