package com.example.tumblr4u.ApiData;

public class LoginRequest {

    private String email;
    private String password;

    public LoginRequest(String Email, String Password){
        this.email = Email;
        this.password = Password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
