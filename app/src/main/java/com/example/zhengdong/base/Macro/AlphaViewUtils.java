/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Macro;

import android.app.Activity;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by zheng.dong on 2018/1/9.
 */

public class AlphaViewUtils {
    public static Window mWindow;
    // 设置灰度级别
    /**
     * 设置背景灰色程度
     *
     * @param level 0.0f-1.0f
     */
    public static void setBackGroundLevel(Context context,float level) {
        mWindow = ((Activity) context).getWindow();
        WindowManager.LayoutParams params = mWindow.getAttributes();
        params.alpha = level;
        mWindow.setAttributes(params);
    }

}
