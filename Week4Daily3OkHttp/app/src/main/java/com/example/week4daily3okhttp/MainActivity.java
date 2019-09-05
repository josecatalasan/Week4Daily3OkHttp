package com.example.week4daily3okhttp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;

import com.example.week4daily3okhttp.flickr.ItemsItem;
import com.example.week4daily3okhttp.okhttp.FlickrAsyncTask;
import com.example.week4daily3okhttp.okhttp.events.FlickrResponseEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvFlickr;
    private FlickrImagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvFlickr = findViewById(R.id.rvFlickr);

        //Start the asyncTask
        FlickrAsyncTask flickrAsyncTask = new FlickrAsyncTask();
        flickrAsyncTask.execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void populateRV(List<ItemsItem> itemList){
        if(adapter == null){
            adapter = new FlickrImagesAdapter(itemList);
            rvFlickr.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
            rvFlickr.setAdapter(adapter);
            SnapHelper helper = new LinearSnapHelper();
            helper.attachToRecyclerView(rvFlickr);
        } else {
            adapter.setItemList(itemList);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFlickrEvent(FlickrResponseEvent flickrResponseEvent){
        if(flickrResponseEvent.getFlickrResponse() != null){
            List<ItemsItem>itemList = flickrResponseEvent.getFlickrResponse().getItems();
            populateRV(itemList);
        }
    }
}
