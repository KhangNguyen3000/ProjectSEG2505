package com.example.nguye.seg2505app.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.nguye.seg2505app.Booking;
import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.OfferedService;
import com.example.nguye.seg2505app.Storables.Rating;
import com.example.nguye.seg2505app.Storables.Storable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GetProviderInfos extends AppCompatActivity {
    private int id_provider;
    private Account provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_provider_infos);
        Bundle extras = getIntent().getExtras();
        id_provider = extras.getInt("providerID");
//        id_provider = (Integer) getIntent().getSerializableExtra("id_provider");
        provider = new Account();
        provider = provider.find(this, "ID", id_provider, false);


        // Getting the screen elements
        TextView p_Fname = findViewById(R.id.p_fName);
        TextView p_lName = findViewById(R.id.p_lName);
        TextView p_cName = findViewById(R.id.p_cName);
        TextView p_licensed = findViewById(R.id.p_licensed);
        TextView p_nStreet = findViewById(R.id.p_nStreet);
        TextView p_street = findViewById(R.id.p_street);
        TextView p_city = findViewById(R.id.p_city);
        TextView p_postalC = findViewById(R.id.p_postalC);
        TextView p_country = findViewById(R.id.p_country);
        TextView p_nPhone = findViewById(R.id.p_nPhone);
        TextView p_email = findViewById(R.id.p_email);
        TextView p_schedule = findViewById(R.id.p_schedule);
        TextView p_description = findViewById(R.id.p_description);
        TextView p_services = findViewById(R.id.p_services);
        RatingBar p_rating = findViewById(R.id.p_rating);

        // Getting provider informations
        ArrayList<OfferedService> servicesL = OfferedService.findAll(this, OfferedService.COL_PROVIDER, provider.getID(), false);
        ArrayList<String> servicesName = ServiceManagementProvider.getNameFromServices(servicesL, getApplicationContext());
        String services = "";
        for(int i = 0; i < servicesName.size(); i++){
            services += servicesName.get(i) + ", ";
        }

        float rating = 0;
        ArrayList <Rating> ratings = Rating.findAll(this, Rating.COL_PROVIDER_ID, id_provider, false);
        for(int i = 0; i < ratings.size(); i++){
            rating += ratings.get(i).getRating();
        }
        rating = rating / ratings.size();

        // Setting informations
        p_Fname.setText(provider.getFirstName());
        p_lName.setText(provider.getLastName());
        p_cName.setText(provider.getCompanyName());
        if(provider.getLicensed() == 1){
            p_licensed.setText("Licensed");
        }
        else {
            p_licensed.setText("");
        }
        p_nStreet.setText(provider.getStreetNumber()+", ");
        p_street.setText(provider.getStreetName()+", ");
        p_city.setText(provider.getCity());
        p_postalC.setText(provider.getPostalCode()+", ");
        p_country.setText(provider.getCountry());
        p_nPhone.setText(provider.getPhoneNumber());
        p_email.setText(provider.getEmail());
        p_schedule.setText("See availabilities");
        p_description.setText(provider.getDescription());
        p_services.setText(services);
        p_rating.setRating(rating);
    }

    public void onResume(){
        super.onResume();
        float rating = 0;
        ArrayList <Rating> ratings = Rating.findAll(this, Rating.COL_PROVIDER_ID, id_provider, false);
        for(int i = 0; i < ratings.size(); i++){
            rating += ratings.get(i).getRating();
        }
        rating = rating / ratings.size();
        RatingBar p_rating = findViewById(R.id.p_rating);
        p_rating.setRating(rating);
        Account provider = new Account();
        provider = provider.find(getApplicationContext(), Account.COL_ID, id_provider, false);
        System.out.println((int) rating);
        provider.setRating((int) rating);
    }
    // Launch Schedule Activity
    public void onClickSchedule(View view){
        Intent intent = new Intent(this, Booking.class);
        intent.putExtra("providerID", provider.getID());
        startActivity(intent);
    }

    // Launch Rating Activity
    public void onClickRating(View view){
        Intent intent = new Intent(this, RateScreen.class);
        intent.putExtra("id_provider", provider.getID());
        startActivity(intent);
    }


}
