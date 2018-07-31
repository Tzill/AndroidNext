package com.geekbrains.weather.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.geekbrains.weather.R;

import java.util.List;

import java.util.logging.LogRecord;

import static android.os.SystemClock.sleep;

/**
 * Created by shkryaba on 26/07/2018.
 */

public class MainService extends IntentService {
    int messageId = 0;
    private Handler mHandler = new Handler();

    private SensorManager sensorManager;
    private List<Sensor> listSensors;
    private Sensor sensor;


    private float sensorValue;
    private float sensorValuePrev;
    private float delta = 1.0f;

    public MainService() {
        super("MainService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        makeNote("onHandleIntent");
    }

    @Override
    public void onCreate() {
        makeNote("onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        makeNote("onStartCommand");
        initSensor();



        return super.onStartCommand(intent, flags, startId);
    }

    private void postToast(){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), "Освещенность: " + Float.toString(sensorValue) + " Lux", Toast.LENGTH_LONG).show();


            }
        });
    }

    @Override
    public void onDestroy() {
        makeNote("onDestroy");
        super.onDestroy();
    }

    // Вывод уведомления в строке состояния
    private void makeNote(String message) {
        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Main service notification")
                .setContentText(message);
        Intent resultIntent = new Intent(this, MainService.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        builder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager =
                (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(messageId++, builder.build());
    }

    private void initSensor() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        listSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        listSensors.get(0).getName();

    }



    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            showLightSensors(sensorEvent);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    private void showLightSensors(SensorEvent sensorEvent) {
        String light = sensorEvent.values.toString();

        sensorValue = sensorEvent.values[0];
        if ((sensorValue > sensorValuePrev+delta)||(sensorValue < sensorValuePrev-delta)) {

            postToast();
            sensorValuePrev = sensorValue;
        }
    }
}