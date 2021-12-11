package com.example.tumblr4u.ApiData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Meta class is the meta data sent from the back-end API
 * */
public class Meta {
    @SerializedName("status")
    @Expose
    private int status; // status of the response 200 --> success 400 --> failure
    @SerializedName("msg")
    @Expose
    private String msg; // message that describes the response

    /**
     * Getter of the status
     * @return The status of the response
     * */
    public int getStatus() {
        return status;
    }

    /**
     * Setter of the status
     * @param status The status of the request
     * */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Getter of the message
     * @return The message of the response
     * */
    public String getMsg() {
        return msg;
    }

    /**
     * Setter of the message
     * @param msg The message of the request
     * */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
