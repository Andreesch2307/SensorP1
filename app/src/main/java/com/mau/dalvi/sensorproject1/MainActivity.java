package com.mau.dalvi.sensorproject1;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor pressure;
    private TextView tvText;
    private Sensor temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvText = (TextView)findViewById(R.id.tvText);
        getSM();
        getTemp();
        }

    public void getSM() {

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        pressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (pressure != null) {
            toastYey();
        } else {
            toastNon();
        }

    }

    public void getTemp(){
        temp = sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
        if (temp != null) {
            Toast toast = Toast.makeText(this, "Temp sensor exists", Toast.LENGTH_SHORT);
            toast.show();

        } else {
            Toast toast = Toast.makeText(this, "Temp sensor does not exist", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void toastYey() {
        CharSequence textSuccess = "Succes, the pressure sensor is alive";
        int duration = Toast.LENGTH_SHORT ;
        Toast toast = Toast.makeText(this, textSuccess, duration );
        toast.show();
    }

    public void toastNon() {
        CharSequence textFail = "Fail, the pressure sensor is missing";
        int duration = Toast.LENGTH_SHORT ;
        Toast toast2 = Toast.makeText(this, textFail, duration);
        toast2.show();
    }

    public void toastReg(){

        CharSequence textReg = "Listeners registered WHOOP" ;
        int duration = Toast.LENGTH_SHORT;
        Toast toast4 = Toast.makeText(this, textReg, duration);
        toast4.show();
    }

    public void toastUnreg(){
        CharSequence textUn = "Listeners unregistered";
        int duration = Toast.LENGTH_SHORT;
        Toast toast3 = Toast.makeText(this, textUn, duration);
        toast3.show();
    }

    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, pressure, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, temp, sensorManager.SENSOR_DELAY_NORMAL);
        toastReg();
    }
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        sensorManager.registerListener(this, temp, SensorManager.SENSOR_DELAY_NORMAL);
        toastUnreg();
    }

    public void onDestroy() {
        super.onDestroy();
        sensorManager = null;
        pressure = null;
        temp = null;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        tvText.setText("Accuracy: " +event.accuracy + "\n Timestamp: " + event.timestamp +
        "\n Values: " + event.values[0] );


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        tvText.setText("Accuracy changed: " + accuracy);

    }
}
