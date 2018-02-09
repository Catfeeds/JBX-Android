/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.First.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Section.First.Adapter.WorkMsgListAdapter;
import com.example.zhengdong.base.Section.First.View.SearchEditText;
import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkSecondAC extends AppCompatActivity {

    @BindView(R.id.work_msg_rv)
    RecyclerView workMsgRv;
    @BindView(R.id.work_msg_view)
    LinearLayout workMsgView;
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
    @BindView(R.id.search_view)
    SearchEditText searchView;
    @BindView(R.id.custom_view)
    LinearLayout customView;
    @BindView(R.id.week_view)
    LinearLayout weekView;
    @BindView(R.id.pic)
    ImageView pic;
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_second_ac);
        ButterKnife.bind(this);
        int X = getIntent().getIntExtra("POSITIONX", -1);
        int Y = getIntent().getIntExtra("POSITIONY", -1);
        name = getIntent().getStringExtra("NAME");
        initMsgNavigationView();
        if (X == 0 && Y == 0) {
            // 初始化 工作 - 消息 模块的rv
            workMsgView.setVisibility(View.VISIBLE);
            initMsgRV();
        } else if (X == 1 && Y == 0) {
            // 客户
            customView.setVisibility(View.VISIBLE);
        } else if (X == 3) {
            // 周报
            weekView.setVisibility(View.VISIBLE);
            pic.setBackgroundResource(R.drawable.week);
        } else if (X == 1 && Y == 3) {
            // 拜访记录
            weekView.setVisibility(View.VISIBLE);
            pic.setBackgroundResource(R.drawable.vistor);
        } else if (X == 0 && Y == 1){
            weekView.setVisibility(View.VISIBLE);
            pic.setBackgroundResource(R.drawable.liu);
        } else if (X == 1 && Y == 1){
            weekView.setVisibility(View.VISIBLE);
            pic.setBackgroundResource(R.drawable.sj);
        }else if (X == 1 && Y == 2){
            weekView.setVisibility(View.VISIBLE);
            pic.setBackgroundResource(R.drawable.addcustom);
        }else if (X == 2 && Y == 0){
            weekView.setVisibility(View.VISIBLE);
            pic.setBackgroundResource(R.drawable.ordertotal);
        }else if (X == 2 && Y == 1){
            weekView.setVisibility(View.VISIBLE);
            pic.setBackgroundResource(R.drawable.receivetotal);
        }else if (X == 2 && Y == 2){
            weekView.setVisibility(View.VISIBLE);
            pic.setBackgroundResource(R.drawable.receivetotal);
        }
        pic.setScaleType(ImageView.ScaleType.CENTER_INSIDE);


    }

    /**
     * 工作 -- 消息头部
     */
    private void initMsgNavigationView() {
        naviBackLay.setVisibility(View.VISIBLE);
        naviTitleTxt.setText(name);
    }

    /**
     * 工作 -- 消息
     */
    private void initMsgRV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        workMsgRv.setLayoutManager(linearLayoutManager);
        WorkMsgListAdapter workMsgListAdapter = new WorkMsgListAdapter(this, null);
        workMsgRv.setAdapter(workMsgListAdapter);
        workMsgListAdapter.setOnItemClickListener(new WorkMsgListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String name) {

            }
        });
    }

    @OnClick(R.id.navi_back_lay)
    public void onViewClicked() {
        finish();
    }
}
