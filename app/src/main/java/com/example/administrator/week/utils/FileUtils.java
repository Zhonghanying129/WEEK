package com.example.administrator.week.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2019/6/9 0009.
 */

public class FileUtils {
    //内存大小0：总大小1：剩余大小 默认是B
    public static long getSDSize(int index)
    {
        File file= Environment.getExternalStorageDirectory();
        //计算一个文件的大小
        StatFs statFs=new StatFs(file.getPath());
        //每一块内存大小
        long tota=statFs.getBlockSize();
        if (index==0)
        {
            //总共存储的数量
            long sizeAll=statFs.getBlockCount();
            return tota*sizeAll;
        }
        else if (index==1)
        {
            long avail=statFs.getAvailableBlocks();
            return tota*avail;
        }
        return 0;
    }
//已用运存大小1：总 0：已用
    public static long getAvailMemory(Context mcontext,int index)
    {
        ActivityManager am=(ActivityManager) mcontext.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi=new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        if (index==0)
        {
            return mi.availMem;
        }
        else if (index==1)
        {
            return mi.totalMem;
        }
        return 0;
    }
    //获取内核版本
    public static String getLinuxVersion()
    {
        java.lang.Process p=null;
        String version="";
        try {
            p=Runtime.getRuntime().exec("cat /proc/version");
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        InputStream is=p.getInputStream();
        InputStreamReader isr=new InputStreamReader(is);
        BufferedReader br=new BufferedReader(isr);
        String result="";
        String line;
        try {
            while ((line=br.readLine())!=null)
            {
                result+=line;
            }
        }
        catch (IOException e)
        {
            LogUtils.e("e"+e.toString());
        }
        LogUtils.e("result:"+result);
        try {
            if (!TextUtils.isEmpty(result))
            {
                version=result.substring(0,21);
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            LogUtils.e("e"+e.toString());
        }
        return version;
    }
}
