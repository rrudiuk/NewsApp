package com.example.android.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving news data
 */

public final class QueryUtils {

    /** Sample JSON response for a News query */
    private static final String SAMPLE_JSON_RESPONSE = "{\"status\": \"ok\",\"totalResults\": 4,\"articles\": [{ " +
            "\"source\": {\"id\": \"techcrunch\", \"name\": \"TechCrunch\"}," +
        "\"author\": \"Megan Rose Dickey\", \"title\": \"Airbnb beats big property landlord’s lawsuit in California\", \"description\": \"A California judge has dismissed Apartment Investment & Management Company's lawsuit against Airbnb. Last February, Aimco, which owns or manages about..\"," +
                "\"url\": \"https://techcrunch.com/2018/01/02/judge-dismisses-aimco-airbnb-lawsuit-california/\", \"urlToImage\": \"https://tctechcrunch2011.files.wordpress.com/2017/05/gettyimages-652988834.jpg\", \"publishedAt\": \"2018-01-02T20:24:31Z\"}, {" +
        "\"source\": {\"id\": \"techcrunch\", \"name\": \"TechCrunch\"}," +
        "\"author\": \"Brian Heater\", \"title\": \"Apple buys app development service Buddybuild\", " +
                " \"description\": \"Apple continues to ramp up its efforts to court developers by making it easier to create and iterate their apps for its platforms. The iPhone giant has now..\"," +
                "\"url\": \"https://techcrunch.com/2018/01/02/apple-buys-app-development-service-buddybuild/\"," +
                "\"urlToImage\": \"https://tctechcrunch2011.files.wordpress.com/2018/01/buddybuild.jpg\", \"publishedAt\": \"2018-01-02T20:02:13Z\"}]}";

    public QueryUtils() {

    }

    public static ArrayList<News> extractNews() {

        // Create an empty ArrayList that we can start adding news to
        ArrayList<News> newsList = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            JSONObject baseJSONResponse = new JSONObject(SAMPLE_JSON_RESPONSE);

            JSONArray articlesArray = baseJSONResponse.getJSONArray("articles");

            // For each News in the newsList, create an {@link News} object
            for (int i = 0; i < articlesArray.length(); i++) {
                // Get a single news at position i within the list of news
                JSONObject currentNews = articlesArray.getJSONObject(i);

                // For a given news, extract the JSONObject associated with the
                // key called "source", which represents a list of properties
                // that include sourse name
                JSONObject currentSource = currentNews.getJSONObject("source");

                // extract the value of the source
                String source = currentSource.getString("name");

                // Create a new {@link News} object with the data
                News news = new News(source);

                // Add the new {@link News} to the list of news.
                newsList.add(news);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the News JSON results", e);
        }

        return newsList;

    }



}
