package com.example.tumblr4u.ApiData.Login_Signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoogleSignupRequest {

    @SerializedName("age")
    @Expose
    private String age;

    @SerializedName("blogName")
    @Expose
    private String blogName;

    public GoogleSignupRequest(String age, String blogName) {
        this.age = age;
        this.blogName = blogName;
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
