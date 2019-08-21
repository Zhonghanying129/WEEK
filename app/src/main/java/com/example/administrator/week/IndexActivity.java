package com.example.administrator.week;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.week.utils.SharedPreUtils;
import com.example.administrator.week.utils.VersionUtils;

public class IndexActivity extends AppCompatActivity {
    private static final String TAG="IndexActivity";
    private Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 1:
                    if(isFirstRunApp())
                    {
                        startActivity(new Intent(IndexActivity.this,MainActivity.class));
                    }
                    else
                    {
                        startActivity(new Intent(IndexActivity.this,GuideActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initView();
    }
    private void requestPermissions()
    {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE,Manifest.permission.READ_CALENDAR,Manifest.permission.CAMERA},100);
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case 100:
            {
                mhandler.sendEmptyMessageDelayed(1,2000);
            }
            return;
        }
    }
    private boolean isFirstRunApp()
    {
        boolean isFirst= SharedPreUtils.getBoolean(this,"isFirst",false);
        if(isFirst)
        {
            return true;
        }
        else
        {
            SharedPreUtils.putBoolean(this,"isFirst",true);
            return false;
        }
    }
    public void onBackPressed()
    {

    }
    private void initView()
    {
        if(VersionUtils.isJELLY_BEAN_MR1())
        {
            requestPermissions();
        }
        else
        {
            mhandler.sendEmptyMessageDelayed(1,5000);
        }
    }
}
