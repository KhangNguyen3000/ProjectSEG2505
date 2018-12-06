package com.example.nguye.seg2505app;

import android.widget.EditText;

import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.CustomSchedule;
import com.example.nguye.seg2505app.Storables.OfferedService;
import com.example.nguye.seg2505app.Storables.Provider;
import com.example.nguye.seg2505app.Storables.Rating;
import com.example.nguye.seg2505app.Storables.ServiceType;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountMethodsTest {

    // Check if setType and getType methods work
    @Test
    public void checkType(){
        Account testAccount = new Account();
        testAccount.setType(2);
        assertEquals(testAccount.getType(), 2);
    }

    // Check if setID and getID methods work
    @Test
    public void checkID(){
        Account testAccount = new Account();
        testAccount.setID(20);
        assertEquals(testAccount.getID(), 20);
    }

    // Check if setEmail and getEmail methods work
    @Test
    public void checkEmail(){
        Account testAccount = new Account();
        testAccount.setEmail("test@test.test");
        assertEquals(testAccount.getEmail(), "test@test.test");
    }

    // Check if setPassword and getPassword methods work
    @Test
    public void checkPassword(){
        Account testAccount = new Account();
        testAccount.setPassword(3);
        assertEquals(testAccount.getPassword(), 3);
    }

    // Check if setFirstName and getFirstName methods work
    @Test
    public void checkFirstName(){
        Account testAccount = new Account();
        testAccount.setFirstName("Test");
        assertEquals(testAccount.getFirstName(), "Test");
    }



    @Test
    public void checkOfferedService(){
        OfferedService testOfferedService = new OfferedService();
        testOfferedService.setTypeID(1);
        testOfferedService.setProviderID(12);
        testOfferedService.setHourlyRate(90);
        assertTrue(testOfferedService.getTypeID() == 1);
        assertTrue( testOfferedService.getProviderID() == 12);
        assertTrue(testOfferedService.getHourlyRate() == 90);
    }

    @Test
    public void checkRating(){
        Rating testRating = new Rating();
        testRating.setComment("Blabla");
        assertEquals(testRating.getComment(), "Blabla");
    }

    @Test
    public void checkProvider(){
        Provider testProvider = new Provider();
        testProvider.setID(1);
        testProvider.setCompanyName("Test");
        assertTrue(testProvider.getID() == 1);
        assertEquals(testProvider.getCompanyName(), "Test");
    }

    @Test
    public void checkSchecule(){
        CustomSchedule testSchedule = new CustomSchedule();
        testSchedule.setEndTime(12);
        assertTrue(testSchedule.getEndTime() == 12);
    }

}
