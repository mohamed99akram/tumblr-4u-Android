package com.example.tumblr4u.ApiData.Login_Signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginSignupData {
    @SerializedName("token")
    @Expose
    private String token;

    public String getToken(){ return token;}
}
