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
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindAddAC extends AppCompatActivity {

    @BindView(R.id.add_word_view)
    ScrollView addWordView;
    @BindView(R.id.add_hr_view)
    ScrollView addHrView;
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
    @BindView(R.id.add_word_lay)
    LinearLayout addWordLay;
    private int currentID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_add_ac);
        ButterKnife.bind(this);
        currentID = getIntent().getIntExtra("currentID", -1);
        initNavigationView();
    }

    private void initNavigationView() {
        naviBackLay.setVisibility(View.VISIBLE);
        if (currentID == 1){
            naviTitleTxt.setText("添加简历");
            addWordView.setVisibility(View.VISIBLE);
        }else if (currentID == 3){
            naviTitleTxt.setText("发布招聘");
            addHrView.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.navi_back_lay)
    public void onViewClicked() {
        finish();
    }
}
