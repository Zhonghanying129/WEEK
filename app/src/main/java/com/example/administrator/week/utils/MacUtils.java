package com.example.administrator.week.utils;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

/**
 * Created by Administrator on 2019/6/9 0009.
 */

public class MacUtils {
    public static String getMac(Context mcontext)
    {
        WifiManager wifiManager=(WifiManager)mcontext.getSystemService(Context.WIFI_SERVICE);
        String mac=wifiManager.getConnectionInfo().getMacAddress();
        if (!TextUtils.isEmpty(mac))
        {
            return mac;
        }
        return "";
    }
}
