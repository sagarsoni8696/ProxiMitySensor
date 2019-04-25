package com.example.proximitysensor;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener

{
    TextView ProximitySensor, data;
    SensorManager mySensorManager;
    Sensor myProximitySensor;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProximitySensor = (TextView) findViewById(R.id.proximitySensor);
        data = (TextView) findViewById(R.id.data);
        mySensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);
        myProximitySensor = mySensorManager.getDefaultSensor(
                Sensor.TYPE_PROXIMITY);
        if (myProximitySensor == null)
        {
            ProximitySensor.setText("There is No Proximity Sensor in your Device!");
        }
        else
            {
            mySensorManager.registerListener(proximitySensorEventListener,
                    myProximitySensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    SensorEventListener proximitySensorEventListener
            = new SensorEventListener()
    {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy)
        {
            // TODO Auto-generated method stub
        }
        @Override
        public void onSensorChanged(SensorEvent event)
        {
            // TODO Auto-generated method stub

                if (event.values[0] < myProximitySensor.getMaximumRange())
                {
                    data.setText("Object is Near to Your Device");
                    // Object is nearby
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                }

                else
                    {
                    data.setText("Object is going Away to Your Device");
                    // Object is going Away
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }


        }
    };

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}