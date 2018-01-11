/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.zhengdong.base.General.BaseAC;
import com.example.zhengdong.base.Section.Four.Adapter.FindGridListAdapter;
import com.example.zhengdong.base.Section.Four.Adapter.SpaceDecoration;
import com.example.zhengdong.base.Section.Four.View.FindJobPupWindow;
import com.example.zhengdong.jbx.R;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindSecondAC extends BaseAC {


    @BindView(R.id.navi_title_txt)
    TextView naviTitleTxt;
    @BindView(R.id.navi_title_lay)
    LinearLayout naviTitleLay;
    @BindView(R.id.navi_right_txt)
    TextView naviRightTxt;

    @BindView(R.id.navi_right_pic_lay)
    LinearLayout naviRightPicLay;
    @BindView(R.id.title_bg)
    RelativeLayout titleBg;
    @BindView(R.id.right_pic)
    ImageView rightPic;
    @BindView(R.id.navi_back_lay)
    LinearLayout naviBackLay;
    @BindView(R.id.navi_right_lay)
    LinearLayout naviRightLay;
    @BindView(R.id.title_view_lay)
    LinearLayout titleViewLay;
    @BindView(R.id.find_job_sc)
    LinearLayout findJobSc;
    @BindView(R.id.find_second_rv)
    RecyclerView findSecondRv;
    @BindView(R.id.tab_lay)
    SlidingTabLayout tabLay;
    @BindView(R.id.vp)
    ViewPager vp;
    private int gridType = -1;
    private String gridName = "";
    private FindJobPupWindow findJobPupWindow;
    public int[] image = {
            R.drawable.icon_fire, R.drawable.icon_fire, R.drawable.icon_fire, R.drawable.icon_fire,
            R.drawable.icon_fire, R.drawable.icon_fire, R.drawable.icon_fire, R.drawable.icon_fire
    };
    public String[] txt = {
            "切割师傅", "折板师傅", "销售人员", "仓管人员",
            "配送人员", "财务人员", "后勤人员", "制图人员"
    };
    private List<Map<String, Object>> data_list = new ArrayList<Map<String, Object>>();
    private String[] mTitles = {
            "为你推荐", "热门岗位", "求职专场"
    };
    private ArrayList<FindJobFC> mFragments = new ArrayList<FindJobFC>();
    private MyPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_second_ac);
        ButterKnife.bind(this);
        gridType = getIntent().getIntExtra("gridType", -1);
        gridName = getIntent().getStringExtra("gridName");
        initNavigationView();
        initGridView();
        initTabLayView();
    }


    private void initGridView() {
        getData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        findSecondRv.setLayoutManager(gridLayoutManager);
        findSecondRv.addItemDecoration(new SpaceDecoration(4, 2, true));
        findSecondRv.setHasFixedSize(true);
        FindGridListAdapter findGridListAdapter = new FindGridListAdapter(this, data_list);
        findSecondRv.setAdapter(findGridListAdapter);
        findGridListAdapter.setOnItemClickListener(new FindGridListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String name, int i) {

            }
        });
    }

    public List<Map<String, Object>> getData() {
        //cion和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < txt.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", image[i]);
            map.put("text", txt[i]);
            data_list.add(map);
        }

        return data_list;
    }

    private void initNavigationView() {
        naviBackLay.setVisibility(View.VISIBLE);
        naviTitleTxt.setText(gridName);
        if (gridType == 1) {
            naviRightPicLay.setVisibility(View.VISIBLE);
            rightPic.setBackgroundResource(R.drawable.icon_job_user);
        }
    }

    private void initTabLayView() {
        for (int i = 0; i < mTitles.length; i++) {
            mFragments.add(new FindJobFC());
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
//        vp.setCurrentItem(4);
//        tab_lay.showDot(4);
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
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    @OnClick({R.id.navi_back_lay, R.id.navi_right_pic_lay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.navi_back_lay:
                finish();
                break;
            case R.id.navi_right_pic_lay:
                initRightPopWindowView();
                break;
            default:
                break;
        }
    }

    // 求职招聘 右侧点击下拉框
    private void initRightPopWindowView() {
        findJobPupWindow = new FindJobPupWindow(FindSecondAC.this, itemsOnClick);
        findJobPupWindow.showAtLocation(FindSecondAC.this.findViewById(R.id.find_second_activity), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            findJobPupWindow.dismiss();
            switch (v.getId()) {
                case R.id.mine_job_lay:
                    initJumpToNext("我的求职");
                    break;
                case R.id.mine_hr_lay:
                    initJumpToNext("我要招人");
                    break;
                case R.id.mine_word_lay:
                    // 我的简历
                    initJumpToNext("我的简历");
                    break;
                default:
                    break;
            }
        }

    };
    public void initJumpToNext(String title){
        Intent intent = new Intent(FindSecondAC.this,FindThreeAC.class);
        intent.putExtra("threeTitle",title);
        startActivity(intent);
    }


}
