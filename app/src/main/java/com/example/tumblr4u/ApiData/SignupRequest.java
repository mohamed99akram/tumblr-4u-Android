package com.example.tumblr4u.ApiData;

public class SignupRequest {
    private String email;
    private String password;
    private String blogName;
    private String age;

    public SignupRequest(String age, String email, String password, String blogName){
        this.email = email;
        this.password = password;
        this.blogName = blogName;
        this.age = age;
    }

    public String getEmail(){ return email; }
    public String getPassword(){ return password; }
    public String getBlog_Name(){ return blogName;}
    public String getAge(){ return age;}

    public void setEmail(String email){this.email = email;}
    public void setPassword(String password){this.password = password;}
    public void setBlog_Name(String blogName){this.blogName = blogName;}
    public void setAge(String age){this.age = age;}

}
