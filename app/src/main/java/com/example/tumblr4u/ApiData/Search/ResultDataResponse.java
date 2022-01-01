package com.example.tumblr4u.ApiData.Search;

import com.example.tumblr4u.ApiData.ViewPost.PostsToShow;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Result data response object corresponding to the json response
 * This api is the result posts related to search word
 * */
public class ResultDataResponse {
    @SerializedName("resultPostHashTag")
    @Expose
    private List<PostsToShow> resultPostHashTag;
    public List<PostsToShow> getResultPostHashTag(){ return resultPostHashTag;}
}
