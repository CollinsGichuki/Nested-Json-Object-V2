package com.snilloc.nestsedjsonobjectii.Model.ImagesResponse;

import com.google.gson.annotations.SerializedName;

public class Photo {
    String title;

    @SerializedName("thumbnailUrl")
    String url;

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
