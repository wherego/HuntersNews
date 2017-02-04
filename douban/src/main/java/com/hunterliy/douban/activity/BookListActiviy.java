package com.hunterliy.douban.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.hunterliy.douban.bean.Book;
import com.hunterliy.douban.adapter.BookAdapter;
import com.hunterliy.douban.bean.BookResponse;
import com.hunterliy.douban.api.DoubanApi;
import com.hunterliy.douban.R;
import com.hunterliy.library.utils.AppObservable;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.functions.Action1;

public class BookListActiviy extends AppCompatActivity {

    RecyclerView recyclerView;
    TwinklingRefreshLayout refreshWidget;
    private List<Book> mlist = new ArrayList<>();
    private BookAdapter mAdapter;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);
        refreshWidget = (TwinklingRefreshLayout)findViewById(R.id.refresh_widget);
        initRefresh();
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BookAdapter(mlist);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(BookListActiviy.this, "eeeee", Toast.LENGTH_SHORT).show();
            }
        });
        getBooksList();
    }

    private void initRefresh() {
        refreshWidget.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getBooksList();
                        page=page+1;
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
                        getBooksList();
                        refreshWidget.finishRefreshing();
                    }
                }, 2000);

            }
        });
    }


    public void getBooksList() {
        Map<String, String> params = new HashMap<>();
        params.put("q", "经济");
        params.put("start", "0");
        params.put("end", "50");
        if (page==0) {
            AppObservable.bindActivity(DoubanApi.http.getBooks(params)).subscribe(new Action1<BookResponse>() {
                @Override
                public void call(BookResponse bookResponse) {
                    mlist.clear();
                    mlist.addAll(bookResponse.getBooks());
                    mAdapter.notifyDataSetChanged();
                }
            });
        }
//        else {
//            AppObservable.bindActivity(this, DoubanApi.http.getBooks().subscribe(new Action1<BookResponse>() {
//                @Override
//                public void call(BookResponse bookResponse) {
//                    refreshWidget.setOverScrollRefreshShow(false);
//                    mlist.addAll(bookResponse.getBooks());
//                    mAdapter.notifyDataSetChanged();
//                }
//            }, new Action1<Throwable>() {
//                @Override
//                public void call(Throwable throwable) {
//                    Toast.makeText(BookListActiviy.this, "network error", Toast.LENGTH_SHORT).show();
//                    refreshWidget.setOverScrollRefreshShow(false);
//                }
//            });
//        }
    }
}
