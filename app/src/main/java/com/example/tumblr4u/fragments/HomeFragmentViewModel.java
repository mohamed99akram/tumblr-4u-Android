package com.example.tumblr4u.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tumblr4u.Repository;
import com.example.tumblr4u.models.Post;

import java.util.ArrayList;

/**
 * @author Mahmoud Amr Nabil
 * @version 1.0
 * @since 11/8,2021
 */
class HomeFragmentViewModel extends ViewModel {
    private Repository repository;
    private LiveData<ArrayList<Post>> liveData;

    public HomeFragmentViewModel(){
        repository = new Repository();
    }

    public LiveData<ArrayList<Post>> getposts() {
        liveData = repository.requestHolidays();
        return liveData;
    }
}
