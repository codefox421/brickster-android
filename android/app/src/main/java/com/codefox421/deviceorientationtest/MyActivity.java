package com.codefox421.deviceorientationtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MyActivity extends Activity
        implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor sensorAccelerometer;

    TextView readingAzimuth, readingPitch, readingRoll;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorAccelerometer = sensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER);

        readingAzimuth = (TextView)findViewById(R.id.azimuth);
        readingPitch = (TextView)findViewById(R.id.pitch);
        readingRoll = (TextView)findViewById(R.id.roll);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                sensorAccelerometer,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

  /*
   * event.values[0]: azimuth, rotation around the Z axis.
   * event.values[1]: pitch, rotation around the X axis.
   * event.values[2]: roll, rotation around the Y axis.
   */

        float valueAzimuth = event.values[0]; // use this one for vertical = 0
        float valuePitch = event.values[1]; // use this one for horizontal = 0
        float valueRoll = event.values[2];

        readingAzimuth.setText("Azimuth: " + String.valueOf(valueAzimuth));
        readingPitch.setText("Pitch: " + String.valueOf(valuePitch));
        readingRoll.setText("Roll: " + String.valueOf(valueRoll));


    }
}
