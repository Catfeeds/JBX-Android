/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Five.Controller;

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

import com.example.zhengdong.base.Section.Five.Adapter.OfferDetailListAdapter;
import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OfferDetailAC extends AppCompatActivity {


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
    @BindView(R.id.offer_detail_rv)
    RecyclerView offerDetailRv;
    @BindView(R.id.navi_back_lay)
    LinearLayout naviBackLay;
    @BindView(R.id.offer_detail_lay)
    LinearLayout offerDetailLay;

    private int offerType = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail_ac);
        ButterKnife.bind(this);
        offerType = getIntent().getIntExtra("offertype", -1);
        if (offerType == 4){
            naviTitleTxt.setText("询价单详情");
            naviBackLay.setVisibility(View.VISIBLE);
            offerDetailLay.setVisibility(View.VISIBLE);
            initRecycleView();
        }
    }

    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        offerDetailRv.setLayoutManager(linearLayoutManager);
        OfferDetailListAdapter offerDetailListAdapter = new OfferDetailListAdapter(this,null);
        offerDetailRv.setAdapter(offerDetailListAdapter);
        offerDetailListAdapter.setOnItemClickListener(new OfferDetailListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int name) {
                Intent intent = new Intent(OfferDetailAC.this,MineOfferSecondAC.class);
                intent.putExtra("offerType",6);
                startActivity(intent);
            }
        });
    }


    @OnClick({R.id.navi_back_lay, R.id.offer_detail_lay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.navi_back_lay:
                finish();
                break;
            case R.id.offer_detail_lay:
                Intent intent = new Intent(OfferDetailAC.this,MineOfferSecondAC.class);
                intent.putExtra("offerType",5);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
