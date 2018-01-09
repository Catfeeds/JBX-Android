package com.example.zhengdong.base.Section.First.Controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Section.First.Adapter.OperateListAdapter;
import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OperationAC extends AppCompatActivity {

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
    @BindView(R.id.navi_right_pic_lay)
    LinearLayout naviRightPicLay;
    @BindView(R.id.title_bg)
    RelativeLayout titleBg;
    @BindView(R.id.operate_rv)
    RecyclerView operateRv;
    @BindView(R.id.confirm_operate_btn)
    Button confirmOperateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_ac);
        ButterKnife.bind(this);
        int type = getIntent().getIntExtra("type", -1);
        initNavigationView(type);
    }

    private void initNavigationView(int type) {
        titleBg.setBackgroundColor(Color.WHITE);
        if (type == 1) {
            naviTitleTxt.setText("请选择要批量移动的成员");
            naviTitleTxt.setTextColor(Color.BLACK);
        } else if (type == 2) {
            naviTitleTxt.setText("请选择要批量删除的成员");
            naviTitleTxt.setTextColor(Color.BLACK);
        }
        naviRightTxt.setText("取消");
        naviRightTxt.setTextColor(Color.parseColor("#307FDE"));

        initOperateView();
    }

    // 初始化rv
    private void initOperateView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        operateRv.setLayoutManager(linearLayoutManager);
        OperateListAdapter operateListAdapter = new OperateListAdapter(this,null);
        operateRv.setAdapter(operateListAdapter);
        operateListAdapter.setOnItemClickListener(new OperateListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int i, int position, String name) {

            }
        });
    }

    @OnClick({R.id.navi_back_lay, R.id.navi_right_lay,R.id.confirm_operate_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.navi_back_lay:
                break;
            case R.id.navi_right_lay:
                finish();
                break;
            case R.id.confirm_operate_btn:
                initMoveToPartmentView();
                break;
            default:
                break;
        }
    }

    // 批量操作要转移的部门
    private void initMoveToPartmentView() {
        Intent intent = new Intent(OperationAC.this,MovePartmentAC.class);
        startActivity(intent);
    }


}
