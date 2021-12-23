package com.example.tumblr4u.ApiData.ViewPost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_ViewPost {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<SinglePostResponse> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SinglePostResponse> getData() {
        return data;
    }

    public void setData(List<SinglePostResponse> data) {
        this.data = data;
    }
}
