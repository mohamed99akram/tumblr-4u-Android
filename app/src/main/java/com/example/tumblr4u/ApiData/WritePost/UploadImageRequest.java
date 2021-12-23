package com.example.tumblr4u.ApiData.WritePost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadImageRequest {
    @SerializedName("imageBase64")
    @Expose
    private String imageBase64;

    public UploadImageRequest(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
