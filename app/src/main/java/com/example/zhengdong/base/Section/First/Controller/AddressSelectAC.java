/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.First.Controller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Section.First.Adapter.CityListAdapter;
import com.example.zhengdong.base.Section.First.Adapter.HeaderRecyclerAndFooterWrapperAdapter;
import com.example.zhengdong.base.Section.First.Adapter.ViewHolder;
import com.example.zhengdong.base.Section.First.Model.CityBean;
import com.example.zhengdong.jbx.R;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.TitleItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressSelectAC extends AppCompatActivity {

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
    @BindView(R.id.right_pic)
    ImageView rightPic;
    @BindView(R.id.navi_right_pic_lay)
    LinearLayout naviRightPicLay;
    @BindView(R.id.title_bg)
    RelativeLayout titleBg;
    @BindView(R.id.address_rv)
    RecyclerView addressRv;
    @BindView(R.id.indexBar)
    IndexBar indexBar;
    @BindView(R.id.tvSideBarHint)
    TextView tvSideBarHint;
    private List<CityBean> mData = null;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private CityListAdapter cityListAdapter;
    private TitleItemDecoration mDecoration;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_select_ac);
        ButterKnife.bind(this);
        initNavigationView();
//        initRecycleView();
    }

    /***
     * 初始化数据
     * */
    private void initRecycleView() {
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        addressRv.setLayoutManager(linearLayoutManager);
        cityListAdapter = new CityListAdapter(this,mData);
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(cityListAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
            }
        };
        addressRv.setAdapter(mHeaderAdapter);
        addressRv.addItemDecoration(mDecoration = new TitleItemDecoration(this, mData).setHeaderViewCount(mHeaderAdapter.getHeaderViewCount()));
        mDecoration.setColorTitleBg(Color.parseColor("#f7f7f7"));
        mDecoration.setmTitleHeight(50);
        cityListAdapter.setOnItemClickListener(new CityListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String name, int brandid) {

            }
        });

        // 初始化数据
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100;i++){
            list.add(i,"测试"+i);
        }
        list.add("a");
        list.add("fff");
        initDatas(list);
    }
    private void initDatas(final ArrayList<String> data) {
        //延迟两秒 模拟加载数据中....
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                mData = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    CityBean brandBean = new CityBean();
                    brandBean.setCity(data.get(i).toString());
                    mData.add(brandBean);
                }
                cityListAdapter.setDatas(mData);
                mHeaderAdapter.notifyDataSetChanged();
                indexBar.setmPressedShowTextView(tvSideBarHint)//设置HintTextView
                        .setNeedRealIndex(false)//设置需要真实的索引
                        .setmLayoutManager(linearLayoutManager)//设置RecyclerView的LayoutManager
                        .setmSourceDatas(mData)//设置数据
                        .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount())//设置HeaderView数量
                        .invalidate();
                mDecoration.setmDatas(mData);
            }
        }, 0);

    }

    private void initNavigationView() {
        naviBackLay.setVisibility(View.VISIBLE);
        naviTitleTxt.setText("选择城市");
    }

    @OnClick(R.id.navi_back_lay)
    public void onViewClicked() {
        finish();
    }
}
