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
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoutiqueListFC extends Fragment {


    @BindView(R.id.boutique_rv)
    DzRecyclerView boutiqueRv;
    Unbinder unbinder;

    String[] picArr = {
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516276552636&di=07b1d664a8f47fc1d346d4455763aebb&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F08f790529822720ef2a7e06771cb0a46f31fabc6.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516871674&di=dbe76b3a7a11a2d7adc08171bf673375&imgtype=jpg&er=1&src=http%3A%2F%2Fb.zol-img.com.cn%2Fsjbizhi%2Fimages%2F7%2F320x510%2F1415695092182.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516276972041&di=5e2c83732978db5f4b77d77340579b8a&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D1108480664%2C391981744%26fm%3D214%26gp%3D0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516276553031&di=d48ac6c496cbeb1fd66022fd82fa9383&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F8601a18b87d6277f3dc16e2a22381f30e824fcd5.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516276553031&di=3ae55e16c239c74aa68d6417cd8549fc&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01fa9e57c515e70000018c1b3e17c4.jpg%401280w_1l_2o_100sh.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516276553031&di=d7a8b690d53eb0a350e03136df049cdf&imgtype=0&src=http%3A%2F%2Fwww.ecorr.org%2Fuploads%2Fallimg%2F2015%2F12%2F21%2F58-151221092344495.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516276553029&di=ef2bab9d7a26f08f2f8687f6ddb06a43&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0104c259ae69a1a8012028a9305fb9.jpg%402o.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516276553028&di=8cb8dec7ccbe04111213f47056aff7bb&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D288378cd376d55fbd1cb7e65054b253f%2Fb3fb43166d224f4a2eb0670003f790529822d1f3.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516276693217&di=32bf99b65d8d4ae078c24e958082eb97&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3D838705fa3187e950561afb2c7940362f%2F3801213fb80e7bec616009d8242eb9389b506b54.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516276693217&di=6a96b5ee489a6045d4d4bf8dc31178ba&imgtype=0&src=http%3A%2F%2Fimage.cn.made-in-china.com%2Fprod%2F000-KEytzMnIbubh.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516276693216&di=cb9f2f52a00933528a1a32e52bdca367&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbcc90077fdb160924ab183042.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516276733545&di=1fd714102515232105833ff6c69d81b2&imgtype=0&src=http%3A%2F%2Fimg000.hc360.cn%2Fhb%2FMTQ3MTQyMzM1NzgwNjE4Mzg2OTkxNzE%3D.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516276795259&di=a25e3955fe6e704541faf8f35d231193&imgtype=0&src=http%3A%2F%2Fwww.jdzj.com%2FUserDocument%2F2012Y%2Fdgxybxg%2FPicture%2F201341483122.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516276956551&di=2b22a48af81000d2ccea3ccb772e4bf8&imgtype=0&src=http%3A%2F%2Fimg5.niutuku.com%2Fphone%2F1212%2F5952%2F5952-niutuku.com-89417.jpg",
    };
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    private int page = 1;
    private BoutiqueListAdapter boutiqueListAdapter;
    private String itemID = "";
    private List<BoutiqueListModel.DataBean.FanexListBean> dataSource;
    private BoutiqueListModel boutiqueListModel;


    public static BoutiqueListFC getInstance(String itemsID) {
        BoutiqueListFC sf = new BoutiqueListFC();
        sf.itemID = itemsID;
        return sf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_boutique_list_fc, container, false);
        unbinder = ButterKnife.bind(this, view);
        initDataSource(itemID,1,10);
        return view;
    }

    private void initDataSource(String item, final int page, int pageNum) {
        if (page == 1){
            dataSource = null;
        }
        HashMap<String,String> map = new HashMap<>();
        map.put("fan_type",item);
        map.put("page", String.valueOf(page));
        map.put("pageNum", String.valueOf(pageNum));
        HttpRequest.URL_GET_REQUEST(getActivity(), UrlUtils.BOUTIQUE_LIST_URL, map, "加载中...", false, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                boutiqueListModel = new Gson().fromJson(response,BoutiqueListModel.class);
                if (boutiqueListModel.getCode() == 200){
                    if (page == 1) {
                        dataSource = boutiqueListModel.getData().getFanexList();
                        initBoutiqueView();
                    } else {
                        dataSource.addAll(boutiqueListModel.getData().getFanexList());
                        boutiqueListAdapter.notifyDataSetChanged();
                    }
                }else {
                    XToast.show(getContext(),""+boutiqueListModel.getMsg());
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
                        page = 1;
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
        StaggerItemSeperateView staggerItemSeperateView = new StaggerItemSeperateView(13);
        boutiqueRv.addItemDecoration(staggerItemSeperateView);

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
                            page = 1;
                            return;
                        }
                        page += 1;
                        initDataSource(itemID, page, 10);
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
                intent.putExtra("Graphical_id",graphical_id);
                intent.putExtra("Fanc_id",Fanc_id);
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
