package com.hunterliy.hunternews.model;

import android.os.Bundle;

import java.util.ArrayList;

public class NewsDetilsModel {

    private OnNewsDetilsListener newsListener;


    public interface OnNewsDetilsListener{
        void onSuccess(ArrayList<String> data);
        void onFailture();
    }

    public void getNewsData(OnNewsDetilsListener newsListener){
        this.newsListener = newsListener;
        Bundle bundle = this.getIntent().getExtras();
        ArrayList<String> data = bundle.getStringArrayList("data");
    }
}
