package com.example.tumblr4u.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tumblr4u.Repository.Repository;

public class SignupWithGoogleViewModel extends ViewModel {

    public MutableLiveData<Boolean> successfulSignup = new MutableLiveData<>(false);
    private Repository database = Repository.INSTANTIATE();
    public void signup(String age, String name){

       // important --------TODO ----- remove this
        successfulSignup.setValue(false);
    }
}
