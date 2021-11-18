package com.example.tumblr4u;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginWithEmailViewModel extends ViewModel {

    public MutableLiveData<Boolean> isValidEmailAndPassword = new MutableLiveData<>();
    private Repository datebase = new Repository();

    public void login(String email, String password){

        isValidEmailAndPassword.setValue(datebase.databaseLogin(email, password));
    }

}
