/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.zhengdong.base.Section.Four.Model.NewsListModel;
import com.example.zhengdong.jbx.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindListAdapter extends RecyclerView.Adapter<FindListAdapter.ViewHolder> {
    public List<NewsListModel.DataBean.EcInformationBean> datas = null;
    // 初始化数据源
    public String[] txt = {
            "报价中心", "求职招聘", "技术论坛", "商家动态", "平台资讯", "行业资讯", "商业资讯", "下游动态"
    };
    public int[] image = {
            R.drawable.offer_header_icon, R.drawable.job_header_icon, R.drawable.tech_header_icon, R.drawable.shop_header_icon,
            R.drawable.plat_header_icon, R.drawable.ind_header_icon, R.drawable.business_header_icon, R.drawable.downstream_header_icon
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
        void OnItemClick(View view, String name, int i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public FindListAdapter(Context context, List<NewsListModel.DataBean.EcInformationBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.find_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        if (position == 0){
            viewHolder.headerLay.setVisibility(View.VISIBLE);
            getData();
            String[] from = {"image", "text"};
            int[] to = {R.id.image, R.id.txt};
            SimpleAdapter simpleAdapter = new SimpleAdapter(mContext, data_list, R.layout.item, from, to);
            viewHolder.gridview.setAdapter(simpleAdapter);
            viewHolder.gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    LogUtil.e("当前点击的是"+i);
                    mOnItemClickListener.OnItemClick(view,data_list.get(i).get("text").toString(),i);
                }
            });
        }else {
            viewHolder.headerLay.setVisibility(View.GONE);
        }
        viewHolder.titleTxt.setText("测试标题");
        viewHolder.subTitleTxt.setText("测试时间");

    }

    public List<Map<String, Object>> getData() {
        //cion和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < image.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", image[i]);
            map.put("text", txt[i]);
            data_list.add(map);
        }

        return data_list;
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.gridview)
        GridView gridview;
        @BindView(R.id.header_lay)
        LinearLayout headerLay;
        @BindView(R.id.title_txt)
        TextView titleTxt;
        @BindView(R.id.sub_title_txt)
        TextView subTitleTxt;
        @BindView(R.id.right_pic)
        ImageView rightPic;
        @BindView(R.id.cell)
        LinearLayout cell;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
