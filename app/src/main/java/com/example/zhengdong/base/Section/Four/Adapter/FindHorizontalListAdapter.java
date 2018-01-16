/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import com.example.zhengdong.base.Section.Four.Model.NewsListModel;
import com.example.zhengdong.base.Section.Four.Model.NewsTitleModel;
import com.example.zhengdong.jbx.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindHorizontalListAdapter extends RecyclerView.Adapter<FindHorizontalListAdapter.ViewHolder> {
    public List<NewsTitleModel.DataBean.EcInformationCatBean> datas = null;

    // 初始化数据源
    public ArrayList<String> txt = new ArrayList<>();
    public ArrayList<Integer> img = new ArrayList<>();
    public ArrayList<Integer> idx = new ArrayList<>();

    public int[] image = {
            R.drawable.offer_header_icon, R.drawable.job_header_icon, R.drawable.tech_header_icon, R.drawable.shop_header_icon,
            R.drawable.plat_header_icon, R.drawable.ind_header_icon, R.drawable.business_header_icon, R.drawable.downstream_header_icon,
            R.drawable.plat_header_icon, R.drawable.ind_header_icon, R.drawable.business_header_icon, R.drawable.downstream_header_icon
    };
    public int[] ids = {
            0, 1, 2, 3
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
    private List<Map<String, Object>> dataSource = new ArrayList<>();

    public interface OnItemClickListener {
        void OnItemClick(View view, String name, int i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public FindHorizontalListAdapter(Context context, List<NewsTitleModel.DataBean.EcInformationCatBean> datas) {
        mContext = context;
        this.datas = datas;
//        "报价中心", "求职招聘", "技术论坛", "商家动态"
        txt.add(0, "报价中心");
        txt.add(1, "求职招聘");
        txt.add(2, "技术论坛");
        txt.add(3, "商家动态");
        for (int i = 0; i < datas.size(); i++) {
            txt.add(i + 4, datas.get(i).getKey_name());
        }
        for (int i = 0; i < image.length; i++) {
            img.add(i, image[i]);
        }
        idx.add(0, 0);
        idx.add(1, 1);
        idx.add(2, 2);
        idx.add(3, 3);
        for (int i = 0; i < datas.size(); i++) {
            idx.add(i + 4, datas.get(i).getId());
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.find_horizontal_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        if (position == 0){
            getData();
            String[] from = {"image", "text"};
            int[] to = {R.id.image, R.id.txt};
            SimpleAdapter simpleAdapter = new SimpleAdapter(mContext, data_list, R.layout.item, from, to);
            viewHolder.gridview.setAdapter(simpleAdapter);
            viewHolder.gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    LogUtil.e("当前点击的是"+i);
                    mOnItemClickListener.OnItemClick(view, data_list.get(i).get("text").toString(), (Integer) data_list.get(i).get("id"));
                }
            });
        }else {
            getSecondData();
            String[] from = {"image", "text"};
            int[] to = {R.id.image, R.id.txt};
            SimpleAdapter simpleAdapter = new SimpleAdapter(mContext, dataSource, R.layout.item, from, to);
            viewHolder.gridview.setAdapter(simpleAdapter);
            viewHolder.gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    LogUtil.e("当前点击的是"+i);
                    mOnItemClickListener.OnItemClick(view, dataSource.get(i).get("text").toString(), (Integer) dataSource.get(i).get("id"));
                }
            });
        }

    }

    public List<Map<String, Object>> getData() {
        //cion和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < txt.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", img.get(i));
            map.put("text", txt.get(i));
            map.put("id", idx.get(i));
            data_list.add(map);
        }
        return data_list;
    }

    public List<Map<String,Object>> getSecondData() {
        for (int i = 8; i < txt.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", img.get(i));
            map.put("text", txt.get(i));
            map.put("id", idx.get(i));
            dataSource.add(map);
        }
        return dataSource;
    }


    @Override
    public int getItemCount() {
        if (datas.size() > 4) {
            return datas.size()/4+1;
        } else {
            return 1;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.gridview)
        GridView gridview;
        @BindView(R.id.cell)
        LinearLayout cell;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
