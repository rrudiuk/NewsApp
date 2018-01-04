package com.example.android.newsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.newsapp.NewsActivity.LOG_TAG;

/**
 * Helper methods related to requesting and receiving news data
 */

public final class QueryUtils {

    /**
     * Query the News dataset and return a list of {@link News} objects.
     */
    public static List<News> fetchNewsData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<News> news = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Earthquake}s
        return news;
    }

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

    public static URL createUrl(String stringUrl) {

        URL url = null;

        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }

        return url;
    }

    public static String makeHttpRequest(URL url) throws IOException {
        // varible that stores the response result
        String jsonResponse = "";

        // if response is null, return empty url
        if(url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            // check if the request was successful
            // then read the input and parse the response
            if(urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            // TODO: Handle the exception
            Log.e(LOG_TAG, "Connection with the server wasn't established", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static ArrayList<News> extractFeatureFromJson(String newsJSON) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding news to
        ArrayList<News> newsList = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            JSONObject baseJSONResponse = new JSONObject(newsJSON);

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
