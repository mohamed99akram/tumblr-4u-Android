package com.example.tumblr4u;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginWithEmailViewModel extends ViewModel {

    public MutableLiveData<Boolean> isValidEmailAndPassword = new MutableLiveData<>();

    public void login(String email, String password){

        isValidEmailAndPassword.setValue(dataBaseLogin(email, password));
    }

    private Boolean dataBaseLogin(String email, String password){
        return true;
    }
}
