package ru.tabiin.alasmaulhusna;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.color.DynamicColors;

import ru.tabiin.alasmaulhusna.util.SharedPreferencesUtils;

public class App extends Application {


    private static App instance = null;

    public static App getInstance() {
        if (instance == null)
            instance = new App();
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        int nightMode = SharedPreferencesUtils.getInteger(this, "nightMode");

        switch (nightMode) {
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
        }

       // if (!SharedPreferencesUtils.getBoolean(this, "useDynamicColors")) {
       //     DynamicColors.applyToActivitiesIfAvailable(this);
       // }
    }




}
