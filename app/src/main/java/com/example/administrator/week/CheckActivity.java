package com.example.administrator.week;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.week.fragment.AppFragment;
import com.example.administrator.week.fragment.MovieFragment;
import com.example.administrator.week.fragment.MusicFragment;

public class CheckActivity extends AppCompatActivity implements View.OnClickListener{

    private AppFragment settingFragment;
    private MovieFragment movieFragment;
    private MusicFragment musicFragment;
    private RelativeLayout firstlayout;
    private RelativeLayout secondlayout;
    private RelativeLayout thirdlayout;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        fragmentManager=getSupportFragmentManager();
        initView();
        firstlayout.setOnClickListener(CheckActivity.this);
        secondlayout.setOnClickListener(CheckActivity.this);
        thirdlayout.setOnClickListener(CheckActivity.this);
    }

    private void initView() {
        firstlayout = (RelativeLayout) findViewById(R.id.screen_layout);
        secondlayout = (RelativeLayout) findViewById(R.id.app_layout);
        thirdlayout = (RelativeLayout) findViewById(R.id.laji_layout);
    }
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.screen_layout:
                setchoiceItem(0);
                break;
            case R.id.app_layout:
                setchoiceItem(1);
                break;
            case R.id.laji_layout:
                Intent intent=new Intent(CheckActivity.this,CleanActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    private void setchoiceItem(int index)
    {
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);
        switch (index)
        {
            case 0:
                if (movieFragment==null)
                {
                    Intent intent=new Intent(CheckActivity.this,Screen.class);
                    startActivity(intent);
                }
                else
                {
                    fragmentTransaction.show(movieFragment);
                }
                break;
            case 1:
                if (settingFragment==null)
                {
                    settingFragment=new AppFragment();
                    fragmentTransaction.add(R.id.check_content,settingFragment);
                }
                else
                {
                    fragmentTransaction.show(settingFragment);
                }
                break;
            case 2:
//                    if (musicFragment==null)
//                    {
//                       musicFragment=new MusicFragment();
//                        fragmentTransaction.add(R.id.check_content,musicFragment);
//                    }
//                    else
//                    {
//                        fragmentTransaction.show(musicFragment);
//                    }
//                break;
        }
        fragmentTransaction.commit();
    }
    private void hideFragments(FragmentTransaction fragmentTransaction)
    {
        if (movieFragment!=null)
        {
            fragmentTransaction.hide(movieFragment);
        }
        if (settingFragment!=null)
        {
            fragmentTransaction.hide(settingFragment);
        }
        if (musicFragment!=null)
        {
            fragmentTransaction.hide(musicFragment);
        }
    }
}
