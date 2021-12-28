package com.example.tumblr4u.ApiData.Search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SuggestedDataResponse {
    @SerializedName("resultHashTag")
    @Expose
    private String [] resultHashTag;

    public String[] getResultHashTag(){
        return resultHashTag;
    }

}
