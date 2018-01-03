package com.example.android.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    // Create a fake list of news.
    ArrayList<News> earthquakes = QueryUtils.extractNews();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
    }
}