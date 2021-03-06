/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Macro.dzRecycleview.DzRecyclerView;
import com.example.zhengdong.base.Macro.dzRecycleview.LoadMoreListener;
import com.example.zhengdong.base.Macro.dzRecycleview.ProgressView;
import com.example.zhengdong.base.Section.Four.Adapter.FindThreeListAdapter;
import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindThreeAC extends AppCompatActivity {


    @BindView(R.id.navi_title_txt)
    TextView naviTitleTxt;
    @BindView(R.id.navi_title_lay)
    LinearLayout naviTitleLay;
    @BindView(R.id.navi_right_txt)
    TextView naviRightTxt;

    @BindView(R.id.right_pic)
    ImageView rightPic;
    @BindView(R.id.navi_right_pic_lay)
    LinearLayout naviRightPicLay;
    @BindView(R.id.title_bg)
    RelativeLayout titleBg;
    @BindView(R.id.common_rv)
    DzRecyclerView common_rv;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.navi_back_lay)
    LinearLayout naviBackLay;
    @BindView(R.id.navi_right_lay)
    LinearLayout naviRightLay;
    private String threeTitle = "";
    private FindThreeListAdapter findThreeListAdapter;
    private int currentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_three_ac);
        ButterKnife.bind(this);
        threeTitle = getIntent().getStringExtra("threeTitle");
        switch (threeTitle) {
            case "我的简历":
                currentID = 1;
                break;
            case "我的求职":
                currentID = 2;
                break;
            case "我要招人":
                currentID = 3;
                break;
            default:
                break;
        }
        initNavigationView();
        initRV();
    }

    /**
     * 初始化rv
     */
    private void initRV() {
        // 初始化刷新界面
        swipeLayout.setColorSchemeResources(R.color.navi_back_blue_color, R.color.navi_back_blue_color, R.color.navi_back_blue_color,
                R.color.navi_back_blue_color);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //注意此处
//                        dataSource.clear();
//                        page = 1;
//                        initNewsListData(currentID, 1, 10, "");
//                        common_rv.refreshComplete();
//                        swipeLayout.setRefreshing(false);
//                        newsListAdapter.notifyDataSetChanged();
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
//                        if (dataSource.size() >= newsListModel.getData().getTotal()) {
//                            common_rv.loadMoreEnd();
//                            page = 1;
//                            return;
//                        }
//                        page += 1;
//                        initNewsListData(currentID, page, 10, "");
//                        common_rv.loadMoreComplete();
                    }
                }, 1000);
            }
        });
        findThreeListAdapter = new FindThreeListAdapter(this, null, currentID);
        common_rv.setAdapter(findThreeListAdapter);
        findThreeListAdapter.setOnItemClickListener(new FindThreeListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String name) {

            }
        });

    }

    private void initNavigationView() {
        naviBackLay.setVisibility(View.VISIBLE);
        naviTitleTxt.setText(threeTitle);
        if (currentID == 1||currentID == 3){
            naviRightTxt.setText("添加");
        }
    }


    @OnClick({R.id.navi_back_lay, R.id.navi_right_lay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.navi_back_lay:
                finish();
                break;
            case R.id.navi_right_lay:
                if (currentID == 1||currentID == 3){
                    initJumpToDetail(currentID);
                }
                break;
        }
    }

    private void initJumpToDetail(int current) {
        Intent intent = new Intent(FindThreeAC.this,FindAddAC.class);
        intent.putExtra("currentID",current);
        startActivity(intent);
    }
}
