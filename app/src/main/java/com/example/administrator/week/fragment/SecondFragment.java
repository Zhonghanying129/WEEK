package com.example.administrator.week.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.week.R;
import com.example.administrator.week.adapter.MusicAdapter;
import com.example.administrator.week.bean.MusicBean;

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

public class SecondFragment extends Fragment {
    private RecyclerView musicRecy;
    private List<MusicBean> mlist=new ArrayList<>();
    MusicAdapter musicAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.music, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        musicRecy = (RecyclerView) view.findViewById(R.id.musicRecy);
        musicRecy.setLayoutManager(new GridLayoutManager(getActivity(),2));
        musicAdapter =new MusicAdapter(getActivity(),mlist);
        musicRecy.setAdapter(musicAdapter);
        sendRequest();
    }
    private void sendRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "https://c.y.qq.com/v8/fcg-bin/fcg_v8_toplist_cp.fcg?g_tk=5381&uin=0&format=json&inCharset=utf-8&outCharset=utf-8¬ice=0&platform=h5&needNewCode=1&tpl=3&page=detail&type=top&topid=36&_=1520777874472";
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
                    JSONArray jsonArray=jsonObject1.getJSONArray("songlist");
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        int albumid=jsonArray.getJSONObject(i).getJSONObject("data").getInt("albumid");
                        String picUrl="http://imgcache.qq.com/music/photo/album_300/"+(albumid%100)+"/300_albumpic_"+albumid+"_0.jpg";
                        String singerName=jsonArray.getJSONObject(i).getJSONObject("data").getJSONArray("singer").getJSONObject(0).getString("name");
                        Log.d("singerName",singerName);
                        String songname=jsonArray.getJSONObject(i).getJSONObject("data").getString("songname");
                        Log.d("songname",songname);
                        MusicBean musicBean=new MusicBean();
                        musicBean.setSinggerName(singerName);
                        musicBean.setSongname(songname);
                        musicBean.setSongPicUrl(picUrl);
                        mlist.add(musicBean);
                        musicAdapter.notifyDataSetChanged();
                    }

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        });



    }
}
