package com.example.administrator.week;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.week.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {

    private ViewPager gvp;
    private List<View> mlist=new ArrayList<>();
    private View viewOne;
    private View viewTwo;
    private View viewThree;
    private int currentPage;
    private int startx=0;
    private int endx=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        ScreenUtils.init(this);
    }

    private void initView() {
        gvp = (ViewPager) findViewById(R.id.gvp);
        viewOne=View.inflate(this,R.layout.guide_one_view,null);
        viewTwo=View.inflate(this,R.layout.guide_two_view,null);
        viewThree=View.inflate(this,R.layout.guide_three_view,null);
        mlist.add(viewOne);
        mlist.add(viewTwo);
        mlist.add(viewThree);
        gvp.setAdapter(new MyGuideViewPagerAdapter());
        gvp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //获取具体动作
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                            startx=(int) event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                            endx=(int)event.getX();
                            if(currentPage==mlist.size()-1)
                            {
                                if(startx-endx>ScreenUtils.width/4)
                                {
                                    startActivity(new Intent(GuideActivity.this,MainActivity.class));
                                }
                            }
                        break;
                }
                return false;
            }
        });
        //监听视图的页面在第几页
        gvp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                    currentPage=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    class MyGuideViewPagerAdapter extends PagerAdapter
    {
        public int getCount()
        {
            return mlist.size();
        }
        public boolean isViewFromObject(View view,Object object)
        {
            return view==object;
        }
        public Object instantiateItem(ViewGroup container,int position)
        {
            ((ViewPager)container).addView(mlist.get(position));
            return mlist.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView(mlist.get(position));
        }
    }
}
