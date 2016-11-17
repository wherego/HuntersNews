package com.hunterliy.hunternews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.hunterliy.hunternews.R;
import com.hunterliy.hunternews.adapter.NewsAdapter;
import com.hunterliy.hunternews.bean.NewsListEntity;
import com.hunterliy.hunternews.model.NewsListModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_widget)
    SwipeRefreshLayout swipeRefreshWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        NewsListModel newsModel = new NewsListModel();
        newsModel.getNewsData(new NewsListModel.OnNewsListener() {
            @Override
            public void onSuccess(final List<NewsListEntity> news_list) {
                NewsAdapter newsAdapter = new NewsAdapter(news_list);
                recyclerView.setAdapter(newsAdapter);
                newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        ArrayList<String> data = new ArrayList<String>();
                        data.add(news_list.get(position).getPicUrl());
                        data.add(news_list.get(position).getUrl());
                        Intent intent = new Intent(MainActivity.this, NewsDetilsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList("data", data);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailture() {
                Toast.makeText(getApplicationContext(), "请检查网络连接", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
