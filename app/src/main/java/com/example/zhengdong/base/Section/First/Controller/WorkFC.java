/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.zhengdong.base.Section.First.Controller;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.system.ErrnoException;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.Macro.CountAnimationTextView;
import com.example.zhengdong.base.Macro.DesUtils;
import com.example.zhengdong.base.Macro.LogUtil;
import com.example.zhengdong.base.Macro.SharedPreferencesUtils;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Main.MessageEvent;
import com.example.zhengdong.base.Section.First.Adapter.AdvitiseListAdapter;
import com.example.zhengdong.base.Section.First.Adapter.BussListAdapter;
import com.example.zhengdong.base.Section.First.Adapter.TradeListAdapter;
import com.example.zhengdong.base.Section.First.Adapter.WorkListAdapter;
import com.example.zhengdong.base.Section.First.Events.ShopEvent;
import com.example.zhengdong.base.Section.First.Model.AdvertiseListModel;
import com.example.zhengdong.base.Section.First.Model.CityBean;
import com.example.zhengdong.base.Section.First.Model.LocationListModel;
import com.example.zhengdong.base.Section.First.Model.OrganPartmentListModel;
import com.example.zhengdong.base.Section.First.View.RecyclerBanner;
import com.example.zhengdong.base.Section.Login.Controller.LoginAC;
import com.example.zhengdong.base.Section.Login.EventBus.LoginEvents;
import com.example.zhengdong.base.Section.Three.Adapter.ShopListAdapter;
import com.example.zhengdong.jbx.R;
import com.google.gson.Gson;
import com.zaaach.citypicker.CityPickerActivity;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zhy.com.highlight.HighLight;
import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkFC extends Fragment {


    @BindView(R.id.navi_back_lay)
    LinearLayout naviBackLay;
    @BindView(R.id.navi_title_txt)
    TextView naviTitleTxt;
    @BindView(R.id.navi_title_lay)
    LinearLayout naviTitleLay;
    @BindView(R.id.navi_right_lay)
    LinearLayout naviRightLay;
    Unbinder unbinder;
    @BindView(R.id.work_rv)
    RecyclerView workRv;
    @BindView(R.id.location_pic)
    ImageView locationPic;
    @BindView(R.id.location_txt)
    TextView locationTxt;
    @BindView(R.id.location_more_pic)
    ImageView locationMorePic;
    @BindView(R.id.right_pic)
    ImageView rightPic;
    @BindView(R.id.navi_right_pic_lay)
    LinearLayout naviRightPicLay;
    @BindView(R.id.ad_rv)
    RecyclerView adRv;
    @BindView(R.id.ad_view)
    LinearLayout adView;
    @BindView(R.id.shop_rv)
    RecyclerView shopRv;
    @BindView(R.id.market_view)
    LinearLayout marketView;
    @BindView(R.id.orgi_view)
    LinearLayout orgiView;
    @BindView(R.id.title_bg)
    RelativeLayout titleBg;
    @BindView(R.id.right_location_pic)
    ImageView rightLocationPic;
    @BindView(R.id.right_location_txt)
    TextView rightLocationTxt;
    @BindView(R.id.right_location_more_pic)
    ImageView rightLocationMorePic;
    @BindView(R.id.navi_location_lay)
    LinearLayout naviLocationLay;
    @BindView(R.id.ad_banner_rv)
    RecyclerBanner adBannerRv;
    @BindView(R.id.f_first_txt)
    CountAnimationTextView fFirstTxt;
    @BindView(R.id.f_second_txt)
    CountAnimationTextView fSecondTxt;
    @BindView(R.id.f_three_txt)
    CountAnimationTextView fThreeTxt;
    @BindView(R.id.f_four_txt)
    CountAnimationTextView fFourTxt;
    @BindView(R.id.first_cell)
    LinearLayout firstCell;
    @BindView(R.id.ad_second_rv)
    RecyclerView adSecondRv;
    @BindView(R.id.second_cell)
    LinearLayout secondCell;
    @BindView(R.id.ad_three_rv)
    RecyclerView adThreeRv;
    @BindView(R.id.three_cell)
    LinearLayout threeCell;
    @BindView(R.id.fragment_work_view)
    FrameLayout fragmentWorkView;
    private View view = null;
    String[] titleArray = { "我常用的","客户/渠道管理", "经营统计","业务汇报",};
    private WorkListAdapter workListAdapter;
    private String app_name = "";
    private LocationManager locationManager = null;
    private double latitude = 0;
    private double longitude = 0;
    private static final int REQUEST_CODE_PICK_CITY = 0;

    private int countIsFirst = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    double[] data = (double[]) msg.obj;
//                    XToast.show(getContext(), "当前的经纬度为" + data[0]);
                    LogUtil.e("当前的经纬度为" + data[0]);
                    initCurrentCityName(data);
                    break;
                case 1:
//                    XToast.show(getContext(), "数据刷新了"+countIsFirst);
                    countIsFirst += 1;
                    if (adView.getVisibility() == View.VISIBLE) {
                        initAdverData();
                    }
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };
    TimerTask task = new TimerTask() {
        public void run() {
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };

    private boolean isMarket = false;
    private ShopListAdapter shopListAdapter;
    private boolean isLogin = false;
    private HighLight mHightLight;
    private Timer timer = new Timer();
    private int userTotal = 0;
    private int itemTotal = 0;
    private int matchTotal = 0;
    private int infoTotal = 0;
    private AdvitiseListAdapter advitiseListAdapter;
    private AdvertiseListModel.DataBean adverDataSource = null;
    private List<RecyclerBanner.BannerEntity> urls = new ArrayList<>();
    private int lastUserStr = 0;
    private int lastTradeStr = 0;
    private int lastCuoStr = 0;
    private int lastNewsStr = 0;
    private TradeListAdapter tradeListAdapter;

    // 显示当前的城市
    private void initCurrentCityName(double[] data) {
        String l = String.valueOf(data[0]);
        String x = String.valueOf(data[1]);
        String url = "http://restapi.amap.com/v3/geocode/regeo?output=json&location=" + x + "," + l + "&key=940582ca3a1088c191b0b036a124aaac&radius=1000&extensions=all";
        HttpRequest.URL_GETJSON_REQUEST(getActivity(), url, "定位中...", false, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                LocationListModel locationListModel = new Gson().fromJson(response, LocationListModel.class);
                if (locationListModel.getStatus().equals("1")) {
                    XToast.show(getContext(), "定位成功!");
                    rightLocationPic.setVisibility(View.GONE);
                    rightLocationMorePic.setVisibility(View.VISIBLE);
                    rightLocationTxt.setText(locationListModel.getRegeocode().getAddressComponent().getCity() + "");
                } else {
                    XToast.show(getContext(), "定位失败!");
                }
            }

            @Override
            public void BEFORE() {

            }

            @Override
            public void AFTER() {

            }

            @Override
            public void NOCONNECTION() {

            }
        });
    }

    @Override
    @Subscribe
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_work_fc, container, false);
            ButterKnife.bind(this, view);
            // 默认
            adView.setVisibility(View.VISIBLE);
            initAdvertismentNaviTitle();
            if (!isLogin){
                initGetLocationView();
            }
