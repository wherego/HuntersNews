package com.hunterliy.library.utils;

import android.app.Activity;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public final class AppObservable {

    public static final boolean USES_SUPPORT_FRAGMENTS;

    static {
        boolean supportFragmentsAvailable = false;
        try {
            Class.forName("android.support.v4.app.Fragment");
            supportFragmentsAvailable = true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
            USES_SUPPORT_FRAGMENTS = supportFragmentsAvailable;
    }

    private AppObservable() {
        throw new AssertionError("No instances");
    }

    public static <T> Observable<T> bindActivity(Activity activity, Observable<T> source) {
        return source.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}