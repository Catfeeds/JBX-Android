/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.zhengdong.jbx.R;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopCarAC extends AppCompatActivity implements OnTabSelectListener {

    @BindView(R.id.back_lay)
    LinearLayout backLay;
    @BindView(R.id.tab_lay)
    SlidingTabLayout tabLay;
    @BindView(R.id.shop_car_lay)
    LinearLayout shopCarLay;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.put_shop_car_lay)
    LinearLayout putShopCarLay;
    @BindView(R.id.cat_shop_view)
    LinearLayout catShopView;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final ArrayList<String> mTitles = new ArrayList();
    private MyPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car_ac);
        ButterKnife.bind(this);
        initTabLayView();
    }

    private void initTabLayView() {
        mTitles.add("商品");
        mTitles.add("详情");
        for (int i = 0; i < mTitles.size(); i++) {
            mFragments.add(ShopCarFC.getInstance(mTitles.get(i)));
        }
        mAdapter = new MyPagerAdapter(this.getSupportFragmentManager());
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
//        vp.setCurrentItem(4);
//        tab_lay.showDot(4);
    }

    @Override
    public void onTabSelect(int position) {
//        XToast.show(getActivity(),"选中了第"+position+"个!");
    }

    @Override
    public void onTabReselect(int position) {
//        XToast.show(getActivity(),"选中了第"+position+"个!");
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    @OnClick({R.id.back_lay, R.id.put_shop_car_lay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_lay:
                finish();
                break;
            case R.id.put_shop_car_lay:
                break;
            case R.id.shop_car_lay:

                break;
            default:
                break;
        }
    }
}
