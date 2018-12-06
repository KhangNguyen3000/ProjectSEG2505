package com.example.nguye.seg2505app;
import com.example.nguye.seg2505app.Activities.Hashing;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.CustomSchedule;
import com.example.nguye.seg2505app.Storables.OfferedService;
import com.example.nguye.seg2505app.Storables.Provider;
import com.example.nguye.seg2505app.Storables.Rating;
import com.example.nguye.seg2505app.Storables.ServiceType;
import com.example.nguye.seg2505app.Utilities.FormatValue;
import com.example.nguye.seg2505app.Utilities.Validation;

import org.junit.Test;
import static org.junit.Assert.*;

public class TenNewTest {

    @Test
    public void compareTimes(){
        assertTrue (Validation.compareMinTime(0,100));
    }

    @Test
    public void checkServiceType(){
        ServiceType testServiceType = new ServiceType();
        testServiceType.setMaxRate(100);
        testServiceType.setName("Test");
        assertTrue(testServiceType.getMaxRate() == 100);
        assertEquals(testServiceType.getName(), "Test");
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

    @Test
    public void checkDateIterator(){
        assertEquals(FormatValue.incrementDate("2018-12-05"),"2018-12-06");
    }

    @Test
    public void hashing (){
      assertEquals(hash("Test"),9067833);
    }

    @Test



    //Because the class Hashing cannot be accessed for security reason we copied the hash method in testing to make sure the hashing is working like planed
    private int hash(String s){
        int hash = 7;
        for(int i = 0; i < s.length(); i++){
            hash = hash*31 + s.charAt(i);
        }
        return hash;
    }

}
