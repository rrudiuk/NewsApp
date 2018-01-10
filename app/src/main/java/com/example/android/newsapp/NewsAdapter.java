package com.example.android.newsapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ruslan on 03.01.18.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Activity context, ArrayList<News> newsList) {

        super(context, 0, newsList);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_view, parent, false);
        }

        // Get the {@link News} object located at this position in the list
        News currentNews = getItem(position);

        // find the TextView in the list_item.xml layout with the ID version_name
        TextView authorTextView = (TextView) listItemView.findViewById(R.id.source);
        /** get the author's name from current News object
         * and set it to be displayed to the TextView */
        authorTextView.setText(currentNews.getSourceName());

        return listItemView;

    }

}