package com.example.tumblr4u.ApiData.Login_Signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Response Data for the login and signup that contains token
 * json version
 * {
 *      "token": "LKFAJJOI76DFJJJSLKDFJOJF"
 * }
 * @version 1.1
 * */
public class LoginSignupData {
    @SerializedName("token")
    @Expose
    private String token;

    public String getToken(){ return token;}
}
