package com.geekbrains.weather;

/**
 * Created by shkryaba on 28/07/2018.
 */

public interface PrefsHelper {

    String getSharedPreferences(String keyPref);

    void saveSharedPreferences(String keyPref, String value);

    void deleteSharedPreferences(String keyPref);
}
