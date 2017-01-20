package com.hunterliy.wangyi;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hunterliy.library.utils.AppObservable;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;


public class Fragment_mobile extends SampleFragment {

    RecyclerView recyclerView;
    TwinklingRefreshLayout refreshWidget;
    private List<News> mlist = new ArrayList<>();
    private NewsAdapter mAdapter;
    private int page = 0;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mobile,container,false);
        refreshWidget = (TwinklingRefreshLayout)v.findViewById(R.id.refresh_widget);
        initRefresh();
        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new NewsAdapter(mlist);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view,int position) {
                ArrayList<String> data = new ArrayList<String>();
                data.add(mlist.get(position).getPicUrl());
                data.add(mlist.get(position).getUrl());
                Intent intent = new Intent(getActivity(),NewsDetilsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("data",data);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return v;
    }

    private void initRefresh() {
        refreshWidget.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getNewsList(page);
                        page = page + 1;
                        refreshWidget.finishLoadmore();
                    }
                }, 2000);
            }

            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getNewsList(0);
                        refreshWidget.finishRefreshing();
                    }
                }, 2000);

            }
        });
    }

    @Override
    public void getNewsList(int page) {
        Log.e("USING", "使用流量了");
        if (page==0) {
            AppObservable.bindActivity(getActivity(), MobileHttpApi.http.getNewsData("be7d7c5a9db7e299d8e537e058ea7cef", "10", 0)).subscribe(new Action1<NewsResponse>() {
                @Override
                public void call(NewsResponse newsResponse) {
                    mlist.clear();
                    mlist.addAll(newsResponse.getNewslist());
                    mAdapter.notifyDataSetChanged();
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    Toast.makeText(getContext(), "network error", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            AppObservable.bindActivity(getActivity(), MobileHttpApi.http.getNewsData("be7d7c5a9db7e299d8e537e058ea7cef","10",page++)).subscribe(new Action1<NewsResponse>() {
                @Override
                public void call(NewsResponse storyResponse) {
                    refreshWidget.setOverScrollRefreshShow(false);
                    mlist.addAll(storyResponse.getNewslist());
                    mAdapter.notifyDataSetChanged();
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    Toast.makeText(getContext(), "network error", Toast.LENGTH_SHORT).show();
                    refreshWidget.setOverScrollRefreshShow(false);
                }
            });
        }
    }

}
