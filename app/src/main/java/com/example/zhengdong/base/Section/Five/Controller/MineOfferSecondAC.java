/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Five.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.zhengdong.base.General.BaseAC;
import com.example.zhengdong.base.Section.Five.Adapter.OfferSecondListAdapter;
import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineOfferSecondAC extends BaseAC {

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
    @BindView(R.id.mine_offer_second_rv)
    RecyclerView mineOfferSecondRv;
    @BindView(R.id.four_cell_bottom_view)
    LinearLayout fourCellBottomView;
    @BindView(R.id.order_price_view)
    LinearLayout orderPriceView;
    @BindView(R.id.order_detail_cell)
    ScrollView orderDetailCell;
    private int offerType = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_offer_second_ac);
        ButterKnife.bind(this);
        offerType = getIntent().getIntExtra("offerType", -1);

        if (offerType == 4) {
            // 我的询价 -- 已报价
            initNavigationView();
            initRecycleView();
            fourCellBottomView.setVisibility(View.VISIBLE);
        } else if (offerType == 5) {
            // 订单报价页面
            naviBackLay.setVisibility(View.VISIBLE);
            orderPriceView.setVisibility(View.VISIBLE);
            naviTitleTxt.setText("订单报价");
            initOrderPriceView();
        } else if (offerType == 6) {
            // 订货单详情
            naviBackLay.setVisibility(View.VISIBLE);
            naviTitleTxt.setText("订货单详情");
            orderDetailCell.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 订单报价页面
     */
    private void initOrderPriceView() {

    }

    /**
     * 商家报价页面
     */
    private void initRecycleView() {
        mineOfferSecondRv.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mineOfferSecondRv.setLayoutManager(linearLayoutManager);
        OfferSecondListAdapter offerSecondListAdapter = new OfferSecondListAdapter(this, null);
        mineOfferSecondRv.setAdapter(offerSecondListAdapter);
        offerSecondListAdapter.setOnItemClickListener(new OfferSecondListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, String name) {
                Intent intent = new Intent(MineOfferSecondAC.this, OfferDetailAC.class);
                intent.putExtra("offertype", offerType);
                startActivity(intent);
            }
        });
    }

    private void initNavigationView() {
        naviBackLay.setVisibility(View.VISIBLE);
        naviRightPicLay.setVisibility(View.VISIBLE);
        rightPic.setBackgroundResource(R.drawable.search_nav_icon);
        if (offerType == 4) {
            naviTitleTxt.setText("商家报价");
        }
    }

    @OnClick({R.id.navi_back_lay, R.id.navi_right_pic_lay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.navi_back_lay:
                finish();
                break;
            case R.id.navi_right_pic_lay:
                break;
        }
    }

    @OnClick(R.id.four_cell_bottom_view)
    public void onViewClicked() {
    }
}
