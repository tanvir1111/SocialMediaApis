package com.example.socialmediaApis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmediaApis.Adapters.TwitterTweetAdapter;
import com.example.socialmediaApis.Adapters.VideoDetailAdapters;
import com.example.socialmediaApis.DataModels.TwitterTweetDataModel;
import com.example.socialmediaApis.DataModels.VideoDetailsDataModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TwitterInfo extends AppCompatActivity {

    ImageView twitterImageView;
    TextView twitterPageNameView, twitterFollowersView, twitterFollowingView, twitterStatusView, twitterLocationView, twitterDescriptionView;
    String twitterImageUrl, twitterPageName, twitterFollowers, twitterFollowing, twitterStatus, twitterLocation;

    RecyclerView recyclerView;
    ArrayList<TwitterTweetDataModel> twitterTweetDataModels;

    public String twitterUrl = "https://api.twitter.com/1.1/users/show.json?screen_name=mkbhd";
    public String tweetUrl = "https://api.twitter.com/2/users/29873662/tweets?max_results=100&tweet.fields=attachments,author_id,context_annotations,conversation_id,created_at,entities,geo,id,in_reply_to_user_id,lang,public_metrics,possibly_sensitive,referenced_tweets,reply_settings,source,text,withheld&expansions=attachments.media_keys,referenced_tweets.id,author_id&media.fields=duration_ms,height,media_key,preview_image_url,public_metrics,type,url,width";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_info);

        twitterImageView = findViewById(R.id.twitterIcon);
        twitterPageNameView = findViewById(R.id.twitterScreenName);
        twitterFollowersView = findViewById(R.id.twitterFollowers);
        twitterFollowingView = findViewById(R.id.twitterFollowing);
        twitterStatusView = findViewById(R.id.twitterStatus);
        twitterLocationView = findViewById(R.id.twitterLocation);
        twitterDescriptionView = findViewById(R.id.twitterDescription);
        recyclerView = findViewById(R.id.twitterTweets);

        twitterTweetDataModels = new ArrayList<>();

        new tweetData().execute();
        new pageData().execute();



    }

    private class pageData extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Twitter Page
            HttpHandler handler=new HttpHandler();
            String jsonStr = handler.makeServiceCall(twitterUrl,"twitter");

            if(jsonStr != null){

                try {
                    JSONObject jsonObject=new JSONObject(jsonStr);
                    Log.d("Response","Page Name: "+jsonObject.getString("screen_name"));

                    twitterPageName=jsonObject.getString("screen_name");
                    twitterFollowers=jsonObject.getString("followers_count");
                    twitterFollowing=jsonObject.getString("friends_count");
                    twitterStatus=jsonObject.getString("statuses_count");
                    twitterImageUrl=jsonObject.getString("profile_image_url");
                    twitterLocation=jsonObject.getString("location");
                    twitterDescriptionView.setText(jsonObject.getString("description"));
                    twitterImageUrl = twitterImageUrl.replaceAll("_normal","");


                }catch (Exception e) {

                }

                Log.d("Response",twitterImageUrl);
                twitterPageNameView.setText(twitterPageName);
                twitterFollowersView.setText(twitterFollowers);
                twitterFollowingView.setText(twitterFollowing);
                twitterStatusView.setText(twitterStatus);
                twitterLocationView.setText(twitterLocation);

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            new ImageDownloader(twitterImageView).execute(twitterImageUrl);



        }
    }

    private class tweetData extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {


            //Tweets
            HttpHandler handler=new HttpHandler();
            String jsonStr = handler.makeServiceCall(tweetUrl,"twitter");

            if(jsonStr != null){

                try {
                    JSONObject responseData=new JSONObject(jsonStr);

                    String id="",date="",tweet="",source="",likes="",retweets="",replies="",quotes="";

                    for(int i=0;i<responseData.getJSONArray("data").length();i++) {
                        JSONObject data = responseData.getJSONArray("data").getJSONObject(i);
                        id = data.getString("id");
                        date = data.getString("created_at");
                        tweet = data.getString("text");
                        source = data.getString("source");
                        JSONObject publicMetrics = data.getJSONObject("public_metrics");
                        likes = publicMetrics.getString("like_count");
                        retweets = publicMetrics.getString("retweet_count");
                        replies = publicMetrics.getString("reply_count");
                        quotes = publicMetrics.getString("quote_count");

                        TwitterTweetDataModel twitterTweetDataModel = new TwitterTweetDataModel(id,date,tweet,source,likes,retweets,replies,quotes);
                        twitterTweetDataModels.add(twitterTweetDataModel);

                    }



                }catch (Exception e) {

                }
            }

//            HttpUrl.Builder urlBuilder = HttpUrl.parse(tweetUrl).newBuilder();
//            String url = urlBuilder.build().toString();
//
//            Request request = new Request.Builder()
//                    .url(url)
//                    .addHeader("Authorization", "Bearer AAAAAAAAAAAAAAAAAAAAAE5cNgEAAAAABrBpiLV5aXk1nFMCl3i5Ro8ptsA%3D9KHayZbLfOHVakpFPV1nxwt4U3JfVY6vNo5opretW6frHpchjU")
//                    .build();
//
//            OkHttpClient client = new OkHttpClient();
//
//            client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    e.printStackTrace();
//                }
//
//                @Override
//                public void onResponse(Call call, final Response response) throws IOException {
//                    if (!response.isSuccessful()) {
//                        throw new IOException("Unexpected code " + response);
//                    } else {
//
//                        String res = response.body().string();
//                        try {
//                            JSONObject responseData=new JSONObject(res);
//                            //Log.d("Response",""+responseData);
//
//
//                            String id="",date="",tweet="",source="",likes="",retweets="",replies="",quotes="";
//
//                            for(int i=0;i<100;i++) {
//                                JSONObject data = responseData.getJSONArray("data").getJSONObject(i);
//                                id = data.getString("id");
//                                date = data.getString("created_at");
//                                tweet = data.getString("text");
//                                source = data.getString("source");
//                                JSONObject publicMetrics = data.getJSONObject("public_metrics");
//                                likes = publicMetrics.getString("like_count");
//                                retweets = publicMetrics.getString("retweet_count");
//                                replies = publicMetrics.getString("reply_count");
//                                quotes = publicMetrics.getString("quote_count");
//
//                                TwitterTweetDataModel twitterTweetDataModel = new TwitterTweetDataModel(id,date,tweet,source,likes,retweets,replies,quotes);
//                                twitterTweetDataModels.add(twitterTweetDataModel);
//
//                            }
//
//
//
//                        }catch (Exception e) {
//
//                        }
//                    }
//
//                }
//            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Log.d("Response","DataModelSize: "+twitterTweetDataModels.size());


            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
            recyclerView.setAdapter(new TwitterTweetAdapter(getApplicationContext(),twitterTweetDataModels));


        }
    }

    private class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        ImageView imageView;

        public ImageDownloader(ImageView imageView){
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String...urls) {

            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){
                e.printStackTrace();
            }
            return logo;
        }

        protected void onPostExecute(Bitmap resultBitmap) {
            twitterImageView.setImageBitmap(resultBitmap);


        }
    }
}
