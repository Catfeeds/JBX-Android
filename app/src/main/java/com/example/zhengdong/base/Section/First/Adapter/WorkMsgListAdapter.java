/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.First.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Section.Four.Model.NewsListModel;
import com.example.zhengdong.jbx.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkMsgListAdapter extends RecyclerView.Adapter<WorkMsgListAdapter.ViewHolder> {
    public List<NewsListModel.DataBean.EcInformationBean> datas = null;

    String[] title = {
            "工作通知", "商城互动", "公司内部"
    };
    int[] icon = {
            R.drawable.infor_tab_notice, R.drawable.infor_tab_bus, R.drawable.infor_tab_bus1
    };
    String[] contentx = {
            "加工单待报价 10条;" + "\n" + "业务流转待处理 3条；", "大兴向你发来一条消息；" + "\n" + " 华盛向你发来两条消息；", "鄂尔多斯：“你什么时候发货给商家”； " + "\n" + "王航：“抓紧点时间，客户催了”；"
    };


    /**
     * 修改 增加context
     *
     * @param datas
     * @param context
     */
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void OnItemClick(View view, String name);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public WorkMsgListAdapter(Context context, List<NewsListModel.DataBean.EcInformationBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.work_msg_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.pic.setBackgroundResource(icon[position]);
        viewHolder.titleTxt.setText(title[position]);
        viewHolder.contentTxt.setText(contentx[position]);
        viewHolder.cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.OnItemClick(view,"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return icon.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.pic)
        ImageView pic;
        @BindView(R.id.title_txt)
        TextView titleTxt;
        @BindView(R.id.time_txt)
        TextView timeTxt;
        @BindView(R.id.content_txt)
        TextView contentTxt;
        @BindView(R.id.cell)
        LinearLayout cell;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
