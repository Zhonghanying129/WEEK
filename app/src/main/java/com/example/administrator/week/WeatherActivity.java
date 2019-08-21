package com.example.administrator.week;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.week.gson.Forecast;
import com.example.administrator.week.gson.Weather;
import com.example.administrator.week.utils.HttpUtil;
import com.example.administrator.week.utils.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2019/4/30 0030.
 */

public class WeatherActivity extends AppCompatActivity {

    private TextView title_city;//
    private TextView title_update_time;//
    private TextView degree_text;//
    private TextView weather_info_text;//
    private LinearLayout forecast_layout;//
    private TextView api_text;//
    private TextView pm25_text;//
    private TextView comfort_text;//
    private TextView car_wash_text;//
    private TextView sport_text;//
    private ScrollView weather_layout;//
    private ImageView bingPicImg;
    public SwipeRefreshLayout swipeRefreshLayout;
    private String mWeatherId;
    public DrawerLayout drawerLayout;
    private Button navButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT>=21)
        {
            View decorView=getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_weather);
        initView();
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString=prefs.getString("weather",null);
        if(weatherString!=null)
        {
            //有缓存时解析天气数据
            Weather weather= Utility.handleWeatherResponse(weatherString);
            mWeatherId=weather.basic.weatherId;
            showWeatherInfo(weather);
        }
        else
        {
            //无缓存时去服务器查询天气
            mWeatherId=getIntent().getStringExtra("weather_id");
            weather_layout.setVisibility(View.INVISIBLE);
            requestWeather(mWeatherId);
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            public void onRefresh()
            {
                requestWeather(mWeatherId);
            }
        });
        String bingpic=prefs.getString("bing_pic",null);
        if (bingpic!=null)
        {
            Glide.with(this).load(bingpic).into(bingPicImg);
        }
        else
        {
            loadBingPic();
        }
        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    /**
     * 根据id请求城市天气信息
     */
    public void requestWeather(final String weatherId)
    {
        String weatherUrl="http://guolin.tech/api/weather?cityid="+weatherId+"&key=bc0418b57b2d4918819d3974ac1285d9";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText=response.body().string();
                final Weather weather= Utility.handleWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather!=null && "ok".equals(weather.status))
                        {
                            SharedPreferences.Editor editor=PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather",responseText);
                            editor.apply();
                            mWeatherId=weather.basic.weatherId;
                            showWeatherInfo(weather);
                        }
                        else
                        {
                            Toast.makeText(WeatherActivity.this,"获取天气信息失败",Toast.LENGTH_SHORT).show();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this,"获取天气信息失败",Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
        loadBingPic();
    }

    /**
     * 处理并展示Weather实体类中的数据
     * @param weather
     */
    /**
     * 加载必应每日一图
     */
    private void loadBingPic()
    {
        String requestBingPic="http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic=response.body().string();
                SharedPreferences.Editor editor=PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("bing_pic",bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }
        });
    }
    private void showWeatherInfo(Weather weather)
    {
        String cityName=weather.basic.cityName;
        String updateTime=weather.basic.update.updateTime.split(" ")[1];
        String degree=weather.now.temperature+"℃";
        String weatherInfo=weather.now.more.info;
        title_city.setText(cityName);
        title_update_time.setText(updateTime);
        degree_text.setText(degree);
        weather_info_text.setText(weatherInfo);
        forecast_layout.removeAllViews();
        for (Forecast forecast:weather.forecastList)
        {
            View view= LayoutInflater.from(this).inflate(R.layout.forecast_item,forecast_layout,false);
            TextView dateText=(TextView)view.findViewById(R.id.date_text);
            TextView infoText=(TextView)view.findViewById(R.id.info_text);
            TextView maxText=(TextView)view.findViewById(R.id.max_text);
            TextView minText=(TextView)view.findViewById(R.id.min_text);
            dateText.setText(forecast.date);
            infoText.setText(forecast.more.info);
            maxText.setText(forecast.temperature.max);
            minText.setText(forecast.temperature.min);
            forecast_layout.addView(view);
        }
        if (weather.aqi!=null)
        {
            api_text.setText(weather.aqi.city.api);
            pm25_text.setText(weather.aqi.city.pm25);
        }
        String comfort="舒适度:"+weather.suggestion.comfort.info;
        String carWash="洗车指数:"+weather.suggestion.carWash.info;
        String sport="运动指数："+weather.suggestion.sport.info;
        comfort_text.setText(comfort);
        car_wash_text.setText(carWash);
        sport_text.setText(sport);
        weather_layout.setVisibility(View.VISIBLE);

    }
    private void initView() {
        navButton=(Button)findViewById(R.id.nav_button);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        bingPicImg=(ImageView)findViewById(R.id.bing_pic_img);
        title_city = (TextView) findViewById(R.id.title_city);
        title_update_time = (TextView) findViewById(R.id.title_update_time);
        degree_text = (TextView) findViewById(R.id.degree_text);
        weather_info_text = (TextView) findViewById(R.id.weather_info_text);
        forecast_layout = (LinearLayout) findViewById(R.id.forecast_layout);
        api_text = (TextView) findViewById(R.id.api_text);
        pm25_text = (TextView) findViewById(R.id.pm25_text);
        comfort_text = (TextView) findViewById(R.id.comfort_text);
        car_wash_text = (TextView) findViewById(R.id.car_wash_text);
        sport_text = (TextView) findViewById(R.id.sport_text);
        weather_layout = (ScrollView) findViewById(R.id.weather_layout);
    }
}
