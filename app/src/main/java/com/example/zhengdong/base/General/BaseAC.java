/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.General;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zhengdong.base.Macro.NetReceiver;
import com.example.zhengdong.jbx.R;

public class BaseAC extends AppCompatActivity {

    NetReceiver mReceiver = new NetReceiver();
    IntentFilter mFilter = new IntentFilter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_ac);
        // 检测网络状况
        initNetWorkAction();
    }
    /**
     * 检测网络变化情况
     * */
    private void initNetWorkAction() {
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver,mFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
