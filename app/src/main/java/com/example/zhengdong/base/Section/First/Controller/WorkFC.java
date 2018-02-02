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
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.zhengdong.base.Macro.DesUtils;
import com.example.zhengdong.base.Macro.LogUtil;
import com.example.zhengdong.base.Macro.SharedPreferencesUtils;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Main.MainAC;
import com.example.zhengdong.base.Main.MessageEvent;
import com.example.zhengdong.base.Section.First.Adapter.AdvitiseListAdapter;
import com.example.zhengdong.base.Section.First.Adapter.WorkListAdapter;
import com.example.zhengdong.base.Section.First.Model.LocationListModel;
import com.example.zhengdong.base.Section.Login.Controller.LoginAC;
import com.example.zhengdong.base.Section.Login.EventBus.LoginEvents;
import com.example.zhengdong.base.Section.Login.Model.LoginModel;
import com.example.zhengdong.base.Section.Three.Adapter.ShopListAdapter;
import com.example.zhengdong.jbx.R;
import com.google.gson.Gson;
import com.zaaach.citypicker.CityPickerActivity;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zhy.com.highlight.HighLight;
import zhy.com.highlight.interfaces.HighLightInterface;
import zhy.com.highlight.position.OnBottomPosCallback;
import zhy.com.highlight.position.OnLeftPosCallback;
import zhy.com.highlight.position.OnRightPosCallback;
import zhy.com.highlight.position.OnTopPosCallback;
import zhy.com.highlight.shape.BaseLightShape;
import zhy.com.highlight.shape.CircleLightShape;
import zhy.com.highlight.shape.OvalLightShape;
import zhy.com.highlight.shape.RectLightShape;
import zhy.com.highlight.view.HightLightView;

