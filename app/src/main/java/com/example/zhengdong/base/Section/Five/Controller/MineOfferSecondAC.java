/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Five.Controller;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.General.BaseAC;
import com.example.zhengdong.base.Macro.LogUtil;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Section.Five.Adapter.OfferSecondListAdapter;
import com.example.zhengdong.base.Section.Five.Adapter.OrderDetailListAdapter;
import com.example.zhengdong.base.Section.Five.Model.MineOffCompanyListModel;
import com.example.zhengdong.base.Section.Five.Model.OrderDetailModel;
import com.example.zhengdong.base.Section.Five.Model.RequestModel;
import com.example.zhengdong.jbx.R;
import com.google.gson.Gson;

import java.util.HashMap;

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
    LinearLayout orderDetailCell;
    @BindView(R.id.speak_price_edt)
    EditText speakPriceEdt;
    @BindView(R.id.speak_day_edt)
    EditText speakDayEdt;
    @BindView(R.id.speak_remark_edt)
    EditText speakRemarkEdt;
    @BindView(R.id.confirm_price_btn)
    Button confirmPriceBtn;
    @BindView(R.id.d_detail_rv)
    RecyclerView dDetailRv;
    //    @BindView(R.id.d_projectName_txt)
//    TextView dProjectNameTxt;
//    @BindView(R.id.d_materialkind_txt)
//    TextView dMaterialkindTxt;
//    @BindView(R.id.d_material_num_txt)
//    TextView dMaterialNumTxt;
//    @BindView(R.id.d_partsum_txt)
//    TextView dPartsumTxt;
//    @BindView(R.id.d_materialsum_txt)
//    TextView dMaterialsumTxt;
//    @BindView(R.id.d_partnum_txt)
//    TextView dPartnumTxt;
//    @BindView(R.id.d_material_txt)
//    TextView dMaterialTxt;
//    @BindView(R.id.d_height_txt)
//    TextView dHeightTxt;
//    @BindView(R.id.d_material_color_txt)
//    TextView dMaterialColorTxt;
//    @BindView(R.id.d_material_gg_txt)
//    TextView dMaterialGgTxt;
//    @BindView(R.id.d_cut_num_txt)
//    TextView dCutNumTxt;
//    @BindView(R.id.d_render_txt)
//    TextView dRenderTxt;
//    @BindView(R.id.d_river_txt)
//    TextView dRiverTxt;
    private int offerType = -1;
    private String enquireID = "";
    private MineOffCompanyListModel mineOffCompanyListModel;
    private OrderDetailModel.DataBean dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_offer_second_ac);
        ButterKnife.bind(this);
        offerType = getIntent().getIntExtra("offerType", -1);
        LogUtil.e("当前的值为", "" + offerType);
        if (offerType == 4) {
            // 我的询价 -- 已报价
            initNavigationView();
            String Match_enqu_id = getIntent().getStringExtra("Match_enqu_id");
            initCompanyListData(Match_enqu_id);
//            fourCellBottomView.setVisibility(View.VISIBLE);
        } else if (offerType == 5) {
            // 订单报价页面
            enquireID = getIntent().getStringExtra("enquireID");
            naviBackLay.setVisibility(View.VISIBLE);
            orderPriceView.setVisibility(View.VISIBLE);
            naviTitleTxt.setText("订单报价");
            initOrderPriceView();
        } else if (offerType == 6) {
            // 订货单详情
            String orderDetailID = getIntent().getStringExtra("orderDetailID");
            naviBackLay.setVisibility(View.VISIBLE);
            naviTitleTxt.setText("订货单详情");
            orderDetailCell.setVisibility(View.VISIBLE);
            initOrderDetailData(orderDetailID);
        }
    }

    /**
     * 商家列表
     */
    private void initCompanyListData(String match_enqu_id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("match_enqu_id", match_enqu_id);
        HttpRequest.URL_GET_REQUEST(this, UrlUtils.MINE_QUIRE_COMPANY_LIST_URL, map, "加载中...", true, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                mineOffCompanyListModel = new Gson().fromJson(response, MineOffCompanyListModel.class);
                if (mineOffCompanyListModel.getCode() == 200) {
                    initRecycleView();
                } else {
                    XToast.show(MineOfferSecondAC.this, "" + mineOffCompanyListModel.getMsg());
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

    /**
     * 订货单详情页面数据
     */
    private void initOrderDetailData(final String orderDetailID) {
        HashMap<String, String> map = new HashMap<>();
        map.put("item_id", orderDetailID);
        HttpRequest.URL_GET_REQUEST(this, UrlUtils.MINE_OFFER_DETAIL_LIST_URL, map, "加载中...", true, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                OrderDetailModel orderDetailModel = new Gson().fromJson(response, OrderDetailModel.class);
                if (orderDetailModel.getCode() == 200) {
                    dataSource = orderDetailModel.getData();
                    initOrderDetailRecycleView();
                } else {
                    XToast.show(getBaseContext(), "" + orderDetailModel.getMsg());
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

    /**
     * 订货单详情页面
     */
    private void initOrderDetailRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dDetailRv.setLayoutManager(linearLayoutManager);
        OrderDetailListAdapter orderDetailListAdapter = new OrderDetailListAdapter(this,dataSource);
        dDetailRv.setAdapter(orderDetailListAdapter);
        orderDetailListAdapter.setOnItemClickListener(new OrderDetailListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, String name) {

            }
        });
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
        OfferSecondListAdapter offerSecondListAdapter = new OfferSecondListAdapter(this, mineOffCompanyListModel);
        mineOfferSecondRv.setAdapter(offerSecondListAdapter);
        offerSecondListAdapter.setOnItemClickListener(new OfferSecondListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, String name) {
//                Intent intent = new Intent(MineOfferSecondAC.this, OfferDetailAC.class);
//                intent.putExtra("offertype", offerType);
//                startActivity(intent);
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

    @OnClick({R.id.navi_back_lay, R.id.navi_right_pic_lay, R.id.four_cell_bottom_view, R.id.confirm_price_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.navi_back_lay:
                finish();
                break;
            case R.id.navi_right_pic_lay:
                break;
            case R.id.four_cell_bottom_view:
                break;
            case R.id.confirm_price_btn:
                initConfirmPriceData();
                break;
            default:
                break;
        }
    }

    /**
     * 立即报价数据
     */
    private void initConfirmPriceData() {
        String price = speakPriceEdt.getText().toString().trim();
        if (TextUtils.isEmpty(price)) {
            XToast.show(getBaseContext(), "请输入报价金额!");
            return;
        }
        String response_remark = speakRemarkEdt.getText().toString().trim();
        if (TextUtils.isEmpty(response_remark)) {
            XToast.show(getBaseContext(), "请输入备注!");
            return;
        }
        String response_day_limit = speakDayEdt.getText().toString().trim();
        if (TextUtils.isEmpty(response_day_limit)) {
            XToast.show(getBaseContext(), "请输入工期!");
            return;
        }


        HashMap<String, String> map = new HashMap<>();
        map.put("enquiry_id", enquireID);
        map.put("price", price);
        map.put("response_remark", response_remark);
        map.put("response_day_limit", response_day_limit);
        map.put("save_type", "1");
        HttpRequest.URL_GET_REQUEST(this, UrlUtils.MINE_SPEAK_PRICE_URL, map, "报价中...", true, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                RequestModel requestModel = new Gson().fromJson(response,RequestModel.class);
                if (requestModel.getCode() == 200){
                    XToast.show(getBaseContext(),"报价成功!");
                    finish();
                }else {
                    XToast.show(getBaseContext(),"报价失败!");
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


}
