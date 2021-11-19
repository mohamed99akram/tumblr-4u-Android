package com.example.tumblr4u.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tumblr4u.Repository.Repository;

/**
 * Login with email view model
 * @author Omar Ahmed
 * @version 1.0
 * */

public class LoginWithEmailViewModel extends ViewModel {

    public MutableLiveData<Boolean> isValidEmailAndPassword = new MutableLiveData<>();
    private Repository datebase = new Repository();

    /**
     * Calls the database to get the authentication status and assign it to the status variable
     * @param email The email of the user
     * @param password The password of the user
     * */
    public void login(String email, String password){

        isValidEmailAndPassword.setValue(datebase.databaseLogin(email, password));
    }

}
