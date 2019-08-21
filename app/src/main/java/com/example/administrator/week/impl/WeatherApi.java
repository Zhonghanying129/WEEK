package com.example.administrator.week.impl;

import com.example.administrator.week.bean.WeatherInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2019/4/27 0027.
 */

public interface WeatherApi {
    //
    //http://api.avatardata.cn/Weather/Query?key=a082c2a315ae4123a3ac541fc1c089e5&cityname=绵阳
    @GET("/Weather/Query?")
    Call<WeatherInfo> getNowWeather(@Query("key") String key,@Query("cityname") String cityname);


}
