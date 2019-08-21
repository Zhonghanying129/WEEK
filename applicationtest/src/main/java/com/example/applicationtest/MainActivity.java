package com.example.applicationtest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView()
    {
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(this);
    }


    public void onClick(View w)
    {
        switch (w.getId()) {
            case R.id.btn:
                int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE))
                    {
                        //Toast.makeText(this, "需要开启权限才能使用该功能", Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
                    } else {
                        Toast.makeText(this, "在设置里开启权限", Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},100);

                    }
                } else {
                    callphone("123456");
                }
                break;

        }
    }

    private void callphone(String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

}
