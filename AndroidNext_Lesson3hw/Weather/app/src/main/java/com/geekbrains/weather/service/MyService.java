package com.geekbrains.weather.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import static android.app.Service.START_NOT_STICKY;

/**
 * Created by shkryaba on 27.07.18.
 */
public class MyService extends Service {
    IBinder mBinder; // Интерфейс связи с клиентом
    @Override
    public void onCreate() {
        // Служба создается
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Служба стартовала
        return START_NOT_STICKY;
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
}