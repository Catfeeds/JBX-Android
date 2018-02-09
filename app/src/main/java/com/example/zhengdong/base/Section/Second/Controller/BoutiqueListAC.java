/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Second.Controller;

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

import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Macro.dzRecycleview.DzRecyclerView;
import com.example.zhengdong.base.Macro.dzRecycleview.LoadMoreListener;
import com.example.zhengdong.base.Macro.dzRecycleview.ProgressView;
import com.example.zhengdong.base.Section.First.Controller.IronMasterWC;
import com.example.zhengdong.base.Section.Four.Adapter.NewsListAdapter;
import com.example.zhengdong.base.Section.Second.Adapter.BoutiqueCommentListAdapter;
import com.example.zhengdong.base.Section.Second.Model.CommnetListModel;
import com.example.zhengdong.jbx.R;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BoutiqueListAC extends AppCompatActivity {

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
    @BindView(R.id.common_rv)
    DzRecyclerView common_rv;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    private BoutiqueCommentListAdapter boutiqueCommentListAdapter;
    private List<CommnetListModel.DataBean.FanCommentsListBean> dataSource;
    private int pages = 1;
    private String fanc_id = "";
    private CommnetListModel commnetListModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boutique_list_ac);
        ButterKnife.bind(this);
        naviBackLay.setVisibility(View.VISIBLE);
        naviTitleTxt.setText("评论列表");
//        initRefreshView();
        fanc_id = getIntent().getStringExtra("Fancid");
        initBoutiqueCommentListData(fanc_id, 1, 10);
    }

    /**
     * 查询评论列表
     */
    private void initBoutiqueCommentListData(String fanc_id, final int page, int pageNum) {
        if (page == 1) {
            dataSource = null;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("fanc_id", fanc_id);
        map.put("page", String.valueOf(page));
        map.put("pageNum", String.valueOf(pageNum));
        HttpRequest.URL_GET_REQUEST(this, UrlUtils.BOUTIQUE_COMMENT_LIST_URL, map, "加载中...", true, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                commnetListModel = new Gson().fromJson(response, CommnetListModel.class);
                if (commnetListModel.getCode() == 200) {
                    if (pages == 1) {
                        dataSource = commnetListModel.getData().getFanCommentsList();
                        initRefreshView();
                    } else {
                        dataSource.addAll(commnetListModel.getData().getFanCommentsList());
                        boutiqueCommentListAdapter.notifyDataSetChanged();
                    }

                } else {
                    XToast.show(getBaseContext(), "" + commnetListModel.getMsg());
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
                        pages = 1;
                        initBoutiqueCommentListData(fanc_id, 1, 10);
                        common_rv.refreshComplete();
                        swipeLayout.setRefreshing(false);
                        boutiqueCommentListAdapter.notifyDataSetChanged();
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
                        if (dataSource.size() >= commnetListModel.getData().getTotal()) {
                            common_rv.loadMoreEnd();
                            pages = 1;
                            return;
                        }
                        pages += 1;
                        initBoutiqueCommentListData(fanc_id, pages, 10);
                        common_rv.loadMoreComplete();
                    }
                }, 1000);
            }
        });
        boutiqueCommentListAdapter = new BoutiqueCommentListAdapter(this, dataSource);
        common_rv.setAdapter(boutiqueCommentListAdapter);
        boutiqueCommentListAdapter.setOnItemClickListener(new BoutiqueCommentListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int name) {

            }
        });

    }


    @OnClick(R.id.navi_back_lay)
    public void onViewClicked() {
        finish();
    }
}
