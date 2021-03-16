package com.example.socialmediaApis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialmediaApis.Adapters.VideoDetailAdapters;
import com.example.socialmediaApis.DataModels.VideoDetailsDataModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class YouTubeInfo extends AppCompatActivity {
    TextView subscribers,channelTitle,totalViews,totalVideos;
    ImageView channelThumbnail;
    RecyclerView videoListRecycler;
    ArrayList<String> videoIDs;
    ArrayList<VideoDetailsDataModel> videoDetailsList;
    public static String API_KEY="AIzaSyD9kSG4AvbxT0YDqC1ZypnJHZSA3G4HP4I";
    String channelID;
    public  String channelInfoUrl;
    public String videoListUrl;

    String title,subscriberCount,totalViewsCount,totalVideosCount,channelThumbnailUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_info);
        channelTitle=findViewById(R.id.channelTitle);
        subscribers=findViewById(R.id.subscribers);
        totalViews=findViewById(R.id.totalViews);
        totalVideos=findViewById(R.id.totalVideos);
        channelThumbnail=findViewById(R.id.channelThumbnail);
        videoListRecycler=findViewById(R.id.videosRecyclerView);
        channelID= getIntent().getStringExtra("channelID");
        channelInfoUrl="https://www.googleapis.com/youtube/v3/channels?part=snippet,statistics&id="+channelID+"&key="+API_KEY;
        videoListUrl="https://youtube.googleapis.com/youtube/v3/search?part=snippet&channelId="+channelID+"&maxResults=10&order=viewCount&key="+API_KEY;
        videoIDs=new ArrayList<>();
        videoDetailsList=new ArrayList<>();
        new GetChannelData().execute();


    }
    private class GetChannelData extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(YouTubeInfo.this, "getting Data", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler handler=new HttpHandler();
            String jsonStr=handler.makeServiceCall(channelInfoUrl,"youtube");
            Log.d("Response",""+jsonStr);

            if(jsonStr != null){
                try {
                    JSONObject jsonObject=new JSONObject(jsonStr);
                    JSONObject snippet=jsonObject.getJSONArray("items").getJSONObject(0).getJSONObject("snippet");
                    JSONObject statistics=jsonObject.getJSONArray("items").getJSONObject(0).getJSONObject("statistics");
                    title=snippet.getString("title");
                    subscriberCount=statistics.getString("subscriberCount");
                    totalViewsCount=statistics.getString("viewCount");
                    totalVideosCount=statistics.getString("videoCount");
                    channelThumbnailUrl=snippet.getJSONObject("thumbnails").getJSONObject("high").getString("url");
                }catch (Exception e){

                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            channelTitle.setText(title);
            subscribers.setText(subscriberCount);
            totalViews.setText(totalViewsCount);
            totalVideos.setText(totalVideosCount);
            Picasso.get().load(channelThumbnailUrl).into(channelThumbnail);
            new GetVideoIDList().execute();

        }
    }

    private class GetVideoIDList extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler handler=new HttpHandler();
            String jsonStr=handler.makeServiceCall(videoListUrl,"youtube");

            if(jsonStr != null){
                try {

                    JSONObject jsonObject=new JSONObject(jsonStr);
                    JSONArray itemsArray=jsonObject.getJSONArray("items");
                    for(int i=0;i<itemsArray.length();i++){
                        String videoID=itemsArray.getJSONObject(i).getJSONObject("id").getString("videoId");
                        videoIDs.add(videoID);
                    }

                }catch (Exception e){

                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new GetVideoData().execute();


        }
    }
    private class GetVideoData extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler handler=new HttpHandler();
            for (int i=0;i<videoIDs.size();i++) {
                String videoURL="https://www.googleapis.com/youtube/v3/videos?part=snippet,statistics&id="+videoIDs.get(i)+"&key="+API_KEY;
                String jsonStr = handler.makeServiceCall(videoURL,"youtube");

                if (jsonStr != null) {
                    try {

                        JSONObject jsonObject = new JSONObject(jsonStr);
                        JSONObject snippet=jsonObject.getJSONArray("items").getJSONObject(0).getJSONObject("snippet");
                        JSONObject statistics=jsonObject.getJSONArray("items").getJSONObject(0).getJSONObject("statistics");

                        String videoThumbnailUrl=snippet.getJSONObject("thumbnails").getJSONObject("high").getString("url");
                        String title=snippet.getString("title");
                        String viewCount=statistics.getString("viewCount");
                        String likeCount=statistics.getString("likeCount");

                        VideoDetailsDataModel videoDetail=new VideoDetailsDataModel(videoThumbnailUrl,title,viewCount,likeCount);

                        videoDetailsList.add(videoDetail);


                    } catch (Exception e) {

                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            videoListRecycler.setLayoutManager(new LinearLayoutManager(YouTubeInfo.this,RecyclerView.VERTICAL,false));
            videoListRecycler.setAdapter(new VideoDetailAdapters(getApplicationContext(),videoDetailsList));
           

        }
    }
}
