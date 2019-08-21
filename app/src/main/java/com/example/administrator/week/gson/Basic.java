package com.example.administrator.week.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2019/4/30 0030.
 */

public class Basic {
    @SerializedName("city")
    public String cityName;
    @SerializedName("id")
    public String weatherId;
    public Update update;
    public class Update
    {
        @SerializedName("loc")
        public String updateTime;
    }
}
