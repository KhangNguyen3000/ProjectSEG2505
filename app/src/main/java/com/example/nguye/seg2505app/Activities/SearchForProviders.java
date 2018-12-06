package com.example.nguye.seg2505app.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.ResourceCursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nguye.seg2505app.MyDBHandler;
import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.DefaultSchedule;
import com.example.nguye.seg2505app.Storables.OfferedService;
import com.example.nguye.seg2505app.Storables.Rating;
import com.example.nguye.seg2505app.Storables.ServiceType;
import com.example.nguye.seg2505app.Storables.Storable;
import com.example.nguye.seg2505app.Utilities.DateTimePicker;
import com.example.nguye.seg2505app.Utilities.FormatValue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SearchForProviders extends AppCompatActivity {

    RadioButton rbName;
    RadioButton rbService;
    RadioButton rbRating;
    RadioButton rbDate;
    EditText editName;
    Spinner serviceSpinner;
    RatingBar editRating;
    EditText editDate;
    EditText editBetween;
    EditText editAnd;
    String searchQuery = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_providers);

        rbName = findViewById(R.id.radioBtnName);
        rbService = findViewById(R.id.radioBtnService);
        rbRating = findViewById(R.id.radioBtnRating);
        rbDate = findViewById(R.id.radioBtnDate);
        editName = findViewById(R.id.srch_input_name);
        serviceSpinner = findViewById(R.id.srch_dd_service);
        editRating = findViewById(R.id.ratingBar);
        editDate = findViewById(R.id.srch_input_date);
        editBetween = findViewById(R.id.srch_input_between);
        editAnd = findViewById(R.id.srch_input_and);

        // Spinner (drop-down) creation
        MyDBHandler dbHandler = new MyDBHandler(this);
        SQLiteDatabase db = dbHandler.getReadableDatabase();

//        String serviceQuery = "SELECT * FROM " + this.getTableName()
//                + " WHERE " + fieldName + " = " + quotes + value + quotes;
        String serviceQuery = "SELECT " + ServiceType.COL_NAME
                + " FROM " + ServiceType.TABLE_NAME
                + " ORDER BY " + ServiceType.COL_NAME;
        System.out.println(serviceQuery);

        // Get all the service types from the table ServiceTypes
        ArrayList<String[]> serviceTypes = Storable.select(this, serviceQuery, 1);
        // Put them in an array of String
        String[] serviceOptions = new String[serviceTypes.size()];
        for (int i = 0; i < serviceTypes.size(); i++) {
            serviceOptions[i] = serviceTypes.get(i)[0];
        }
        // Set the service options using the array
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, serviceOptions);
        serviceSpinner.setAdapter(adapter);

//        Cursor cursor = db.rawQuery(serviceQuery, null);
//
//        CursorAdapter serviceAdapter = new SimpleCursorAdapter(this,
//                android.R.layout.simple_list_item_1,
//                cursor,
//                new String[] {ServiceType.COL_NAME},
//                new int[] {R.id.srch_dd_service},
//                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
//        // TODO test the drop-down list
//        // TODO sort the services in alphabetical order



//        serviceSpinner.setAdapter(serviceAdapter);
    }




    public void showDatePicker(View view) {
        DateTimePicker.showDatePicker(view);
    }

    public void showTimePicker(View view) {
        DateTimePicker.showTimePicker(view);
    }

