package com.example.administrator.week;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.example.administrator.week.bean.AppBean;
import com.example.administrator.week.utils.LogUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/5/11 0011.
 */

public class AppManager {
    private static AppManager manager=null;
    private Context mcontext;
    private PackageManager pm;
    private List<PackageInfo> mListAllApp;
    private AppManager(Context mcontext)
    {
        this.mcontext=mcontext;
        initAppManager();
    }
    private void initAppManager()
    {
        pm=mcontext.getPackageManager();
        mListAllApp=pm.getInstalledPackages(0);
    }
    public static AppManager getInstance(Context mcontext)
    {
        if (manager==null)
        {
            synchronized (AppManager.class)
            {
                if (manager==null)
                {
                    manager=new AppManager(mcontext);
                }
            }
        }
        return manager;
    }
    //获取系统安装的所有应用
    public List<AppBean> getAllApp()
    {
        List<AppBean> mlist=new ArrayList<>();
        //遍历
        for (PackageInfo info:mListAllApp)
        {
            ApplicationInfo applicationInfo=info.applicationInfo;
            AppBean appBean=new AppBean();
            appBean.setIcon(applicationInfo.loadIcon(pm));
            appBean.setAppName(pm.getApplicationLabel(applicationInfo).toString());
            appBean.setPackageName(applicationInfo.packageName);
            File file=new File(applicationInfo.sourceDir);
            appBean.setAppSize(formatData(file));
            //
            int flag=applicationInfo.flags;
            if ((flag&ApplicationInfo.FLAG_SYSTEM)!=0)
            {
                appBean.setSystem(true);
            }
            mlist.add(appBean);
        }
        return mlist;
    }
        private String formatData(File file)
        {
            float fileSize=file.length()/1024/1024;
            DecimalFormat df=new DecimalFormat("0.00");
            if (fileSize>1024)
            {
                float sizes=fileSize/1024;
                return df.format(sizes)+"G";
            }
            else
            {
                return df.format(fileSize)+"MB";
            }
        }
        public String getAppVersion(String packageName)
        {
            try {
                PackageInfo info=pm.getPackageInfo(packageName,0);
                return "版本号:"+info.versionName;
            }
            catch (PackageManager.NameNotFoundException e)
            {
                return "版本号：获取失败";
            }
        }
        public Drawable getAppIcon(String packageName)
        {
            try {
                PackageInfo info=pm.getPackageInfo(packageName,0);
                Drawable drawable=info.applicationInfo.loadIcon(pm);
                return drawable;
            }catch (PackageManager.NameNotFoundException e)
            {
                e.printStackTrace();
            }
            return null;
        }
        public String[] getAppPermissions(String packageName)
        {
            try {
                PackageInfo info=pm.getPackageInfo(packageName,PackageManager.GET_PERMISSIONS);
                String[] permissions=info.requestedPermissions;
                return permissions;
            }catch (PackageManager.NameNotFoundException e)
            {
                LogUtils.e(e.toString());
            }
            return null;
        }
        public void uninstallApp(String packageName)
        {
            Intent intent=new Intent(Intent.ACTION_DELETE);
            Uri uri=Uri.parse("package:"+packageName);
            intent.setData(uri);
            mcontext.startActivity(intent);
        }


}
