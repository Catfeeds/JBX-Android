/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Section.Four.Adapter.GoodsListAdapter;
import com.example.zhengdong.base.Section.Four.View.FilterHeaderView.FilterView;
import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderPriceAC extends AppCompatActivity {


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
    @BindView(R.id.filter_view)
    FilterView filterView;
    @BindView(R.id.navi_back_lay)
    LinearLayout naviBackLay;
    @BindView(R.id.goods_rv)
    RecyclerView goodsRv;
    private int filterPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_price_ac);
        ButterKnife.bind(this);
        initNavigationView();
        initFilterView();
        initGoodsRV();
    }

    /**
     * 货品的头部
     * */
    private void initGoodsRV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        goodsRv.setLayoutManager(linearLayoutManager);
        GoodsListAdapter goodsListAdapter = new GoodsListAdapter(this,null);
        goodsRv.setAdapter(goodsListAdapter);
        goodsListAdapter.setOnItemClickListener(new GoodsListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String name, int position, int i) {
                Intent intent = new Intent(OrderPriceAC.this,ShopCarAC.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化头部
     */
    private void initNavigationView() {
        naviBackLay.setVisibility(View.VISIBLE);
        naviTitleTxt.setText("货品采购");
    }

    /**
     * 头部分类界面
     */
    private void initFilterView() {
        filterView.setFilterData(this);
        filterView.setOnFilterClickListener(new FilterView.OnFilterClickListener() {
            @Override
            public void onFilterClick(int position) {
                filterPosition = position;
                filterView.show(position);
            }
        });
        // 第一个
        filterView.setOnItemCategoryClickListener(new FilterView.OnItemCategoryClickListener() {
            @Override
            public void onItemCategoryClick() {

            }
        });
        // 第二个
        filterView.setOnItemSortClickListener(new FilterView.OnItemSortClickListener() {
            @Override
            public void onItemSortClick() {

            }
        });
        // 第三个
        filterView.setOnItemFilterClickListener(new FilterView.OnItemFilterClickListener() {
            @Override
            public void onItemFilterClick() {

            }
        });

    }

    @OnClick(R.id.navi_back_lay)
    public void onViewClicked() {
        if (filterView.isShowing()) {
            filterView.resetAllStatus();
        }
        finish();
    }
}
