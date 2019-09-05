package com.example.week4daily3okhttp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
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

    private List<ItemsItem> itemList;

    protected FlickrImagesAdapter(List<ItemsItem> itemList) {
        this.itemList = itemList;
    }

    protected void setItemList(List<ItemsItem> itemList) {
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

        private ViewHolder(@NonNull View itemView) {
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
                            LayoutInflater factory = LayoutInflater.from(view.getContext());
                            @SuppressLint("InflateParams") final View dialogView = factory.inflate(R.layout.dialog_image, null);
                            TextView url = view.findViewById(R.id.tvURL);
                            ImageView image = dialogView.findViewById(R.id.ivDialogImage);
                            Glide.with(dialogView).load(url.getText().toString()).into(image);
                            builder.setView(dialogView).show();
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
