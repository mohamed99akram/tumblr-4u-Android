package com.example.tumblr4u.ApiData;

public class LoginRequest {

    private String Email;
    private String Password;

    public LoginRequest(String Email, String Password){
        this.Email = Email;
        this.Password = Password;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }
}
