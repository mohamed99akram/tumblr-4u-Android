package com.example.tumblr4u.ApiData.Search;

import com.example.tumblr4u.ApiData.ViewPost.PostsToShow;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultDataResponse {
    @SerializedName("resultPostHashTag")
    @Expose
    private List<PostsToShow> resultPostHashTag;
    public List<PostsToShow> getResultPostHashTag(){ return resultPostHashTag;}
}
