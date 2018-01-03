package com.example.android.newsapp;

import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving news data
 */

public final class QueryUtils {

    /** Sample JSON response for a News query */
    private static final String SAMPLE_JSON_RESPONSE = "{\"status\": \"ok\",\"totalResults\": 4, -\"articles\": [ -{ " +
            "-\"source\": {\"id\": \"techcrunch\", \"name\": \"TechCrunch\"}," +
        "\"author\": \"Megan Rose Dickey\", \"title\": \"Airbnb beats big property landlord’s lawsuit in California\", \"description\": \"A California judge has dismissed Apartment Investment & Management Company's lawsuit against Airbnb. Last February, Aimco, which owns or manages about..\"," +
                "\"url\": \"https://techcrunch.com/2018/01/02/judge-dismisses-aimco-airbnb-lawsuit-california/\", \"urlToImage\": \"https://tctechcrunch2011.files.wordpress.com/2017/05/gettyimages-652988834.jpg\", \"publishedAt\": \"2018-01-02T20:24:31Z\"}, -{" +
        "-\"source\": {\"id\": \"techcrunch\", \"name\": \"TechCrunch\"}," +
        "\"author\": \"Brian Heater\", \"title\": \"Apple buys app development service Buddybuild\", " +
                " \"description\": \"Apple continues to ramp up its efforts to court developers by making it easier to create and iterate their apps for its platforms. The iPhone giant has now..\"," +
                "\"url\": \"https://techcrunch.com/2018/01/02/apple-buys-app-development-service-buddybuild/\"," +
                "\"urlToImage\": \"https://tctechcrunch2011.files.wordpress.com/2018/01/buddybuild.jpg\", \"publishedAt\": \"2018-01-02T20:02:13Z\"}]}";

    public QueryUtils() {

    }

    public static ArrayList<News> extractNews() {

        // Create an empty ArrayList that we can start adding news to
        ArrayList<News> NewsList = new ArrayList<>();

        return NewsList;

    }



}
