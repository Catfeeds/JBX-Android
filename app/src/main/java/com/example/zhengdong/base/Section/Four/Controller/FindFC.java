/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.Macro.LogUtil;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Section.First.Controller.IronMasterWC;
import com.example.zhengdong.base.Section.Four.Adapter.FindListAdapter;
import com.example.zhengdong.base.Section.Four.Model.NewsListModel;
import com.example.zhengdong.base.Section.Four.Model.NewsTitleModel;
import com.example.zhengdong.base.Section.Four.View.FindSearchEdtView;
import com.example.zhengdong.jbx.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.pic)
    ImageView pic;
    @BindView(R.id.txt)
    TextView txt;
    @BindView(R.id.off_line_view)
    LinearLayout offLineView;
    private View views;
    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<String> mNewsIDs = new ArrayList<>();
    private List<NewsTitleModel.DataBean.EcInformationCatBean> dataSource = null;
    private NewsListModel newsListModel;
    private Timer timer = new Timer();
    private int countIsFirst = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
//                    XToast.show(getContext(),"刷新新闻"+countIsFirst);
                    if (dataSource != null){
                        int size = dataSource.size();
                        int current = (int) (Math.random() * size);
                        LogUtil.e("当前新闻id"+current);
                        initNewsListData(String.valueOf(dataSource.get(current).getId()),1,5,"");
                    }
                    countIsFirst += 1;
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };
    TimerTask task = new TimerTask() {
        public void run() {
            Message message = new Message();
            message.what = 0;
            handler.sendMessage(message);
        }
    };
    private List<NewsListModel.DataBean.EcInformationBean> newDataSource = null;
    private FindListAdapter findListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (views == null) {
            views = inflater.inflate(R.layout.fragment_find_fc, container, false);
            unbinder = ButterKnife.bind(this, views);
//            initRecycleView();
            if (countIsFirst == 0){
                timer.schedule(task, 100, 20000);
            }else {
                timer.schedule(task, 20000, 20000);
            }
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
        HttpRequest.URL_JSONGETNOPARAM_REQUEST(getActivity(), UrlUtils.NEWS_TITLE_LIST, "加载中...", true, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                NewsTitleModel newsTitleModel = new Gson().fromJson(response, NewsTitleModel.class);
                if (newsTitleModel.getCode() == 200) {
                    findRv.setVisibility(View.VISIBLE);
                    offLineView.setVisibility(View.GONE);
                    dataSource = newsTitleModel.getData().getEcInformationCat();
                    initRecycleView();

                    // 第一次
                    initNewsListData(String.valueOf(dataSource.get(0).getId()),1,10,"");
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
                findRv.setVisibility(View.GONE);
                offLineView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initNewsListData(String newsID, final int pages, int pageNum, String searchTxt) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Information_id", newsID);
        map.put("page", String.valueOf(pages));
        map.put("pageNum", String.valueOf(10));
        map.put("titleName", searchTxt);
        HttpRequest.URL_JSONGET_REQUEST(getActivity(), UrlUtils.NEWS_LIST_URL, map, "加载中...", false, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                newsListModel = new Gson().fromJson(response, NewsListModel.class);
                if (newsListModel.getCode() == 200) {
                    newDataSource=null;
                    newDataSource = newsListModel.getData().getEcInformation();
                    if (countIsFirst == 0){
                        initRecycleView();
                    }else {
                        findListAdapter.setNewsDatas(newDataSource);
//                        findListAdapter.notifyDataSetChanged();
                    }

                } else {
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

    @Override
    public void onResume() {
        super.onResume();
//        if (timer != null){
//            timer.cancel();
//        }
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
        findListAdapter = new FindListAdapter(getActivity(), dataSource,newDataSource);
        findRv.setAdapter(findListAdapter);
        findListAdapter.setOnItemClickListener(new FindListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String name, int position, int gridviewPosition) {
                if (position == 1){
                    LogUtil.e("当前点击的页面是", name + gridviewPosition);
                    if (gridviewPosition != 3){
                        Intent intent = new Intent(getActivity(), FindSecondAC.class);
                        intent.putExtra("gridType", gridviewPosition);
                        intent.putExtra("gridName", name);
                        startActivity(intent);
                    }
                }else if (position == 2){
                    Intent intent = new Intent(getActivity(), IronMasterWC.class);
                    intent.putExtra("webType", 2);
                    intent.putExtra("content", name);
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (timer != null){
            timer.cancel();
        }
    }

    @OnClick(R.id.off_line_view)
    public void onViewClicked() {
        initTabLayData();
    }
}
