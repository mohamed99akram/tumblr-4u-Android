package com.example.tumblr4u.ViewModel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tumblr4u.ApiData.LoginResponse;
import com.example.tumblr4u.GeneralPurpose.Services;
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
    public MutableLiveData<Boolean> isValidEmail = new MutableLiveData<>();
    private Repository database = Repository.INSTANTIATE();
    private Services services = new Services();

    public void signup(String age, String email, String password, String name){
        if(!services.isValidEmail(email)){
            isValidEmail.setValue(false);
            return;
        }
        Call<LoginResponse> response = database.databaseSignup(age, email, password, name);
        response.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {

                //int statusCode = response.body().getMeta().getStatus();
                Log.e("sign up", response.toString());
                /*
                if(statusCode >= 200 && statusCode <= 299) {
                    successfulSignup.setValue(true);
                } else {
                    successfulSignup.setValue(false);
                }
                 */
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }
}