//    public void search(View view) {
//        boolean isCheckedName = rbName.isChecked();
//        boolean isCheckedService = rbService.isChecked();
//        boolean isCheckedRating = rbRating.isChecked();
//        boolean isCheckedDate = rbDate.isChecked();
//
//        String query = "";
//        String queryName = "";
//        String queryService = "";
//        String queryRating = "";
//        String queryDate = "";
//
//        if (isCheckedName) {
//            // TODO works if the firstName and lastName are joined
//            String name = editName.getText().toString();
//            queryName = "SELECT " + Account.COL_ID
//                    + " FROM " + Account.TABLE_NAME
//                    + " WHERE " + Account.COL_FIRSTNAME + " || " + Account.COL_LASTNAME
//                    + " = \"" + name + "\""
//                    + " AND " + Account.COL_TYPE + " = 2";
//            System.out.println(queryName);
//        }
//        if (isCheckedService) {
//            String service = serviceSpinner.getSelectedItem().toString();
////            queryService = "SELECT OS." + OfferedService.COL_PROVIDER
////                    + " FROM " + OfferedService.TABLE_NAME + " OS"
////                    + " INNER JOIN " + ServiceType.TABLE_NAME + " ST"
////                    + " ON OS." + OfferedService.COL_TYPE
////                    + " WHERE " + OfferedService.COL_TYPE
////                    + " IN (SELECT " + ServiceType.COL_ID
////                        + " FROM " + ServiceType.TABLE_NAME
////                        + " WHERE " + ServiceType.COL_NAME + " = \"" + service + "\")";
//            queryService = "SELECT " + OfferedService.COL_PROVIDER
//                    + " FROM " + OfferedService.TABLE_NAME
//                    + " WHERE " + OfferedService.COL_TYPE
//                    + " IN (SELECT " + ServiceType.COL_ID
//                    + " FROM " + ServiceType.TABLE_NAME
//                    + " WHERE " + ServiceType.COL_NAME + " = \"" + service + "\")";
//            System.out.println(queryService);
//        }
//        if (isCheckedRating) {
//            float rating = editRating.getRating();
//            queryRating = "SELECT " + Account.COL_ID
//                    + " FROM " + Account.TABLE_NAME
//                    + " WHERE (" + Account.COL_RATING + " >= " + rating
//                    + ") OR (" + Account.COL_RATING + " = 0)";
//            // when searching by rating, the providers with no rating are displayed regardless of the specified criterion.
//            System.out.println(queryRating);
//        }
//        if (isCheckedDate && !(isCheckedName || isCheckedService || isCheckedRating)) {
//            // if only the date criterion is specified, get the providers' IDs directly from the table DefaultSchedules
//            String dateValue = editDate.getText().toString();
//            String between = editBetween.getText().toString();
//            String and = editAnd.getText().toString();
//
//            String[] weekdayFields = DefaultSchedule.getWeekdayField(dateValue);
//
//            // Select the records where the provider is available during the specified timeslot,
//            //  but only check the effective record (according to the effective date)
//            queryDate = "SELECT " + DefaultSchedule.COL_PROVIDER
//                    + " FROM " + DefaultSchedule.TABLE_NAME
//                    + " WHERE " + weekdayFields[0] + " <= " + FormatValue.timeStringToMin(between)
//                    + " AND " + weekdayFields[1] + " >= " + FormatValue.timeStringToMin(and)
//                    + " AND " + DefaultSchedule.COL_EFFECTIVEDATE
//                    + " = (SELECT MAX(" + DefaultSchedule.COL_EFFECTIVEDATE
//                    + ") FROM " + DefaultSchedule.TABLE_NAME
//                    + " WHERE " + DefaultSchedule.COL_EFFECTIVEDATE + " <= \"" + dateValue + "\")";
//            System.out.println(queryDate);
//        }
//
//        // TODO use INNER JOIN instead of UNION
//        // If a name, service and/or rating criteria have been specified, get a list of the
//        //  providers' IDs corresponding to those criteria
//        ArrayList<String[]> providerIDs;
//        if (isCheckedName || isCheckedService || isCheckedRating) {
//            if (isCheckedName) {
//                query += queryName;
//                if (isCheckedService) {
//                    query += " INNER JOIN (" + queryService
//                            + ") ON " + Account.TABLE_NAME + "." + Account.COL_ID
//                            + " = " + OfferedService.TABLE_NAME + "." + OfferedService.COL_PROVIDER;
//                }
//                if (isCheckedRating) {
//                    query += " INNER JOIN (" + queryRating
//                            + ") ON " + Account.TABLE_NAME + "." + Account.COL_ID
//                            + " = " + Rating.TABLE_NAME + "." + Rating.COL_PROVIDER_ID;
//                }
//            } else {
//                if (isCheckedService) {
//                    query += queryService;
//                    if (isCheckedRating) {
//                        query += " INNER JOIN (" + queryRating
//                                + ") ON " + OfferedService.TABLE_NAME + "." + OfferedService.COL_ID
//                                + " = " + Rating.TABLE_NAME + "." + Rating.COL_PROVIDER_ID;
//                    }
//                } else {
//                    query += queryRating; // has to be true
//                }
//            }
//            if (isCheckedDate) {
//                query = queryDate + " AND " + DefaultSchedule.COL_PROVIDER + " IN " + query;
//            }
//        } else if (isCheckedDate) {
//            query = queryDate;
//        } else {
//            Toast toast = Toast.makeText(this, "You must specify at least one search criterion.", Toast.LENGTH_SHORT);
//            toast.show();
//            return;
//        }
//
//        providerIDs = Storable.select(this, query, 1);
//        if (providerIDs.size() > 0) {
//            int[] pIDs = new int[providerIDs.size()];
//            int i = 0;
//            for (String[] record : providerIDs) {
//                pIDs[i] = Integer.parseInt(record[0]);
//                i++;
//            }
//            Intent intent = new Intent(this, SearchResults.class);
//            // Convert the ArrayList to a simple int[]
//            intent.putExtra("providers", pIDs);
//            startActivity(intent);
//        } else {
//            Toast toast = Toast.makeText(this, "No provider match your criteria.", Toast.LENGTH_LONG);
//            toast.show();
//        }
//        // TODO break down in smaller functions
//    }

    /**
     * Generate a query to search by name
     * @return
     */
    public String searchByName() {
        // TODO works if the firstName and lastName are joined
        String name = editName.getText().toString();
        String query = "SELECT " + Account.COL_ID
                + " FROM " + Account.TABLE_NAME
                + " WHERE " + Account.COL_FIRSTNAME + " || " + Account.COL_LASTNAME
                + " = \"" + name.replaceAll("\\s+","") + "\""
                + " AND " + Account.COL_TYPE + " = 2";
        System.out.println(query);
        return query;
    }

    /**
     * Generate a query to search by service
     * @return
     */
    public String searchByService() {
        String service = serviceSpinner.getSelectedItem().toString();
        String query = "SELECT " + OfferedService.COL_PROVIDER
                + " FROM " + OfferedService.TABLE_NAME
                + " WHERE " + OfferedService.COL_TYPE
                + " IN (SELECT " + ServiceType.COL_ID
                + " FROM " + ServiceType.TABLE_NAME
                + " WHERE " + ServiceType.COL_NAME + " = \"" + service + "\")";
        System.out.println(query);
        return query;
    }

    /**
     * Generate a query to search by rating
     * @return
     */
    public String searchByRating() {
        float rating = editRating.getRating();
        String query = "SELECT " + Account.COL_ID
                + " FROM " + Account.TABLE_NAME
                + " WHERE (" + Account.COL_RATING + " >= " + rating
                + ") OR (" + Account.COL_RATING + " = 0)";
        // when searching by rating, the providers with no rating are displayed regardless of the specified criterion.
        System.out.println(query);
        return query;
    }

    /**
     * Generate a query to search by date
     * @return
     */
    public String searchByDate() {
        String dateValue = editDate.getText().toString();
        String between = editBetween.getText().toString();
        String and = editAnd.getText().toString();

        String[] weekdayFields = DefaultSchedule.getWeekdayField(dateValue);

        // Select the records where the provider is available during the specified timeslot,
        //  but only check the effective record (according to the effective date)
        String query = "SELECT " + DefaultSchedule.COL_PROVIDER
                + " FROM " + DefaultSchedule.TABLE_NAME
                + " WHERE " + weekdayFields[0] + " <= " + FormatValue.timeStringToMin(between)
                + " AND " + weekdayFields[1] + " >= " + FormatValue.timeStringToMin(and)
                + " AND " + DefaultSchedule.COL_EFFECTIVEDATE
                + " = (SELECT MAX(" + DefaultSchedule.COL_EFFECTIVEDATE
                + ") FROM " + DefaultSchedule.TABLE_NAME
                + " WHERE " + DefaultSchedule.COL_EFFECTIVEDATE + " <= \"" + dateValue + "\")";
        System.out.println(query);
        return query;
    }

