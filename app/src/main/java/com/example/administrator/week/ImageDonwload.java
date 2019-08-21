package com.example.administrator.week;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2019/5/16 0016.
 */

public class ImageDonwload {
    private static String filePath;
    private static Bitmap mBitmap;
    private static Context context;
    private static String mSaveMessage;
    private static String mfileName="图片图片";
    public static void donwloadImage(Context contexts,String filePaths)
    {
        context=contexts;
        filePath=filePaths;
        new Thread(saveIamge).start();
    }
    private static Runnable saveIamge=new Runnable() {
        @Override
        public void run() {
            try {
                mBitmap= BitmapFactory.decodeStream(getImageStream(filePath));
                saveFile(mBitmap,mfileName);
                mSaveMessage="图片已保存到dcim";
            }catch (IOException e)
            {
                mSaveMessage="图片保存失败";
                e.printStackTrace();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            messageHandler.sendMessage(messageHandler.obtainMessage());
        }
    };
    private static Handler messageHandler=new Handler()
    {
        public void handleMessage(Message message)
        {
            Toast.makeText(context,mSaveMessage,Toast.LENGTH_SHORT).show();
        }
    };
    public static InputStream getImageStream(String path) throws Exception
    {
        URL url=new URL(path);
        HttpURLConnection connection=(HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setRequestMethod("GET");
        if (connection.getResponseCode()==HttpURLConnection.HTTP_OK)
        {
            return connection.getInputStream();
        }
        return null;
    }
    public static void saveFile(Bitmap bitmap,String filename)throws IOException
    {
        File dirFile=new File(Environment.getExternalStorageDirectory().getPath());
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myFile=new File(Environment.getExternalStorageDirectory().getPath()+ "/DCIM/",System.currentTimeMillis()+ ".jpg");
        BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(myFile));
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,bos);
        bos.flush();
        bos.close();
        Intent intent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri=Uri.fromFile(myFile);
        intent.setData(uri);
        context.sendBroadcast(intent);
    }
}