import static android.app.Activity.RESULT_OK;
import static android.system.Os.remove;

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
    private View view = null;
    String[] titleArray = {"管理员控制台", "我常用的", "业务汇报", "客户/渠道管理", "经营统计"};
    private WorkListAdapter workListAdapter;
    private String app_name = "";
    private LocationManager locationManager = null;
    private double latitude = 0;
    private double longitude = 0;
    private static final int REQUEST_CODE_PICK_CITY = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            double[] data = (double[]) msg.obj;
            XToast.show(getContext(), "当前的经纬度为" + data[0]);
            LogUtil.e("当前的经纬度为" + data[0]);
            initCurrentCityName(data);
        }
    };
    private boolean isMarket = false;
    private ShopListAdapter shopListAdapter;
    private boolean isLogin = false;
    private HighLight mHightLight;

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
                    locationPic.setVisibility(View.GONE);
                    locationMorePic.setVisibility(View.VISIBLE);
                    locationTxt.setText(locationListModel.getRegeocode().getAddressComponent().getCity() + "");
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
            initGetLocationView();
            initAdvertismentRecycleView();
            // 商城
            marketView.setVisibility(View.GONE);
            initMarketRecycleView();
        }
        return view;
    }

    private void initViewShowOrHide() {
        // 根据权限来判断用户可以看到的界面
        if (!isLogin) {
            // 未登录  广告 + 商城
            // 广告
            adView.setVisibility(View.VISIBLE);
            initAdvertismentNaviTitle();
            initGetLocationView();
            initAdvertismentRecycleView();
            // 商城
            marketView.setVisibility(View.GONE);
            initMarketRecycleView();
        } else {
            // 登录  SAS + 商城
            adView.setVisibility(View.GONE);
            orgiView.setVisibility(View.VISIBLE);
            initWorkNavigationView();
            initRV();
            // 商城
            marketView.setVisibility(View.GONE);
            initMarketRecycleView();
        }
    }


    //订阅方法，当接收到事件的时候，会调用该方法 threadMode = ThreadMode.MAIN
    @Subscribe(sticky = true)
    public void onLoginEvents(LoginEvents loginEvents) {
        isLogin = loginEvents.isLogin();
        // 通知刷选界面
        LogUtil.e("登录状态:", loginEvents.getMessage() + loginEvents.isLogin());
        initViewShowOrHide();
    }

    /**
     * 初始化广告模块界面
     */
    private void initAdvertismentRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adRv.setLayoutManager(linearLayoutManager);
        final AdvitiseListAdapter advitiseListAdapter = new AdvitiseListAdapter(getActivity(), null);
        adRv.setAdapter(advitiseListAdapter);
        advitiseListAdapter.setOnItemClickListener(new AdvitiseListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String i, String position, String name) {

            }
        });

    }

    /**
     * 商城的头部
     */
    private void initMarketNavigationView() {
        naviTitleTxt.setText("聚不锈云商城");
        naviBackLay.setVisibility(View.GONE);
        naviRightPicLay.setVisibility(View.VISIBLE);
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
        naviRightPicLay.setVisibility(View.VISIBLE);
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
        naviRightPicLay.setVisibility(View.VISIBLE);
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
        locationPic.setVisibility(View.VISIBLE);
        locationMorePic.setVisibility(View.GONE);
        MPermissions.requestPermissions(WorkFC.this, 4, Manifest.permission.ACCESS_COARSE_LOCATION);
        naviBackLay.setOnClickListener(new View.OnClickListener() {
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
                XToast.show(getContext(), "当前选择：" + city);
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
//        Toast.makeText(getActivity(), "用户拒绝", Toast.LENGTH_SHORT).show();
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
                XToast.show(getActivity(), "点击了第" + index + "行第" + position + "个" + ",名字是" + name);
                initJumpToSectionWithPost(index, position);
            }
        });

    }

    // 跳转
    private void initJumpToSectionWithPost(int index, int position) {
        if (index == 0 && position == 0) {
            // 组织架构
            Intent intent = new Intent(getActivity(), CommonAC.class);
            startActivity(intent);
        } else if (index == 1 && position == 3) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
            String key = sdf.format(new Date());
//            XToast.show("the date: " + key);
            String url = "";

            try {
                String desId = DesUtils.encrypt("73", key);
                url = UrlUtils.IRON_MASTER_URL + "/GCGL/html/wap/gc/html/login2.html?u=" + desId + "&client=android";
            } catch (Exception e) {
            }
            Intent intent = new Intent(getActivity(), IronMasterWC.class);
            intent.putExtra("webType", 1);
            intent.putExtra("url", url);
            startActivity(intent);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.navi_right_pic_lay)
    public void onViewClicked() {
        if (!isLogin) {
            if (!isMarket) {
                isMarket = true;
                marketView.setVisibility(View.VISIBLE);
                adView.setVisibility(View.GONE);
                initMarketNavigationView();
                EventBus.getDefault().post(new MessageEvent("商家"));
            } else {
                isMarket = false;
                marketView.setVisibility(View.GONE);
                adView.setVisibility(View.VISIBLE);
                initAdvertismentNaviTitle();
                EventBus.getDefault().post(new MessageEvent("首页"));
            }
        } else {
            if (!isMarket) {
                isMarket = true;
                marketView.setVisibility(View.VISIBLE);
                orgiView.setVisibility(View.GONE);
                initRV();
                initMarketNavigationView();
                EventBus.getDefault().post(new MessageEvent("商家"));
            } else {
                isMarket = false;
                marketView.setVisibility(View.GONE);
                orgiView.setVisibility(View.VISIBLE);
                initWorkNavigationView();
                EventBus.getDefault().post(new MessageEvent("工作"));
            }
        }

    }


    /**
     * 显示 next模式 我知道了提示高亮布局
     *
     * @param view id为R.id.iv_known的控件
     */
    public void showNextKnownTipView(View view) {
        mHightLight = new HighLight(getActivity())//
                .autoRemove(false)//设置背景点击高亮布局自动移除为false 默认为true
//                .intercept(false)//设置拦截属性为false 高亮布局不影响后面布局的滑动效果
                .intercept(true)//拦截属性默认为true 使下方ClickCallback生效
                .enableNext()//开启next模式并通过show方法显示 然后通过调用next()方法切换到下一个提示布局，直到移除自身
//                .setClickCallback(new HighLight.OnClickCallback() {
//                    @Override
//                    public void onClick() {
//                        Toast.makeText(MainActivity.this, "clicked and remove HightLight view by yourself", Toast.LENGTH_SHORT).show();
//                        remove(null);
//                    }
//                })
                .anchor(view.findViewById(R.id.navi_right_pic_lay))//如果是Activity上增加引导层，不需要设置anchor
                .addHighLight(R.id.navi_back_lay, R.layout.info_known, new OnLeftPosCallback(45), new RectLightShape(0, 0, 15, 0, 0))//矩形去除圆角
                .addHighLight(R.id.first_lay, R.layout.info_known, new OnRightPosCallback(5), new BaseLightShape(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()), TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()), 0) {
                    @Override
                    protected void resetRectF4Shape(RectF viewPosInfoRectF, float dx, float dy) {
                        //缩小高亮控件范围
                        viewPosInfoRectF.inset(dx, dy);
                    }

                    @Override
                    protected void drawShape(Bitmap bitmap, HighLight.ViewPosInfo viewPosInfo) {
                        //custom your hight light shape 自定义高亮形状
                        Canvas canvas = new Canvas(bitmap);
                        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                        paint.setDither(true);
                        paint.setAntiAlias(true);
                        //blurRadius必须大于0
                        if (blurRadius > 0) {
                            paint.setMaskFilter(new BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.SOLID));
                        }
                        RectF rectF = viewPosInfo.rectF;
                        canvas.drawOval(rectF, paint);
                    }
                })
                .addHighLight(R.id.first_lay, R.layout.info_known, new OnTopPosCallback(), new CircleLightShape())
                .addHighLight(view, R.layout.info_known, new OnBottomPosCallback(10), new OvalLightShape(5, 5, 20))
                .setOnRemoveCallback(new HighLightInterface.OnRemoveCallback() {//监听移除回调
                    @Override
                    public void onRemove() {
                        Toast.makeText(getActivity(), "The HightLight view has been removed", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnShowCallback(new HighLightInterface.OnShowCallback() {//监听显示回调
                    @Override
                    public void onShow(HightLightView hightLightView) {
                        Toast.makeText(getActivity(), "The HightLight view has been shown", Toast.LENGTH_SHORT).show();
                    }
                }).setOnNextCallback(new HighLightInterface.OnNextCallback() {
                    @Override
                    public void onNext(HightLightView hightLightView, View targetView, View tipView) {
                        // targetView 目标按钮 tipView添加的提示布局 可以直接找到'我知道了'按钮添加监听事件等处理
                        Toast.makeText(getActivity(), "The HightLight show next TipView，targetViewID:" + (targetView == null ? null : targetView.getId()) + ",tipViewID:" + (tipView == null ? null : tipView.getId()), Toast.LENGTH_SHORT).show();
                    }
                });
        mHightLight.show();
    }

    public  void showKnownTipView(View view)
    {
        mHightLight = new HighLight(getActivity())//
                .autoRemove(false)//设置背景点击高亮布局自动移除为false 默认为true
                .intercept(false)//设置拦截属性为false 高亮布局不影响后面布局的滑动效果 而且使下方点击回调失效
                .setClickCallback(new HighLight.OnClickCallback() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick() {
//                        Toast.makeText(MainActivity.this, "clicked and remove HightLight view by yourself", Toast.LENGTH_SHORT).show();
                        try {
                            remove(null);
                        } catch (ErrnoException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .anchor(view.findViewById(R.id.fragment_work_view))//如果是Activity上增加引导层，不需要设置anchor
                .addHighLight(R.id.navi_right_pic_lay,R.layout.info_known,new OnLeftPosCallback(45),new RectLightShape())
//                .addHighLight(R.id.btn_light,R.layout.info_known,new OnRightPosCallback(5),new CircleLightShape(0,0,0))
//                .addHighLight(R.id.btn_bottomLight,R.layout.info_known,new OnTopPosCallback(),new CircleLightShape())
                .addHighLight(view,R.layout.info_known,new OnBottomPosCallback(10),new OvalLightShape(5,5,20));
        mHightLight.show();

//        //added by isanwenyu@163.com 设置监听器只有最后一个添加到HightLightView的knownView响应了事件
//        //优化在布局中声明onClick方法 {@link #clickKnown(view)}响应所有R.id.iv_known的控件的点击事件
//        View decorLayout = mHightLight.getHightLightView();
//        ImageView knownView = (ImageView) decorLayout.findViewById(R.id.iv_known);
//        knownView.setOnClickListener(new View.OnClickListener()
//          {
//            @Override
//            public void onClick(View view) {
//                remove(null);
//            }
//        });
    }

}
