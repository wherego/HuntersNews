package com.hunterliy.library.cache;

import android.util.Log;

import com.google.gson.Gson;
import com.hunterliy.library.bean.JsonBean;
import com.hunterliy.library.utils.FileUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DiskCache implements ICache{

    private String CACHE_PATH;

    public DiskCache() {
        CACHE_PATH = FileUtils.getCacheDir();
    }

    @Override
    public <T extends JsonBean> Observable<T> get(final String key, final Class<T> clz) {

        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {

                Log.e("cache","硬盘读取load cache from"+key);
                String fileName = CACHE_PATH+key;
                String result = FileUtils.readTextFromFile(fileName);
                if (subscriber.isUnsubscribed()){
                    return;
                }
                if (result == null){
                    subscriber.onNext(null);
                }else {
                    T t = new Gson().fromJson(result,clz);
                    subscriber.onNext(t);
                }

                subscriber.onCompleted();
            }
        })
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public <T extends JsonBean> void put(final String key, final T t) {

        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {

                Log.e("cache","硬盘存储save to disk"+key);

                String fileName = CACHE_PATH + key;
                String result = t.toString();
                FileUtils.saveText2SDcard(fileName,result);
                if (!subscriber.isUnsubscribed()){
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();


    }
}
