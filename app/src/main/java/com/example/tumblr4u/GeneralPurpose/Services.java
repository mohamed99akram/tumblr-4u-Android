package com.example.tumblr4u.GeneralPurpose;

import java.util.regex.Pattern;

public class Services {
    public void trimData(StringBuilder age, StringBuilder email, StringBuilder password, StringBuilder name){
        age.trimToSize();
        email.trimToSize();
        password.trimToSize();
        name.trimToSize();
    }

    public Boolean isValidEmail(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }
}
