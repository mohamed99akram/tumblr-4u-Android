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
 * Login with email view model
 *
 * @author Omar Ahmed
 * @version 1.0
 */

public class LoginWithEmailViewModel extends AndroidViewModel {

    public MutableLiveData<Boolean> isValidEmailAndPassword = new MutableLiveData<>();
    public MutableLiveData<Boolean> isValidEmail = new MutableLiveData<>();
    private Repository datebase = Repository.INSTANTIATE();
    private Services services = new Services();

    public LoginWithEmailViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Calls the database to get the authentication status and assign it to the status variable
     *
     * @param email    The email of the user
     * @param password The password of the user
     */
    public void login(String email, String password) {
        if (!services.isValidEmail(email)) {
            isValidEmail.setValue(false);
            return;
        }
        Call<LoginResponse> response = datebase.databaseLogin(email, password);
        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.e("signin", "response.body() = " + (response.body() != null
                        ? response.body().getResponse().getMessage() : "null"));

                Log.e("sign in", response.toString());

                if (response.isSuccessful()) {
                    String token;
                    if (response.body() != null) {
                        token = response.body().getResponse().getData().getToken();

                        // ----------- store this token ------------
                        Prefs.storeToken(getApplication(),token);

                        Log.i("LoginEmail", token);
                        isValidEmailAndPassword.setValue(true);
                    } else {
                        isValidEmailAndPassword.setValue(false);
                    }
                } else {
                    isValidEmailAndPassword.setValue(false);
                }
                //                int statusCode = response.code();
//                if(statusCode >= 200 && statusCode <= 299) {
//                    isValidEmailAndPassword.setValue(true);
//                } else {
//                    isValidEmailAndPassword.setValue(false);
//                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                isValidEmailAndPassword.setValue(false);
                Log.e("LoginEmail", t.getMessage());
            }
        });
    }

    /**
     * Get the status of validation of email and password
     *
     * @return The validation of email and password
     */
    public Boolean getEmailAndPasswordStatus() {
        return isValidEmailAndPassword.getValue();
    }

}
