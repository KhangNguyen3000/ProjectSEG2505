package com.example.nguye.seg2505app;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.CustomSchedule;
import com.example.nguye.seg2505app.Storables.ServiceType;
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

}
