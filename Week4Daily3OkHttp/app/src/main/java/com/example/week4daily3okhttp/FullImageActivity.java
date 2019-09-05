package com.example.week4daily3okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FullImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        ImageView ivFullImage = findViewById(R.id.ivFullImage);
        Glide.with(ivFullImage).load(getIntent().getStringExtra("picture")).into(ivFullImage);
    }
}
