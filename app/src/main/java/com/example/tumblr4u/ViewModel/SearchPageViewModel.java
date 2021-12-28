package com.example.tumblr4u.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tumblr4u.Models.SearchSuggestionItem;
import com.example.tumblr4u.Repository.Repository;

import java.util.ArrayList;

public class SearchPageViewModel extends ViewModel {
    public MutableLiveData<ArrayList<SearchSuggestionItem>> searchSuggestionItems = new MutableLiveData<>();
    private Repository dataBase = Repository.INSTANTIATE();

    public void getSuggestedItems(String searchWord){

    }

}
