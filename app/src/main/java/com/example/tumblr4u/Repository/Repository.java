package com.example.tumblr4u.Repository;

import androidx.annotation.NonNull;

import com.example.tumblr4u.ApiData.AddComment.CommentRequest;
import com.example.tumblr4u.ApiData.AddComment.CommentResponse;
import com.example.tumblr4u.ApiData.Login_Signup.GoogleLoginRequest;
import com.example.tumblr4u.ApiData.Login_Signup.GoogleSignupRequest;
import com.example.tumblr4u.ApiData.Login_Signup.LoginRequest;
import com.example.tumblr4u.ApiData.Login_Signup.LoginResponse;
import com.example.tumblr4u.ApiData.Login_Signup.SignupRequest;
import com.example.tumblr4u.ApiData.RetrieveBlog.BlogResponse;
import com.example.tumblr4u.ApiData.RetrieveNotes.NotesResponse;
import com.example.tumblr4u.ApiData.Search.SuggestedDataResponse;
import com.example.tumblr4u.ApiData.ViewPost.HomePostsResponse;
import com.example.tumblr4u.ApiData.WritePost.CreatePostRequest;
import com.example.tumblr4u.ApiData.WritePost.CreatePostResponse;
import com.example.tumblr4u.ApiData.WritePost.UploadImageRequest;
import com.example.tumblr4u.ApiData.WritePost.UploadImageResponse;
import com.example.tumblr4u.ApiInterfaces.ApiInterface;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import co.infinum.retromock.BodyFactory;
import co.infinum.retromock.Retromock;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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


    /**
     * The Constructor of the repository class
     * */
    private Repository(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retromock retromock = new Retromock.Builder()
                .retrofit(retrofit)
//                .defaultBodyFactory(new ResourceBodyFactory())
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
     * @param email The email address of the user
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
     * @param name the user name
     * @param token - the google token is provided by the google API in the user's mobile phone
     *              and it will be sent to the server to be exchanged with server's token.
     *              - server's token will then be used for actions that need authorization
     *              to check that the user can have access to some resource
     * */
    public Call<LoginResponse> databaseSignupWithGoogle(String age, String name, String token){
        GoogleSignupRequest request = new GoogleSignupRequest( age, name);
        // TODO move Bearer to getToken Method
        return apiInterface.googleSignup("Bearer "+token, request);
    }

    /**
     * send request to backend API to login with google
     * @param googleIdToken - the google token is provided by the google API in the user's mobile phone
     *                      and it will be sent to the server to be exchanged with server's token.
     *                      - server's token will then be used for actions that need authorization
     *                      to check that the user can have access to some resource
     * */
    public Call<LoginResponse> databaseLoginWithGoogle(String googleIdToken){
        GoogleLoginRequest request = new GoogleLoginRequest(googleIdToken);
        return apiInterface.googleLogin(request);
    }

    /**
     * request home posts
     * */
    public Call<HomePostsResponse> requestHomePosts(String token) {
        // TODO move Bearer to getToken Method
        return apiInterface.getHomePosts("Bearer "+token);
    }

    /**
     *TODO change to Call<publishPRe
     * */
    public void publishPost(String token, String blogId, String postHtml, String type){

    }
    /**
     *
     * */
    public Call<UploadImageResponse> uploadImage(String token, String imageBase64){
        UploadImageRequest request = new UploadImageRequest(imageBase64);
        return apiInterface.uploadImage(token, request);
    }
    /**
     * Make Post
     * */
    public Call<CreatePostResponse> makePost(String token, String blogId, String postHtml, String postType){
        CreatePostRequest createPostRequest = new CreatePostRequest();
        return apiInterface.createPost("Bearer "+token, blogId, createPostRequest);
    }
    /**
     * Get Blog data
     * */
    public Call<BlogResponse> getBlog(String token, String blogId){
        // TODO move Bearer to getToken Method
        return apiInterface.getBlog("Bearer "+token, blogId);
    }
    /**
     * Get Post Notes
     * */
    public Call<NotesResponse> getNotes(String token, String notesId){
        // TODO move Bearer to getToken Method
        return apiInterface.getNotes("Bearer "+token, notesId);
    }
    /**
     * Press Like Button
     * */
    public Call<String> pressLike(String token, String blogId, String postId){
        return apiInterface.pressLike("Bearer "+token, blogId, postId);
    }

    /**
     * Add new Comment
     * */
    public Call<String> makeComment(String token, String blogId, String postId, String commentText){
        CommentRequest commentRequest = new CommentRequest(commentText);
        return apiInterface.makeComment("Bearer "+token, blogId, postId, commentRequest);
    }
    public Call<SuggestedDataResponse> dataBaseGetSuggestedItems(String token, String searchWord){
        return apiInterface.getSuggestedItems("Bearer " + token, searchWord);
    }

}

/**
 * This class may be used by retro-mock to load json files from resources directory
 * an instance of it is passed to the defaultBodyFactory when creating an instance of
 * retro-mock
 * */
final class ResourceBodyFactory implements BodyFactory {

    @Override
    public InputStream create(@NonNull final String input) throws IOException {
        return new FileInputStream(
                ResourceBodyFactory.class.getClassLoader().getResource(input).getFile()
        );
    }
}