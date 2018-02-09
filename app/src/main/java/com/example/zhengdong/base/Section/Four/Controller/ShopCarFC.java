/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.zhengdong.base.Section.First.View.RecyclerBanner;
import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopCarFC extends Fragment {


    @BindView(R.id.banner_rv)
    RecyclerBanner bannerRv;
    @BindView(R.id.goods_view)
    LinearLayout goodsView;
    @BindView(R.id.detail_view)
    LinearLayout detailView;
    Unbinder unbinder;
    private String mID;
    private View view = null;

    public ShopCarFC() {
        // Required empty public constructor
    }

    public static ShopCarFC getInstance(String mID) {
        ShopCarFC sf = new ShopCarFC();
        sf.mID = mID;
        return sf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null){
            view = inflater.inflate(R.layout.fragment_shop_car_fc, container, false);
            unbinder = ButterKnife.bind(this, view);
            if (mID.equals("商品")){
                goodsView.setVisibility(View.VISIBLE);
                initBannerView();
            }else if (mID.equals("详情")){
                detailView.setVisibility(View.VISIBLE);
            }
        }

        return view;
    }

    /**
     * 商品轮播图
     * */
    private void initBannerView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