//    // From https://developer.android.com/guide/topics/ui/controls/radiobutton#java
//    public void onRadioButtonClicked(View view) {
//        // Is the button now checked?
//        boolean checked = ((RadioButton) view).isChecked();
//
//        // Check which radio button was clicked
//        switch(view.getId()) {
//            case R.id.radioBtnName:
//                if (checked)
//                    searchQuery = searchByName();
//                    break;
//            case R.id.radioBtnService:
//                if (checked)
//                    searchQuery = searchByService();
//                    break;
//            case R.id.radioBtnRating:
//                if (checked)
//                    searchQuery = searchByRating();
//                    break;
//            case R.id.radioBtnDate:
//                if (checked)
//                    searchQuery = searchByDate();
//                    break;
//        }
//    }

    // TODO add validation
    // TODO fix the rating bar
    public void onClickSearch(View view) {
        rbName = findViewById(R.id.radioBtnName);
        rbService = findViewById(R.id.radioBtnService);
        rbRating = findViewById(R.id.radioBtnRating);
        rbDate = findViewById(R.id.radioBtnDate);
        Toast errorMessage;

        if (rbName.isChecked()) {
            searchQuery = searchByName();
        } else if (rbService.isChecked()) {
            searchQuery = searchByService();
        } else if (rbRating.isChecked()) {
            searchQuery = searchByRating();
        } else if (rbDate.isChecked()) {
            searchQuery = searchByDate();
        } else {
            errorMessage = Toast.makeText(this, "You must specify a search criteria.", Toast.LENGTH_LONG);
            errorMessage.show();
        }

        ArrayList<String[]> providerIDs;
        if (!searchQuery.equals("")) {
            // Find the ID of all the providers that correspond to the specified criterion
            providerIDs = Storable.select(this, searchQuery, 1);
            if (providerIDs.size() > 0) {
                int[] pIDs = new int[providerIDs.size()];
                int i = 0;
                for (String[] record : providerIDs) {
                    pIDs[i] = Integer.parseInt(record[0]);
                    i++;
                }
                Intent intent = new Intent(this, SearchResults.class);
                // Convert the ArrayList to a simple int[]
                intent.putExtra("providers", pIDs);
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(this, "No provider match your criteria.", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    // TODO disable/enable the fields when the checkboxes are toggled

}
