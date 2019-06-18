package com.example.mydemoapp;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

    private static MyApp instance;
    AppComponent appComponent;
    Context context;

    public static Context getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        context = this;
        appComponent = DaggerAppComponent.builder().build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

}
