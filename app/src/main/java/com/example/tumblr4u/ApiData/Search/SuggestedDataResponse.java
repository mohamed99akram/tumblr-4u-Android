package com.example.tumblr4u.ApiData.Search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * This class corresponding to suggested data response json message
 * This response comes when someone searches a word in the search field
 * */
public class SuggestedDataResponse {
    @SerializedName("resultHashTag")
    @Expose
    private List<String> resultHashTag;

    public List<String> getResultHashTag(){
        return resultHashTag;
    }

}
