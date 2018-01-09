/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.First.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.Macro.SharedPreferencesUtils;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Section.First.Adapter.CommonListAdapter;
import com.example.zhengdong.base.Section.First.Events.PassEvent;
import com.example.zhengdong.base.Section.First.Model.OrganPartmentListModel;
import com.example.zhengdong.base.Section.First.View.CustomSheetView;
import com.example.zhengdong.base.Section.First.View.SearchEditText;
import com.example.zhengdong.jbx.R;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommonAC extends AppCompatActivity {

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
    @BindView(R.id.search_view)
    SearchEditText searchView;
    @BindView(R.id.common_rv)
    RecyclerView commonRv;
    @BindView(R.id.add_people_btn)
    Button addPeopleBtn;
    @BindView(R.id.add_partment_btn)
    Button addPartmentBtn;
    private CustomSheetView sheetView;
    private OrganPartmentListModel organPartmentListModel;
    private String app_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_ac);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        app_name = String.valueOf(SharedPreferencesUtils.getParam(CommonAC.this, UrlUtils.APP_NAME, ""));
        initTitleView();
    }

    private void initTitleView() {
        naviBackLay.setVisibility(View.VISIBLE);
        naviTitleTxt.setText(app_name);
        naviRightTxt.setText("批量操作");
        searchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //完成自己的事件
                    initSearchViewData();
                }
                return false;
            }
        });

        initPartmentData();
    }

    // 搜索单个的部门
    private void initSearchViewData() {

    }

    // 获取部门列表
    private void initPartmentData() {
        String token = String.valueOf(SharedPreferencesUtils.getParam(CommonAC.this, UrlUtils.APP_TOKEN, ""));
        String org_id = String.valueOf(SharedPreferencesUtils.getParam(CommonAC.this,UrlUtils.APP_ORANGE_ID,""));
        HashMap<String, String> map = new HashMap<>();
        map.put("parent_dept_id","root");
        map.put("token",token);
        map.put("org_id",org_id);
        HttpRequest.URL_JSONGET_REQUEST(this, UrlUtils.GET_PARENT_PARTMENT_LIST_URL,map,"加载中...", true, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                organPartmentListModel = new Gson().fromJson(response, OrganPartmentListModel.class);
                if (organPartmentListModel.getCode() == 200) {
                    XToast.show(getBaseContext(), "部门获取成功!");
                    initCommonRV();
                } else {
                    XToast.show(getBaseContext(), "" + organPartmentListModel.getMsg());
                }
            }
            @Override
            public void NOCONNECTION() {

            }
            @Override
            public void BEFORE() {
                XToast.show(getBaseContext(), "出现前");
            }

            @Override
            public void AFTER() {
                XToast.show(getBaseContext(), "出现后");
            }
        });
    }

    private void initCommonRV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        commonRv.setLayoutManager(linearLayoutManager);
        CommonListAdapter commonListAdapter = new CommonListAdapter(this, organPartmentListModel);
        commonRv.setAdapter(commonListAdapter);
        commonListAdapter.setOnItemClickListener(new CommonListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String parent_dept_id, String name, String deptid) {
                SharedPreferencesUtils.setParam(CommonAC.this, "second",deptid);
                Intent intentx = new Intent(CommonAC.this, OrganListAC.class);
                intentx.putExtra("dept_id",deptid);
                intentx.putExtra("title",name);
                intentx.putExtra("parent_dept_id",parent_dept_id);
                startActivity(intentx);
            }
        });

    }


    @OnClick({R.id.add_people_btn, R.id.add_partment_btn, R.id.navi_back_lay, R.id.navi_right_lay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_people_btn:
                initAddPeopleView();
                break;
            case R.id.add_partment_btn:
                initAddPartmentView();
                break;
            case R.id.navi_back_lay:
                finish();
                break;
            case R.id.navi_right_lay:
                initBottomSheetView();
                break;
        }
    }

    // 添加员工
    private void initAddPeopleView() {
        Intent intent = new Intent(CommonAC.this, AddtionalAC.class);
        intent.putExtra("type", 1);
        startActivity(intent);
    }

    // 添加部门
    private void initAddPartmentView() {
        Intent intent = new Intent(CommonAC.this, AddtionalAC.class);
        intent.putExtra("type", 2);
        startActivity(intent);
    }
    // 添加部门回调
    @Subscribe
    public void onEventMainThread(PassEvent event) {
//        if (event.getName().equals("1")){
//            initPartmentData();
//        }
    }

    // 批量操作
    private void initBottomSheetView() {
        sheetView = new CustomSheetView.Builder(CommonAC.this)

                .addMenu("批量移动成员", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sheetView.dismiss();
                        Intent intent = new Intent(CommonAC.this, OperationAC.class);
                        intent.putExtra("type", 1);
                        startActivity(intent);
                    }
                }).addMenu("批量删除成员", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sheetView.dismiss();
                        Intent intent = new Intent(CommonAC.this, OperationAC.class);
                        intent.putExtra("type", 2);
                        startActivity(intent);
                    }
                }).create();
        sheetView.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
