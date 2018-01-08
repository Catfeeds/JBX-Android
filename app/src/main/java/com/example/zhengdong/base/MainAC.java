package com.example.zhengdong.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengdong.base.APIManager.CheckNetService;
import com.example.zhengdong.base.General.BaseAC;
import com.example.zhengdong.base.Macro.LogUtil;
import com.example.zhengdong.base.Macro.NetReceiver;
import com.example.zhengdong.base.Section.News.Controller.NewsListFC;
import com.example.zhengdong.jbx.R;

import java.util.HashMap;

import com.example.zhengdong.base.Section.Message.Controller.MessageFC;
import com.example.zhengdong.base.Section.Mine.Controller.MineFC;
import com.example.zhengdong.base.Section.News.Controller.NewsFC;
import com.example.zhengdong.base.Section.Shop.Controller.ShopFC;
import com.example.zhengdong.base.Section.Work.Controller.WorkFC;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAC extends BaseAC implements View.OnClickListener {
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    public static final int PAGE_FIVE = 4;

    HashMap<Integer, Fragment> fragments = new HashMap<>();
    @BindView(R.id.first_pic)
    ImageView firstPic;
    @BindView(R.id.first_txt)
    TextView firstTxt;
    @BindView(R.id.first_lay)
    LinearLayout firstLay;
    @BindView(R.id.second_pic)
    ImageView secondPic;
    @BindView(R.id.second_txt)
    TextView secondTxt;
    @BindView(R.id.second_lay)
    LinearLayout secondLay;
    @BindView(R.id.three_pic)
    ImageView threePic;
    @BindView(R.id.three_txt)
    TextView threeTxt;
    @BindView(R.id.three_lay)
    LinearLayout threeLay;
    @BindView(R.id.four_pic)
    ImageView fourPic;
    @BindView(R.id.four_txt)
    TextView fourTxt;
    @BindView(R.id.four_lay)
    LinearLayout fourLay;
    @BindView(R.id.five_pic)
    ImageView fivePic;
    @BindView(R.id.five_txt)
    TextView fiveTxt;
    @BindView(R.id.five_lay)
    LinearLayout fiveLay;
    private int fragmentContentId = R.id.container;
    private int currentTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        setContentView(R.layout.activity_main_ac);
        ButterKnife.bind(this);
        initTabView();
//         启动服务
//        // 启动网络服务
//        Intent intent = new Intent(MainAC.this,CheckNetService.class);
//        MyConnection myConnection = new MyConnection();
////            CheckNetService checkNetService = new CheckNetService();
//        bindService(intent,myConnection, BIND_AUTO_CREATE);

    }


    // 服务监听方法
    public class MyConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtil.e("服务已连接");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtil.e("服务已断开");
        }

        @Override
        public void onBindingDied(ComponentName name) {

        }
    }

    private void initTabView() {
        fragments.put(PAGE_ONE, new WorkFC());
        fragments.put(PAGE_TWO, new MessageFC());
        fragments.put(PAGE_THREE, new ShopFC());
        fragments.put(PAGE_FOUR, new NewsFC());
        fragments.put(PAGE_FIVE, new MineFC());
        firstLay.setOnClickListener(this);
        secondLay.setOnClickListener(this);
        threeLay.setOnClickListener(this);
        fourLay.setOnClickListener(this);
        fiveLay.setOnClickListener(this);
        FragmentTransaction ft = MainAC.this.getSupportFragmentManager().beginTransaction();
        ft.add(fragmentContentId, fragments.get(PAGE_ONE));
        currentTab = PAGE_ONE;
        firstTxt.setTextColor(ContextCompat.getColor(this, R.color.skyblue));
        firstPic.setBackgroundResource(R.drawable.shop_sel_icon);
        ft.commit();
    }

    private void changeTab(int page) {
        if (currentTab == page) {
            return;
        }
        Fragment fragment = fragments.get(page);
        FragmentTransaction ft = MainAC.this.getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            ft.add(fragmentContentId, fragment);
        }
        ft.hide(fragments.get(currentTab));
        ft.show(fragments.get(page));
        changeButtonStatus(currentTab, false);
        currentTab = page;
        changeButtonStatus(currentTab, true);
        if (!this.isFinishing()) {
            ft.commitAllowingStateLoss();
        }
    }

    private void changeButtonStatus(int index, boolean check) {
        switch (index) {
            case PAGE_ONE:
                if (check) {
                    firstPic.setBackgroundResource(R.drawable.shop_sel_icon);
                    firstTxt.setTextColor(ContextCompat.getColor(this, R.color.skyblue));
                } else {
                    firstPic.setBackgroundResource(R.drawable.shop_icon);
                    firstTxt.setTextColor(ContextCompat.getColor(this, R.color.gray_40));
                }
                break;
            case PAGE_TWO:
                if (check) {
                    secondPic.setBackgroundResource(R.drawable.star_sel_icon);
                    secondTxt.setTextColor(ContextCompat.getColor(this, R.color.skyblue));
                } else {
                    secondPic.setBackgroundResource(R.drawable.star_icon);
                    secondTxt.setTextColor(ContextCompat.getColor(this, R.color.gray_40));
                }
                break;
            case PAGE_THREE:
//                if (check) {
                    threePic.setBackgroundResource(R.drawable.job_icon);
                    threeTxt.setTextColor(ContextCompat.getColor(this, R.color.skyblue));
//                } else {
//                    threePic.setBackgroundResource(R.drawable.job_icon);
//                    threeTxt.setTextColor(ContextCompat.getColor(this, R.color.gray_40));
//                }
                break;
            case PAGE_FOUR:
                if (check) {
                    fourPic.setBackgroundResource(R.drawable.find_sel_icon);
                    fourTxt.setTextColor(ContextCompat.getColor(this, R.color.skyblue));
                } else {
                    fourPic.setBackgroundResource(R.drawable.find_icon);
                    fourTxt.setTextColor(ContextCompat.getColor(this, R.color.gray_40));
                }
                break;
            case PAGE_FIVE:
                if (check) {
                    fivePic.setBackgroundResource(R.drawable.my_sel_icon);
                    fiveTxt.setTextColor(ContextCompat.getColor(this, R.color.skyblue));
                } else {
                    fivePic.setBackgroundResource(R.drawable.my_icon);
                    fiveTxt.setTextColor(ContextCompat.getColor(this, R.color.gray_40));
                }
                break;
            default:
                firstPic.setBackgroundResource(R.drawable.shop_sel_icon);
                firstTxt.setTextColor(ContextCompat.getColor(this, R.color.skyblue));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first_lay:
                changeTab(PAGE_ONE);
                break;
            case R.id.second_lay:
                changeTab(PAGE_TWO);
                break;
            case R.id.three_lay:
                changeTab(PAGE_THREE);
                break;
            case R.id.four_lay:
                changeTab(PAGE_FOUR);
                break;
            case R.id.five_lay:
                changeTab(PAGE_FIVE);
                break;
            default:
                break;
        }
    }
}
