package com.hunterliy.hunternews.activity;

import android.app.Application;

public class HunterNewsApp extends Application{

    private static HunterNewsApp INSTANCE;
    private String ROOT_PATH;

    public static HunterNewsApp getInstance(){
        return INSTANCE;
    }
    public String getRoot(){
        return ROOT_PATH;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        ROOT_PATH = getDir("HunterNews",MODE_PRIVATE).getAbsolutePath();
        if (!ROOT_PATH.endsWith("/"))
            ROOT_PATH+="/";
    }
}
