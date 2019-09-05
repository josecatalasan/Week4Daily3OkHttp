package com.example.week4daily3okhttp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.week4daily3okhttp.flickr.ItemsItem;

import java.util.List;

public class FlickrImagesAdapter extends RecyclerView.Adapter<FlickrImagesAdapter.ViewHolder> {

    List<ItemsItem> itemList;

    public FlickrImagesAdapter(List<ItemsItem> itemList) {
        this.itemList = itemList;
    }

    public void setItemList(List<ItemsItem> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.flickr_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemsItem currentItem = itemList.get(position);
        //TODO Format the dates
        holder.tvTaken.setText("Date Taken: " + currentItem.getDateTaken().substring(0,10));
        holder.tvPublished.setText("Date Published: " + currentItem.getPublished().substring(0,10));
        Glide.with(holder.itemView).load(currentItem.getMedia().getM()).circleCrop().into(holder.ivThumbnail);
        holder.tvURL.setText(currentItem.getMedia().getM());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        ImageView ivThumbnail;
        TextView tvTaken, tvPublished, tvURL;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            tvTaken = itemView.findViewById(R.id.tvTaken);
            tvPublished = itemView.findViewById(R.id.tvPublished);
            tvURL = itemView.findViewById(R.id.tvURL);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(final View view) {

            DialogInterface.OnClickListener confirmation = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int choice) {
                    switch(choice){
                        case DialogInterface.BUTTON_POSITIVE:
                            Intent intent = new Intent(view.getContext(), FullImageActivity.class);
                            TextView textView = view.findViewById(R.id.tvURL);
                            intent.putExtra("picture", textView.getText().toString());
                            view.getContext().startActivity(intent);
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                            //builder.setView()
                            break;
                    }

                }
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setMessage("What would you like to do?").setPositiveButton("See Full Image", confirmation)
                    .setNegativeButton("See Small Image",confirmation).show();
            return true;
        }
    }
}
