package com.snilloc.nestsedjsonobjectii.Views;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.snilloc.nestsedjsonobjectii.Model.ImagesResponse.Photo;
import com.snilloc.nestsedjsonobjectii.Model.UsersListResponse.Users;
import com.snilloc.nestsedjsonobjectii.Network.JsonPlaceHolderAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersListViewModel extends ViewModel {

    //Get a list of the users
    private MutableLiveData<List<Users>> usersList;
    private LiveData<List<Users>> usersListData;

    public LiveData<List<Users>> getUsersList(){
        if (usersList == null){
            Log.d("Network Call", "usersList is null");
            usersList = new MutableLiveData<List<Users>>();
            loadUsersList();

            usersListData = usersList;
            Log.d("Network Call", "loadUsersList called");
        }
        return usersListData;
    }

    //Get a list of the urls
    private MutableLiveData<List<Photo>> photoUrls;
    private LiveData<List<Photo>> imageUrls;

    public LiveData<List<Photo>> getImageUrls(){
        if (photoUrls == null){
            Log.d("Network Call", "urlsList is null");
            photoUrls = new MutableLiveData<List<Photo>>();
        }
        loadUrls();
        Log.d("Network Call", "loadUrls called");

        imageUrls = photoUrls;
        return imageUrls;
    }
    //Build the Retrofit instance
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //Use the retrofit instance to create the method body of JsonPlaceHolderApi Interface
    private JsonPlaceHolderAPI placeHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

    public void loadUsersList() {
        Log.d("Network Call", "loadUsersList called");
        //Execute the Network request
        Call<List<Users>> call = placeHolderAPI.getUsers();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if (!response.isSuccessful()){
                    Log.d("Network Call", "Error Code: "+ response.code());
                    return;
                }

                if (!(response.body() == null)){
                    usersList.postValue(response.body());
                    Log.d("Network Call", "network call successful " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Log.d("Network Call", "failure "  + t.getMessage());
            }
        });
    }

    public void loadUrls(){
        Log.d("Network Call", "loadUrls called");
        Call<List<Photo>> call= placeHolderAPI.getPhotos();
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (!response.isSuccessful()){
                    Log.d("Network Call", "Error Code2: "+ response.code());
                    return;
                }
                if (!(response.body() == null)){
                    photoUrls.postValue(response.body());
                    Log.d("Network Call", "network call2 successful " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.d("Network Call", "failure2 "  + t.getMessage());
            }
        });
    }
}
