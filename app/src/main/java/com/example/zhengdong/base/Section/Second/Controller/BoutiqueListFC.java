/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Second.Controller;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Macro.dzRecycleview.DzRecyclerView;
import com.example.zhengdong.base.Macro.dzRecycleview.LoadMoreListener;
import com.example.zhengdong.base.Macro.dzRecycleview.ProgressView;
import com.example.zhengdong.base.Section.Five.View.StaggerItemSeperateView;
import com.example.zhengdong.base.Section.Second.Adapter.BoutiqueListAdapter;
import com.example.zhengdong.base.Section.Second.Model.BoutiqueListModel;
import com.example.zhengdong.jbx.R;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoutiqueListFC extends Fragment {


    @BindView(R.id.boutique_rv)
    DzRecyclerView boutiqueRv;
    Unbinder unbinder;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.pic)
    ImageView pic;
    @BindView(R.id.txt)
    TextView txt;
    @BindView(R.id.off_line_view)
    LinearLayout offLineView;
    private int pages = 1;
    private BoutiqueListAdapter boutiqueListAdapter;
    private String itemID = "";
    private List<BoutiqueListModel.DataBean.FanexListBean> dataSource;
    private BoutiqueListModel boutiqueListModel;
    private View view;


    public static BoutiqueListFC getInstance(String itemsID) {
        BoutiqueListFC sf = new BoutiqueListFC();
        sf.itemID = itemsID;
        return sf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_boutique_list_fc, container, false);
            ButterKnife.bind(this, view);
            initDataSource(itemID, 1, 10);
            // 给rv添加分割线,而且只能执行一次
            StaggerItemSeperateView staggerItemSeperateView = new StaggerItemSeperateView(13);
            boutiqueRv.addItemDecoration(staggerItemSeperateView);
        }
        return view;
    }

    private void initDataSource(String item, final int page, int pageNum) {
        if (page == 1) {
            dataSource = null;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("fan_type", item);
        map.put("page", String.valueOf(page));
        map.put("pageNum", String.valueOf(pageNum));
        HttpRequest.URL_GET_REQUEST(getActivity(), UrlUtils.BOUTIQUE_LIST_URL, map, "加载中...", false, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                boutiqueListModel = new Gson().fromJson(response, BoutiqueListModel.class);
                if (boutiqueListModel.getCode() == 200) {
                    swipeLayout.setVisibility(View.VISIBLE);
                    offLineView.setVisibility(View.GONE);
                    if (page == 1) {
                        dataSource = boutiqueListModel.getData().getFanexList();
                        if (dataSource.size() == 0){
                            swipeLayout.setVisibility(View.GONE);
                            offLineView.setVisibility(View.VISIBLE);
                            pic.setBackgroundResource(R.drawable.duanwang8);
                            txt.setText("暂无相关数据...");
                        }else {
                            initBoutiqueView();
                        }
                    } else {
                        dataSource.addAll(boutiqueListModel.getData().getFanexList());
                        boutiqueListAdapter.notifyDataSetChanged();
                    }
                } else {
                    XToast.show(getContext(), "" + boutiqueListModel.getMsg());
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
                swipeLayout.setVisibility(View.GONE);
                offLineView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initBoutiqueView() {

        swipeLayout.setColorSchemeResources(R.color.navi_back_blue_color, R.color.navi_back_blue_color, R.color.navi_back_blue_color,
                R.color.navi_back_blue_color);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //注意此处
                        dataSource.clear();
                        pages = 1;
                        initDataSource(itemID, 1, 10);
                        boutiqueRv.refreshComplete();
                        swipeLayout.setRefreshing(false);
                        boutiqueListAdapter.notifyDataSetChanged();
                    }
                }, 1000);

            }
        });

        boutiqueRv.setPadding(13, 13, 13, 13);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        boutiqueRv.setLayoutManager(staggeredGridLayoutManager);


        //设置自定义加载中和到底了效果
        ProgressView progressView = new ProgressView(getActivity());
        progressView.setIndicatorId(0);
        progressView.setIndicatorColor(0xff307FDE);
        boutiqueRv.setFootLoadingView(progressView);
        boutiqueRv.setCanloadMore(true);
        TextView textView = new TextView(getActivity());
        textView.setText("已经到底了~");
        textView.setTextColor(Color.BLACK);
        boutiqueRv.setFootEndView(textView);
        boutiqueRv.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (dataSource.size() >= boutiqueListModel.getData().getTotal()) {
                            boutiqueRv.loadMoreEnd();
                            pages = 1;
                            return;
                        }
                        pages += 1;
                        initDataSource(itemID, pages, 10);
                        boutiqueRv.loadMoreComplete();
                    }
                }, 1000);
            }
        });

        boutiqueListAdapter = new BoutiqueListAdapter(getActivity(), dataSource);
        boutiqueRv.setAdapter(boutiqueListAdapter);
        boutiqueListAdapter.setOnItemClickListener(new BoutiqueListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String graphical_id, String Fanc_id) {
                Intent intent = new Intent(getActivity(), BoutiqueDetailAC.class);
                intent.putExtra("Graphical_id", graphical_id);
                intent.putExtra("Fanc_id", Fanc_id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.off_line_view)
    public void onViewClicked() {
        initDataSource(itemID, 1, 10);
    }
}
