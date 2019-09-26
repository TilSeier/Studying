package com.tilseier.studying.screens.retrofit.model;

import com.google.gson.annotations.SerializedName;

//https://jsonplaceholder.typicode.com/posts
public class Post {

    private Integer id;

    private int userId;

    private String title;

    @SerializedName("body")
    private String text;

    public Post(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
    
}
