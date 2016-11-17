package com.hunterliy.hunternews.utils;

import android.util.Log;

import com.hunterliy.hunternews.apiService.ApiService;
import com.hunterliy.hunternews.bean.NewsList;
import com.hunterliy.hunternews.bean.NewsListEntity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class HttpUtils {

    public static List<NewsListEntity> showList = new ArrayList<NewsListEntity>();

    public static List<NewsListEntity> getNewsData(){
         Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.tianapi.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
            ApiService api =retrofit.create(ApiService.class);
            api.getNews("be7d7c5a9db7e299d8e537e058ea7cef","10",10)
                .subscribeOn(Schedulers.io())
                .map(new Func1<NewsList, List<NewsListEntity>>() {
                    @Override
                    public List<NewsListEntity> call(NewsList newsList) {
                        List<NewsListEntity> list  = new ArrayList<NewsListEntity>();
                        for (NewsList.NewsListBean newsListBean :newsList.getNewslist()) {
                            NewsListEntity newsListEntity = new NewsListEntity();
                            newsListEntity.setCtime(newsListBean.getCtime());
                            newsListEntity.setDescription(newsListBean.getDescription());
                            newsListEntity.setPicUrl(newsListBean.getPicUrl());
                            newsListEntity.setTitle(newsListBean.getTitle());
                            newsListEntity.setUrl(newsListBean.getUrl());
                            list.add(newsListEntity);
                        }
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<NewsListEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ERROR","网络连接失败");
                    }

                    @Override
                    public void onNext(List<NewsListEntity> list) {
                        for (NewsListEntity en:list
                                ) {
                            showList.add(en);
                        }
                    }
                });
    return showList;
}
}
