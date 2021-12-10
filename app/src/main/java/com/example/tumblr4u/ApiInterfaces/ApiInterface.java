package com.example.tumblr4u.ApiInterfaces;

/**
 * @author Omar Ahmed
 * @version 1.0
 * */
import com.example.tumblr4u.ApiData.LoginRequest;
import com.example.tumblr4u.ApiData.LoginResponse;
import com.example.tumblr4u.ApiData.SignupRequest;

import java.util.HashMap;

import co.infinum.retromock.meta.Mock;
import co.infinum.retromock.meta.MockResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * The Api Interface which responsible for making a requests to the back-end server
 * */
public interface ApiInterface {
    @Mock
    @MockResponse(body = "{\"meta\":{\"status\":300, \"msg\": \"CREATED\"}, \"res\":{\"message\":\"sign up successfully\", \"data\": \"token\"}}")
    @POST("login")
    public Call<LoginResponse> Login(@Body LoginRequest request);

    @Mock
    @MockResponse(body = "{\"meta\":{\"status\":201, \"msg\": \"CREATED\"}, \"res\":{\"message\":\"sign up successfully\", \"data\": \"token\"}}")
    @POST("signup")
    public Call<LoginResponse> Signup(@Body SignupRequest request);
}
