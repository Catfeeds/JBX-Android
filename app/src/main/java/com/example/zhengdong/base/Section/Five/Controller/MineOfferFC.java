/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Five.Controller;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhengdong.base.Macro.LogUtil;
import com.example.zhengdong.base.Macro.dzRecycleview.DzRecyclerView;
import com.example.zhengdong.base.Macro.dzRecycleview.LoadMoreListener;
import com.example.zhengdong.base.Macro.dzRecycleview.ProgressView;
import com.example.zhengdong.base.Section.Five.Adapter.MineOfferListAdapter;
import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineOfferFC extends Fragment {


    @BindView(R.id.common_rv)
    DzRecyclerView common_rv;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    Unbinder unbinder;
    private View view;
    private String currentID = "";
    private int ids = -1;
    private MineOfferListAdapter mineOfferListAdapter;
    private int types=0;

    public MineOfferFC() {
        // Required empty public constructor
    }

    public static MineOfferFC getInstance(int ids, String currentID) {
        MineOfferFC sf = new MineOfferFC();
        sf.currentID = currentID;
        sf.ids = ids;
        return sf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_mine_offer_fc, container, false);
            unbinder = ButterKnife.bind(this, view);
            LogUtil.e("当前的页面为第", ids + "页的" + currentID);
            if (ids == 1 && currentID.equals("待报价")){
                types = 1;
            }else if (ids == 1 && currentID.equals("已报价")){
                types = 2;
            }else if (ids == 2 && currentID.equals("待报价")){
                types = 3;
            }else if (ids == 2 && currentID.equals("已报价")){
                types = 4;
            }
            initRView();
        }
        return view;
    }

    /**
     * 初始化recycleview
     */
    private void initRView() {
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        common_rv.setLayoutManager(linearLayoutManager);
        //设置自定义加载中和到底了效果
        ProgressView progressView = new ProgressView(getActivity());
        progressView.setIndicatorId(0);
        progressView.setIndicatorColor(0xff307FDE);
        common_rv.setFootLoadingView(progressView);
        common_rv.setCanloadMore(true);
        TextView textView = new TextView(getActivity());
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
        mineOfferListAdapter = new MineOfferListAdapter(getActivity(),null,types);
        common_rv.setAdapter(mineOfferListAdapter);
        mineOfferListAdapter.setOnItemClickListener(new MineOfferListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int cellType) {
                Intent intent = new Intent(getActivity(),MineOfferSecondAC.class);
                intent.putExtra("offerType",types);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
