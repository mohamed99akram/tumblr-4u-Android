package com.example.tumblr4u.ApiData.WritePost;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadImageResponse {

    @SerializedName("images")
    @Expose
    private List<String> images = null;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

}