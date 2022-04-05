package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.JsonUtil;
import ca.jrvs.apps.twitter.model.Tweet;
import com.google.gdata.util.common.base.PercentEscaper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

public class TwitterDao implements CrdDao<Tweet, String> {

    //URI Constants
    private static final String API_BASE_URI = "https://api.twitter.com";
    private static final String POST_PATH = "/1.1/statuses/update.json";
    private static final String SHOW_PATH = "/1.1/statuses/show.json";
    private static final String DELETE_PATH = "/1.1/statuses/destroy";

    //URI symbols
    private static final String QUERY_SYM = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";

    //Response Code
    private static final int HTTP_OK = 200;

    private HttpHelper httpHelper;

    @Autowired
    public TwitterDao(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public Tweet create(Tweet entity) {
        //Construct URI
        URI uri;
        try {
            uri = getPostUri(entity);
        } catch (URISyntaxException | UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Invalid tweet input", e);
        }

        // Execute HTTP Request
        HttpResponse response = httpHelper.httpPost(uri);

        //Validate response and parse response to tweet object body
        return parseResponseBody(response, HTTP_OK);
    }

    @Override
    public Tweet findById(String s) {
        HttpResponse response;
        try {
            String uriString = API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + s;
            response = httpHelper.httpGet(new URI(uriString));
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid input", e);
        }
        return parseResponseBody(response, HTTP_OK);
    }

    @Override
    public Tweet deleteById(String s) {
        HttpResponse response;
        try {
            String uriString = API_BASE_URI + DELETE_PATH + "/" + s + ".json";
            response = httpHelper.httpPost(new URI(uriString));
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid input", e);
        }

        return parseResponseBody(response, HTTP_OK);
    }

    private URI getPostUri(Tweet tweet) throws URISyntaxException, UnsupportedEncodingException {
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        String text = tweet.getText();
        String uriString =
                API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL + percentEscaper.escape(text);
        if (tweet.getCoordinates() != null) {
            uriString =
                    uriString + AMPERSAND + "long" + EQUAL + tweet.getCoordinates().getCoordinates().get(0)
                            + AMPERSAND + "lat" + EQUAL + tweet.getCoordinates().getCoordinates().get(1);
        }

        return new URI(uriString);
    }

     public Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {
        Tweet tweet = null;

        //Check response status
        int status  = response.getStatusLine().getStatusCode();
        if (status != expectedStatusCode) {
            try {
                System.out.println(EntityUtils.toString(response.getEntity()));
            } catch (IOException e) {
                System.out.println("Response has no entity.");
            }
            throw new RuntimeException("Unexpected HTTP status:" + status);
        }

        if (response.getEntity() == null){
            throw new RuntimeException("Empty response body");
        }

        //Convert Response entity to str
        String jsonStr;
        try{
            jsonStr = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert entity to String", e);
        }

        //Deser JSON string to Tweet object
        try {
            tweet = JsonUtil.toObjectFromJson(jsonStr, Tweet.class);
        } catch (IOException e) {
            throw new RuntimeException("Unable to convert JSON string to Object", e);
        }
        return tweet;
    }
}
