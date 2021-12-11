package com.example.tumblr4u.Repository;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.tumblr4u.ApiData.GoogleLoginRequest;
import com.example.tumblr4u.ApiData.GoogleSignupRequest;
import com.example.tumblr4u.ApiData.LoginRequest;
import com.example.tumblr4u.ApiData.LoginResponse;
import com.example.tumblr4u.ApiData.SignupRequest;
import com.example.tumblr4u.ApiInterfaces.ApiInterface;
import com.example.tumblr4u.Models.Post;
import com.example.tumblr4u.View.LoginWithEmail;

import java.util.ArrayList;
import java.util.HashMap;

import co.infinum.retromock.Retromock;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Converter.Factory.*;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Android Team
 * @version 1.0
 * @since 11/8/2021
 */
public class Repository {

    // The base url used in the back-end API
    private static final String BASE_URL = "http://tumblr4u.eastus.cloudapp.azure.com:5000/";
    // The instance of the repository class and it static variable so that the class can be instantiated once
    private static Repository INSTANCE = null;
    private ApiInterface apiInterface;

    public MutableLiveData<ArrayList<Post>> requestHolidays() {
        MutableLiveData<ArrayList<Post>> posts = new MutableLiveData<>();
        ArrayList<Post> temp = new ArrayList<>();
        ArrayList<String> imgsUrl = new ArrayList<>();
        imgsUrl.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        imgsUrl.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        imgsUrl.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        imgsUrl.add("https://i.redd.it/j6myfqglup501.jpg");
        ArrayList<String> tags = new ArrayList<>();
        tags.add("#banana ");
        temp.add(new Post(1, "multi_imgs", imgsUrl, "none", "lady gaga eats kaka with donald trump and 4 others", tags));
        temp.add(new Post(1, "multi_imgs", imgsUrl, "none", "lady gaga eats kaka with donald trump and 4 others", tags));
        temp.add(new Post(1, "multi_imgs", imgsUrl, "none", "lady gaga eats kaka with donald trump and 4 others", tags));
        temp.add(new Post(1, "multi_imgs", imgsUrl, "none", "lady gaga eats kaka with donald trump and 4 others", tags));
        posts.setValue(temp);
        return posts;
    }

    /**
     * The Constructor of the repository class
     * */
    public Repository(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retromock retromock = new Retromock.Builder()
                .retrofit(retrofit)
                .build();

        //apiInterface =  retrofit.create(ApiInterface.class);
        apiInterface = retromock.create(ApiInterface.class); // FOR TESTING
    }

    /**
     * This static method is responsible for instantiating the repository if it doesn't exist
     * @return The repo which already instantiated
     * */
    public static Repository INSTANTIATE(){
        if(null == INSTANCE){ INSTANCE = new Repository(); }
        return INSTANCE;
    }

    /**
     * Sends a request to the back-end API with email and password to make sure that they exist
     * @param email Email address for the user
     * @param password Password for the user
     * @return (Call<Login Response>) The response from the beck-end server as an class object
     * */
    public Call<LoginResponse> databaseLogin(String email, String password) {
        LoginRequest request = new LoginRequest(email, password);
        return apiInterface.Login(request);

    }

    /**
     * Sends a request to the back-end API to sign up
     * @param age The age of the user
     * @param email The email eddress of the user
     * @param password A plain text password of the user
     * @param name The user name
     * @return A response from the back-end server as an json class object
     * */
    public Call<LoginResponse> databaseSignup(String age, String email, String password, String name){
        SignupRequest request = new SignupRequest(age, email, password, name);
        return apiInterface.Signup(request);
    }

    /**
     * send request to backend API to signup with google
     * @param age The age of the user
     * @param name The name of the user
     * @param token The token <TODO>
     * @return The signup response
     * */
    public Call<LoginResponse> databaseSignupWithGoogle(int age, String name, String token){
        GoogleSignupRequest request = new GoogleSignupRequest(token, age, name);
        return apiInterface.googleSignup(request);
    }

    /**
     * send request to backend API to login with google
     * @param googleIdToken The token <TODO>
     * @return The login response
     * */
    public Call<LoginResponse> databaseLoginWithGoogle(String googleIdToken){
        GoogleLoginRequest request = new GoogleLoginRequest(googleIdToken);
        return apiInterface.googleLogin(request);
    }
}
