package com.example.nguye.seg2505app;


import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.Storable;

import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    TestDataBase data = new TestDataBase();
    Context context = new Activity();

    @Test
    public void sadasd() {
        ArrayList<String> array = Storable.selectAllInColumn(context, Account.COL_ID, Account.TABLE_NAME, "FirstName = \"admin\"");
        for (String record : array) {
            System.out.println(record);
        }
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


}