package com.example.tumblr4u.GeneralPurpose;

import java.util.regex.Pattern;

/**
 * This class is responsible for providing any additional services needed across many classes
 * */
public class Services {

    /**
     * This function removes the spaces around these given strings
     * @param age The age of the user
     * @param email The email of the user
     * @param password The password of the user
     * @param name The name of the user
     * */
    public void trimData(StringBuilder age, StringBuilder email, StringBuilder password, StringBuilder name){
        age.trimToSize();
        email.trimToSize();
        password.trimToSize();
        name.trimToSize();
    }

    /**
     * This function validate that the email on the correct format  (name@domain.com)
     * @param email The entered email
     * @return if the email on the correct format or not
     * */
    public Boolean isValidEmail(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }
}
