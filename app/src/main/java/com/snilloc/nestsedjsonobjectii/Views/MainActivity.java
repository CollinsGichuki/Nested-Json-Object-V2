package com.snilloc.nestsedjsonobjectii.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.snilloc.nestsedjsonobjectii.Pojos.Users;
import com.snilloc.nestsedjsonobjectii.R;
import com.snilloc.nestsedjsonobjectii.UserAdapter;
import com.snilloc.nestsedjsonobjectii.UsersListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String detailID = "MainActivity.ID";
    public static final String detailName = "MainActivity.NAME";
    public static final String detailUserName = "MainActivity.USERNAME";
    public static final String detailEmail = "MainActivity.EMAIL";
    public static final String detailStreet = "MainActivity.STREET";
    public static final String detailSuite = "MainActivity.SUITE";
    public static final String detailCity = "MainActivity.CITY";
    public static final String detailZipCode = "MainActivity.ZIP_CODE";
    public static final String detailLatitude = "MainActivity.LATITUDE";
    public static final String detailLongitude = "MainActivity.LONGITUDE";
    public static final String detailPhone = "MainActivity.PHONE";
    public static final String detailWebsite = "MainActivity.WEBSITE";
    public static final String detailCompanyName = "MainActivity.COMPANY_NAME";
    public static final String detailCatchPhrase = "MainActivity.CATCH_PHRASE";
    public static final String detailCompanyBs= "MainActivity.COMPANY_BS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsersListViewModel viewModel = new ViewModelProvider(this).get(UsersListViewModel.class);

        //RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Since the size of the list won't change, let's make the recyclerview more efficient
        recyclerView.setHasFixedSize(true);

        final UserAdapter adapter = new UserAdapter();
        recyclerView.setAdapter(adapter);

        //get the list
        viewModel.getUsersList().observe(this, new Observer<List<Users>>() {
            @Override
            public void onChanged(List<Users> users) {
                adapter.submitList(users);
            }
        });

        //Set onClickListener
        adapter.setItemClickListener(new UserAdapter.OnCardClickListener() {
            @Override
            public void onItemClick(Users users) {
                //Intent to start the Detail Activity
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                //Add the vales to be populated in the detail activity
                intent.putExtra(detailID, users.getId());
                intent.putExtra(detailName, users.getName());
                intent.putExtra(detailUserName, users.getUserName());
                intent.putExtra(detailEmail, users.getEmail());
                intent.putExtra(detailStreet, users.getAddress().getStreet());
                intent.putExtra(detailSuite, users.getAddress().getSuite());
                intent.putExtra(detailCity, users.getAddress().getCity());
                intent.putExtra(detailZipCode, users.getAddress().getZipCode());
                intent.putExtra(detailLatitude, users.getAddress().getGeo().getLatitude());
                intent.putExtra(detailLongitude, users.getAddress().getGeo().getLongitude());
                intent.putExtra(detailPhone, users.getPhone());
                intent.putExtra(detailWebsite, users.getWebsite());
                intent.putExtra(detailCompanyName, users.getCompany().getName());
                intent.putExtra(detailCatchPhrase, users.getCompany().getCatchPhrase());
                intent.putExtra(detailCompanyBs, users.getCompany().getBs());

                //Launch Detail Activity
                startActivity(intent);
            }
        });
    }
}