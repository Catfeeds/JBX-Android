package com.example.zhengdong.base.Section.First.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Section.First.Adapter.PeoDetailListAdapter;
import com.example.zhengdong.base.Section.First.View.CustomSheetView;
import com.example.zhengdong.base.Section.First.View.SearchEditText;
import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PeopleDetailAC extends AppCompatActivity {

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
    @BindView(R.id.search_view)
    SearchEditText searchView;
    @BindView(R.id.common_rv)
    RecyclerView commonRv;
    @BindView(R.id.add_people_btn)
    Button addPeopleBtn;
    @BindView(R.id.add_partment_btn)
    Button addPartmentBtn;
    private CustomSheetView sheetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_detail_ac);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        naviBackLay.setVisibility(View.VISIBLE);
        naviTitleTxt.setText("测试标题");
        naviRightTxt.setText("批量操作");
        searchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    //完成自己的事件
                }
                return false;
            }
        });
        initPeopleDetailListView();
    }

    private void initPeopleDetailListView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        commonRv.setLayoutManager(linearLayoutManager);
        PeoDetailListAdapter peoDetailListAdapter = new PeoDetailListAdapter(this,null);
        commonRv.setAdapter(peoDetailListAdapter);
        peoDetailListAdapter.setOnItemClickListener(new PeoDetailListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int i, int position, String name) {

            }
        });
    }

    @OnClick({R.id.navi_back_lay, R.id.navi_right_lay, R.id.add_people_btn, R.id.add_partment_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.navi_back_lay:
                finish();
                break;
            case R.id.navi_right_lay:
                // 批量操作
                initLotsOperaView();
                break;
            case R.id.add_people_btn:
                initAddPeople();
                break;
            case R.id.add_partment_btn:
                initPartmentSettingView();
                break;
        }
    }

    private void initPartmentSettingView() {
        Intent intent = new Intent(PeopleDetailAC.this,MovePartmentAC.class);
        startActivity(intent);
    }

    private void initAddPeople() {
        Intent intent = new Intent(PeopleDetailAC.this,AddtionalAC.class);
        intent.putExtra("type",1);
        startActivity(intent);
    }

    private void initLotsOperaView() {
        sheetView = new CustomSheetView.Builder(PeopleDetailAC.this)

                .addMenu("批量移动成员", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sheetView.dismiss();
                        Intent intent = new Intent(PeopleDetailAC.this,OperationAC.class);
                        intent.putExtra("type",1);
                        startActivity(intent);
                    }
                }).addMenu("批量删除成员", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sheetView.dismiss();
                        Intent intent = new Intent(PeopleDetailAC.this,OperationAC.class);
                        intent.putExtra("type",2);
                        startActivity(intent);
                    }
                }).create();
        sheetView.show();
    }
}
