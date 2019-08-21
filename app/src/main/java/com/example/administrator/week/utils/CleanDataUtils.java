package com.example.administrator.week.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.example.administrator.week.Screen;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2019/6/12 0012.
 */

public class CleanDataUtils {
    public static String getTotalCacheSize(Context context)throws Exception
    {
        long cacheSize=getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            cacheSize+=getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }
    public static void clearAllCache(Context context)
    {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            deleteDir(context.getExternalCacheDir());
        }
    }

    /**
     * 清空缓存
     * @param dir
     * @return
     */
    private static boolean deleteDir(File dir)
    {
        if (dir!=null&&dir.isDirectory())
        {
            String[] children=dir.list();
            for (int i=0;i<children.length;i++)
            {
                boolean success=deleteDir(new File(dir,children[i]));
                if (!success)
                {
                    return false;
                }
            }
        }
        return dir.delete();
    }
    /**
     * 清除本应用内部缓存
     */
    public static void cleanInternalCache(Context context)
    {
        deleteFileByDirectory(context.getCacheDir());
    }
    //清空本应用所有数据库
    public static void cleanDatabases(Context context)
    {
        deleteFileByDirectory(new File("/data/data/"+context.getPackageName()+"/databases"));
    }
    //清空本应用sharedpreference
    public static void cleanSharedPreference(Context context)
    {
        deleteFileByDirectory(new File("data/data"+context.getPackageName()+"/shared_prefs"));
    }
    public static void cleanDatabaseByName(Context context,String dbName)
    {
        context.deleteDatabase(dbName);
    }
    //清除/data/data/com.xxx.xx/files下的内容
    public static void cleanFiles(Context context)
    {
        deleteFileByDirectory(context.getFilesDir());
    }
    //
    public static void cleanExternalCache(Context context)
    {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            deleteFileByDirectory(context.getExternalCacheDir());
        }
    }
    public static void cleanCustomCache(String filePath)
    {
        deleteFileByDirectory(new File(filePath));
    }
    public static void cleanApplicationData(Context context,String... filepath)
    {
        cleanInternalCache(context);
        cleanExternalCache(context);
        cleanDatabases(context);
        cleanSharedPreference(context);
        cleanFiles(context);
        if (filepath==null)
        {
            return;
        }
        for (String filePath:filepath)
        {
            cleanCustomCache(filePath);
        }
    }

    private static void deleteFileByDirectory(File directory)
    {
        if (directory!=null && directory.exists()&&directory.isDirectory())
        {
            for (File item:directory.listFiles())
            {
                item.delete();
            }
        }
    }
    public static long getFolderSize(File file)throws Exception
    {
        long size=0;
        try {
            File[] fileList=file.listFiles();
            for (int i=0;i<fileList.length;i++)
            {
                if (fileList[i].isDirectory())
                {
                    size=size+getFolderSize(fileList[i]);
                }
                else
                {
                    size=size+fileList[i].length();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return size;
    }
    public static void deleteFolderFile(String filePath,boolean deleteThisPath)
    {
        if (!TextUtils.isEmpty(filePath))
        {
            try {
                File file=new File(filePath);
                if (file.isDirectory())
                {
                    File files[]=file.listFiles();
                    for (int i=0;i<files.length;i++)
                    {
                        deleteFolderFile(files[i].getAbsolutePath(),true);
                    }
                }
                if (deleteThisPath)
                {
                    if (!file.isDirectory())
                    {
                        file.delete();
                    }
                    else
                    {
                        if (file.listFiles().length==0)
                        {
                            file.delete();
                        }
                    }
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

public static String getFormatSize(double size)
{
    double kiloByte=size/1024;
    LogUtils.d("megaByte size:"+kiloByte);
    double megaByte=kiloByte/1024;
    if (megaByte<1)
    {
        BigDecimal byBytel=new BigDecimal(Double.toString(kiloByte));
        return byBytel.setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString()+"KB";
    }
    double gigaM=megaByte/1024;
    LogUtils.d("gigaM size:"+gigaM);
    if (megaByte<1)
    {
        BigDecimal bgByte2=new BigDecimal(Double.toString(megaByte));
        return bgByte2.setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString()+"MB";
    }
    double tear=gigaM/1024;
    LogUtils.d("tear size:"+tear);
    BigDecimal bgByte3=new BigDecimal(Double.toString(gigaM));
    return bgByte3.setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString()+"GB";

}
public static String getCacheSize(File f) throws Exception
{
    return getFormatSize(getFolderSize(f));
}













}
