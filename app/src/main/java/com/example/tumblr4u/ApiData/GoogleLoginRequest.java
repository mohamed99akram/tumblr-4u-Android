package com.example.tumblr4u.ApiData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoogleLoginRequest {
    @SerializedName("googleToken")
    @Expose
    private String googleToken;

    public GoogleLoginRequest(String googleToken) {
        this.googleToken = googleToken;
    }

    public String getGoogleToken() {
        return googleToken;
    }

    public void setGoogleToken(String googleToken) {
        this.googleToken = googleToken;
    }
}
