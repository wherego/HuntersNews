package com.hunterliy.hunternews.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HttpHelper {

    private static final int TIME_OUT_LIMIT = 30;
    private OkHttpClient mOkHttpClient;

    private HttpHelper(){
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT_LIMIT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT_LIMIT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT_LIMIT, TimeUnit.SECONDS)
                .build();
    }
    private static final HttpHelper getInstance(){
        return LazyHolder.INSTANCE;
    }

    private static final class LazyHolder{
        private static final HttpHelper INSTANCE= new HttpHelper();
    }

    public Retrofit getRetrofit(String url){
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

}
