package com.example.zhengdong.base.Section.News.Controller;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhengdong.base.APIManager.CheckNetService;
import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.Macro.LogUtil;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Macro.dzRecycleview.DzRecyclerView;
import com.example.zhengdong.base.Macro.dzRecycleview.LoadMoreListener;
import com.example.zhengdong.base.Macro.dzRecycleview.ProgressView;
import com.example.zhengdong.base.Section.News.Adapter.NewsListAdapter;
import com.example.zhengdong.base.Section.News.Evevts.NewsEvent;
import com.example.zhengdong.base.Section.News.Model.NewsListModel;
import com.example.zhengdong.base.Section.Work.Controller.IronMasterWC;
import com.example.zhengdong.jbx.R;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


@SuppressLint("ValidFragment")
public class NewsListFC extends Fragment {
    @BindView(R.id.common_rv)
    DzRecyclerView common_rv;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    Unbinder unbinder;
    private String mTitle;
    private View view = null;
    private NewsListAdapter newsListAdapter;
    private List<NewsListModel.DataBean.EcInformationBean> dataSource;
    private String currentID = "";
    private int page = 1;
    private NewsListModel newsListModel;
    private String newsID = "";

    public static NewsListFC getInstance(String newsID) {
        NewsListFC sf = new NewsListFC();
        sf.newsID = newsID;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fr_simple_card, null);
            ButterKnife.bind(this, view);
            EventBus.getDefault().register(this);
            LogUtil.e("当前的页面四" + newsID);
            currentID = newsID;
            initNewsListData(currentID, 1, 10, "");
        }
        return view;
    }

    // 初始化数据
    @Subscribe
    public void onEventMainThread(NewsEvent event) {
        page = 1;
        initNewsListData(event.getNewsID(), 1, 10, event.getSearchTxt());
    }

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
        HttpRequest.URL_JSONGET_REQUEST(getActivity(), UrlUtils.NEWS_LIST_URL, map, "", false, new HttpInterFace() {
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
                    XToast.show(getContext(), "" + newsListModel.getMsg());
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
                        initNewsListData(currentID, 1, 10, "");
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
                        if (dataSource.size() >= newsListModel.getData().getTotal()) {
                            common_rv.loadMoreEnd();
                            page = 1;
                            return;
                        }
                        page += 1;
                        initNewsListData(currentID, page, 10, "");
                        common_rv.loadMoreComplete();
                    }
                }, 1000);
            }
        });
        newsListAdapter = new NewsListAdapter(getActivity(), dataSource);
        common_rv.setAdapter(newsListAdapter);
        newsListAdapter.setOnItemClickListener(new NewsListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String content) {
                Intent intent = new Intent(getActivity(), IronMasterWC.class);
                intent.putExtra("webType", 2);
                intent.putExtra("content", content);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}