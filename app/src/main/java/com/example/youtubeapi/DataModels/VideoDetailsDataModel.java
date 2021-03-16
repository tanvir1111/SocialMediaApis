package com.example.youtubeapi.DataModels;

public class VideoDetailsDataModel {
    private String thumbURL;
    private String title;
    private String views;
    private String likes;

    public VideoDetailsDataModel(String thumbURL, String title, String views, String likes) {
        this.thumbURL = thumbURL;
        this.title = title;
        this.views = views;
        this.likes = likes;
    }

    public String getThumbURL() {
        return thumbURL;
    }

    public String getTitle() {
        return title;
    }

    public String getViews() {
        return views;
    }

    public String getLikes() {
        return likes;
    }
}
