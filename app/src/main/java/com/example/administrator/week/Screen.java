package com.example.administrator.week;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Screen extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_screen_bg;
    private int index=0;
    private int[] mColor={Color.RED,Color.GREEN,Color.BLUE,Color.BLACK,Color.WHITE,Color.GRAY};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        initView();
    }
    private void initView() {
        tv_screen_bg = (TextView) findViewById(R.id.tv_screen_bg);
        tv_screen_bg.setOnClickListener(this);
        tv_screen_bg.setBackgroundColor(mColor[index]);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_screen_bg:
                index++;
                if (index>=mColor.length){
                    Toast.makeText(this,"即将退出",Toast.LENGTH_SHORT);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();

                        }
                    },1500);
                }else {
                    if (mColor[index]==Color.WHITE){
                        tv_screen_bg.setTextColor(Color.BLACK);
                    }else {
                        tv_screen_bg.setTextColor(Color.WHITE);
                    }
                    tv_screen_bg.setBackgroundColor(mColor[index]);
                }
        }
    }

}
