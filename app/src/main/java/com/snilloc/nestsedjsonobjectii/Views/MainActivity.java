package com.snilloc.nestsedjsonobjectii.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.snilloc.nestsedjsonobjectii.Model.ImagesResponse.Photo;
import com.snilloc.nestsedjsonobjectii.Model.UsersListResponse.Users;
import com.snilloc.nestsedjsonobjectii.R;
import com.snilloc.nestsedjsonobjectii.UserAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.name);
//        final ImageView imageView = findViewById(R.id.imageView);

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
//                for (int i = 0; i < users.size(); i++){
//                    textView.append(users.get(i).getName() + "\n");
//                }
            }
        });

        //Set onClickListener

//        viewModel.getImageUrls().observe(this, new Observer<List<Photo>>() {
//            @Override
//            public void onChanged(List<Photo> photos) {
//                Log.d("Network Call", "onChanged");
//                for (int i = 0; i < 10; i++){
//                    String[] urlList = new String[10];
//                    urlList[i] = photos.get(i).getUrl();
//
//                    Context context = getApplicationContext();
//                    Picasso.Builder picassoBuilder = new Picasso.Builder(context);
//                    picassoBuilder.downloader(new OkHttp3Downloader(context));
//                    picassoBuilder.build().load(urlList[i])
//                            .placeholder((R.drawable.ic_launcher_background))
//                            .error(R.drawable.ic_launcher_background)
//                            .into(imageView);
//                }
//            }
//        });
    }
}