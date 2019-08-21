package com.example.administrator.week.utils;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2019/6/9 0009.
 */

public class FormatUtils {
    public static String formatSize(long size)
    {
        LogUtils.d("size:"+size);
        double kilo=size/1024;
        LogUtils.d("kilo size:"+kilo);
        if (kilo<1)
        {
            return size+"B";
        }
        double megaByte=kilo/1024;
        LogUtils.d("megaByte size:"+megaByte);
        if (megaByte<1)
        {
            BigDecimal byBytel=new BigDecimal(Double.toString(kilo));
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
}
