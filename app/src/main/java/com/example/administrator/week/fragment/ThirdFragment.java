package com.example.administrator.week.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.week.R;
import com.example.administrator.week.adapter.MovieAdapter;
import com.example.administrator.week.bean.MovieBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2019/3/30 0030.
 */

public class ThirdFragment extends Fragment {
    private RecyclerView movieRecy;
    private List<MovieBean> mlist = new ArrayList<>();
    MovieAdapter movieAdapter;
    private RecyclerView movie;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        movie = (RecyclerView) view.findViewById(R.id.movie);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        movie.setLayoutManager(layoutManager);
        movieAdapter=new MovieAdapter(getActivity(),mlist);
        movie.setAdapter(movieAdapter);
        sendRequest();
    }
    private void sendRequest()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "https://douban.uieee.com/v2/movie/in_theaters?city=绵阳&start=0&count=10";
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
    private void showReponse(final String responseData)
    {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject1=new JSONObject(responseData);
                    JSONArray jsonArray=jsonObject1.getJSONArray("subjects");
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        String average= jsonArray.getJSONObject(i).getJSONObject("rating").getString("average");
                        String type=jsonArray.getJSONObject(i).getString("genres");
                        String title=jsonArray.getJSONObject(i).getString("title");
                        String id=jsonArray.getJSONObject(i).getString("id");
                        String actor=jsonArray.getJSONObject(i).getJSONArray("casts").getJSONObject(0).getString("name");
                        String moviePicUrl=jsonArray.getJSONObject(i).getJSONObject("images").getString("small");
                        MovieBean movieBean=new MovieBean();
                        movieBean.setActor(actor);
                        movieBean.setTitle(title);
                        movieBean.setId(id);
                        movieBean.setAverager(average);
                        movieBean.setType(type);
                        movieBean.setMoviePicUrl(moviePicUrl);
                        mlist.add(movieBean);
                        movieAdapter.notifyDataSetChanged();
                    }
                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
