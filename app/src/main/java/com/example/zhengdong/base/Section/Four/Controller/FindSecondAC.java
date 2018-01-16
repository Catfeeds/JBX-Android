/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.General.BaseAC;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Macro.dzRecycleview.DzRecyclerView;
import com.example.zhengdong.base.Macro.dzRecycleview.LoadMoreListener;
import com.example.zhengdong.base.Macro.dzRecycleview.ProgressView;
import com.example.zhengdong.base.Section.First.Controller.IronMasterWC;
import com.example.zhengdong.base.Section.Four.Adapter.FindGridListAdapter;
import com.example.zhengdong.base.Section.Four.Adapter.NewsListAdapter;
import com.example.zhengdong.base.Section.Four.Adapter.SpaceDecoration;
import com.example.zhengdong.base.Section.Four.Model.NewsListModel;
import com.example.zhengdong.base.Section.Four.View.FindJobPupWindow;
import com.example.zhengdong.jbx.R;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;

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
    @BindView(R.id.common_rv)
    DzRecyclerView common_rv;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.new_list_view)
    LinearLayout newListView;
    @BindView(R.id.find_second_activity)
    RelativeLayout findSecondActivity;
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
    private List<NewsListModel.DataBean.EcInformationBean> dataSource;
    private NewsListModel newsListModel;
    private int page = 1;
    private NewsListAdapter newsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_second_ac);
        ButterKnife.bind(this);
        gridType = getIntent().getIntExtra("gridType", -1);
        gridName = getIntent().getStringExtra("gridName");
        initNavigationView();

        // 前四个的界面
        if (gridType < 10) {
            findJobSc.setVisibility(View.VISIBLE);
            initGridView();
            initTabLayView();
        } else {
            // 新闻模块的界面
            newListView.setVisibility(View.VISIBLE);
            initNewsListData(String.valueOf(gridType),1,10,"");
        }


    }

    /**
     * 初始化新闻模块的界面
     */
    // 刷新数据
    private void initNewsListData(String newsID, final int pages, int pageNum, String searchTxt) {
        if (pages == 1) {
            dataSource = null;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("Information_id", newsID);
        map.put("page", String.valueOf(pages));
        map.put("pageNum", String.valueOf(10));
        map.put("titleName", searchTxt);
        HttpRequest.URL_JSONGET_REQUEST(this, UrlUtils.NEWS_LIST_URL, map, "", false, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                newsListModel = new Gson().fromJson(response, NewsListModel.class);
                if (newsListModel.getCode() == 200) {
                    if (pages == 1) {
                        dataSource = newsListModel.getData().getEcInformation();
                        initRefreshView();
                    } else {
                        dataSource.addAll(newsListModel.getData().getEcInformation());
                        newsListAdapter.notifyDataSetChanged();
                    }

                } else {
                    XToast.show(getBaseContext(), "" + newsListModel.getMsg());
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


    // 初始化刷新界面
    private void initRefreshView() {
        swipeLayout.setColorSchemeResources(R.color.navi_back_blue_color, R.color.navi_back_blue_color, R.color.navi_back_blue_color,
                R.color.navi_back_blue_color);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //注意此处
                        dataSource.clear();
                        page = 1;
                        initNewsListData(String.valueOf(gridType), 1, 10, "");
                        common_rv.refreshComplete();
                        swipeLayout.setRefreshing(false);
                        newsListAdapter.notifyDataSetChanged();
                    }
                }, 1000);

            }
        });
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3) {
//            //            @Override
////            public boolean canScrollVertically() {
////            return false;
////        }
//            // SC嵌套ReCV防止数据显示不完整
//            @Override
//            public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
//                int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
//                super.onMeasure(recycler, state, widthSpec, expandSpec);
//            }
//        };
//        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        common_rv.setLayoutManager(linearLayoutManager);
        //设置自定义加载中和到底了效果
        ProgressView progressView = new ProgressView(this);
        progressView.setIndicatorId(0);
        progressView.setIndicatorColor(0xff307FDE);
        common_rv.setFootLoadingView(progressView);
        common_rv.setCanloadMore(true);
        TextView textView = new TextView(this);
        textView.setText("已经到底了~");
        textView.setTextColor(Color.BLACK);
        common_rv.setFootEndView(textView);
        common_rv.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (dataSource.size() >= newsListModel.getData().getTotal()) {
                            common_rv.loadMoreEnd();
                            page = 1;
                            return;
                        }
                        page += 1;
                        initNewsListData(String.valueOf(gridType), page, 10, "");
                        common_rv.loadMoreComplete();
                    }
                }, 1000);
            }
        });
        newsListAdapter = new NewsListAdapter(this, dataSource);
        common_rv.setAdapter(newsListAdapter);
        newsListAdapter.setOnItemClickListener(new NewsListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String content) {
                Intent intent = new Intent(FindSecondAC.this, IronMasterWC.class);
                intent.putExtra("webType", 2);
                intent.putExtra("content", content);
                startActivity(intent);
            }
        });

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

    public void initJumpToNext(String title) {
        Intent intent = new Intent(FindSecondAC.this, FindThreeAC.class);
        intent.putExtra("threeTitle", title);
        startActivity(intent);
    }


}
