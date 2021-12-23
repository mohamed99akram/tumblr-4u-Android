package com.example.tumblr4u.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.tumblr4u.ApiData.Login_Signup.LoginResponse;
import com.example.tumblr4u.GeneralPurpose.Prefs;
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

public class SignupWithEmailViewModel extends AndroidViewModel {

    public MutableLiveData<Boolean> successfulSignup = new MutableLiveData<>();
    public MutableLiveData<Boolean> isValidEmail = new MutableLiveData<>();
    private Repository database = Repository.INSTANTIATE();
    private Services services = new Services();

    public SignupWithEmailViewModel(@NonNull Application application) {
        super(application);
    }

    public void signup(String age, String email, String password, String name){
        if(!services.isValidEmail(email)){
            isValidEmail.setValue(false);
            return;
        }
        Call<LoginResponse> response = database.databaseSignup(age, email, password, name);
        response.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {

//                Log.e("sign up", response.body()!=null?response.body().toString():"null");
//                int statusCode = response.body().getMeta().getStatus();
                Log.e("sign up", response.toString());
                Log.e("signup",response.message());
                Log.e("signup",response.isSuccessful()+"");
                Log.e("signup",response.code()+"");

                if (response.isSuccessful()) {
                    String token;
                    if (response.body() != null) {
                        token = response.body().getResponse().getData();

                        // ----------- store this token ------------
                        Prefs.storeToken(getApplication(),token);

                        Log.i("LoginEmail", token);
                        successfulSignup.setValue(true);
                    } else {
                        successfulSignup.setValue(false);
                    }
                } else {
                    successfulSignup.setValue(false);
                }
//                int statusCode = response.code();
//                if(statusCode >= 200 && statusCode <= 299) {
//                    successfulSignup.setValue(true);
//                } else {
//                    successfulSignup.setValue(false);
//                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("Sign up", "failed to make the request");
                Log.e("sign up",t.getMessage());
            }
        });
    }
}
