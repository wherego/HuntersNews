package com.hunterliy.wangyi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsDetilsActivity extends AppCompatActivity {

    private WebView webView;
    private ImageView ivImage;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detils);
        webView = (WebView) findViewById(R.id.web_text);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("新闻详情");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Bundle bundle = this.getIntent().getExtras();
        final ArrayList<String> data = bundle.getStringArrayList("data");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(data.get(1));
        Glide.with(this)
                .load(data.get(0))
                .error(R.mipmap.ic_launcher)
                .fitCenter().into(ivImage);
    }
}
