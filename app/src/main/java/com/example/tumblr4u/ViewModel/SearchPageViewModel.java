package com.example.tumblr4u.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tumblr4u.ApiData.Search.SuggestedDataResponse;
import com.example.tumblr4u.GeneralPurpose.Prefs;
import com.example.tumblr4u.Models.SearchSuggestionItem;
import com.example.tumblr4u.Repository.Repository;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * View model of search page that manage its logic and routes its requests
 * */
public class SearchPageViewModel extends AndroidViewModel {
    public MutableLiveData<ArrayList<String>> searchSuggestionItems = new MutableLiveData<>();
    private Repository dataBase = Repository.INSTANTIATE();

    public SearchPageViewModel(@NonNull Application application) {
        super(application);
    }

    public void getSuggestedItems(String searchWord){
        Call<SuggestedDataResponse> response =  dataBase.dataBaseGetSuggestedItems(Prefs.getToken(getApplication()), searchWord);
        response.enqueue(new Callback<SuggestedDataResponse>() {

            @Override
            public void onResponse(Call<SuggestedDataResponse> call, Response<SuggestedDataResponse> response) {
                ArrayList<String> list = new ArrayList<>();
                Log.e("string", response.body().toString());
                for (String element : response.body().getResultHashTag()) {
                    list.add(element);
                }
                searchSuggestionItems.setValue(list);
            }

            @Override
            public void onFailure(Call<SuggestedDataResponse> call, Throwable t) {
            }
        });
    }
}
