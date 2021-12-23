package com.example.tumblr4u.ApiInterfaces;

/**
 * @author Omar Ahmed
 * @version 1.0
 */

import com.example.tumblr4u.ApiData.Login_Signup.GoogleLoginRequest;
import com.example.tumblr4u.ApiData.Login_Signup.GoogleSignupRequest;
import com.example.tumblr4u.ApiData.ViewPost.HomePostsRequest;
import com.example.tumblr4u.ApiData.ViewPost.HomePostsResponse;
import com.example.tumblr4u.ApiData.Login_Signup.LoginRequest;
import com.example.tumblr4u.ApiData.Login_Signup.LoginResponse;
import com.example.tumblr4u.ApiData.Login_Signup.SignupRequest;
import com.example.tumblr4u.ApiData.WritePost.UploadImageRequest;
import com.example.tumblr4u.ApiData.WritePost.UploadImageResponse;

import co.infinum.retromock.meta.Mock;
import co.infinum.retromock.meta.MockResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * The Api Interface which responsible for making a requests to the back-end server
 * */
public interface ApiInterface {
    @Mock
    @MockResponse(body = "{\"meta\":{\"status\":200, \"msg\": \"CREATED\"}, "
            + "\"res\":{\"message\":\"sign up successfully\", \"data\": \"token\"}}")
//    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "google_login_response.json")
    @POST("login")
    public Call<LoginResponse> Login(@Body LoginRequest request);

    @Mock
    @MockResponse(body = "{\"meta\":{\"status\":201, \"msg\": \"CREATED\"}, "
            + "\"res\":{\"message\":\"sign up successfully\", \"data\": \"token\"}}")
    @POST("signup")
    public Call<LoginResponse> Signup(@Body SignupRequest request);

    // TODO change this
    @Mock
//    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "google_login_response.json")
//    @MockResponse(body = "google_login_response.json")
    @MockResponse(body = "{\"meta\":{\"status\":201, \"msg\": \"CREATED\"}, "
            + "\"res\":{\"message\":\"sign up successfully\", \"data\": \"YourToken\"}}")
//    @Headers({"authorization: Bearer token"})
    @PUT("/google/info")
    public Call<LoginResponse> googleSignup(@Header("Authorization") String token,
            @Body GoogleSignupRequest request);

    @Mock
//    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "google_login_response.json")
    @MockResponse(body = "{\"meta\":{\"status\":201, \"msg\": \"CREATED\"}, "
            + "\"res\":{\"message\":\"sign up successfully\", \"data\": \"YourToken\"}}")

    @POST("androidSignUpWithGoogle")
    public Call<LoginResponse> googleLogin(@Body GoogleLoginRequest request);


    // ----------------- Home Posts --------------
    @Mock
    @MockResponse(body = "{\n"
            + "  \"meta\": {\n"
            + "    \"status\": 201,\n"
            + "    \"msg\": \"Successful\"\n"
            + "  },\n"
            + "  \"res\": {\n"
            + "    \"message\": \"Dashboard Got Successfully\",\n"
            + "    \"data\": [\n"
            + "      {\n"
            + "        \"_id\": \"1\",\n"
            + "        \"blogId\": \"1\",\n"
            + "        \"postHtml\": \"<h1>post 1 Mocked</h1>\",\n"
            + "        \"type\": \"link\",\n"
            + "        \"state\": \"published\"\n"
            + "      },\n"
            + "      {\n"
            + "        \"_id\": \"2\",\n"
            + "        \"blogId\": \"1\",\n"
            + "        \"postHtml\": \"<h1>post 2 Mocked</h1>\",\n"
            + "        \"type\": \"link\",\n"
            + "        \"state\": \"published\"\n"
            + "      },\n"
            + "      {\n"
            + "        \"_id\": \"3\",\n"
            + "        \"blogId\": \"4\",\n"
            + "        \"postHtml\": \"<h1>post 3 Mocked</h1>\",\n"
            + "        \"type\": \"link\",\n"
            + "        \"state\": \"published\"\n"
            + "      },\n"
            + "      {\n"
            + "        \"_id\": \"4\",\n"
            + "        \"blogId\": \"3\",\n"
            + "        \"postHtml\": \"<h1>post 4 Mocked</h1>\",\n"
            + "        \"type\": \"link\",\n"
            + "        \"state\": \"published\"\n"
            + "      },\n"
            + "      {\n"
            + "        \"_id\": \"5\",\n"
            + "        \"blogId\": \"2\",\n"
            + "        \"postHtml\": \"<h1>post 5 Mocked</h1>\",\n"
            + "        \"type\": \"link\",\n"
            + "        \"state\": \"published\"\n"
            + "      }\n"
            + "    ]\n"
            + "  }\n"
            + "}")
    @POST("dashboard") //TODO make token in header
    public Call<HomePostsResponse> getHomePosts(@Body HomePostsRequest request);

    @Mock
    @MockResponse(body = "{\n"
            + "  \"meta\": {\n"
            + "    \"status\": 201,\n"
            + "    \"msg\": \"CREATED\"\n"
            + "  },\n"
            + "  \"res\": {\n"
            + "    \"message\": \"sign up successfully from file\",\n"
            + "    \"data\": \"https://www.vbetnews.com/wp-content/uploads/2020/08/P2020-08-25-Salsburg_Liverpool-83.jpg.jpg\"\n"
            + "  }\n"
            + "}")
    @POST("uploadImage")
    public Call<UploadImageResponse> uploadImage(@Header("token") String token, @Body
            UploadImageRequest request);

}