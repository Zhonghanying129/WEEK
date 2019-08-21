package com.example.administrator.week;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsDetailActivity extends AppCompatActivity {

    private WebView newsdetail_wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        initView();
        Intent intent = getIntent();
        String weburl = intent.getStringExtra("weburl");
        newsdetail_wv.getSettings().setJavaScriptEnabled(true);
        newsdetail_wv.setWebViewClient(new WebViewClient());
        newsdetail_wv.loadUrl(weburl);//得到精选页面传过来的网址，并显示详情。
    }
    private void initView() {
        newsdetail_wv = (WebView) findViewById(R.id.newsdetail_wv);
    }
}
