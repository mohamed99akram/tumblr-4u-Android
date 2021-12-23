package com.example.tumblr4u.ApiData.WritePost;

import com.example.tumblr4u.ApiData.Login_Signup.Response;
import com.example.tumblr4u.ApiData.Meta;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadImageResponse {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("res")
    @Expose
    private Response res;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Response getRes() {
        return res;
    }

    public void setRes(Response res) {
        this.res = res;
    }
}
