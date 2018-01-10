package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

import static com.example.android.newsapp.NewsActivity.LOG_TAG;

/**
 * Created by ruslan on 10.01.18.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    /** Query URL */
    private String mUrl;

    public NewsLoader(Context context, String url) {

        super(context);
        mUrl = url;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.v(LOG_TAG, "Loader is beeing loaded");
    }

    @Override
    public List<News> loadInBackground() {

        // Don't perform the request if there are no URLs, or the first URL is null.
        if (mUrl == null) {
            return null;
        }

        List<News> result = QueryUtils.fetchNewsData(mUrl);

        return result;
    }

}