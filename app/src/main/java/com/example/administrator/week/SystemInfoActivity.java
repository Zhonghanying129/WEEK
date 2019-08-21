package com.example.administrator.week;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.example.administrator.week.adapter.SystemInfoAdapter;
import com.example.administrator.week.bean.SystemInfoModel;
import com.example.administrator.week.bean.Type;
import com.example.administrator.week.utils.FileUtils;
import com.example.administrator.week.utils.FormatUtils;
import com.example.administrator.week.utils.VersionUtils;

import java.util.ArrayList;
import java.util.List;

public class SystemInfoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SystemInfoAdapter systemInfoAdapter;
    private List<SystemInfoModel> modelList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_info);
        intitData();
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        systemInfoAdapter=new SystemInfoAdapter(this,modelList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(systemInfoAdapter);
        systemInfoAdapter.setOnItemClickListener(new SystemInfoAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                switch (position)
                {
                    case 7:
                        Toast.makeText(SystemInfoActivity.this, "ROOT", Toast.LENGTH_SHORT).show();
                        break;
                    case 8:
                        Toast.makeText(SystemInfoActivity.this, "同步", Toast.LENGTH_SHORT).show();
                        break;
                    case 16:
                        Toast.makeText(SystemInfoActivity.this, "降温", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
    @SuppressLint("MissingPermission")
    private void intitData()
    {
        addTitle("基本信息");
        addContentText("品牌：", Build.MANUFACTURER);
        addContentText("型号：",Build.MODEL);
        addContentText("CPU:",Build.CPU_ABI);
        addContentText("分辨率:",ScreenManager.getmInStance(this).getW()+"x"+ScreenManager.getmInStance(this).getH());
        addContentText("IMEI:",((TelephonyManager)(getSystemService(TELEPHONY_SERVICE))).getDeviceId());
        addContentButton("一键Root","Root");
        addContentButton("数据同步","同步");
        addTitle("存储信息");
        addContentText("总大小:", FormatUtils.formatSize(FileUtils.getSDSize(0)) + "");
        addContentText("可用大小:", FormatUtils.formatSize(FileUtils.getSDSize(1)) + "");
        addContentText("运行内存:",
                FormatUtils.formatSize(FileUtils.getAvailMemory(this, 0))
                        + " / " +
                        FormatUtils.formatSize(FileUtils.getAvailMemory(this, 1)));

        addTitle("电池");
        addContentText("电量", "");
        addContentText("温度", 0 + "");
        addContentButton("一键降温", "降温");

        addTitle("OS");
        addContentText("Android版本", VersionUtils.getVersion() + "");
        addContentText("内核版本", FileUtils.getLinuxVersion());

        addTitle("传输");
        addContentText("NFC", getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC)?"支持":"不支持");

        addTitle("传感器");
        addContentButton("指南针", "使用");
    }

    /**
     * 添加头部
     *
     * @param title
     */
    private void addTitle(String title) {
        SystemInfoModel model = new SystemInfoModel();
        model.setTitle(title);
        model.setType(Type.LAYOUT_TITLE);
        modelList.add(model);
    }
    /**
     * 添加对应数据
     *
     * @param key
     * @param value
     */
    private void addContentText(String key, String value) {
        SystemInfoModel model = new SystemInfoModel();
        model.setKey(key);
        model.setValue(value);
        model.setType(Type.LAYOUT_CONTENT_TEXT);
        modelList.add(model);
    }
    /**
     * 添加有按钮的item
     *
     * @param key
     * @param value
     */
    private void addContentButton(String key, String value) {
        SystemInfoModel model = new SystemInfoModel();
        model.setButtonkey(key);
        model.setButtonvalue(value);
        model.setType(Type.LAYOUT_CONTENT_BUTTON);
        modelList.add(model);
    }
}
