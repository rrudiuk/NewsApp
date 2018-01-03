package com.example.android.newsapp;

/**
 * Created by ruslan on 03.01.18.
 */

public class News {

    private String sourceName;
    private String title;
    private String urlToImage;
    private String date;
    private String description;
    private String url;
    private String author;

    public News() {

    }

    public News(String sourceName, String title, String urlToImage, String date, String description, String url, String author) {

        this.sourceName = sourceName;
        this.title = title;
        this.urlToImage = urlToImage;
        this.date = date;
        this.description = description;
        this.url = url;
        this.author = author;

    }

    // getters

    public String getSourceName() {
        return sourceName;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }
}
