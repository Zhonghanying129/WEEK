package com.example.administrator.week;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2019/5/16 0016.
 */

public class CategroyActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView bi_cate;
    private Button button_all;
    private Button button_mingxing;
    private Button button_meinv;
    private Button button_qiche;
    private LinearLayout bizhi_1;
    private Button button_fengjing;
    private Button button_keai;
    private Button button_weimei;
    private Button button_dongman;
    private LinearLayout bizhi_2;
    private Button button_aiqing;
    private Button button_youxi;
    private Button button_yingshi;
    private Button button_dongwu;
    private LinearLayout bizhi_3;
    private Button button_zhiwu;
    private Button button_tiyu;
    private Button button_meishi;
    private Button button_chuangyi;
    private LinearLayout bizhi_4;
    private TextView color_cate;
    private Button button_yellow;
    private Button button_orange;
    private Button button_red;
    private Button button_pink;
    private Button button_zise;
    private LinearLayout bizhi_color;
    private Button button_green;
    private Button button_bule;
    private Button button_huise;
    private Button button_black;
    private Button button_white;
    private RelativeLayout picture_categroy;
    private String cate;
    private String color;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_categroy);
        initView();
    }

    private void initView() {
        bi_cate = (TextView) findViewById(R.id.bi_cate);
        button_all = (Button) findViewById(R.id.button_all);
        button_mingxing = (Button) findViewById(R.id.button_mingxing);
        button_meinv = (Button) findViewById(R.id.button_meinv);
        button_qiche = (Button) findViewById(R.id.button_qiche);
        bizhi_1 = (LinearLayout) findViewById(R.id.bizhi_1);
        button_fengjing = (Button) findViewById(R.id.button_fengjing);
        button_keai = (Button) findViewById(R.id.button_keai);
        button_weimei = (Button) findViewById(R.id.button_weimei);
        button_dongman = (Button) findViewById(R.id.button_dongman);
        bizhi_2 = (LinearLayout) findViewById(R.id.bizhi_2);
        button_aiqing = (Button) findViewById(R.id.button_aiqing);
        button_youxi = (Button) findViewById(R.id.button_youxi);
        button_yingshi = (Button) findViewById(R.id.button_yingshi);
        button_dongwu = (Button) findViewById(R.id.button_dongwu);
        bizhi_3 = (LinearLayout) findViewById(R.id.bizhi_3);
        button_zhiwu = (Button) findViewById(R.id.button_zhiwu);
        button_tiyu = (Button) findViewById(R.id.button_tiyu);
        button_meishi = (Button) findViewById(R.id.button_meishi);
        button_chuangyi = (Button) findViewById(R.id.button_chuangyi);
        bizhi_4 = (LinearLayout) findViewById(R.id.bizhi_4);
        color_cate = (TextView) findViewById(R.id.color_cate);
        button_yellow = (Button) findViewById(R.id.button_yellow);
        button_orange = (Button) findViewById(R.id.button_orange);
        button_red = (Button) findViewById(R.id.button_red);
        button_pink = (Button) findViewById(R.id.button_pink);
        button_zise = (Button) findViewById(R.id.button_zise);
        bizhi_color = (LinearLayout) findViewById(R.id.bizhi_color);
        button_green = (Button) findViewById(R.id.button_green);
        button_bule = (Button) findViewById(R.id.button_bule);
        button_huise = (Button) findViewById(R.id.button_huise);
        button_black = (Button) findViewById(R.id.button_black);
        button_white = (Button) findViewById(R.id.button_white);
        picture_categroy = (RelativeLayout) findViewById(R.id.picture_categroy);

        button_all.setOnClickListener(this);
        button_mingxing.setOnClickListener(this);
        button_meinv.setOnClickListener(this);
        button_qiche.setOnClickListener(this);
        button_fengjing.setOnClickListener(this);
        button_keai.setOnClickListener(this);
        button_weimei.setOnClickListener(this);
        button_dongman.setOnClickListener(this);
        button_aiqing.setOnClickListener(this);
        button_youxi.setOnClickListener(this);
        button_yingshi.setOnClickListener(this);
        button_dongwu.setOnClickListener(this);
        button_zhiwu.setOnClickListener(this);
        button_tiyu.setOnClickListener(this);
        button_meishi.setOnClickListener(this);
        button_chuangyi.setOnClickListener(this);
        button_yellow.setOnClickListener(this);
        button_orange.setOnClickListener(this);
        button_red.setOnClickListener(this);
        button_pink.setOnClickListener(this);
        button_zise.setOnClickListener(this);
        button_green.setOnClickListener(this);
        button_bule.setOnClickListener(this);
        button_huise.setOnClickListener(this);
        button_black.setOnClickListener(this);
        button_white.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_all:
                cate=String.valueOf(0);
                Intent intent=new Intent();
                intent.putExtra("CATE",cate);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.button_mingxing:
                cate=String.valueOf(2338);
                Intent intent1=new Intent();
                intent1.putExtra("CATE",cate);
                setResult(RESULT_OK,intent1);
                finish();
                break;
            case R.id.button_meinv:
                cate=String.valueOf(2340);
                Intent intent2=new Intent();
                intent2.putExtra("CATE",cate);
                setResult(RESULT_OK,intent2);
                finish();
                break;
            case R.id.button_qiche:
                cate=String.valueOf(2342);
                Intent intent3=new Intent();
                intent3.putExtra("CATE",cate);
                setResult(RESULT_OK,intent3);
                finish();
                break;
            case R.id.button_fengjing:
                cate=String.valueOf(2341);
                Intent intent4=new Intent();
                intent4.putExtra("CATE",cate);
                setResult(RESULT_OK,intent4);
                finish();
                break;
            case R.id.button_keai:
                cate=String.valueOf(2343);
                Intent intent5=new Intent();
                intent5.putExtra("CATE",cate);
                setResult(RESULT_OK,intent5);
                finish();
                break;
            case R.id.button_weimei:
                cate=String.valueOf(2344);
                Intent intent6=new Intent();
                intent6.putExtra("CATE",cate);
                setResult(RESULT_OK,intent6);
                finish();
                break;
            case R.id.button_dongman:
                cate=String.valueOf(2346);
                Intent intent7=new Intent();
                intent7.putExtra("CATE",cate);
                setResult(RESULT_OK,intent7);
                finish();
                break;
            case R.id.button_aiqing:
                cate=String.valueOf(2347);
                Intent intent8=new Intent();
                intent8.putExtra("CATE",cate);
                setResult(RESULT_OK,intent8);
                finish();
                break;
            case R.id.button_youxi:
                cate=String.valueOf(2353);
                Intent intent9=new Intent();
                intent9.putExtra("CATE",cate);
                setResult(RESULT_OK,intent9);
                finish();
                break;
            case R.id.button_yingshi:
                cate=String.valueOf(2354);
                Intent intent10=new Intent();
                intent10.putExtra("CATE",cate);
                setResult(RESULT_OK,intent10);
                finish();
                break;
            case R.id.button_dongwu:
                cate=String.valueOf(2355);
                Intent intent11=new Intent();
                intent11.putExtra("CATE",cate);
                setResult(RESULT_OK,intent11);
                finish();
                break;
            case R.id.button_zhiwu:
                cate=String.valueOf(2356);
                Intent intent12=new Intent();
                intent12.putExtra("CATE",cate);
                setResult(RESULT_OK,intent12);
                finish();
                break;
            case R.id.button_tiyu:
                cate=String.valueOf(2360);
                Intent intent13=new Intent();
                intent13.putExtra("CATE",cate);
                setResult(RESULT_OK,intent13);
                finish();
                break;
            case R.id.button_meishi:
                cate=String.valueOf(2362);
                Intent intent14=new Intent();
                intent14.putExtra("CATE",cate);
                setResult(RESULT_OK,intent14);
                finish();
                break;
            case R.id.button_chuangyi:
                cate=String.valueOf(2352);
                Intent intent15=new Intent();
                intent15.putExtra("CATE",cate);
                setResult(RESULT_OK,intent15);
                finish();
                break;
            case R.id.button_yellow:
                color=String.valueOf(1);
                Intent intent16=new Intent();
                intent16.putExtra("CATE",color);
                setResult(RESULT_OK,intent16);
                finish();
                break;
            case R.id.button_orange:
                color=String.valueOf(2);
                Intent intent17=new Intent();
                intent17.putExtra("CATE",color);
                setResult(RESULT_OK,intent17);
                finish();
                break;
            case R.id.button_red:
                color=String.valueOf(3);
                Intent intent18=new Intent();
                intent18.putExtra("CATE",color);
                setResult(RESULT_OK,intent18);
                finish();
                break;
            case R.id.button_pink:
                color=String.valueOf(4);
                Intent intent19=new Intent();
                intent19.putExtra("CATE",color);
                setResult(RESULT_OK,intent19);
                finish();
                break;
            case R.id.button_zise:
                color=String.valueOf(5);
                Intent intent20=new Intent();
                intent20.putExtra("CATE",color);
                setResult(RESULT_OK,intent20);
                finish();
                break;
            case R.id.button_green:
                color=String.valueOf(6);
                Intent intent21=new Intent();
                intent21.putExtra("CATE",color);
                setResult(RESULT_OK,intent21);
                finish();
                break;
            case R.id.button_bule:
                color=String.valueOf(7);
                Intent intent22=new Intent();
                intent22.putExtra("CATE",color);
                setResult(RESULT_OK,intent22);
                finish();
                break;
            case R.id.button_huise:
                color=String.valueOf(8);
                Intent intent23=new Intent();
                intent23.putExtra("CATE",color);
                setResult(RESULT_OK,intent23);
                finish();
                break;
            case R.id.button_black:
                color=String.valueOf(9);
                Intent intent24=new Intent();
                intent24.putExtra("CATE",color);
                setResult(RESULT_OK,intent24);
                finish();
                break;
            case R.id.button_white:
                color=String.valueOf(11);
                Intent intent25=new Intent();
                intent25.putExtra("CATE",color);
                setResult(RESULT_OK,intent25);
                finish();
                break;
        }
    }
}
