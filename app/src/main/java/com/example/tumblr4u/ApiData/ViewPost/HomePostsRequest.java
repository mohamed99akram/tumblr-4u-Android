package com.example.tumblr4u.ApiData.ViewPost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomePostsRequest {
    @SerializedName("Token")
    @Expose
    private String Token;

    public HomePostsRequest(String token) {
        Token = token;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
