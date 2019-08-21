package com.example.administrator.week.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2019/4/30 0030.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;
    @SerializedName("cond")
    public More more;
    public class More
    {
        @SerializedName("txt")
        public String info;
    }
}
