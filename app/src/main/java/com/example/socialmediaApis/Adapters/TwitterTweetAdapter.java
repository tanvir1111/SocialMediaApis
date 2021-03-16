package com.example.socialmediaApis.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmediaApis.DataModels.TwitterTweetDataModel;
import com.example.socialmediaApis.DataModels.VideoDetailsDataModel;
import com.example.socialmediaApis.R;
import com.example.socialmediaApis.ViewHolders.TwitterTweetViewHolder;
import com.example.socialmediaApis.ViewHolders.VideoDetailViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TwitterTweetAdapter extends RecyclerView.Adapter<TwitterTweetViewHolder> {
    Context context;
    ArrayList<TwitterTweetDataModel> twitterTweetDataModels;

    public TwitterTweetAdapter(Context context, ArrayList<TwitterTweetDataModel> twitterTweetDataModels) {
        this.context = context;
        this.twitterTweetDataModels = twitterTweetDataModels;
    }

    @NonNull
    @Override
    public TwitterTweetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.twitter_tweets_child,parent,false);
        TwitterTweetViewHolder twitterTweetViewHolder=new TwitterTweetViewHolder(view);
        return twitterTweetViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TwitterTweetViewHolder holder, int position) {

        holder.id.setText(twitterTweetDataModels.get(position).getId());
        holder.date.setText(twitterTweetDataModels.get(position).getDate());
        holder.tweet.setText(twitterTweetDataModels.get(position).getTweet());
        holder.source.setText(twitterTweetDataModels.get(position).getSource());
        holder.likes.setText(twitterTweetDataModels.get(position).getLikes());
        holder.retweets.setText(twitterTweetDataModels.get(position).getRetweets());
        holder.replies.setText(twitterTweetDataModels.get(position).getReplies());
        holder.quotes.setText(twitterTweetDataModels.get(position).getQuotes());
        holder.tweetImage.setImageResource(R.drawable.icon_awesome_twitter_square);
        holder.likeIcon.setImageResource(R.drawable.like);
        holder.retweetIcon.setImageResource(R.drawable.retweet);
        holder.replyIcon.setImageResource(R.drawable.reply);
        holder.quoteIcon.setImageResource(R.drawable.quote);

    }

    @Override
    public int getItemCount() {
        return twitterTweetDataModels.size();
    }
}
