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
    public MutableLiveData<Boolean> successfulSignup = new MutableLiveData<>(false);
    private Repository database = Repository.INSTANTIATE();

    public void login(String googleIdToken){

//       // important --------TODO ----- remove this
//        successfulSignup.setValue(false);
        Call<LoginResponse> response = database.databaseLoginWithGoogle(googleIdToken );

        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                //TODO store this token somehwere
                String token = response.body().getResponse().getData();
                Log.e("SignIn Google","TOKEN: "+token);
                Log.e("SignIn Google", "response code = "+response.code());

                int statusCode = response.code();
                if(statusCode >= 200 && statusCode <= 299) {
                    successfulSignup.setValue(true);
                } else {
                    successfulSignup.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("SignIn Google", t.getMessage());
            }
        });
    }
}
