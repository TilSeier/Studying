package com.tilseier.studying.screens.Model_View_ViewModel_CodingWithMitch.models;

public class NicePlace {

    String title;
    String imageUrl;

    public NicePlace() {
    }

    public NicePlace(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
