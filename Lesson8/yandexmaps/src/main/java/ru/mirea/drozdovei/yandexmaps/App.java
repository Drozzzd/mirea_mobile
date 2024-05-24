package ru.mirea.drozdovei.yandexmaps;

import android.app.Application;

import com.yandex.mapkit.MapKitFactory;

public class App  extends Application {
    private final String MAPKIT_API_KEY = "5185018a-c13c-473c-a842-2f87fd125673";

    public void onCreate() {
        super.onCreate();
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
    }
}