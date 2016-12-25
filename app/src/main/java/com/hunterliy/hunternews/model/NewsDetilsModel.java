package com.hunterliy.hunternews.model;


public class NewsDetilsModel {

    private OnNewsDetilsListener newsListener;


    public interface OnNewsDetilsListener{
        void onSuccess();
        void onFailture();
    }

    public void getNewsData(OnNewsDetilsListener newsListener){
        this.newsListener = newsListener;
    }
}
