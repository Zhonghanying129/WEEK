package com.example.administrator.week.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2019/4/30 0030.
 */

public class HttpUtil {
   public static void sendOkHttpRequest(String address,okhttp3.Callback callback)
   {
       OkHttpClient client=new OkHttpClient();
       Request request=new Request.Builder().url(address).build();
       client.newCall(request).enqueue(callback);
   }

}
