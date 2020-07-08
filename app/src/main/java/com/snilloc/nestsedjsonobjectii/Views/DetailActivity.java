package com.snilloc.nestsedjsonobjectii.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.snilloc.nestsedjsonobjectii.R;

public class DetailActivity extends AppCompatActivity {
    //The Views
    TextView id;
    TextView name;
    TextView userName;
    TextView email;
    TextView street;
    TextView suite;
    TextView city;
    TextView zipCode;
    TextView latitude;
    TextView longitude;
    TextView phoneUmber;
    TextView website;
    TextView companyName;
    TextView catchPhrase;
    TextView companyBs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Get the intent that started this activity
        Intent intent = getIntent();

        //Instantiate the views
        id = findViewById(R.id.detail_id);
        name = findViewById(R.id.detail_name);
        userName = findViewById(R.id.detail_user_name);
        email = findViewById(R.id.detail_email);
        street = findViewById(R.id.detail_street);
        suite = findViewById(R.id.detail_suite);
        city= findViewById(R.id.detail_city);
        zipCode = findViewById(R.id.detail_zip_code);
        latitude = findViewById(R.id.detail_latitude);
        longitude = findViewById(R.id.detail_longitude);
        phoneUmber = findViewById(R.id.detail_phoneNumber);
        website = findViewById(R.id.detail_website);
        companyName = findViewById(R.id.detail_company_name);
        catchPhrase = findViewById(R.id.detail_catch_phrase);
        companyBs = findViewById(R.id.detail_company_bs);

        //Set the data
        setData(intent);
    }

    private void setData(Intent intent) {
        //Cast id to a String
        id.setText(String.valueOf(intent.getIntExtra(MainActivity.detailID, 0)));
        name.setText(intent.getStringExtra(MainActivity.detailName));
        userName.setText(intent.getStringExtra(MainActivity.detailUserName));
        email.setText(intent.getStringExtra(MainActivity.detailEmail));
        street.setText(intent.getStringExtra(MainActivity.detailStreet));
        suite.setText(intent.getStringExtra(MainActivity.detailSuite));
        city.setText(intent.getStringExtra(MainActivity.detailCity));
        zipCode.setText(intent.getStringExtra(MainActivity.detailZipCode));
        latitude.setText(intent.getStringExtra(MainActivity.detailLatitude));
        longitude.setText(intent.getStringExtra(MainActivity.detailLongitude));
        phoneUmber.setText(intent.getStringExtra(MainActivity.detailPhone));
        website.setText(intent.getStringExtra(MainActivity.detailWebsite));
        companyName.setText(intent.getStringExtra(MainActivity.detailCompanyName));
        catchPhrase.setText(intent.getStringExtra(MainActivity.detailCatchPhrase));
        companyBs.setText(intent.getStringExtra(MainActivity.detailCompanyBs));
    }
}