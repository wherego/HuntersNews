package com.hunterliy.hunternews.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hunterliy.hunternews.R;
import com.hunterliy.hunternews.model.NewsDetilsModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsDetilsActivity extends AppCompatActivity {


    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.activity_news_detils);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initData() {
        toolbar.setTitle("新闻详情");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        NewsDetilsModel newsDetilsModel = new NewsDetilsModel();
        newsDetilsModel.getNewsData(new NewsDetilsModel.OnNewsDetilsListener() {
            @Override
            public void onSuccess(ArrayList<String> data) {
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(data.get(1));
                Glide.with(getApplicationContext())
                        .load(data.get(0)).error(R.mipmap.ic_launcher)
                        .fitCenter().into(ivImage);
            }

            @Override
            public void onFailture() {

            }
        });

    }
}
