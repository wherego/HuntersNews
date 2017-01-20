package com.hunterliy.hunternews.activity;

import android.app.Application;

public class HunterNewsApp extends Application{

    private static HunterNewsApp INSTANCE;

    public static HunterNewsApp getInstance(){
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
