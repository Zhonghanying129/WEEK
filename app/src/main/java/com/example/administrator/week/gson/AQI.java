package com.example.administrator.week.gson;

/**
 * Created by Administrator on 2019/4/30 0030.
 */

public class AQI {
    public AQICity city;
    public class AQICity
    {
        public String api;
        public String pm25;
    }
}
