package com.example.tumblr4u.ApiInterfaces;

/**
 * @author Omar Ahmed
 * @version 1.0
 * */
import com.example.tumblr4u.ApiData.LoginRequest;
import com.example.tumblr4u.ApiData.LoginResponse;
import com.example.tumblr4u.ApiData.SignupRequest;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * The Api Interface which responsible for making a requests to the back-end server
 * */
public interface ApiInterface {
    @POST("login")
    public Call<LoginResponse> Login(@Body LoginRequest request);

    @POST("signup")
    public Call<LoginResponse> Signup(@Body HashMap request);
}
