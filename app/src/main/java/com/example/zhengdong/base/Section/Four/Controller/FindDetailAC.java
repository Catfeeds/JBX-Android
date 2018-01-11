/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindDetailAC extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_detail_ac);
        ButterKnife.bind(this);
        initNavigationView();
    }

    private void initNavigationView() {
        naviBackLay.setVisibility(View.VISIBLE);
        naviTitleTxt.setText("详情");
    }

    @OnClick(R.id.navi_back_lay)
    public void onViewClicked() {
        finish();
    }
}
