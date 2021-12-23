package com.example.tumblr4u.ApiData.Login_Signup;

import com.example.tumblr4u.ApiData.Meta;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This class is the template for the login response
 * */
public class LoginResponse {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("res")
    @Expose
    private Response res;

    /**
     * Setter of the meta data
     * @param meta The meta data of the request
     * */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     * Setter of the response
     * @param response The response of the request
     * */
    public void setResponse(Response response) {
        this.res = response;
    }

    /**
     * Getter of the meta data
     * */
    public Meta getMeta() {
        return meta;
    }

    /**
     * Getter of the response data
     * */
    public Response getResponse() {
        return res;
    }
}
