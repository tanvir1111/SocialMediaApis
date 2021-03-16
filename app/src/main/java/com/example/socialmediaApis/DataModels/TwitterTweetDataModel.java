package com.example.socialmediaApis.DataModels;

public class TwitterTweetDataModel {
    private String id;
    private String date;
    private String tweet;
    private String source;
    private String likes;
    private String retweets;
    private String replies;
    private String quotes;


    public TwitterTweetDataModel(String id, String date, String tweet, String source, String likes, String retweets, String replies, String quotes) {
        this.id = id;
        this.date = date;
        this.tweet = tweet;
        this.source = source;
        this.likes = likes;
        this.retweets = retweets;
        this.replies = replies;
        this.quotes = quotes;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTweet() {
        return tweet;
    }

    public String getSource() {
        return source;
    }

    public String getLikes() {
        return likes;
    }

    public String getRetweets() {
        return retweets;
    }

    public String getReplies() {
        return replies;
    }

    public String getQuotes() {
        return quotes;
    }
}
