/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Five.Controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.General.BaseAC;
import com.example.zhengdong.base.Section.Four.Controller.FindJobFC;
import com.example.zhengdong.base.Section.Four.Controller.FindSecondAC;
import com.example.zhengdong.jbx.R;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineOfferAC extends BaseAC {

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
    @BindView(R.id.tab_lay)
    SlidingTabLayout tabLay;
    @BindView(R.id.vp)
    ViewPager vp;
    private String titleName="";
    private String[] mTitles = {
            "待报价","已报价"
    };
    private ArrayList<MineOfferFC> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_offer_ac);
        ButterKnife.bind(this);
        titleName = getIntent().getStringExtra("offerName");
        initNavigationView();
        initTabLayView();
    }

    private void initTabLayView() {
        int type = 0;
        if (titleName.equals("我的报价")){
            type = 1;
        }else if (titleName.equals("我的询价")){
            type = 2;
        }
        for (int i = 0; i < mTitles.length; i++) {
            mFragments.add(MineOfferFC.getInstance(type,mTitles[i]));
        }
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mAdapter);
        tabLay.setViewPager(vp);
        tabLay.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                LogUtil.e("选中的页面为",mTitles.get(position));
            }

            @Override
            public void onTabReselect(int position) {
//                LogUtil.e("选中两次的页面为",mTitles.get(position));
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                LogUtil.e("当前滚动的页面为"+position);
            }

            @Override
            public void onPageSelected(int position) {
//                LogUtil.e("当前选中的页面为"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        tabLay.showMsg(1,5);
        tabLay.setMsgMargin(5,65,10);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    private void initNavigationView() {
        naviBackLay.setVisibility(View.VISIBLE);
        naviTitleTxt.setText(titleName);
    }

    @OnClick({R.id.navi_back_lay, R.id.navi_right_pic_lay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.navi_back_lay:
                finish();
                break;
            case R.id.navi_right_pic_lay:
                break;
        }
    }
}
