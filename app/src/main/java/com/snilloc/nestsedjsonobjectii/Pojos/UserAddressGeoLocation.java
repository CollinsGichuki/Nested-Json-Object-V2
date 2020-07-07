package com.snilloc.nestsedjsonobjectii.Pojos;


import com.google.gson.annotations.SerializedName;

public class UserAddressGeoLocation {
    @SerializedName("lat")
    private String latitude;

    @SerializedName("lng")
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
