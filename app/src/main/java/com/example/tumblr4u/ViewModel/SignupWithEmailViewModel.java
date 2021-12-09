package com.example.tumblr4u.ViewModel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tumblr4u.ApiData.LoginResponse;
import com.example.tumblr4u.Repository.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Signup with email view model
 * @author Omar Ahmed
 * @version 1.0
 * */

public class SignupWithEmailViewModel extends ViewModel {

    public MutableLiveData<Boolean> successfulSignup = new MutableLiveData<>();
    private Repository database = Repository.INSTANTIATE();

    public void signup(String age, String email, String password, String name){

        Log.e("signup information", age + email + password + name);
        Call<LoginResponse> response = database.databaseSignup(age, email, password, name);
        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                Log.e("sign up response5555", response.toString());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }
}
