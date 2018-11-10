package com.example.nguye.seg2505app;

import org.junit.Test;


import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    TestDataBase data = new TestDataBase();

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void hasAdmin(){

        assertEquals( data.getDatabase().existsType(1),true);
    }

    /*@Test
    public void hasUnoAdmin(){



    }*/



    @Test
    public void validUsers(){
        List<String> users =data.getDatabase().getList("Email","Accounts");
        for (String elem: users){
            assertTrue(Validation.validEmailString(elem));
        }
    }


}