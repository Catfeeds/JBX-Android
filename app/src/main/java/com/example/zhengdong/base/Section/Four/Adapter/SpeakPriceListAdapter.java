/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Section.Four.Model.NewsTitleModel;
import com.example.zhengdong.jbx.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpeakPriceListAdapter extends RecyclerView.Adapter<SpeakPriceListAdapter.ViewHolder> {
    public List<NewsTitleModel.DataBean.EcInformationCatBean> datas = null;
    int [] picArr = {
           R.drawable.icon_guancai,R.drawable.icon_ban,R.drawable.icon_peijian,R.drawable.icon_zhipin
    };
    String [] strArr = {
            "提供最新厂家不锈钢板材价格表、不 锈钢板材批发报价以及不锈钢板材品 牌、种类、图片信息。","提供不锈钢管材产品的详细参数，实 时报价，价格行情，优质批发\\供应 等信息。","不锈钢配件批发，厂家直销优质产品， 品类齐全，品牌多样，供您选择!","国内专业的不锈钢制品网上商城，提 供不锈钢制品价格，报价等信息，买 不锈钢制品，上聚不锈就购了。"
    };
    String [] titleArr = {
            "不锈钢管材","不锈钢板(卷)","不锈钢配件","不锈钢制品"
    };
    /**
     * 修改 增加context
     *
     * @param datas
     * @param context
     */
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;
    private List<Map<String, Object>> data_list = new ArrayList<Map<String, Object>>();

    public interface OnItemClickListener {
        void OnItemClick(View view, String name, int position, int i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public SpeakPriceListAdapter(Context context, List<NewsTitleModel.DataBean.EcInformationCatBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.speak_price_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        viewHolder.firstPic.setBackgroundResource(picArr[position]);
        viewHolder.firstTxt.setText(titleArr[position]);
        viewHolder.secondTxt.setText(strArr[position]);
        viewHolder.firstCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.OnItemClick(view,"",position,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return picArr.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.first_pic)
        ImageView firstPic;
        @BindView(R.id.first_txt)
        TextView firstTxt;
        @BindView(R.id.second_txt)
        TextView secondTxt;
        @BindView(R.id.first_cell)
        LinearLayout firstCell;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
