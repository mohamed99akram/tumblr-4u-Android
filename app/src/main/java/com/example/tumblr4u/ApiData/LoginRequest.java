package com.example.tumblr4u.ApiData;

/**
 * The login request class is the template of the request API
 * */
public class LoginRequest {

    private String Email;
    private String Password;

    /**
     * The constructor of the login request
     * @param Email The email of the user
     * @param Password The password of the user
     * */
    public LoginRequest(String Email, String Password){
        this.Email = Email;
        this.Password = Password;
    }

    /**
     * Setter of the email
     * @param email The email of the user
     * */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     * Setter of the password
     * @param password The password of the user
     * */
    public void setPassword(String password) {
        Password = password;
    }

    /**
     * Getter of the email
     * @return The email of the user
     * */
    public String getEmail() {
        return Email;
    }

    /**
     * Getter of the password
     * @return The password of the user
     * */
    public String getPassword() {
        return Password;
    }
}
