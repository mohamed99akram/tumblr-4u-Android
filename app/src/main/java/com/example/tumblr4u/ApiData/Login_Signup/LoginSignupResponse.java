package com.example.tumblr4u.ApiData.Login_Signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginSignupResponse {
    @SerializedName("message")
    @Expose
    private String message; // description of response if it succeeded or not
    @SerializedName("data")
    @Expose
    private LoginSignupData data;    // The data sent from the server in this case is a token

    /**
     * Getter of the message
     * @return The message
     * */
    public String getMessage() {
        return message;
    }

    /**
     * Setter of the message
     * @param message The message
     * */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Getter of the data
     * @return The data
     * */
    public LoginSignupData getData() {
        return data;
    }

    /**
     * Setter of the data
     * @param data The data
     * */
    public void setData(LoginSignupData data) {
        this.data = data;
    }
}
