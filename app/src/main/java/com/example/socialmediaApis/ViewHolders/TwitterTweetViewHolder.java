package com.example.socialmediaApis.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmediaApis.R;

public class TwitterTweetViewHolder extends RecyclerView.ViewHolder {
   public TextView id,date,tweet,source,likes,retweets,replies,quotes;
   public ImageView likeIcon, retweetIcon, replyIcon, quoteIcon, tweetImage;

    public TwitterTweetViewHolder(@NonNull View itemView) {
        super(itemView);

        id = itemView.findViewById(R.id.child_tweetID);
        date = itemView.findViewById(R.id.child_tweetDate);
        tweet = itemView.findViewById(R.id.child_tweet);
        source = itemView.findViewById(R.id.child_tweetSource);
        likes = itemView.findViewById(R.id.child_tweetLikes);
        retweets = itemView.findViewById(R.id.child_tweetRetweets);
        replies = itemView.findViewById(R.id.child_tweetReplies);
        quotes = itemView.findViewById(R.id.child_tweetQuotes);
        likeIcon = itemView.findViewById(R.id.child_tweetLikeIcon);
        retweetIcon = itemView.findViewById(R.id.child_tweetRetweetIcon);
        replyIcon = itemView.findViewById(R.id.child_tweetReplyIcon);
        quoteIcon = itemView.findViewById(R.id.child_tweetQuoteIcon);
        tweetImage = itemView.findViewById(R.id.child_tweetIcon);



    }
}
