/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Five.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Section.Five.Model.MineOfferAleadListModel;
import com.example.zhengdong.base.Section.Five.Model.MineOfferWaitListModel;
import com.example.zhengdong.base.Section.Five.Model.MineRequireAleadListModel;
import com.example.zhengdong.base.Section.Five.Model.MineRequireWaitListModel;
import com.example.zhengdong.jbx.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineOfferListAdapter extends RecyclerView.Adapter<MineOfferListAdapter.ViewHolder> {



    private List<MineOfferWaitListModel.DataBean.EnquirylistBean> datas1 = null;
    private List<MineOfferAleadListModel.DataBean.EnquirylistBean> datas2 = null;
    private List<MineRequireWaitListModel.DataBean.EnquirylistBean> datas3 = null;
    private List<MineRequireAleadListModel.DataBean.EnquirylistBean> datas4 = null;
    private int types = 0;


    /**
     * 修改 增加context
     *
     * @param datas
     * @param context
     */
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void OnItemClick(View view, int name, String match_enqu_id);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public MineOfferListAdapter(Context context, List<MineOfferWaitListModel.DataBean.EnquirylistBean> datas1, List<MineOfferAleadListModel.DataBean.EnquirylistBean> datas2, List<MineRequireWaitListModel.DataBean.EnquirylistBean> datas3, List<MineRequireAleadListModel.DataBean.EnquirylistBean> datas4, int types) {
        mContext = context;
        this.datas1 = datas1;
        this.datas2 = datas2;
        this.datas3 = datas3;
        this.datas4 = datas4;
        this.types = types;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mine_offer_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        if (types == 1) {
            viewHolder.firstCell.setVisibility(View.VISIBLE);
            viewHolder.oSpeakNumerTxt.setText("报价单号: " + datas1.get(position).getEnqu_code());
            viewHolder.oSpeakPeopleTxt.setText("制单人员: " + datas1.get(position).getUser_name());
            viewHolder.oSpeakTimeTxt.setText("发布时间: " + datas1.get(position).getCreate_ts());
            viewHolder.firstCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view, types, datas1.get(position).getMatch_enqu_id());
                }
            });
        } else {
            viewHolder.firstCell.setVisibility(View.GONE);
        }
        if (types == 2) {
            viewHolder.secondCell.setVisibility(View.VISIBLE);
            viewHolder.twoOrderNumberTxt.setText("报价单号: " + datas2.get(position).getResp_code());
            viewHolder.twoMakePeopleTxt.setText("制单人员: " + datas2.get(position).getUser_name());
            viewHolder.twoPublishTimeTxt.setText("发布时间: " + datas2.get(position).getCreate_ts());
            viewHolder.twoOrderTimeTxt.setText("报价时间: " + datas2.get(position).getModify_ts());
            viewHolder.twoTotalmoneyTxt.setText("" + datas2.get(position).getPrice());
            viewHolder.twoRemarkTxt.setText("备注: " + datas2.get(position).getRemark());
            viewHolder.secondCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view, types, datas2.get(position).getMatch_resp_id());
                }
            });
        } else {
            viewHolder.secondCell.setVisibility(View.GONE);
        }
        if (types == 3) {
            viewHolder.threeCell.setVisibility(View.VISIBLE);
            viewHolder.tRequireNumberTxt.setText("询价单号: " + datas3.get(position).getEnqu_code());
            viewHolder.tRequireTimeTxt.setText("发布时间: " + datas3.get(position).getCreate_ts());
            viewHolder.threeCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view, types, datas3.get(position).getMatch_enqu_id());
                }
            });
        } else {
            viewHolder.threeCell.setVisibility(View.GONE);
        }
        if (types == 4) {
            viewHolder.fourCell.setVisibility(View.VISIBLE);
            viewHolder.fourOrderNumberTxt.setText("报价单号: "+datas4.get(position).getResp_code());
            viewHolder.fourPublishTxt.setText("报价时间: "+datas4.get(position).getCreate_ts());
            viewHolder.fourTotalTxt.setText("有"+datas4.get(position).getOrg_count()+"个商家报价");
            viewHolder.fourCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view, types, datas4.get(position).getMatch_enqu_id());
                }
            });
        } else {
            viewHolder.fourCell.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        if (types == 1) {
            return datas1.size();
        } else if (types == 2) {
            return datas2.size();
        } else if (types == 3) {
            return datas3.size();
        } else if (types == 4) {
            return datas4.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.first_cell)
        LinearLayout firstCell;
        @BindView(R.id.o_speak_numer_txt)
        TextView oSpeakNumerTxt;
        @BindView(R.id.o_speak_people_txt)
        TextView oSpeakPeopleTxt;
        @BindView(R.id.o_speak_time_txt)
        TextView oSpeakTimeTxt;

        @BindView(R.id.second_cell)
        LinearLayout secondCell;
        @BindView(R.id.two_order_number_txt)
        TextView twoOrderNumberTxt;
        @BindView(R.id.two_make_people_txt)
        TextView twoMakePeopleTxt;
        @BindView(R.id.two_publish_time_txt)
        TextView twoPublishTimeTxt;
        @BindView(R.id.two_order_time_txt)
        TextView twoOrderTimeTxt;
        @BindView(R.id.two_totalmoney_txt)
        TextView twoTotalmoneyTxt;
        @BindView(R.id.two_remark_txt)
        TextView twoRemarkTxt;

        @BindView(R.id.three_cell)
        LinearLayout threeCell;
        @BindView(R.id.t_require_number_txt)
        TextView tRequireNumberTxt;
        @BindView(R.id.t_require_time_txt)
        TextView tRequireTimeTxt;

        @BindView(R.id.four_cell)
        LinearLayout fourCell;
        @BindView(R.id.four_order_number_txt)
        TextView fourOrderNumberTxt;
        @BindView(R.id.four_publish_txt)
        TextView fourPublishTxt;
        @BindView(R.id.four_total_txt)
        TextView fourTotalTxt;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
