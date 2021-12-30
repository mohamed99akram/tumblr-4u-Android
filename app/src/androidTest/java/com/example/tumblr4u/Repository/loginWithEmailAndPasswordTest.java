package com.example.tumblr4u.Repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class loginWithEmailAndPasswordTest {
    @Test
    public void getEmailAndPasswordValidationTest(){
        Repository loginWithEmailAndPasswordRepo = Repository.INSTANTIATE();
//        assertTrue(loginWithEmailAndPasswordRepo.databaseLogin("omar.ahmed314@hotmail.com", "12345"));
//        assertFalse(loginWithEmailAndPasswordRepo.databaseLogin("mar.ahmed314@hotmail.com", "12345"));
    }

    @Test
    public void TestTheNullValuesForDatabaseValidation(){
        Repository loginWithEmailAndPasswordRepo = Repository.INSTANTIATE();
//        assertFalse(loginWithEmailAndPasswordRepo.databaseLogin(null, "12345"));
//        assertFalse(loginWithEmailAndPasswordRepo.databaseLogin("omar.ahmed314@hotmail.com", null));
//        assertFalse(loginWithEmailAndPasswordRepo.databaseLogin(null, null));
    }

    @Test
    public void TestTheEmptyStringValueForDatabaseValidation(){
        Repository loginWithEmailAndPasswordRepo = Repository.INSTANTIATE();
//        assertFalse(loginWithEmailAndPasswordRepo.databaseLogin("", "12345"));
//        assertFalse(loginWithEmailAndPasswordRepo.databaseLogin("omar.ahmed314@hotmail.com", ""));
//        assertFalse(loginWithEmailAndPasswordRepo.databaseLogin("", ""));
    }
}