//            initAdverData();
//            if (countIsFirst == 0){
//                timer.schedule(task, 100, 10000);
//            }else {
                timer.schedule(task, 100, 10000);
//            }
            initAdvertismentRecycleView();
            // 商城
            marketView.setVisibility(View.GONE);
            initMarketRecycleView();

            // 防止卡顿
            adRv.setNestedScrollingEnabled(false);
            adSecondRv.setNestedScrollingEnabled(false);
            adThreeRv.setNestedScrollingEnabled(false);

        }
        return view;
    }

    /**
     * 广告页面数据   5s刷新一次,进行数据的动态变化
     */
    private void initAdverData() {
        HttpRequest.URL_JSONGETNOPARAM_REQUEST(getActivity(), UrlUtils.HOME_ADVERTISEMENT_URL, "刷新中...", false, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                AdvertiseListModel advertiseListModel = new Gson().fromJson(response, AdvertiseListModel.class);
                if (advertiseListModel.getCode() == 200) {
                    // 注册用户
                    userTotal = advertiseListModel.getData().getUserTotal();
                    // 交易笔数
                    itemTotal = advertiseListModel.getData().getItemTotal();
                    // 撮合信息
                    matchTotal = advertiseListModel.getData().getMatchinfoTotal();
                    // 平台咨询
                    infoTotal = advertiseListModel.getData().getInformationTotal();
                    adverDataSource = advertiseListModel.getData();
//                    LogUtil.e("开始刷新了"+countIsFirst);
                    if (countIsFirst == 1){
                        // 轮播图
                        urls.clear();
                        for (int i = 0;i<adverDataSource.getTop_pic_list().size();i++){
                            LogUtil.e("轮播图地址为",""+adverDataSource.getTop_pic_list().get(i).getPicUrl());
                            urls.add(new Entity(adverDataSource.getTop_pic_list().get(i).getPicUrl(),""));
                        }
                        adBannerRv.setDatas(urls);

                        // 商家列表
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        adThreeRv.setLayoutManager(linearLayoutManager);
                        BussListAdapter bussListAdapter = new BussListAdapter(getActivity(),adverDataSource);
                        adThreeRv.setAdapter(bussListAdapter);

                    }
                    // 防止第一次刷新的时候网络出错
                    if (countIsFirst>1 && urls.size() == 1){
                        urls.clear();
                        for (int i = 0;i<adverDataSource.getTop_pic_list().size();i++){
                            urls.add(new Entity(adverDataSource.getTop_pic_list().get(i).getPicUrl(),""));
                        }
                        adBannerRv.setDatas(urls);

                        // 商家列表
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        adThreeRv.setLayoutManager(linearLayoutManager);
                        BussListAdapter bussListAdapter = new BussListAdapter(getActivity(),adverDataSource);
                        adThreeRv.setAdapter(bussListAdapter);

                    }
                    // 刷新文字
                    fFirstTxt.setDecimalFormat(new DecimalFormat("###,###,###"))
                            .setAnimationDuration(5000)
                            .countAnimation(lastUserStr, userTotal);
                    fSecondTxt.setDecimalFormat(new DecimalFormat("###,###,###"))
                            .setAnimationDuration(5000)
                            .countAnimation(lastTradeStr, itemTotal);
                    fThreeTxt.setDecimalFormat(new DecimalFormat("###,###,###"))
                            .setAnimationDuration(5000)
                            .countAnimation(lastCuoStr, matchTotal);
                    fFourTxt.setDecimalFormat(new DecimalFormat("###,###,###"))
                            .setAnimationDuration(5000)
                            .countAnimation(lastNewsStr, infoTotal);
                    lastUserStr = userTotal;
                    lastTradeStr = itemTotal;
                    lastCuoStr = matchTotal;
                    lastNewsStr = infoTotal;

//                    SlideInUpAnimator animator = new SlideInUpAnimator(new OvershootInterpolator(1f));
//                    adSecondRv.setItemAnimator(animator);
//                    adSecondRv.getItemAnimator().setChangeDuration(1000);
                    // 实时交易
                    tradeListAdapter.setDatas(adverDataSource);


                } else {
                }
            }

            @Override
            public void BEFORE() {

            }

            @Override
            public void AFTER() {

            }

            @Override
            public void NOCONNECTION() {

            }
        });
    }

    private void initViewShowOrHide() {
        // 根据权限来判断用户可以看到的界面
        if (!isLogin) {
            // 未登录  广告 + 商城
            // 广告
            adView.setVisibility(View.VISIBLE);
            initAdvertismentNaviTitle();
//            initAdvertismentRecycleView();
            initGetLocationView();
            // 商城
            marketView.setVisibility(View.GONE);
            initMarketRecycleView();
            locationTxt.setText("");
        } else {
            // 登录  SAS + 商城
            adView.setVisibility(View.GONE);
            orgiView.setVisibility(View.VISIBLE);
            naviLocationLay.setVisibility(View.GONE);
            initWorkNavigationView();
            initRV();
            // 商城
            marketView.setVisibility(View.GONE);
            initMarketRecycleView();
            locationTxt.setText("");
        }
    }


    //订阅方法，当接收到事件的时候，会调用该方法 threadMode = ThreadMode.MAIN
    @Subscribe(sticky = true)
    public void onLoginEvents(LoginEvents loginEvents) {
        isLogin = loginEvents.isLogin();
        // 通知刷选界面
//        LogUtil.e("登录状态:", loginEvents.getMessage() + loginEvents.isLogin());
        initViewShowOrHide();
    }

    @Subscribe(sticky = true)
    public void onShopEvents(ShopEvent shopEvent) {
        isMarket = shopEvent.isMarket();
        // 通知刷选界面
        LogUtil.e("点击状态:", shopEvent.getName() + shopEvent.isMarket());
        if (!isLogin) {
            // 未登录  广告 + 商城
            // 广告
            adView.setVisibility(View.GONE);
            // 商城
            marketView.setVisibility(View.VISIBLE);
            initMarketNavigationView();
            initMarketRecycleView();
            naviBackLay.setVisibility(View.VISIBLE);
            locationTxt.setText("首页");
            naviLocationLay.setVisibility(View.VISIBLE);
        } else {
            // 登录  SAS + 商城
            adView.setVisibility(View.GONE);
            orgiView.setVisibility(View.GONE);
            // 商城
            marketView.setVisibility(View.VISIBLE);
            initMarketRecycleView();
            initMarketNavigationView();
            naviBackLay.setVisibility(View.VISIBLE);
            locationTxt.setText("工作");
            naviLocationLay.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 初始化广告模块界面
     */
    private void initAdvertismentRecycleView() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        adRv.setLayoutManager(linearLayoutManager);
//        advitiseListAdapter = new AdvitiseListAdapter(getActivity(), adverDataSource);
//        adRv.setAdapter(advitiseListAdapter);
//        advitiseListAdapter.setOnItemClickListener(new AdvitiseListAdapter.OnItemClickListener() {
//            @Override
//            public void OnItemClick(View view, String i, String position, String name) {
//
//            }
//        });

        urls.add(new Entity("http://oot34wnx6.bkt.clouddn.com/banner1.png",""));
        adBannerRv.setOnPagerClickListener(new RecyclerBanner.OnPagerClickListener() {
            @Override
            public void onClick(RecyclerBanner.BannerEntity entity) {
                LogUtil.e("网址为" + entity.getLink());

            }
        });
        adBannerRv.setDatas(urls);
        // 文字
        fFirstTxt.setDecimalFormat(new DecimalFormat("###,###,###"))
                .setAnimationDuration(5000)
                .countAnimation(0, 0);
        fSecondTxt.setDecimalFormat(new DecimalFormat("###,###,###"))
                .setAnimationDuration(5000)
                .countAnimation(0, 0);
        fThreeTxt.setDecimalFormat(new DecimalFormat("###,###,###"))
                .setAnimationDuration(5000)
                .countAnimation(0, 0);
        fFourTxt.setDecimalFormat(new DecimalFormat("###,###,###"))
                .setAnimationDuration(5000)
                .countAnimation(0, 0);

        // 刷新实时交易
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adSecondRv.setLayoutManager(linearLayoutManager);
        tradeListAdapter = new TradeListAdapter(getActivity(),adverDataSource);
        adSecondRv.setAdapter(tradeListAdapter);
        tradeListAdapter.setOnItemClickListener(new TradeListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String i, String position, String name) {

            }
        });

    }
    private class Entity implements RecyclerBanner.BannerEntity {

        String url;
        String link;

        public Entity(String url, String link) {
            this.url = url;
            this.link = link;
        }

        @Override
        public String getUrl() {
            return url;
        }

        @Override
        public String getLink() {
            return link;
        }
    }

    /**
     * 商城的头部
     */
    private void initMarketNavigationView() {
        naviTitleTxt.setText("聚不锈云商城");
//        naviBackLay.setVisibility(View.GONE);
//        naviRightPicLay.setVisibility(View.VISIBLE);
        rightPic.setBackgroundResource(R.drawable.icon_ad_switch);
        naviTitleTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginAC.class);
                startActivity(intent);
            }
        });
    }

    /**
     * SAS的头部
     */
    private void initWorkNavigationView() {
        naviTitleTxt.setText("聚不锈云服务");
        naviBackLay.setVisibility(View.GONE);
//        naviRightPicLay.setVisibility(View.VISIBLE);
        rightPic.setBackgroundResource(R.drawable.icon_ad_switch);
        naviTitleTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginAC.class);
                startActivity(intent);
            }
        });

    }

    /**
     * 广告的头部
     */
    private void initAdvertismentNaviTitle() {
        naviTitleTxt.setText("聚不锈云平台");
        naviBackLay.setVisibility(View.VISIBLE);
        naviLocationLay.setVisibility(View.VISIBLE);
        rightPic.setBackgroundResource(R.drawable.icon_ad_switch);
        naviTitleTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginAC.class);
                startActivity(intent);
            }
        });

    }

    /**
     * 初始化商家界面
     */
    private void initMarketRecycleView() {
        shopRv.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        shopRv.setLayoutManager(linearLayoutManager);
        shopListAdapter = new ShopListAdapter(getActivity(), null);
        shopRv.setAdapter(shopListAdapter);
        shopListAdapter.setOnItemClickListener(new ShopListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, String name) {

            }
        });
    }

    /**
     * 获取定位 网络IP定位和GPS定位两种
     */
    private void initGetLocationView() {
        naviLocationLay.setVisibility(View.VISIBLE);
        rightLocationMorePic.setVisibility(View.GONE);
        MPermissions.requestPermissions(WorkFC.this, 4, Manifest.permission.ACCESS_COARSE_LOCATION);
        naviLocationLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(),AddressSelectAC.class);
