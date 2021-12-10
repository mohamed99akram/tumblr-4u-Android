package com.example.tumblr4u.ApiData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoogleSignupRequest {
    @SerializedName("googleToken")
    @Expose
    private String googleToken;

    @SerializedName("age")
    @Expose
    private int age;

    @SerializedName("blogName")
    @Expose
    private String blogName;

    public GoogleSignupRequest(String googleToken, int age, String blogName) {
        this.googleToken = googleToken;
        this.age = age;
        this.blogName = blogName;
    }

    public String getGoogleToken() {
        return googleToken;
    }

    public void setGoogleToken(String googleToken) {
        this.googleToken = googleToken;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }
}
