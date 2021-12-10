package com.example.tumblr4u.ApiData;

/**
 * This class is the data of the login or signup response
 * */
public class Response {
    private String message; // description of response if it succeeded or not
    private String data;    // The data sent from the server in this case is a token

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
    public String getData() {
        return data;
    }

    /**
     * Setter of the data
     * @param data The data
     * */
    public void setData(String data) {
        this.data = data;
    }
}
