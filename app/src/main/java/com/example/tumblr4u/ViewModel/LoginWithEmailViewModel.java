package com.example.tumblr4u.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tumblr4u.ApiData.LoginResponse;
import com.example.tumblr4u.Repository.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Login with email view model
 * @author Omar Ahmed
 * @version 1.0
 * */

public class LoginWithEmailViewModel extends ViewModel {

    public MutableLiveData<Boolean> isValidEmailAndPassword = new MutableLiveData<>();
    private Repository datebase = Repository.INSTANTIATE();

    /**
     * Calls the database to get the authentication status and assign it to the status variable
     * @param email The email of the user
     * @param password The password of the user
     * */
    public void login(String email, String password){

        Call<LoginResponse> response = datebase.databaseLogin(email, password);
        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.e("loginclass5555555555555", response.toString());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                isValidEmailAndPassword.setValue(false);
            }
        });
    }

    /**
     * Get the status of validation of email and password
     * @return The validation of email and password
     * */
    public Boolean getEmailAndPasswordStatus(){
        return isValidEmailAndPassword.getValue();
    }

}
