package com.example.youtubeapi.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeapi.DataModels.VideoDetailsDataModel;
import com.example.youtubeapi.R;
import com.example.youtubeapi.ViewHolders.VideoDetailViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VideoDetailAdapters extends RecyclerView.Adapter<VideoDetailViewHolder> {
    Context context;
    ArrayList<VideoDetailsDataModel> videoDetailsDataModels;

    public VideoDetailAdapters( Context context, ArrayList<VideoDetailsDataModel> videoDetailsDataModels) {
        this.context = context;
        this.videoDetailsDataModels = videoDetailsDataModels;
    }

    @NonNull
    @Override
    public VideoDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.video_details_child,parent,false);
        VideoDetailViewHolder videoDetailViewHolder=new VideoDetailViewHolder(view);
        return videoDetailViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoDetailViewHolder holder, int position) {

        holder.title.setText(videoDetailsDataModels.get(position).getTitle());
        holder.views.setText(videoDetailsDataModels.get(position).getViews());
        holder.likes.setText(videoDetailsDataModels.get(position).getLikes());
        Picasso.get().load(videoDetailsDataModels.get(position).getThumbURL()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return videoDetailsDataModels.size();
    }
}
