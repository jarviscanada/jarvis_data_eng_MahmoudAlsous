package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

public class TwitterHttpHelperTest {
    private HttpHelper httpHelper;

    @Before
    public void setup() {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        System.out.println(consumerKey + "|" + consumerSecret + "|" + accessToken + "|" + tokenSecret);

        this.httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    }

    @Test
    public void httpPost() throws Exception{
        HttpResponse response = httpHelper.httpPost((new URI("https://api.twitter.com//1.1/statuses/update.json?status=first_tweet2")));
        System.out.println((EntityUtils.toString(response.getEntity())));
    }

    @Test
    public void httpGet() throws Exception {
        HttpResponse response = httpHelper.httpGet(new URI("https://api.twitter.com/1.1/statuses/show.json?id=1507842133453062147"));
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
