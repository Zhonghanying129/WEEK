package com.example.administrator.week.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2019/5/11 0011.
 */

public class AppBean {
    private Drawable icon;
    private String appName;
    private String appSize;
    private boolean isSystem=false;
    private String packageName;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public boolean isSystem() {
        return isSystem;
    }

    public void setSystem(boolean system) {
        isSystem = system;
    }
}
