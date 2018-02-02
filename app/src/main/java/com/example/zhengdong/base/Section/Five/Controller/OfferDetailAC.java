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

import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Section.Five.Adapter.OfferDetailListAdapter;
import com.example.zhengdong.base.Section.Login.Model.RequireOrderListModel;
import com.example.zhengdong.jbx.R;
import com.google.gson.Gson;

import java.util.HashMap;

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
    private String match_enqu_id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail_ac);
        ButterKnife.bind(this);
        offerType = getIntent().getIntExtra("offertype", -1);
        naviBackLay.setVisibility(View.VISIBLE);
        naviTitleTxt.setText("询价单详情");
        match_enqu_id = getIntent().getStringExtra("Match_enqu_id");
        initRequireDetailData(match_enqu_id);
        if(offerType == 1){
            offerDetailLay.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 询价单详情列表
     *
     * @param match_enqu_id*/
    private void initRequireDetailData(String match_enqu_id) {
        HashMap<String,String> map = new HashMap<>();
        map.put("enquiry_id",match_enqu_id);
        if (offerType == 1){
            map.put("sort","1");
        }else {
            map.put("sort","0");
        }
        HttpRequest.URL_GET_REQUEST(this, UrlUtils.MINE_REQUIRE_DETAIL_LIST_URL, map, "加载中...", true, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                RequireOrderListModel requireOrderListModel = new Gson().fromJson(response,RequireOrderListModel.class);
                if (requireOrderListModel.getCode() == 200){
                    initRecycleView(requireOrderListModel);
                }else {
                    XToast.show(getBaseContext(),""+requireOrderListModel.getMsg());
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

    private void initRecycleView(RequireOrderListModel requireOrderListModel) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        offerDetailRv.setLayoutManager(linearLayoutManager);
        OfferDetailListAdapter offerDetailListAdapter = new OfferDetailListAdapter(this,requireOrderListModel);
        offerDetailRv.setAdapter(offerDetailListAdapter);
        offerDetailListAdapter.setOnItemClickListener(new OfferDetailListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String name) {
                Intent intent = new Intent(OfferDetailAC.this,MineOfferSecondAC.class);
                intent.putExtra("offerType",6);
                intent.putExtra("orderDetailID",name);
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
                intent.putExtra("enquireID",match_enqu_id);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
