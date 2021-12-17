package com.example.tumblr4u.ApiInterfaces;

/**
 * @author Omar Ahmed
 * @version 1.0
 * */

import com.example.tumblr4u.ApiData.GoogleLoginRequest;
import com.example.tumblr4u.ApiData.GoogleSignupRequest;
import com.example.tumblr4u.ApiData.HomePostsRequest;
import com.example.tumblr4u.ApiData.HomePostsResponse;
import com.example.tumblr4u.ApiData.LoginRequest;
import com.example.tumblr4u.ApiData.LoginResponse;
import com.example.tumblr4u.ApiData.SignupRequest;

import co.infinum.retromock.meta.Mock;
import co.infinum.retromock.meta.MockResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * The Api Interface which responsible for making a requests to the back-end server
 * */
public interface ApiInterface {
    @Mock
    @MockResponse(body = "{\"meta\":{\"status\":200, \"msg\": \"CREATED\"}, \"res\":{\"message\":\"sign up successfully\", \"data\": \"token\"}}")
//    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "google_login_response.json")
    @POST("login")
    public Call<LoginResponse> Login(@Body LoginRequest request);

    @Mock
    @MockResponse(body = "{\"meta\":{\"status\":201, \"msg\": \"CREATED\"}, \"res\":{\"message\":\"sign up successfully\", \"data\": \"token\"}}")
    @POST("signup")
    public Call<LoginResponse> Signup(@Body SignupRequest request);

    // TODO change this
    @Mock
//    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "google_login_response.json")
//    @MockResponse(body = "google_login_response.json")
    @MockResponse(body = "{\"meta\":{\"status\":201, \"msg\": \"CREATED\"}, \"res\":{\"message\":\"sign up successfully\", \"data\": \"YourToken\"}}")
    @POST("/google/info")
    public Call<LoginResponse> googleSignup(@Body GoogleSignupRequest request);

    @Mock
//    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "google_login_response.json")
    @MockResponse(body = "{\"meta\":{\"status\":201, \"msg\": \"CREATED\"}, \"res\":{\"message\":\"sign up successfully\", \"data\": \"YourToken\"}}")

    @POST("androidSignUpWithGoogle")
    public Call<LoginResponse> googleLogin(@Body GoogleLoginRequest request);


    // ----------------- Home Posts --------------

    @POST("dashboard")
    public Call<HomePostsResponse> getHomePosts(@Body HomePostsRequest request);
}