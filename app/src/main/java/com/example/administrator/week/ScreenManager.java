package com.example.administrator.week;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Administrator on 2019/6/9 0009.
 */

public class ScreenManager {
    private Context mcontext;
    private  static ScreenManager mInStance=null;
    private WindowManager wm;
    private DisplayMetrics dm;
    private ScreenManager(Context mcontext)
    {
        this.mcontext=mcontext;
        wm=(WindowManager)mcontext.getSystemService(Context.WINDOW_SERVICE);
        dm=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
    }
    public static ScreenManager getmInStance(Context mcontext)
    {
        if (mInStance==null)
        {
            synchronized (ScreenManager.class)
            {
                if (mInStance==null)
                {
                    mInStance=new ScreenManager(mcontext);
                }
            }
        }
        return mInStance;
    }
    public int getW()
    {
        return dm.widthPixels;
    }
    public int getH()
    {
        return dm.heightPixels;
    }
}
