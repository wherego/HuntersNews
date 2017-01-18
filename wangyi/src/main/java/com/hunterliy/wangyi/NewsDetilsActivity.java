package com.hunterliy.wangyi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsDetilsActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detils);
        webView = (WebView) findViewById(R.id.web_text);
        Intent intent = this.getIntent();
//        final ArrayList<String> data = bundle.getStringArrayList("data");
        String data = intent.getStringExtra("data");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(data);

    }
}
