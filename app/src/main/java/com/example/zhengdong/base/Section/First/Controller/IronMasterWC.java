package com.example.zhengdong.base.Section.First.Controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Section.Login.Controller.LoginAC;
import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IronMasterWC extends AppCompatActivity {

    @BindView(R.id.navi_back_lay)
    LinearLayout naviBackLay;
    @BindView(R.id.navi_title_txt)
    TextView naviTitleTxt;
    @BindView(R.id.navi_title_lay)
    LinearLayout naviTitleLay;
    @BindView(R.id.navi_right_txt)
    TextView naviRightTxt;
    @BindView(R.id.navi_right_lay)
    LinearLayout naviRightLay;
    @BindView(R.id.navi_right_pic_lay)
    LinearLayout naviRightPicLay;
    @BindView(R.id.title_bg)
    RelativeLayout titleBg;
    @BindView(R.id.master_webview)
    WebView masterWebview;
    private String url = "";
    private int webType=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iron_master_wc);
        ButterKnife.bind(this);
        webType = getIntent().getIntExtra("webType",-1);
        initNavigationView();
        initWebView();
    }

    private void initWebView() {
        masterWebview.setOverScrollMode(View.OVER_SCROLL_NEVER);
        masterWebview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); // 取消滚动条白边效果

        WebSettings setting = masterWebview.getSettings();
        setting.setDomStorageEnabled(true);
        setting.setAppCacheMaxSize(1024*1024*8);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        setting.setAppCachePath(appCachePath);
        setting.setAllowFileAccess(true);
        setting.setAppCacheEnabled(true);

        setting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 允许JS执行
        setting.setJavaScriptEnabled(true);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int scale = dm.densityDpi;
        if (scale == 240) {
            masterWebview.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (scale == 160) {
            masterWebview.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else {
            masterWebview.getSettings().setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        }

        if (webType == 1){
            url = getIntent().getStringExtra("url");
            if (TextUtils.isEmpty(url)){
                XToast.show(getBaseContext(),"您还没有登录!请登录!");
                Intent intent1 = new Intent(IronMasterWC.this, LoginAC.class);
                startActivity(intent1);
                finish();
            }
            masterWebview.loadUrl(url);
//        mWebView.loadUrl("http://192.168.10.197:8080/GCGL/html/wap/gc/html/login2.html?u=ctUeDG3x+BU=&client=android");
            masterWebview.setWebViewClient(new WebViewClient() {
                // 点击超链接的时候重新在原来进程上加载URL
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
                // webview加载完成
                @Override
                public void onPageFinished(WebView view, String url) {
                }
            });
        }else if (webType == 2){
            String content = getIntent().getStringExtra("content");
            StringBuilder sb = new StringBuilder();
            sb.append(content);
            String css = "<head><style>img{max-width:340px !important;}</style></head>";
            masterWebview.loadDataWithBaseURL(null, css + sb.toString(), "text/html" , "utf-8", null);
//            masterWebview.loadData(content, "text/html" , "utf-8");
        }


        masterWebview.setWebChromeClient(new WebChromeClient()
                                    {
                                        public void onProgressChanged(WebView view, int progress)
                                        {
                                            // TODO Auto-generated method stub
                                            if (progress == 100) {
                                                // 网页加载完成
//                                                Toast.makeText(MainActivity.this, "加载中...", Toast.LENGTH_SHORT).show();
                                            } else {
                                                // 加载中

                                            }

                                        }
                                    }
        );

        masterWebview.setDownloadListener(new MyDownloadListenter());
    }
    class MyDownloadListenter implements DownloadListener {
        @Override
        public void onDownloadStart(String url, String userAgent,String contentDisposition, String mimetype, long contentLength) {
            //下载任务，主要有两种方式
            //（1）自定义下载任务
            //（2）调用系统的download的模块
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }


    // 标题栏
    private void initNavigationView() {
        naviBackLay.setVisibility(View.VISIBLE);
        if (webType == 1){
            naviTitleTxt.setText("钣金大师");
        }else if (webType == 2){
            naviTitleTxt.setText("资讯详情");
        }
    }

    @OnClick(R.id.navi_back_lay)
    public void onViewClicked() {
        finish();
    }
}
