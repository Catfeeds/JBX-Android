package com.example.zhengdong.base;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.example.zhengdong.base.Main.MainAC;
import com.example.zhengdong.jbx.R;


public class WelcomeAC extends AppCompatActivity {
    private static final int Time = 2000;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;
    private boolean isFirstIn = false;
    String username;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    allFlipper.setDisplayedChild(1);
                    goHome();
                    break;
                case GO_GUIDE:
                    goHome();
                    break;
                default:
                    break;
            }
        }
    };
    private ViewFlipper allFlipper;
    private RelativeLayout splashLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initState();
        setContentView(R.layout.activity_welcome_ac);
        initView();
        allFlipper = (ViewFlipper) findViewById(R.id.allFlipper);
        // 判断是否是第一次安装APP
        SharedPreferences sharedPreferences = getSharedPreferences("Ucoon", MODE_PRIVATE);
        isFirstIn = sharedPreferences.getBoolean("isFirstIn", true);
        if (!isFirstIn) {
            handler.sendEmptyMessageDelayed(GO_HOME, Time);
        } else {
            handler.sendEmptyMessageDelayed(GO_GUIDE, Time);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstIn", false);
            editor.commit();
        }
    }
    /**
     * 沉浸式状态栏
     */
    private void initState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
    /**
     * 判断是否是登录状况,如果是,跳转到主界面.如果不是,则跳转到登录界面
     * */
    private void goHome() {
        Intent intent = new Intent(WelcomeAC.this, MainAC.class);
        startActivity(intent);
        finish();
    }
    /**
     * 判断是不是第一次打开APP
     * */
    private void goGuide() {
//        Intent intent = new Intent(WelcomeAC.this, GuideAC.class);
//        startActivity(intent);
//        finish();
    }
    private void initView() {
        splashLayout = (RelativeLayout) findViewById(R.id.splashLayout);
    }

}
