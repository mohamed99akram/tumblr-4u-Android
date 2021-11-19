package com.example.tumblr4u.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tumblr4u.Repository.Repository;

public class LoginWithEmailViewModel extends ViewModel {

    public MutableLiveData<Boolean> isValidEmailAndPassword = new MutableLiveData<>();
    private Repository datebase = new Repository();

    public void login(String email, String password){

        isValidEmailAndPassword.setValue(datebase.databaseLogin(email, password));
    }

}
