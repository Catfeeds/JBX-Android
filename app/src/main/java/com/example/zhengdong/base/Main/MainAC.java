/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Main;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.General.BaseAC;
import com.example.zhengdong.base.Macro.LogUtil;
import com.example.zhengdong.base.Macro.SharedPreferencesUtils;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Section.Four.Controller.FindFC;
import com.example.zhengdong.base.Section.Login.Controller.LoginAC;
import com.example.zhengdong.base.Section.Login.Controller.SelectOrgAC;
import com.example.zhengdong.base.Section.Login.EventBus.LoginEvents;
import com.example.zhengdong.base.Section.Login.Model.LoginModel;
import com.example.zhengdong.base.Section.Three.Controller.IronMasterFC;
import com.example.zhengdong.base.Section.Three.View.IronMasterPupWindow;
import com.example.zhengdong.jbx.R;

import java.util.HashMap;

import com.example.zhengdong.base.Section.Second.Controller.MessageFC;
import com.example.zhengdong.base.Section.Five.Controller.MineFC;
import com.example.zhengdong.base.Section.Four.Controller.NewsFC;
import com.example.zhengdong.base.Section.Three.Controller.ShopFC;
import com.example.zhengdong.base.Section.First.Controller.WorkFC;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAC extends BaseAC implements View.OnClickListener {
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    public static final int PAGE_FIVE = 4;
    public static boolean isOnLine;

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
    private IronMasterPupWindow ironMasterPupWindow;
    private String currentTxt="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        setContentView(R.layout.activity_main_ac);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        initTabView();
        initRefreshTokenData();
    }

    /**
     * 刷新TOKEN
     */
    private void initRefreshTokenData() {
        String user = String.valueOf(SharedPreferencesUtils.getParam(MainAC.this, UrlUtils.APP_USERNAME, ""));
        String paw = String.valueOf(SharedPreferencesUtils.getParam(MainAC.this, UrlUtils.APP_PASSWORD, ""));
        if (TextUtils.isEmpty(user)||TextUtils.isEmpty(paw)){
            isOnLine = false;
            EventBus.getDefault().postSticky(new LoginEvents("1",false));
//            Intent intent = new Intent(MainAC.this, LoginAC.class);
//            startActivity(intent);
        }else {
            HashMap<String, String> map = new HashMap<>();
            map.put("userName", user);
            map.put("password", paw);
            HttpRequest.URL_GET_REQUEST(MainAC.this, UrlUtils.LOGIN_URL, map, "登录中...", false, new HttpInterFace() {
                @Override
                public void URL_REQUEST(String response) {
                    LoginModel loginModel = new Gson().fromJson(response, LoginModel.class);
                    if (loginModel.getCode() == 200) {
                        String token = loginModel.getData().getToken();
                        SharedPreferencesUtils.setParam(MainAC.this, UrlUtils.APP_TOKEN, token);
                        // 判断是否有多个组织机构
                        if (loginModel.getOtherData() != null) {
                            // 默认选择第一个组织机构
//                            startActivityWithTwoValue(SelectOrgAC.class, new Gson().toJson(loginModel));
                            isOnLine = true;
                            initFirstSelectData(loginModel);

                        }else {
                            isOnLine = false;
                            EventBus.getDefault().postSticky(new LoginEvents("",false));
                        }
                    } else {
                    }
                }

                @Override
                public void NOCONNECTION() {

                }

                @Override
                public void BEFORE() {

                }

                @Override
                public void AFTER() {

                }
            });
        }
    }

    /**
     * 默认选择第一个组织机构
     *
     * @param loginModel*/
    private void initFirstSelectData(LoginModel loginModel) {
        String org_id = String.valueOf(SharedPreferencesUtils.getParam(MainAC.this, UrlUtils.APP_ORANGE_ID,""));
        HashMap<String,String> map = new HashMap<>();
        map.put("token",loginModel.getData().getToken());
        map.put("org_id",org_id);
        HttpRequest.URL_REQUEST(this, map, UrlUtils.SETTING_ORGAN_ID_URL, true, "", new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                LoginModel resopnseModel = new Gson().fromJson(response,LoginModel.class);
                if (resopnseModel.getCode() == 200){
                    isOnLine = true;
                    EventBus.getDefault().postSticky(new LoginEvents("1",true));
                }else {
                    XToast.show(getBaseContext(),""+resopnseModel.getMsg());
                    isOnLine = false;
                    EventBus.getDefault().postSticky(new LoginEvents("1",false));
                }
            }
            @Override
            public void NOCONNECTION() {

            }
            @Override
            public void BEFORE() {

            }

            @Override
            public void AFTER() {

            }
        });
    }

    private void startActivityWithTwoValue(Class<?> cls, String value) {
        Intent intent = new Intent(MainAC.this, cls);
        intent.putExtra("values", value);
        startActivity(intent);
    }

    //订阅方法，当接收到事件的时候，会调用该方法  threadMode = ThreadMode.MAIN
    @Subscribe()
    public void onEvent(MessageEvent messageEvent) {
        currentTxt = messageEvent.getMessage();
        firstTxt.setText(messageEvent.getMessage());
    }

    private void initTabView() {
        fragments.put(PAGE_ONE, new WorkFC());
        fragments.put(PAGE_TWO, new MessageFC());
        fragments.put(PAGE_THREE, null);
        fragments.put(PAGE_FOUR, new FindFC());
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
        EventBus.getDefault().unregister(this);
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
//                changeTab(PAGE_THREE);
                ironMasterPupWindow = new IronMasterPupWindow(MainAC.this, itemsOnClick);
                ironMasterPupWindow.showAtLocation(MainAC.this.findViewById(R.id.activity_main_ac), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
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

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            ironMasterPupWindow.dismiss();
            switch (v.getId()) {
//                case R.id.publish_lay:
//                    startActivity(PublishPublishAC.class);
//                    break;
//                case R.id.care_lay:
//                    startActivity(CuringAC.class);
////                    startActivity(FreeAppraisaAC.class);
//                    break;
//                case R.id.rec_lay:
//                    startActivity(PublishBuyPlusAC.class);
//                    break;
                default:
                    break;
            }
        }

    };
}
