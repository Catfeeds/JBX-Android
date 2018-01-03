package com.example.zhengdong.base.Section.Mine.Controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Section.Mine.Adapter.MineListAdapter;
import com.example.zhengdong.base.Section.Mine.View.CircleImageView;
import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFC extends Fragment {


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
    Unbinder unbinder;
    @BindView(R.id.mine_name_txt)
    TextView mineNameTxt;
    @BindView(R.id.mine_partment_txt)
    TextView minePartmentTxt;
    @BindView(R.id.mine_header_pic)
    CircleImageView mineHeaderPic;
    @BindView(R.id.mine_shopname_txt)
    TextView mineShopnameTxt;
    @BindView(R.id.mine_shopname_lay)
    LinearLayout mineShopnameLay;
    @BindView(R.id.mine_ispass_pic)
    ImageView mineIspassPic;
    @BindView(R.id.mine_rv)
    RecyclerView mineRv;
    Unbinder unbinder1;
    private View view;
    private MineListAdapter mineListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_mine_fc, container, false);
            unbinder = ButterKnife.bind(this, view);
            initScanView();
            initMineListView();
        }
        return view;
    }

    private void initMineListView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mineRv.setLayoutManager(linearLayoutManager);
        mineListAdapter = new MineListAdapter(getActivity(),null);
        mineRv.setAdapter(mineListAdapter);
        mineListAdapter.setOnItemClickListener(new MineListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, String name) {

            }
        });
    }

    private void initScanView() {
        naviRightPicLay.setVisibility(View.VISIBLE);
        naviTitleTxt.setText("我的");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.mine_header_pic, R.id.mine_shopname_lay,R.id.navi_right_pic_lay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_header_pic:
                break;
            case R.id.mine_shopname_lay:
                break;
            case R.id.navi_right_pic_lay:
                break;
            default:
                break;
        }
    }
}
