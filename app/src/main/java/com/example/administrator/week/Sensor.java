package com.example.administrator.week;

import android.app.Activity;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class Sensor extends Activity implements SensorEventListener {


    private ImageView znzImage;
    float currentDegree=0f;
    SensorManager manager;
    private android.hardware.Sensor accelerometer;
    private android.hardware.Sensor magnetic;
    private float[] accelerometerValues=new float[3];
    private float[] magneticFieldValues=new float[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        initView();
        manager=(SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer=manager.getDefaultSensor(android.hardware.Sensor.TYPE_ACCELEROMETER);
        magnetic=manager.getDefaultSensor(android.hardware.Sensor.TYPE_MAGNETIC_FIELD);
        calculateOrientation();
    }
    @Override
    protected void onResume()
    {
        manager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_GAME);
        manager.registerListener(this,magnetic,SensorManager.SENSOR_DELAY_GAME);
        super.onResume();
    }
    @Override
    protected void onPause()
    {
        manager.unregisterListener(this);
        super.onPause();
    }
    @Override
    protected void onStop()
    {
        manager.unregisterListener(this);
        super.onStop();
    }
    private float calculateOrientation()
    {
        float[] values=new float[3];
        float[] R=new float[9];
        SensorManager.getRotationMatrix(R,null,accelerometerValues,magneticFieldValues);
        SensorManager.getOrientation(R,values);
        values[0]=(float)Math.toDegrees(values[0]);
        return values[0];
    }
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if (event.sensor.getType()== android.hardware.Sensor.TYPE_ACCELEROMETER)
        {
            accelerometerValues=event.values;
        }
        if (event.sensor.getType()== android.hardware.Sensor.TYPE_MAGNETIC_FIELD)
        {
            magneticFieldValues=event.values;
        }
        float degree=calculateOrientation();
        RotateAnimation re=new RotateAnimation(currentDegree,-degree, Animation.RELATIVE_TO_PARENT,0.5f
        ,Animation.RELATIVE_TO_SELF,0.5f);
        re.setDuration(200);
        znzImage.startAnimation(re);
        currentDegree=-degree;
    }
    @Override
    public void onAccuracyChanged(android.hardware.Sensor sensor,int accuracy)
    {

    }
    private void initView() {
        znzImage = (ImageView) findViewById(R.id.znzImage);
    }
}
