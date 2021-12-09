package com.example.tumblr4u.ApiData;

public class SignupRequest {
    private String Email;
    private String Password;
    private String Blog_Name;
    private String Age;
    private String Stuff;

    public SignupRequest(String age, String email, String password, String blogName){
        this.Email = email;
        this.Password = password;
        this.Blog_Name = blogName;
        this.Age = age;
    }

    public String getEmail(){ return Email; }
    public String getPassword(){ return Password; }
    public String getBlog_Name(){ return Blog_Name;}
    public String getAge(){ return Age;}
    public String getStuff() { return Stuff; }

    public void setEmail(String email){Email = email;}
    public void setPassword(String password){Password = password;}
    public void setBlog_Name(String blogName){Blog_Name = blogName;}
    public void setAge(String age){Age = age;}
    public void setStuff(String stuff){Stuff = stuff;}

}
