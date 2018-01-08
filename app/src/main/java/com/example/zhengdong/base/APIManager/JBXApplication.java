package com.example.zhengdong.base.APIManager;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import okhttp3.OkHttpClient;

/**
 * Created by zheng.dong on 2017/12/13.
 */

public class JBXApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 适配5.0以下OKhttp崩溃的情况
        MultiDex.install(this);
        // 配置cookie
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .addInterceptor(new LoggerInterceptor("TAG"))
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);


    }
}
