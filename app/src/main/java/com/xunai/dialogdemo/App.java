package com.xunai.dialogdemo;

import android.app.Application;

public class App extends Application {

    private static App instance = null;

    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //单利
        instance = this;
    }

}
