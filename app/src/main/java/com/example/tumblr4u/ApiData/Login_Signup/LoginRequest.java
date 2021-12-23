package com.example.tumblr4u.ApiData.Login_Signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The login request class is the template of the request API
 * */
public class LoginRequest {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;

    /**
     * The constructor of the login request
     * @param Email The email of the user
     * @param Password The password of the user
     * */
    public LoginRequest(String Email, String Password){
        this.email = Email;
        this.password = Password;
    }

    /**
     * Setter of the email
     * @param email The email of the user
     * */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setter of the password
     * @param password The password of the user
     * */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter of the email
     * @return The email of the user
     * */
    public String getEmail() {
        return email;
    }

    /**
     * Getter of the password
     * @return The password of the user
     * */
    public String getPassword() {
        return password;
    }
}
