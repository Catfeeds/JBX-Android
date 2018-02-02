package com.example.zhengdong.base.APIManager;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
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

        /***
         * 初始化友盟各个平台的KEY
         * */
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx06f743d492caa4c3", "e380fe2466a5413506b1ea0860c7ac93");
        PlatformConfig.setQQZone("1106627131", "1Rb55kS7nsSY6JBR");
//        PlatformConfig.setSinaWeibo("3613900046", "7adc9187e699e83330c28d8b4abafb2f","http://sns.whalecloud.com");
//        PlatformConfig.setDing("dingoaw1zc3hnitsoab1qb");
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
