package com.example.administrator.week;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.week.utils.CleanDataUtils;

public class CleanActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView clean_stroge;
    private LinearLayout cleanactivity;
    private Button clean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean);
        initView();
        try {
            String totalCacheSize = CleanDataUtils.getTotalCacheSize(getApplicationContext());
            clean_stroge.setText(totalCacheSize);
        } catch (Exception e) {
        }
    }


    private void initView() {
        clean_stroge = (TextView) findViewById(R.id.clean_stroge);
        cleanactivity = (LinearLayout) findViewById(R.id.cleanactivity);
        clean = (Button) findViewById(R.id.clean);
        clean.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clean:
                try {
                    CleanDataUtils.clearAllCache(getApplicationContext());
                    String size = CleanDataUtils.getTotalCacheSize(getApplicationContext());
                    Toast.makeText(this,"清理成功",Toast.LENGTH_SHORT).show();
                    clean_stroge.setText(size);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
        }
    }
}
