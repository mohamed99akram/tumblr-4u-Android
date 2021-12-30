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

public class SignInWithGoogleViewModel extends AndroidViewModel {
    public MutableLiveData<Boolean> successfulSignIn = new MutableLiveData<>(false);
    private Repository database = Repository.INSTANTIATE();

    public SignInWithGoogleViewModel(@NonNull Application application) {
        super(application);
    }

    public void login(String googleIdToken) {

        Call<GoogleLoginResponse> response = database.databaseLoginWithGoogle(googleIdToken);
        response.enqueue(new Callback<GoogleLoginResponse>() {
            @Override
            public void onResponse(Call<GoogleLoginResponse> call,
                    Response<GoogleLoginResponse> response) {

                Log.e("SignIn Google", "response code = " + response.code());
                if (response.isSuccessful()) {
                    String token;
                    if (response.body() != null) {
                        token = response.body().getResponse().getData().getToken();

                        // ----------- store this token ------------
                        Prefs.storeToken(getApplication(),token);
                        Log.i("SignInGoogle","Make sure token is stored: token ="+Prefs.getToken(getApplication()));

                        Log.e("Sign In Google", "Token = " + token);
                        successfulSignIn.setValue(true);
                    } else {
                        successfulSignIn.setValue(false);
                    }
                } else {
                    successfulSignIn.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<GoogleLoginResponse> call, Throwable t) {
                Log.e("SignIn Google", t.getMessage());
                for(StackTraceElement el:t.getStackTrace()){
                    Log.e("SignInWithGoogle", el.toString());
                }
            }
        });
    }
}
