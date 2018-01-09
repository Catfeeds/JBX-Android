package com.example.zhengdong.base.Section.First.Controller;

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

import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Section.First.Adapter.PartmentListAdapter;
import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovePartmentAC extends AppCompatActivity {

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
        setContentView(R.layout.activity_move_partment_ac);
        ButterKnife.bind(this);
        initTitleView();
    }

    private void initTitleView() {
        titleBg.setBackgroundColor(Color.WHITE);
        naviTitleTxt.setText("请选择要转移的部门");
        naviTitleTxt.setTextColor(Color.BLACK);
        naviRightTxt.setText("取消");
        naviRightTxt.setTextColor(Color.parseColor("#307FDE"));

        initMoveRV();
    }

    private void initMoveRV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        operateRv.setLayoutManager(linearLayoutManager);
        PartmentListAdapter partmentListAdapter = new PartmentListAdapter(this,null);
        operateRv.setAdapter(partmentListAdapter);
        partmentListAdapter.setOnItemClickListener(new PartmentListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int i, int position, String name) {

            }
        });
    }

    @OnClick({R.id.navi_right_lay, R.id.confirm_operate_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.navi_right_lay:
                finish();
                break;
            case R.id.confirm_operate_btn:
                XToast.show(getBaseContext(),"点击移动!");
                break;
            default:
                break;
        }
    }
}
