//package com.hunterliy.hunternews.activity;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//import com.github.mzule.activityrouter.annotation.Router;
//import com.hunterliy.hunternews.R;
//import com.hunterliy.hunternews.api.ZhihuApi;
//import com.hunterliy.hunternews.bean.NewsDetailResponse;
//import com.hunterliy.library.utils.AppObservable;
//
//import java.util.ArrayList;
//
//import rx.functions.Action1;
//
//@Router("story_detils/:id/:title")
//public class StoryDetilsActivity extends AppCompatActivity {
//
//    ImageView ivImage;
//    Toolbar toolbar;
//    WebView webView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        initView();
//    }
//
//    private void initView() {
//        setContentView(R.layout.activity_news_detils);
//        ivImage = (ImageView) findViewById(R.id.ivImage);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        webView = (WebView) findViewById(R.id.web_view);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//        WebSettings settings = webView.getSettings();
//        settings.setDomStorageEnabled(true);
//        settings.setJavaScriptEnabled(true);
//        Bundle bundle = this.getIntent().getExtras();
//        final ArrayList<String> data = bundle.getStringArrayList("data");
//        AppObservable.bindActivity(StoryDetilsActivity.this, ZhihuApi.http.getNewsDetail(data.get(0))).subscribe(new Action1<NewsDetailResponse>() {
//            @Override
//            public void call(NewsDetailResponse newsDetailResponse) {
//                toolbar.setTitle(data.get(1));
//                Glide.with(StoryDetilsActivity.this)
//                        .load(newsDetailResponse.getImage())
//                        .asBitmap()
//                        .into(ivImage);
//                if (newsDetailResponse.getCss() != null && newsDetailResponse.getCss().size() > 0) {
//                    String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/news.css\" type=\"text/css\">";
//                    String html = "<html><head>" + css + "</head><body>" + newsDetailResponse.getBody() + "</body></html>";
//                    html = html.replace("<div class=\"img-place-holder\">", "");
//                    webView.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "UTF-8", null);
//                } else {
//                    webView.loadData(newsDetailResponse.getBody(), "text/html", "utf-8");
//                }
//            }
//        }, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//                Toast.makeText(StoryDetilsActivity.this, "network error", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//}
