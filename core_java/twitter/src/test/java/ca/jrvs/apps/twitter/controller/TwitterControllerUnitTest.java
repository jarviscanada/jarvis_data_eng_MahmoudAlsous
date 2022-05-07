package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {
    @Mock
    TwitterService twitterService;

    @InjectMocks
    TwitterController twitterController;

    @Test
    public void postTweet() {
        when(twitterService.postTweet(any())).thenReturn(new Tweet());
        String[] args = {"post", "text", "1d:2d"};
        Tweet post = twitterController.postTweet(args);
        System.out.println(post.getText());
        assertNull(post.getText());
    }

    @Test
    public void showTweet() {
        when(twitterService.showTweet(any(), any())).thenReturn(new Tweet());
        String[] args = {"show", "123", "fields"};
        Tweet showTweet = twitterController.showTweet(args);
        assertNull(showTweet.getText());
    }

    @Test
    public void deleteTweet() {
        when(twitterService.deleteTweets(any())).thenReturn(new ArrayList<>());
        String[] ids = {"123", "456", "789"};
        String[] args = {"delete", String.valueOf(ids)};
        List<Tweet> deletedTweets = twitterController.deleteTweet(args);
        assertEquals(0, deletedTweets.size());
    }
}
