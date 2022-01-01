package com.example.tumblr4u.ViewModel;


import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.tumblr4u.ApiData.Login_Signup.GoogleLoginResponse;
import com.example.tumblr4u.ApiData.Login_Signup.LoginResponse;
import com.example.tumblr4u.GeneralPurpose.Prefs;
import com.example.tumblr4u.Repository.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * - This class extends AndroidViewModel to access getApplication() method
 * which gives access to sharedPreferences to get and store token
 * */
public class SignupWithGoogleViewModel extends AndroidViewModel {

    public MutableLiveData<Boolean> successfulSignup = new MutableLiveData<>(false);
    private final Repository database = Repository.INSTANTIATE();

    public SignupWithGoogleViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * <br>
     * - signup happens in two stages:
     *      1) send google token & receive my token -> databaseLoginWithGoogle()
     *      2) send age, blogName & receive access -> databaseSignUpWithGoogle()
     * <br>
     * @param age the user age
     * @param name the blogName
     * @param googleIdToken google token, get it from google API
     * */
    public void signup(String age, String name, String googleIdToken) {

        Call<GoogleLoginResponse> response = database.databaseLoginWithGoogle(googleIdToken);
        response.enqueue(new Callback<GoogleLoginResponse>() {
            @Override
            public void onResponse(Call<GoogleLoginResponse> call,
                    Response<GoogleLoginResponse> response) {
                Log.i("SignUp Google", "response code:" + response.code());
                if (response.isSuccessful()) {
                    final String token;
                    if (response.body() != null) {
                        // TODO store this token somewhere
                        token = response.body().getResponse().getData();
                        // Store the token
                        Prefs.storeToken(getApplication(),token);
                    }
                    else{
                        token = null;
                    }
                    Log.i("SignUp Google", "TOKEN: " + token);
                    Log.i("SignUp Google",
                            "message = " + response.body().getResponse().getMessage());
                    // ------------------ inner request ----------
                    Call<GoogleLoginResponse> response2 = database.databaseSignupWithGoogle(age, name, token);
                    response2.enqueue(new Callback<GoogleLoginResponse>() {
                        @Override
                        public void onResponse(Call<GoogleLoginResponse> call,
                                Response<GoogleLoginResponse> response) {
                            if (response.isSuccessful()) {
                                successfulSignup.setValue(true);
                                Log.i("SignUp Google",
                                        "Signed Up Successfully and sent age and name");
                                Log.i("SignUp Google",
                                        "message = " + response.body().getResponse().getMessage());
                            } else {
                                successfulSignup.setValue(false);
                                Log.i("SignUp Google",
                                        "Signup with google failed @age and name");
                                Log.i("SignUp Google",
                                        "message = " + response.toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<GoogleLoginResponse> call, Throwable t) {
                            Log.e("Sign Up Google (inner)", t.getMessage());
                        }
                    });
                } else {
                    successfulSignup.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<GoogleLoginResponse> call, Throwable t) {
                Log.e("SignUp Google (outer)", t.getMessage());
            }
        });
    }
}
