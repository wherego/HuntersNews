package com.hunterliy.wangyi.ui;


import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hunterliy.wangyi.R;

public abstract class SampleFragment extends Fragment {

    private boolean isViewInit = false;
    private boolean isDataInit = false;
    private boolean isVisibleToUser;
    private int page;

    public SampleFragment() {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        checkIfLoadData();
        Log.e("tag", "setUserVisibleHint"+isVisibleToUser);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        checkIfLoadData();
        Log.e("tag", "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("tag", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("tag", "onCreateView");
        return inflater.inflate(R.layout.fragment_sample, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewInit = true;
        Log.e("tag", "onViewCreated");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        checkIfLoadData();
        Log.e("tag", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("tag", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("tag", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("tag", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("tag", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("tag", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("tag", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("tag", "onDetach");
    }

    public void checkIfLoadData(){
        if (isViewInit&&isVisibleToUser&&!isDataInit){
            isDataInit = true;
            getNewsList(page);
        }
    }

    public abstract void getNewsList(int page);
}
