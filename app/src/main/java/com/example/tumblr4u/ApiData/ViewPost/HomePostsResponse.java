//Genereated by https://www.jsonschema2pojo.org
package com.example.tumblr4u.ApiData.ViewPost;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomePostsResponse {

    @SerializedName("res")
    @Expose
    private Res res;

    public Res getRes() {
        return res;
    }

    public void setRes(Res res) {
        this.res = res;
    }

}
