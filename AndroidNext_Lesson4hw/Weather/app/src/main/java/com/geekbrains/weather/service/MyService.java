package com.geekbrains.weather.service;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.widget.Toast;

import com.geekbrains.weather.ui.base.BaseActivity;

import java.io.BufferedOutputStream;
import java.util.Calendar;
import java.util.List;

/**
 * Created by shkryaba on 27.07.18.
 */
public class MyService extends Service implements SensorEventListener {
    IBinder mBinder; // Интерфейс связи с клиентом
    private SensorManager sensorManager;
    private List<Sensor> listSensors;
    private Sensor sensor;

    @Override
    public void onCreate() {
        // Служба создается
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                sensorManager.SENSOR_DELAY_NORMAL);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Привязка клиента
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // Удаление привязки
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        // Перепривязка клиента
    }

    @Override
    public void onDestroy() {
        // Уничтожение службы
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            writeData(sensorEvent.values);
    }

    private void writeData(float[] values) {

        try {
            Intent intent = new Intent(BaseActivity.BROADCAST_ACTION);
            intent.putExtra(BaseActivity.SENSOR_VAL, values[0]);
            sendBroadcast(intent);
        } catch (Throwable t1) {
            Toast.makeText(getApplicationContext(),
                    "Exception: " + t1.toString(), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}