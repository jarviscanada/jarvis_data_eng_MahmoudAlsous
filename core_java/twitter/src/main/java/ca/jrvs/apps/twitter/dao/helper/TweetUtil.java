package ca.jrvs.apps.twitter.dao.helper;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Entities;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;

public class TweetUtil {

    /**
     * Build tweet instance
     *
     * @param text
     * @param lat
     * @param lon
     * @return Tweet
     */
    public static Tweet buildTweet(String text, Double lon, Double lat) {
        Entities entities = new Entities();
        entities.setHashtags(new ArrayList<>());
        entities.setUserMentions(new ArrayList<>());

        Coordinates coordinates = new Coordinates();
        List<Double> coords = new ArrayList<>();
        coords.add(lon);
        coords.add(lat);
        coordinates.setCoordinates(coords);
        coordinates.setType("Point");

        Tweet tweet = new Tweet();
        tweet.setText(text);
        tweet.setCoordinates(coordinates);
        tweet.setEntities(entities);
        tweet.setRetweet_count(0);
        tweet.setRetweet_count(0);
        tweet.setFavorited(false);
        tweet.setRetweeted(false);

        return tweet;
    }
}