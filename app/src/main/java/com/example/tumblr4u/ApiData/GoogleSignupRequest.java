package com.example.tumblr4u.ApiData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoogleSignupRequest {
    @SerializedName("Token")
    @Expose
    private String Token;

    @SerializedName("age")
    @Expose
    private String age;

    @SerializedName("blogName")
    @Expose
    private String blogName;

    public GoogleSignupRequest(String Token, String age, String blogName) {
        this.Token = Token;
        this.age = age;
        this.blogName = blogName;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }
}
