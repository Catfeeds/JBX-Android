package com.example.zhengdong.base.Macro;

import android.content.Context;

/**
 * Created by cfwifine on 16/10/24.
 */
public class AppConfig {

    public static Context applicationContext;
    public static boolean idDebug=false;

    public static Context getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(Context applicationContext) {
        AppConfig.applicationContext = applicationContext;
    }
}
