package com.example.administrator.week.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.week.NewsDetailActivity;
import com.example.administrator.week.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2019/4/7 0007.
 */

public class SelectFragment extends Fragment {
    private ViewPager jingxuanvp;
    private List<View> mlist=new ArrayList<>();
    private MyjingxuanAdapter myjxAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selectfragment, container, false);
        initView(view);
        return view;
    }
    private void initView(View view) {
        jingxuanvp = (ViewPager) view.findViewById(R.id.jingxuanvp);
        myjxAdapter=new MyjingxuanAdapter();
        jingxuanvp.setAdapter(myjxAdapter);
        sendRequest();
    }
    private void sendRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://api.jisuapi.com/news/get?channel=头条&start=0&num=10&appkey=d93ad03619f83935";
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

    private void showReponse(final String response) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                   JSONArray jsonArray=jsonObject.getJSONObject("result").getJSONArray("list");
                  for (int i=0;i<jsonArray.length();i++)
                  {
                      String title=jsonArray.getJSONObject(i).getString("title");
                      String time=jsonArray.getJSONObject(i).getString("time");
                      String src=jsonArray.getJSONObject(i).getString("src");
                      String category=jsonArray.getJSONObject(i).getString("category");
                      String pic=jsonArray.getJSONObject(i).getString("pic");
                      final String weburl=jsonArray.getJSONObject(i).getString("weburl");
                      View view=View.inflate(getActivity(),R.layout.jingxuan,null);
                      TextView title_tv=(TextView)view.findViewById(R.id.jingxuan_title);
                      title_tv.setText(title);
                      TextView time_tv=(TextView)view.findViewById(R.id.jingxuan_time);
                      time_tv.setText(time);
                      TextView src_tv=(TextView)view.findViewById(R.id.jingxuan_src);
                      src_tv.setText(src);
                      TextView category_tv=(TextView)view.findViewById(R.id.jingxuan_category);
                      category_tv.setText(category);
                      ImageView pic_iv=(ImageView) view.findViewById(R.id.jingxuan_pic);
                      Glide.with(getActivity()).load(pic).into(pic_iv);//载入图片
                      pic_iv.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              Intent intent=new Intent(getActivity(), NewsDetailActivity.class);
                              intent.putExtra("weburl",weburl);
                              startActivity(intent);//获取到详情页面的地址，并通过意图传入到详情页面的。
                          }
                      });
                      mlist.add(view);
                      myjxAdapter.notifyDataSetChanged();
                  }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    class MyjingxuanAdapter extends PagerAdapter
    {

        @Override
        public int getCount() {
            return mlist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager)container).addView(mlist.get(position));
            return mlist.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView(mlist.get(position));

        }
    }
}
