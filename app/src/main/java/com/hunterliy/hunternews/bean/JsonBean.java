package com.hunterliy.hunternews.bean;

import com.google.gson.Gson;

public abstract class JsonBean {

    private static final long EXPIRE_LIMIT = 60*60*1000;
    private long mCreateTime;
    public JsonBean(){
        mCreateTime = System.currentTimeMillis();
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    public boolean isExpire(){
        return System.currentTimeMillis()-mCreateTime>EXPIRE_LIMIT;
    }
}
