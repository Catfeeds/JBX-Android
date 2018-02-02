/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Three.Controller;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.Macro.DesUtils;
import com.example.zhengdong.base.Macro.LogUtil;
import com.example.zhengdong.base.Macro.SharedPreferencesUtils;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Section.First.Controller.IronMasterWC;
import com.example.zhengdong.base.Section.Login.Controller.LoginAC;
import com.example.zhengdong.jbx.R;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class IronMasterFC extends Fragment {


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
    Unbinder unbinder;
    @BindView(R.id.master_webview)
    WebView masterWebview;

    public IronMasterFC() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_iron_master_fc, container, false);
        unbinder = ButterKnife.bind(this, view);
        initNavigationView();
        initWebView();
        return view;
    }

    // 初始化钣金大师
    private void initWebView() {
        masterWebview.setOverScrollMode(View.OVER_SCROLL_NEVER);
        masterWebview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); // 取消滚动条白边效果
        WebSettings setting = masterWebview.getSettings();
        setting.setDomStorageEnabled(true);
        setting.setAppCacheMaxSize(1024 * 1024 * 8);
        String appCachePath = getActivity().getApplicationContext().getCacheDir().getAbsolutePath();
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
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHH");
        String key = sdf.format(new java.util.Date());
        String url = "";
        String token = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), UrlUtils.APP_TOKEN, ""));

        try {
            String desId = DesUtils.encrypt("73", key);
//            url = UrlUtils.IRON_MASTER_URL + "/GCGL/html/wap/gc/html/login2.html?u=" + desId + "&client=android";
            LogUtil.e("当前的网址:"+url);
            url = UrlUtils.IRON_NEW_MASTER_URL+token;
        }catch (Exception e){
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


        masterWebview.setWebChromeClient(new WebChromeClient() {
                                             public void onProgressChanged(WebView view, int progress) {
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
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            //下载任务，主要有两种方式
            //（1）自定义下载任务
            //（2）调用系统的download的模块
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

    }


    private void initNavigationView() {
        naviTitleTxt.setText("钣金大师");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
