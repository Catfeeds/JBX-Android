/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Controller;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.Macro.AlphaViewUtils;
import com.example.zhengdong.base.Macro.LogUtil;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Macro.comView.SoftKeyBoardListener;
import com.example.zhengdong.base.Section.Four.Adapter.FindListAdapter;
import com.example.zhengdong.base.Section.Four.Model.NewsTitleModel;
import com.example.zhengdong.base.Section.Four.View.FindSearchEdtView;
import com.example.zhengdong.jbx.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFC extends Fragment {


    @BindView(R.id.search_view)
    FindSearchEdtView findSearchEdt;
    Unbinder unbinder;
    @BindView(R.id.find_rv)
    RecyclerView findRv;
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
    Unbinder unbinder1;
    private View views;
    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<String> mNewsIDs = new ArrayList<>();
    private List<NewsTitleModel.DataBean.EcInformationCatBean> dataSource = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (views == null) {
            views = inflater.inflate(R.layout.fragment_find_fc, container, false);
            unbinder = ButterKnife.bind(this, views);
//            initRecycleView();
            initSearchView();
            initTabLayData();
        }
        return views;
    }

    /**
     * 初始化数据源
     */
    // 初始化标题栏数据
    private void initTabLayData() {
        HttpRequest.URL_JSONGETNOPARAM_REQUEST(getActivity(), UrlUtils.NEWS_TITLE_LIST, "加载中...", false, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                LogUtil.e("打印的json" + response);
                NewsTitleModel newsTitleModel = new Gson().fromJson(response, NewsTitleModel.class);
                if (newsTitleModel.getCode() == 200) {
                    dataSource = newsTitleModel.getData().getEcInformationCat();
//                    for (int i = 0; i < newsTitleModel.getData().getEcInformationCat().size(); i++) {
//                        mTitles.add(newsTitleModel.getData().getEcInformationCat().get(i).getKey_name());
//                        mNewsIDs.add(String.valueOf(newsTitleModel.getData().getEcInformationCat().get(i).getId()));
//                    }
                    initRecycleView();
                } else {
                    XToast.show(getActivity().getBaseContext(), "" + newsTitleModel.getMsg());
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


    private void initSearchView() {
        naviTitleTxt.setText("发现");

//        // 监听键盘事件
//        SoftKeyBoardListener.setListener(getActivity(), new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
//            @Override
//            public void keyBoardShow(int height) {
//                // 设置灰色区域
//                AlphaViewUtils.setBackGroundLevel(getActivity(), 0.6f);
//            }
//
//            @Override
//            public void keyBoardHide(int height) {
//                AlphaViewUtils.setBackGroundLevel(getActivity(), 1.0f);
//            }
//        });
//        getActivity().getWindow().getDecorView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LogUtil.e("点击了Xxx");
//                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                // 隐藏软键盘
//                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
//            }
//        });
//        findSearchEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    //完成自己的事件
//                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                    // 隐藏软键盘
//                    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
//                }
//                return false;
//            }
//        });

    }

    /**
     * 初始化RV
     *
     * @param
     */
    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        findRv.setLayoutManager(linearLayoutManager);
        FindListAdapter findListAdapter = new FindListAdapter(getActivity(), dataSource);
        findRv.setAdapter(findListAdapter);
        findListAdapter.setOnItemClickListener(new FindListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String name, int gridviewPosition) {
                LogUtil.e("当前点击的页面是", name + gridviewPosition);
                Intent intent = new Intent(getActivity(), FindSecondAC.class);
                intent.putExtra("gridType", gridviewPosition);
                intent.putExtra("gridName", name);
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
