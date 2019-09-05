package com.example.week4daily3okhttp.okhttp.events;

import com.example.week4daily3okhttp.flickr.FlickrResponse;

public class FlickrResponseEvent {
    FlickrResponse flickrResponse;

    public FlickrResponse getFlickrResponse() {
        return flickrResponse;
    }

    public void setFlickrResponse(FlickrResponse flickrResponse) {
        this.flickrResponse = flickrResponse;
    }

    public FlickrResponseEvent(FlickrResponse flickrResponse) {
        this.flickrResponse = flickrResponse;
    }
}
