package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TwitterService implements Service{

    private CrdDao dao;

    @Autowired
    public TwitterService(CrdDao dao){
        this.dao = dao;
    }

    /**
     * Checks if the passed ID value is in correct format
     * @param id
     * @return true/false
     */
    private boolean isValidID(String id) {
        if (id == null){
            return false;
        }

        try {
            Long.parseLong(id);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private void validatePostTweet(Tweet tweet) {
        if (tweet.getText().length() < 1 || tweet.getText().length() > 140) {
            throw new IllegalArgumentException("Tweet length is invalid.");
        }

        Coordinates coords = tweet.getCoordinates();
        if(coords != null && coords.getCoordinates() != null) {
            if  (coords.getCoordinates().get(0) > 180.0 || coords.getCoordinates().get(0) < -180.0 || coords.getCoordinates().get(1) > 90.0 || coords.getCoordinates().get(1) < -90.0) {
                throw new IllegalArgumentException("Tweet coordinates are invalid.");
            }
        }
    }

    private Tweet filter(Tweet tweet, String[] fields) {
        Class tweetClass = Tweet.class;
        Field fieldList[] = tweetClass.getDeclaredFields();

        HashSet<String> fieldsToAdd = new HashSet<>();
        for (String field : fields) {
            fieldsToAdd.add(field);
        }

        try {
            for (Field field : fieldList) {
                if (!fieldsToAdd.contains(field.getName())) {
                    char[] getAccess = field.getName().toCharArray();
                    getAccess[0] = Character.toUpperCase(getAccess[0]);
                    String methodName = "set" + String.valueOf(getAccess);

                    // Create a method with method name accessed and set it to null arguments
                    Method set = tweetClass.getMethod(methodName, field.getType());
                    set.invoke(tweet, (Object) null);
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Could not filter tweet object.", e);
        }
        return tweet;
    }


    /**
     * Validate and post a user input Tweet
     *
     * @param tweet tweet to be created
     * @return created tweet
     * @throws IllegalArgumentException if text exceed max number of allowed characters or lat/long out of range
     */
    @Override
    public Tweet postTweet(Tweet tweet) {
        //Business logic function
        validatePostTweet(tweet);

        //Create a tweet using the dao object
        return (Tweet) dao.create(tweet);
    }

    /**
     * Search a tweet by ID
     *
     * @param id     tweet id
     * @param fields set fields not in the list to null
     * @return Tweet object which is returned by the Twitter API
     * @throws IllegalArgumentException if id or fields param is invalid
     */
    @Override
    public Tweet showTweet(String id, String[] fields) {
        if (isValidID(id) == false || fields == null) {
            return null;
        }
        Tweet tweet = filter((Tweet) dao.findById(id), fields);
        return tweet;
    }

    /**
     * Delete Tweet(s) by id(s).
     *
     * @param ids tweet IDs which will be deleted
     * @return A list of Tweets
     * @throws IllegalArgumentException if one of the IDs is invalid.
     */
    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        List<Tweet> tweets = new ArrayList<>();
        for (String id : ids) {
            if(isValidID(id)){
                tweets.add((Tweet) dao.deleteById(id));
            }
        }
        return tweets;
    }
}
