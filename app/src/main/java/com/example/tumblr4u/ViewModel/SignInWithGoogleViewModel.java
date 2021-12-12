package com.example.tumblr4u.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tumblr4u.ApiData.LoginResponse;
import com.example.tumblr4u.Repository.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInWithGoogleViewModel extends ViewModel {
    public MutableLiveData<Boolean> successfulSignIn = new MutableLiveData<>(false);
    private Repository database = Repository.INSTANTIATE();

    public void login(String googleIdToken){

        Call<LoginResponse> response = database.databaseLoginWithGoogle(googleIdToken );

        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                Log.e("SignIn Google", "response code = "+response.code());
                if(response.isSuccessful()){
                    String token;
                    if (response.body() != null) {
                        // TODO store this token somewhere
                        token = response.body().getResponse().getData();
                        successfulSignIn.setValue(true);
                        Log.e("Sign In Google", "Token = " + token);
                    }
                    else{
                        successfulSignIn.setValue(false);
                    }
                }
                else{
                    successfulSignIn.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("SignIn Google", t.getMessage());
            }
        });
    }
}
