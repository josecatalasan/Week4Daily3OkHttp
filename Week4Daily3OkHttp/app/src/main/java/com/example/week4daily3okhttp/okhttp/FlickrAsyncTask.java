package com.example.week4daily3okhttp.okhttp;

import android.os.AsyncTask;

import com.example.week4daily3okhttp.flickr.FlickrResponse;
import com.example.week4daily3okhttp.okhttp.events.FlickrResponseEvent;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

public class FlickrAsyncTask extends AsyncTask<Void, String, FlickrResponse> {

    @Override
    protected FlickrResponse doInBackground(Void... voids) {
        OkHttpHelper okHttpHelper = new OkHttpHelper();
        String json;
        try{
            json = okHttpHelper.executeSyncOkHttpRequest();
            Gson gson = new Gson();
            FlickrResponse flickrResponse = gson.fromJson(json, FlickrResponse.class);
            return flickrResponse;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(FlickrResponse flickrResponse) {
        super.onPostExecute(flickrResponse);
        EventBus.getDefault().post(new FlickrResponseEvent(flickrResponse));
    }
}
