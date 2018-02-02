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

import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.Macro.LogUtil;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Macro.dzRecycleview.DzRecyclerView;
import com.example.zhengdong.base.Macro.dzRecycleview.LoadMoreListener;
import com.example.zhengdong.base.Macro.dzRecycleview.ProgressView;
import com.example.zhengdong.base.Section.Five.Adapter.MineOfferListAdapter;
import com.example.zhengdong.base.Section.Five.Model.MineOfferAleadListModel;
import com.example.zhengdong.base.Section.Five.Model.MineOfferWaitListModel;
import com.example.zhengdong.base.Section.Five.Model.MineRequireAleadListModel;
import com.example.zhengdong.base.Section.Five.Model.MineRequireWaitListModel;
import com.example.zhengdong.jbx.R;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

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
    private int types = 0;
    private MineOfferWaitListModel mineOfferWaitListModel;
    private MineRequireWaitListModel mineRequireWaitListModel;
    private int pages = 1;
    private List<MineRequireWaitListModel.DataBean.EnquirylistBean> requireWaitDataSource;
    private List<MineOfferWaitListModel.DataBean.EnquirylistBean> offerWaitListDataSource;
    private MineOfferAleadListModel mineOfferAleadListModel;
    private List<MineOfferAleadListModel.DataBean.EnquirylistBean> offerAleadDataSource;
    private MineRequireAleadListModel mineRequireAleadListModel;
    private List<MineRequireAleadListModel.DataBean.EnquirylistBean> requireAleadDataSource;

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
            if (ids == 1 && currentID.equals("待报价")) {
                types = 1;
            } else if (ids == 1 && currentID.equals("已报价")) {
                types = 2;
            } else if (ids == 2 && currentID.equals("待报价")) {
                types = 3;
            } else if (ids == 2 && currentID.equals("已报价")) {
                types = 4;
            }
            initMineOfferData(types, 1, 10);
        }
        return view;
    }

    /**
     * 查询数据
     */
    private void initMineOfferData(final int quireTypes, int page, int pageSize) {
        if (pages == 1 && quireTypes == 3) {
            requireWaitDataSource = null;
        }
        if (pages == 1 && quireTypes == 1) {
            offerWaitListDataSource = null;
        }
        if (pages == 1 && quireTypes == 2) {
            offerAleadDataSource = null;
        }
        if (pages == 1 && quireTypes == 4) {
            requireAleadDataSource = null;
        }
        HashMap<String, String> map = new HashMap<>();
        switch (quireTypes) {
            case 1:
                map.put("is_to_enquiry", "0");
                break;
            case 2:
                map.put("is_to_enquiry", "1");
                break;
            case 3:
                map.put("is_enquiry", "0");
                break;
            case 4:
                map.put("is_enquiry", "1");
                break;
            default:
                break;
        }

        map.put("page", String.valueOf(page));
        map.put("pageNum", String.valueOf(pageSize));
        String url = "";
        if (quireTypes == 1 || quireTypes == 2) {
            url = UrlUtils.MINE_OFFER_LIST_SPEAK_URL;
        } else {
            url = UrlUtils.MINE_OFFER_LIST_REQUIRE_URL;
        }

        HttpRequest.URL_GET_REQUEST(getActivity(), url, map, "加载中...", false, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                switch (quireTypes) {
                    case 1:
                        mineOfferWaitListModel = new Gson().fromJson(response, MineOfferWaitListModel.class);
                        if (mineOfferWaitListModel.getCode() == 200) {
                            if (pages == 1) {
                                offerWaitListDataSource = mineOfferWaitListModel.getData().getEnquirylist();
                                initRView();
                            } else {
                                offerWaitListDataSource.addAll(mineOfferWaitListModel.getData().getEnquirylist());
                                mineOfferListAdapter.notifyDataSetChanged();
                            }
                        } else {
                            XToast.show(getContext(), "" + mineOfferWaitListModel.getMsg());
                        }
                        break;
                    case 2:
                        mineOfferAleadListModel = new Gson().fromJson(response, MineOfferAleadListModel.class);
                        if (mineOfferAleadListModel.getCode() == 200) {
                            if (pages == 1) {
                                offerAleadDataSource = mineOfferAleadListModel.getData().getEnquirylist();
                                initRView();
                            } else {
                                offerAleadDataSource.addAll(mineOfferAleadListModel.getData().getEnquirylist());
                                mineOfferListAdapter.notifyDataSetChanged();
                            }
                        } else {
                            XToast.show(getContext(), "" + mineOfferAleadListModel.getMsg());
                        }


                        break;
                    case 3:
                        mineRequireWaitListModel = new Gson().fromJson(response, MineRequireWaitListModel.class);
                        if (mineRequireWaitListModel.getCode() == 200) {
                            if (pages == 1) {
                                requireWaitDataSource = mineRequireWaitListModel.getData().getEnquirylist();
                                initRView();
                            } else {
                                requireWaitDataSource.addAll(mineRequireWaitListModel.getData().getEnquirylist());
                                mineOfferListAdapter.notifyDataSetChanged();
                            }

                        } else {
                            XToast.show(getContext(), "" + mineRequireWaitListModel.getMsg());
                        }
                        break;
                    case 4:
                        mineRequireAleadListModel = new Gson().fromJson(response, MineRequireAleadListModel.class);
                        if (mineRequireAleadListModel.getCode() == 200) {
                            if (pages == 1) {
                                requireAleadDataSource = mineRequireAleadListModel.getData().getEnquirylist();
                                initRView();
                            } else {
                                requireAleadDataSource.addAll(mineRequireAleadListModel.getData().getEnquirylist());
                                mineOfferListAdapter.notifyDataSetChanged();
                            }

                        } else {
                            XToast.show(getContext(), "" + mineRequireAleadListModel.getMsg());
                        }
                        break;
                    default:
                        break;
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
                        if (types == 1) {
                            offerWaitListDataSource.clear();
                        } else if (types == 3) {
                            requireWaitDataSource.clear();
                        } else if (types == 2) {
                            offerAleadDataSource.clear();
                        } else if (types == 4) {
                            requireAleadDataSource.clear();
                        }
                        pages = 1;
                        initMineOfferData(types, 1, 10);
                        common_rv.refreshComplete();
                        swipeLayout.setRefreshing(false);
                        mineOfferListAdapter.notifyDataSetChanged();

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
                        if (types == 1) {
                            if (offerWaitListDataSource.size() >= mineOfferWaitListModel.getData().getTotal()) {
                                common_rv.loadMoreEnd();
                                pages = 1;
                                return;
                            }
                        } else if (types == 3) {
                            if (requireWaitDataSource.size() >= mineRequireWaitListModel.getData().getTotal()) {
                                common_rv.loadMoreEnd();
                                pages = 1;
                                return;
                            }
                        } else if (types == 2) {
                            if (offerAleadDataSource.size() >= mineOfferAleadListModel.getData().getTotal()) {
                                common_rv.loadMoreEnd();
                                pages = 1;
                                return;
                            }
                        } else if (types == 4) {
                            if (requireAleadDataSource.size() >= mineRequireAleadListModel.getData().getTotal()) {
                                common_rv.loadMoreEnd();
                                pages = 1;
                                return;
                            }
                        }
                        pages += 1;
                        initMineOfferData(types, pages, 10);
                        common_rv.loadMoreComplete();


                    }
                }, 1000);
            }
        });
        mineOfferListAdapter = new MineOfferListAdapter(getActivity(), offerWaitListDataSource, offerAleadDataSource, requireWaitDataSource, requireAleadDataSource, types);
        common_rv.setAdapter(mineOfferListAdapter);
        mineOfferListAdapter.setOnItemClickListener(new MineOfferListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int cellType, String match_enqu_id) {
                LogUtil.e("点击了cell" + cellType);
                if (cellType == 4) {
                    Intent intent = new Intent(getActivity(), MineOfferSecondAC.class);
                    intent.putExtra("offerType", cellType);
                    intent.putExtra("Match_enqu_id", match_enqu_id);
                    startActivity(intent);
                } else if(cellType == 1||cellType == 3) {
                    Intent intent = new Intent(getActivity(), OfferDetailAC.class);
                    intent.putExtra("offertype", cellType);
                    intent.putExtra("Match_enqu_id", match_enqu_id);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initMineOfferData(types,1,10);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
