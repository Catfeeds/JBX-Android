package com.example.zhengdong.base.Section.Login.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.General.BaseAC;
import com.example.zhengdong.base.Macro.SharedPreferencesUtils;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Section.Login.Adapter.OrganListAdapter;
import com.example.zhengdong.base.Section.Login.EventBus.LoginEvents;
import com.example.zhengdong.base.Section.Login.Model.LoginModel;
import com.example.zhengdong.base.Section.Login.Model.OrganListModel;
import com.example.zhengdong.jbx.R;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectOrgAC extends BaseAC {

    @BindView(R.id.navi_back_lay)
    LinearLayout naviBackLay;
    @BindView(R.id.navi_title_txt)
    TextView naviTitleTxt;
    @BindView(R.id.navi_title_lay)
    LinearLayout naviTitleLay;
    @BindView(R.id.navi_right_txt)
    TextView naviRightTxt;
    @BindView(R.id.navi_right_lay)
    LinearLayout naviRightLay;
    @BindView(R.id.navi_right_pic_lay)
    LinearLayout naviRightPicLay;
    @BindView(R.id.title_bg)
    RelativeLayout titleBg;
    @BindView(R.id.org_rv)
    RecyclerView orgRv;
    private LoginModel loginModel;
    private String organID="";
    private String organName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_org_ac);
        ButterKnife.bind(this);
//        EventBus.getDefault().register(SelectOrgAC.this);
        initGetData();
        initNavigationView();
    }

    private void initGetData() {
//        RxBus.getDefault().toObservable()
//                .map(new Function<Object, LoginModel>() {
//                    @Override
//                    public LoginModel apply(Object o) throws Exception {
//                        return (LoginModel)o;
//                    }
//                })
//                .subscribe(new Consumer<LoginModel>() {
//                    @Override
//                    public void accept(LoginModel otherDataBean) throws Exception {
//                        if (otherDataBean != null) {
//                            XToast.show(getBaseContext(),"触底的"+otherDataBean.getData().getUserName());
//                        }
//                    }
//                });
//        compositeDisposable = new CompositeDisposable();
//        RxBus.getInstance().toObservable(LoginModel.class)
//                .subscribe(new Observer<LoginModel>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
////                        LogUtil.e("测试"+(LoginModel)d.getData().getToken());
//                        compositeDisposable.add(d);
//                    }
//
//                    @Override
//                    public void onNext(LoginModel loginModel) {
//                        LogUtil.e("测试"+loginModel.getData().getToken());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//        RxBus.getInstance().toObservale(String.class)
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        XToast.show(getBaseContext(),"接受数据"+s);
//                        LogUtil.e("接受数据"+s);
//                    }
//                });
//        RxBus.getDefault().toObservable(String.class)
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        LogUtil.e("彩色"+s);
//                    }
//                });

        String response = getIntent().getStringExtra("values");
        loginModel = new Gson().fromJson(response,LoginModel.class);
        if (loginModel.getOtherData() == null){
            return;
        }
        initOrgRV();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(SelectOrgAC.this);
    }

    private void initNavigationView() {
        naviBackLay.setVisibility(View.VISIBLE);
        naviTitleTxt.setText("请选择所在的组织机构");
        naviRightLay.setVisibility(View.VISIBLE);
        naviRightTxt.setText("确定");
    }

    private void initOrgRV() {
        ArrayList<OrganListModel> list = new ArrayList<>();
        for (int i = 0;i<loginModel.getOtherData().size();i++){
            OrganListModel organListModel = new OrganListModel(false,loginModel.getOtherData().get(i));
            list.add(organListModel);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        orgRv.setLayoutManager(linearLayoutManager);
        OrganListAdapter organListAdapter = new OrganListAdapter(this,list);
        orgRv.setAdapter(organListAdapter);
        organListAdapter.setOnItemClickListener(new OrganListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int i, String position, String name) {
                organID = name;
                organName = position;
            }
        });
    }

    @OnClick({R.id.navi_back_lay, R.id.navi_right_lay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.navi_back_lay:
                finish();
                break;
            case R.id.navi_right_lay:
                initSetOrganID();
                break;
        }
    }

    // 设置组织机构ID
    private void initSetOrganID() {
        if (TextUtils.isEmpty(organID)){
            XToast.show(getBaseContext(),"请选择一个组织机构!");
            return;
        }
        HashMap<String,String> map = new HashMap<>();
        map.put("token",loginModel.getData().getToken());
        map.put("org_id",organID);
        HttpRequest.URL_REQUEST(this, map, UrlUtils.SETTING_ORGAN_ID_URL, true, "", new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                LoginModel resopnseModel = new Gson().fromJson(response,LoginModel.class);
                if (resopnseModel.getCode() == 200){
                    XToast.show(getBaseContext(),"设置成功!");
                    SharedPreferencesUtils.setParam(SelectOrgAC.this,UrlUtils.APP_NAME,organName);
                    SharedPreferencesUtils.setParam(SelectOrgAC.this,UrlUtils.APP_ORANGE_ID,organID);
                    EventBus.getDefault().post(new LoginEvents("登录成功!",true));
                    finish();
                }else {
                    XToast.show(getBaseContext(),""+loginModel.getMsg());
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
