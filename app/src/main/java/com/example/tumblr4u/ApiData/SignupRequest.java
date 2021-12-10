package com.example.tumblr4u.ApiData;

/**
 * Class of the signup request template
 * */
public class SignupRequest {
    private String email;
    private String password;
    private String blogName;
    private String age;

    /**
     * Constructor signup request
     * @param age The age of the user
     * @param email The email of the user
     * @param password The password of the user
     * @param blogName The user name
     *  */
    public SignupRequest(String age, String email, String password, String blogName){
        this.email = email;
        this.password = password;
        this.blogName = blogName;
        this.age = age;
    }

    /**
     * Getter of the email
     * @return The email of the user
     * */
    public String getEmail(){ return email; }

    /**
     * Getter of the password
     * @return The password of the user
     * */
    public String getPassword(){ return password; }

    /**
     * Getter of the user name
     * @return The user name
     * */
    public String getBlog_Name(){ return blogName;}

    /**
     * Getter of the age
     * @return The age of the user
     * */
    public String getAge(){ return age;}

    /**
     * Setter of the email
     * @param email The email of the user
     * */
    public void setEmail(String email){this.email = email;}

    /**
     * Setter of the password
     * @param password The password of the user
     * */
    public void setPassword(String password){this.password = password;}

    /**
     * Setter of the user name
     * @param blogName The name of the user
     * */
    public void setBlog_Name(String blogName){this.blogName = blogName;}

    /**
     * Setter of the age
     * @param age The age of the user
     * */
    public void setAge(String age){this.age = age;}

}
