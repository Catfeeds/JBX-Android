/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.zhengdong.base.Section.Work.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.Macro.DesUtils;
import com.example.zhengdong.base.Macro.SharedPreferencesUtils;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.jbx.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.example.zhengdong.base.Section.Login.Controller.LoginAC;
import com.example.zhengdong.base.Section.Work.Adapter.WorkListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    private View view = null;
    String [] titleArray = {"管理员控制台","我常用的","业务汇报","客户/渠道管理","经营统计"};
    private WorkListAdapter workListAdapter;
    private String app_name="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_work_fc, container, false);
            ButterKnife.bind(this, view);
            initConfigurationNaviTitle();
        }
        return view;
    }

    // 配置导航栏
    private void initConfigurationNaviTitle() {
        app_name = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), UrlUtils.APP_NAME,""));
        if (!TextUtils.isEmpty(app_name)){
            naviTitleTxt.setText(app_name);
        }else {
            naviTitleTxt.setText("厦门钢鲸科技股份有限公司");
        }
        naviTitleTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginAC.class);
                startActivity(intent);
            }
        });
        initRV();
    }

    // 配置recycleview
    private void initRV() {
        // 配置数据源
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i<titleArray.length;i++){
            arrayList.add(titleArray[i]);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        workRv.setLayoutManager(linearLayoutManager);
        workListAdapter = new WorkListAdapter(getActivity(),arrayList);
        workRv.setAdapter(workListAdapter);
        workListAdapter.setOnItemClickListener(new WorkListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int index, int position, String name) {
                XToast.show(getActivity(),"点击了第"+index+"行第"+position+"个"+",名字是"+name);
                initJumpToSectionWithPost(index,position);
            }
        });

    }

    // 跳转
    private void initJumpToSectionWithPost(int index, int position) {
        if (index == 0 && position == 0){
            // 组织架构
            Intent intent = new Intent(getActivity(),CommonAC.class);
            startActivity(intent);
        }else if (index == 1 && position == 3){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHH");
            String key = sdf.format(new java.util.Date());
//            XToast.show("the date: " + key);
            String url = "";

            try {
                String desId = DesUtils.encrypt("73", key);
                url = UrlUtils.IRON_MASTER_URL + "/GCGL/html/wap/gc/html/login2.html?u=" + desId + "&client=android";
            }catch (Exception e){
            }
            Intent intent = new Intent(getActivity(),IronMasterWC.class);
            intent.putExtra("webType",1);
            intent.putExtra("url",url);
            startActivity(intent);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
