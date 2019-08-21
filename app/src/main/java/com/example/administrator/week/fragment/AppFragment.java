package com.example.administrator.week.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.week.AppManager;
import com.example.administrator.week.R;
import com.example.administrator.week.adapter.AppManagerAdapter;
import com.example.administrator.week.bean.AppBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/5/11 0011.
 */

public class AppFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    private TabLayout app_tab;
    private RecyclerView app_recy;
    private String[] mStrTitle={"已安装","系统应用","全部"};
    private List<AppBean> mlist=new ArrayList<>();
    private List<AppBean> mAlllist=new ArrayList<>();
    private int currentTab=0;
    private AppManagerAdapter mAppanagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        app_tab = (TabLayout) view.findViewById(R.id.app_tab);
        app_recy = (RecyclerView) view.findViewById(R.id.app_recy);
        mAppanagerAdapter=new AppManagerAdapter(getActivity(),mlist);
        app_recy.setLayoutManager(new LinearLayoutManager(getActivity()));
        app_recy.setAdapter(mAppanagerAdapter);
        mAlllist= AppManager.getInstance(getActivity()).getAllApp();
        for (int i=0;i<mStrTitle.length;i++)
        {
            app_tab.addTab(app_tab.newTab().setText(mStrTitle[i]));
        }
        app_tab.addOnTabSelectedListener(this);
    }
    public void onTabSelected(TabLayout.Tab tab)
    {
        parsingApp(tab.getPosition());
    }
    private void parsingApp(int currentTab)
    {
        this.currentTab=currentTab;
        if (mlist!=null)
        {
            if (mlist.size()>0)
            {
                mlist.clear();
            }
        }
        for (int i=0;i<mAlllist.size();i++)
        {
            AppBean appBean=mAlllist.get(i);
            switch (currentTab)
            {
                case 0:
                    if (!appBean.isSystem())
                    {
                        mlist.add(appBean);
                    }
                    break;
                case 1:
                    if (appBean.isSystem())
                    {
                        mlist.add(appBean);
                    }
                    break;
                case 2:
                    mlist.addAll(mAlllist);
                    break;
            }
        }
        mAppanagerAdapter.notifyDataSetChanged();
    }
    public void onTabUnselected(TabLayout.Tab tab )
    {

    }
    public void onTabReselected(TabLayout.Tab tab)
    {

    }
}
