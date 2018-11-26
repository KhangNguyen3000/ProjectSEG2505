package com.example.nguye.seg2505app;

import android.support.test.rule.ActivityTestRule;

import com.example.nguye.seg2505app.Activities.MainActivity;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.Storable;
import com.example.nguye.seg2505app.Utilities.App;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class VariousTests {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void openToTestingActivity() {
        onView(withId(R.id.openTestActivity)).perform(click());
    }

    @Test
    public void Storable_select() {
        ArrayList<String> list = Storable.selectAllInColumn(App.getContext(), Account.COL_FIRSTNAME, Account.TABLE_NAME, "ID >= 1");
        for (String record : list) {
            System.out.println(record);
        }
        System.out.println("afhap[sfhasflksfl;sjflsfjaslfjsda;lfkjsdlfas;fakjf;af");
    }
}
