package com.example.tumblr4u.ViewModel;


import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tumblr4u.ApiData.LoginResponse;
import com.example.tumblr4u.Repository.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupWithGoogleViewModel extends ViewModel {

    public MutableLiveData<Boolean> successfulSignup = new MutableLiveData<>(false);
    private Repository database = Repository.INSTANTIATE();

    public void signup(String age, String name, String googleIdToken){

//       // important --------TODO ----- remove this
//        successfulSignup.setValue(false);
        Call<LoginResponse> response = database.databaseSignupWithGoogle(Integer.parseInt(age),name,googleIdToken );

        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                Log.i("SignUp Google", "response code:"+ response.code());
                successfulSignup.setValue(true);

                //TODO store this token somehwere
//                String token = response.body().getResponse().getData();
//                Log.i("SignUp Google","TOKEN: "+token);
//                Log.i("SignUp Google","message = "+response.body().getResponse().getMessage());
//                Log.i("SignUp Google", "response code:"+ response.code());
//                int statusCode = response.code();
//                if(statusCode >= 200 && statusCode <= 299) {
//                    successfulSignup.setValue(true);
//                } else {
//                    successfulSignup.setValue(false);
//                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("SignUp Google", t.getMessage());
            }
        });
    }
}
