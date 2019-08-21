package com.example.administrator.week;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieMoreActivity extends AppCompatActivity {

    private ImageView movie_more_iv;
    private TextView movie_more_title;
    private TextView movie_more_average;
    private TextView movie_more_actor;
    private TextView movie_more_type;
    private TextView movie_more_summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_more);
        initView();
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Log.d("MovieMoreActivity",id);
        String url = "https://douban.uieee.com/v2/movie/subject/"+ id;
        sendRequest(url);
    }

    private void sendRequest(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    String responsedata = response.body().string();
                    showReponse(responsedata);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showReponse(final String responseData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject1 = new JSONObject(responseData);
                    String title = jsonObject1.getString("title");
                    Log.d("MovieMoreActivity",title);
                    String average = jsonObject1.getJSONObject("rating").getString("average");
                    Log.d("MovieMoreActivity",average);
                    String type = jsonObject1.getString("genres");
                    Log.d("MovieMoreActivity",type);
                    String summary = jsonObject1.getString("summary");
                    String iv=jsonObject1.getJSONObject("images").getString("small");
                    Glide.with(MovieMoreActivity.this).load(iv).into(movie_more_iv);
                    //movie_more_actor.setText(actor);
                    movie_more_average.setText(average);
                    movie_more_summary.setText(summary);
                    movie_more_title.setText(title);
                    movie_more_type.setText(type);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void initView() {
        movie_more_iv = (ImageView) findViewById(R.id.movie_more_iv);
        movie_more_title = (TextView) findViewById(R.id.movie_more_title);
        movie_more_average = (TextView) findViewById(R.id.movie_more_average);
        //movie_more_actor = (TextView) findViewById(R.id.movie_more_actor);
        movie_more_type = (TextView) findViewById(R.id.movie_more_type);
        movie_more_summary = (TextView) findViewById(R.id.movie_more_summary);
    }
}
