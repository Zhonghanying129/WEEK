package com.example.administrator.week.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.week.MianActivity;
import com.example.administrator.week.R;
import com.example.administrator.week.Screen;

/**
 * Created by Administrator on 2019/4/6 0006.
 */

public class MainFragment extends Fragment {
    private BottomNavigationView btmNv;
    private SelectFragment selectFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainfragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        btmNv = (BottomNavigationView) view.findViewById(R.id.btmNv);
        btmNv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.first)
                {
                   Intent intent=new Intent(getActivity(), Screen.class);
                    startActivity(intent);
                }
                if(item.getItemId()==R.id.space)
                {
                    FragmentManager fmm=getActivity().getSupportFragmentManager();
                    FragmentTransaction fb=fmm.beginTransaction();
                    fb.replace(R.id.main_layout,new MusicFragment());
                    fb.commit();
                }

                return true;
            }

        });
    }
}
