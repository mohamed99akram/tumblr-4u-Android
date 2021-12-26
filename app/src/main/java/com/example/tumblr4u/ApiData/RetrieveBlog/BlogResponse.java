
package com.example.tumblr4u.ApiData.RetrieveBlog;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BlogResponse {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("res")
    @Expose
    private Res res;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Res getRes() {
        return res;
    }

    public void setRes(Res res) {
        this.res = res;
    }

}
