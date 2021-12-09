package com.example.tumblr4u.ApiData;

import org.json.JSONObject;

public class LoginResponse {
    private Meta meta;
    private Response res;

    public void setData(Meta meta) {
        this.meta = meta;
    }

    public void setResponse(Response response) {
        this.res = response;
    }

    public Meta getMeta() {
        return meta;
    }

    public Response getResponse() {
        return res;
    }
}
