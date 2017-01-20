package com.hunterliy.hunternews.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.mzule.activityrouter.annotation.Router;
import com.hunterliy.hunternews.R;
import com.hunterliy.hunternews.adapter.StoryAdapter;
import com.hunterliy.hunternews.api.ZhihuApi;
import com.hunterliy.hunternews.bean.Story;
import com.hunterliy.hunternews.bean.StoryResponse;
import com.hunterliy.hunternews.cache.CacheManager;
import com.hunterliy.hunternews.cache.NetWorkCache;
import com.hunterliy.library.utils.AppObservable;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

@Router("story_list")
public class StoryListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    RecyclerView recyclerView;
    TwinklingRefreshLayout refreshWidget;
    NavigationView navigationView;
    private List<Story> mlist = new ArrayList<>();
    private StoryAdapter mAdapter;
    private int page = 0;
    private static final String API = "http://api.1-blog.com/biz/bizserver/xiaohua/list.do?page=%s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshWidget = (TwinklingRefreshLayout) findViewById(R.id.refresh_widget);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initRefresh();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new StoryAdapter(mlist);
        recyclerView.setAdapter(mAdapter);
        getLatestData();
        mAdapter.setOnItemClickListener(new StoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                ArrayList<String> data = new ArrayList<String>();
//                data.add(String.valueOf(mlist.get(position).getId()));
//                data.add(mlist.get(position).getTitle());
//                Intent intent = new Intent(StoryListActivity.this, StoryDetilsActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putStringArrayList("data",data);
//                intent.putExtras(bundle);
//                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.story:
                Toast.makeText(StoryListActivity.this, "story", Toast.LENGTH_SHORT).show();
                break;
            case R.id.news:
                Toast.makeText(StoryListActivity.this, "news", Toast.LENGTH_SHORT).show();
                break;
            case R.id.book:
                Toast.makeText(StoryListActivity.this, "book", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

    private void initRefresh() {
        refreshWidget.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                getHistoryData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshWidget.finishLoadmore();
                    }
                }, 2000);
            }

            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                getLatestData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshWidget.finishRefreshing();
                    }
                }, 2000);

            }
        });
    }

    private void getLatestData() {
        page = 0;
        getStoryList("");
    }

    private void getHistoryData() {
        String time = getDateString(new Date());
        String key = String.valueOf(Long.valueOf(time) - page);
        page += 1;
        getStoryList(key);
    }

    private void getStoryList(String newsKey) {
        NetWorkCache<StoryResponse> net = new NetWorkCache<StoryResponse>() {
            @Override
            public Observable<StoryResponse> get(String key, Class<StoryResponse> clz) {
                Observable<StoryResponse> o = AppObservable.bindActivity(ZhihuApi.http.getLatestNews());
                Log.e("cache", "网络读取load from newwork");
                return o;
            }
        };
        Observable<StoryResponse> ob = CacheManager.getInstance().load(API,StoryResponse.class,net);

        if (newsKey.equals("")) {
            ob.subscribe(new Action1<StoryResponse>() {
                @Override
                public void call(StoryResponse storyResponse) {
                    mlist.clear();
                    mlist.addAll(storyResponse.getStories());
                    mAdapter.notifyDataSetChanged();
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    Toast.makeText(StoryListActivity.this, "network error", Toast.LENGTH_SHORT).show();
                }
            });
        }
//        else {
//            AppObservable.bindActivity(this, ZhihuApi.http.getHistoryNews(newsKey)).subscribe(new Action1<StoryResponse>() {
//                @Override
//                public void call(StoryResponse storyResponse) {
//                    refreshWidget.setOverScrollRefreshShow(false);
//                    mlist.addAll(storyResponse.getStories());
//                    mAdapter.notifyDataSetChanged();
//                }
//            }, new Action1<Throwable>() {
//                @Override
//                public void call(Throwable throwable) {
//                    Toast.makeText(StoryListActivity.this, "network error", Toast.LENGTH_SHORT).show();
//                    refreshWidget.setOverScrollRefreshShow(false);
//                }
//            });
//        }
    }

    public static String getDateString(Date date) {
        String year = (date.getYear() + 1900) + "";
        String mm = (date.getMonth() + 1) + "";
        if (Integer.valueOf(mm).intValue() < 10) {
            mm = "0" + mm;
        }
        String day = date.getDate() + "";
        if (Integer.valueOf(day).intValue() < 10) day = "0" + day;
        return year + mm + day;
    }

}
