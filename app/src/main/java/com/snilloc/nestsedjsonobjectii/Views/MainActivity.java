package com.snilloc.nestsedjsonobjectii.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.snilloc.nestsedjsonobjectii.Pojos.Users;
import com.snilloc.nestsedjsonobjectii.R;
import com.snilloc.nestsedjsonobjectii.UserAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.name);

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
                Log.d("Network Call", "onChanged");
            }
        });
        //Set onClickListener
        adapter.setItemClickListener(new UserAdapter.OnCardClickListener() {
            @Override
            public void onItemClick(Users users) {
                Toast.makeText(MainActivity.this, users.getName() + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}