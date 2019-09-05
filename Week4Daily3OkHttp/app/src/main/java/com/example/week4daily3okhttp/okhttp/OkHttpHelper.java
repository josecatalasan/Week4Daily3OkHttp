package com.example.week4daily3okhttp.okhttp;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpHelper {
    public static final String API_CALL = "http://api.flickr.com/services/feeds/photos_public.gne?tag=kitten&format=json&nojsoncallback=1";

    public OkHttpClient getClient(){
        return new OkHttpClient.Builder().build();
    }

    public String executeSyncOkHttpRequest() throws IOException {
        Request request = new Request.Builder().url(API_CALL).build();

        Response response = getClient().newCall(request).execute();
        return response.body().string();
    }
}
