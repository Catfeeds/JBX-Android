/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Second.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.Macro.LogUtil;
import com.example.zhengdong.base.Section.First.View.GlideApp;
import com.example.zhengdong.base.Section.Five.View.CircleImageView;
import com.example.zhengdong.base.Section.Second.Model.BoutiqueListModel;
import com.example.zhengdong.jbx.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BoutiqueListAdapter extends RecyclerView.Adapter<BoutiqueListAdapter.ViewHolder> {


    private int types = 0;
    public List<BoutiqueListModel.DataBean.FanexListBean> datas = null;

    /**
     * 修改 增加context
     *
     * @param datas
     * @param context
     */
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void OnItemClick(View view, String graphical_id, String name);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public BoutiqueListAdapter(Context context, List<BoutiqueListModel.DataBean.FanexListBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.boutique_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.nameTxt.setText("" + datas.get(position).getUser_name());
        viewHolder.timeTxt.setText("" + datas.get(position).getApply_ts());
        String flag = "";
        for (int i = 0;i< datas.get(position).getFanc_flag().size();i++){
            flag += "#"+datas.get(position).getFanc_flag().get(i) + "  ";
        }
        viewHolder.flagTxt.setText(""+flag);
        LogUtil.e("图片路径为",""+UrlUtils.BASE_PIC_URL+datas.get(position).getPic_path());
        GlideApp.with(mContext)
                .load(UrlUtils.BASE_PIC_URL + datas.get(position).getPic_path())
                .placeholder(R.drawable.placerholder)
                .into(viewHolder.pic);
        viewHolder.txt.setText(datas.get(position).getPresentation());

        viewHolder.cardCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.OnItemClick(view, datas.get(position).getGraphical_id(),datas.get(position).getFanc_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.pic)
        ImageView pic;
        @BindView(R.id.txt)
        TextView txt;
        @BindView(R.id.card_cell)
        LinearLayout cardCell;
        @BindView(R.id.header_pic)
        CircleImageView headerPic;
        @BindView(R.id.name_txt)
        TextView nameTxt;
        @BindView(R.id.time_txt)
        TextView timeTxt;
        @BindView(R.id.flag_txt)
        TextView flagTxt;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