//                startActivity(intent);
                //启动
                startActivityForResult(new Intent(getActivity(), CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);
            }
        });
    }

    //重写onActivityResult方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
//                XToast.show(getContext(), "当前选择：" + city);
                rightLocationMorePic.setVisibility(View.VISIBLE);
                rightLocationPic.setVisibility(View.GONE);
                rightLocationTxt.setText(""+city);
            }
        }
    }

    @PermissionGrant(4)
    public void requestContactSuccess() {
//        Toast.makeText(getActivity(), "用户同意", Toast.LENGTH_SHORT).show();
        // 权限分配完毕,开始定位
        initLocationData();
    }

    @PermissionDenied(4)
    public void requestContactFailed() {
//        Toast.makeText(getActivity(), "打开定位权限失败!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initLocationData() {
        // 进行定位
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean isOpen = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!isOpen) {
            // 提示用户是否打开gps
            new MaterialDialog.Builder(getActivity())
                    .title("温馨提示")
                    .content("是否打开手机GPS定位服务?")
                    .positiveText("去设置")
                    .negativeText("暂不考虑")
                    .positiveColor(getResources().getColor(R.color.skyblue))
                    .negativeColor(getResources().getColor(R.color.skyblue))
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(intent, 1315);
                        }
                    })
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            XToast.show(getContext(), "定位失败!请到地区选择界面选择所在地区!");
                        }
                    })
                    .show();
        }
        new Thread() {
            @Override
            public void run() {
                //获取Location
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {
                    latitude = location.getLatitude(); // 经度
                    longitude = location.getLongitude(); // 纬度
                    double[] data = {latitude, longitude};
                    Message msg = handler.obtainMessage();
                    msg.obj = data;
                    msg.what = 0;
                    handler.sendMessage(msg);
                }
            }
        }.start();
        //监视地理位置变化
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 1, locationListener);

    }

    /**
     * LocationListern监听器
     * 参数：地理位置提供器、监听位置变化的时间间隔、位置变化的距离间隔、LocationListener监听器
     */

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(Location location) {
            //如果位置发生变化,重新显示
            if (location != null) {
                latitude = location.getLatitude(); // 经度
                longitude = location.getLongitude(); // 纬度
                double[] data = {latitude, longitude};
                Message msg = handler.obtainMessage();
                msg.obj = data;
                msg.what = 0;
                handler.sendMessage(msg);
            }
        }
    };


    /**
     * SAS 服务 模块
     */
    // 配置recycleview
    private void initRV() {
        // 配置数据源
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < titleArray.length; i++) {
            arrayList.add(titleArray[i]);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        workRv.setLayoutManager(linearLayoutManager);
        workListAdapter = new WorkListAdapter(getActivity(), arrayList);
        workRv.setAdapter(workListAdapter);
        workListAdapter.setOnItemClickListener(new WorkListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int index, int position, String name) {
//                XToast.show(getActivity(), "点击了第" + index + "行第" + position + "个" + ",名字是" + name);
                initJumpToSectionWithPost(index, position,name);
            }
        });

    }

    // 跳转
    private void initJumpToSectionWithPost(int index, int position, String name) {
        if (index == 0 && position == 0) {
            // 消息通知
            Intent intent = new Intent(getActivity(), WorkSecondAC.class);
            intent.putExtra("POSITIONX",0);
            intent.putExtra("POSITIONY",0);
            intent.putExtra("NAME",name);
            startActivity(intent);
        } else if (index == 0 && position == 3) {
            String tokenx = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), UrlUtils.APP_TOKEN,""));
            String url = UrlUtils.IRON_NEW_MASTER_URL + "?token=" + tokenx;
            Intent intent = new Intent(getActivity(), IronMasterWC.class);
            intent.putExtra("webType", 1);
            intent.putExtra("url", url);
            startActivity(intent);
        } else if (index == 1 && position == 0){
            Intent intent = new Intent(getActivity(), WorkSecondAC.class);
            intent.putExtra("POSITIONX",1);
            intent.putExtra("POSITIONY",0);
            intent.putExtra("NAME",name);
            startActivity(intent);
        } else if (index == 3){
            Intent intent = new Intent(getActivity(), WorkSecondAC.class);
            intent.putExtra("POSITIONX",3);
            intent.putExtra("POSITIONY",2);
            intent.putExtra("NAME",name);
            startActivity(intent);
        } else if (index == 1 && position == 3){
            Intent intent = new Intent(getActivity(), WorkSecondAC.class);
            intent.putExtra("POSITIONX",1);
            intent.putExtra("POSITIONY",3);
            intent.putExtra("NAME",name);
            startActivity(intent);
        }else if (index == 0 && position == 1){
            Intent intent = new Intent(getActivity(), WorkSecondAC.class);
            intent.putExtra("POSITIONX",0);
            intent.putExtra("POSITIONY",1);
            intent.putExtra("NAME",name);
            startActivity(intent);
        }else if (index == 1 && position == 1){
            Intent intent = new Intent(getActivity(), WorkSecondAC.class);
            intent.putExtra("POSITIONX",1);
            intent.putExtra("POSITIONY",1);
            intent.putExtra("NAME",name);
            startActivity(intent);
        } else if (index == 1 && position == 2){
            Intent intent = new Intent(getActivity(), WorkSecondAC.class);
            intent.putExtra("POSITIONX",1);
            intent.putExtra("POSITIONY",2);
            intent.putExtra("NAME",name);
            startActivity(intent);
        } else if (index == 2 && position == 0){
            Intent intent = new Intent(getActivity(), WorkSecondAC.class);
            intent.putExtra("POSITIONX",2);
            intent.putExtra("POSITIONY",0);
            intent.putExtra("NAME",name);
            startActivity(intent);
        }else if (index == 2 && position == 1){
            Intent intent = new Intent(getActivity(), WorkSecondAC.class);
            intent.putExtra("POSITIONX",2);
            intent.putExtra("POSITIONY",1);
            intent.putExtra("NAME",name);
            startActivity(intent);
        }else if (index == 2 && position == 2){
            Intent intent = new Intent(getActivity(), WorkSecondAC.class);
            intent.putExtra("POSITIONX",2);
            intent.putExtra("POSITIONY",2);
            intent.putExtra("NAME",name);
            startActivity(intent);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (timer != null){
            timer.cancel();
        }
    }

    @OnClick(R.id.navi_back_lay)
    public void onViewClicked() {
        if (!isLogin) {
            if (!isMarket) {
                isMarket = true;
                marketView.setVisibility(View.VISIBLE);
                adView.setVisibility(View.GONE);
                initMarketNavigationView();
                EventBus.getDefault().post(new MessageEvent("商城"));
                locationTxt.setText("首页");
            } else {
                isMarket = false;
                marketView.setVisibility(View.GONE);
                adView.setVisibility(View.VISIBLE);
                initAdvertismentNaviTitle();
//                EventBus.getDefault().post(new MessageEvent("首页"));
                locationTxt.setText("");

            }
        } else {
            if (!isMarket) {
                isMarket = true;
                marketView.setVisibility(View.VISIBLE);
                orgiView.setVisibility(View.GONE);
                initRV();
                initMarketNavigationView();
                EventBus.getDefault().post(new MessageEvent("商城"));
                locationTxt.setText("工作");
            } else {
                isMarket = false;
                marketView.setVisibility(View.GONE);
                orgiView.setVisibility(View.VISIBLE);
                initWorkNavigationView();
//                EventBus.getDefault().post(new MessageEvent("工作"));
                locationTxt.setText("");
            }
        }

    }


}
