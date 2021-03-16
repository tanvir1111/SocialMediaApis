package com.example.socialmediaApis.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.socialmediaApis.R;

public class VideoDetailViewHolder extends RecyclerView.ViewHolder {
   public TextView views,likes,title;
   public ImageView thumbnail;

    public VideoDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        views=itemView.findViewById(R.id.videoViews);
        likes=itemView.findViewById(R.id.videoLikes);
        title=itemView.findViewById(R.id.videoTitle);
        thumbnail=itemView.findViewById(R.id.videoThumbnail);


    }
}
