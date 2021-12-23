package com.example.tumblr4u.ApiData.ViewPost;

import com.example.tumblr4u.ApiData.Meta;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomePostsResponse {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("res")
    @Expose
    private Response_ViewPost res;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Response_ViewPost getResponse() {
        return res;
    }

    public void setResponse(Response_ViewPost res) {
        this.res = res;
    }
}
