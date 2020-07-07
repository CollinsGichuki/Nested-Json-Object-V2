package com.snilloc.nestsedjsonobjectii.Network;

import com.snilloc.nestsedjsonobjectii.Model.ImagesResponse.Photo;
import com.snilloc.nestsedjsonobjectii.Model.UsersListResponse.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderAPI {
    @GET("users")
    Call<List<Users>> getUsers();

    @GET("photos")
    Call<List<Photo>> getPhotos();
}
