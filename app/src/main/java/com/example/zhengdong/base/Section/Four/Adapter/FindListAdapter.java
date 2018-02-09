/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Section.First.View.GlideApp;
import com.example.zhengdong.base.Section.Four.Model.NewsListModel;
import com.example.zhengdong.base.Section.Four.Model.NewsTitleModel;
import com.example.zhengdong.base.Section.Four.View.HorizontalRecycleView.PagingScrollHelper;
import com.example.zhengdong.jbx.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindListAdapter extends RecyclerView.Adapter<FindListAdapter.ViewHolder> {
    private List<NewsListModel.DataBean.EcInformationBean> newsDatas = null;
    public List<NewsTitleModel.DataBean.EcInformationCatBean> datas = null;

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


    public FindListAdapter(Context context, List<NewsTitleModel.DataBean.EcInformationCatBean> datas, List<NewsListModel.DataBean.EcInformationBean> newDataSource) {
        mContext = context;
        this.datas = datas;
        this.newsDatas = newDataSource;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.find_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        if (position == 0) {
            viewHolder.headerLay.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//            HorizontalPageLayoutManager linearLayoutManager = new HorizontalPageLayoutManager(1,2);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            viewHolder.horizontalRv.setLayoutManager(linearLayoutManager);
            PagingScrollHelper pagingScrollHelper = new PagingScrollHelper();
            pagingScrollHelper.setUpRecycleView(viewHolder.horizontalRv);
            pagingScrollHelper.setOnPageChangeListener(new PagingScrollHelper.onPageChangeListener() {
                @Override
                public void onPageChange(int index) {
                    if (index == 0){
                        viewHolder.firstPoint.setBackgroundResource(R.drawable.point_deep);
                        viewHolder.secondPoint.setBackgroundResource(R.drawable.point_light);
                    }else if (index == 1){
                        viewHolder.firstPoint.setBackgroundResource(R.drawable.point_light);
                        viewHolder.secondPoint.setBackgroundResource(R.drawable.point_deep);
                    }
                }
            });
            FindHorizontalListAdapter findHorizontalListAdapter = new FindHorizontalListAdapter(mContext, datas);
            viewHolder.horizontalRv.setAdapter(findHorizontalListAdapter);
            findHorizontalListAdapter.setOnItemClickListener(new FindHorizontalListAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(View view, String name, int newId) {
                    mOnItemClickListener.OnItemClick(view, name, 1, newId);
                }
            });



        } else {
            viewHolder.headerLay.setVisibility(View.GONE);
        }
        if (newsDatas != null){
            viewHolder.titleTxt.setText(""+newsDatas.get(position).getTitle());
            viewHolder.subTitleTxt.setText(""+newsDatas.get(position).getAdd_time());
            GlideApp.with(mContext)
                    .load(newsDatas.get(position).getImgUrl())
                    .placeholder(R.drawable.placerholder)
                    .centerCrop()
                    .into(viewHolder.rightPic);
            viewHolder.cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,""+newsDatas.get(position).getContent(),2,position);
                }
            });
        }else {
            viewHolder.titleTxt.setText("暂无数据");
            viewHolder.subTitleTxt.setText("暂无数据");
        }

    }

    public void setNewsDatas(List<NewsListModel.DataBean.EcInformationBean> newDataSource){
        newsDatas = newDataSource;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (newsDatas != null){
            return newsDatas.size();
        }else {
            return 5;
        }
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
        @BindView(R.id.horizontal_rv)
        RecyclerView horizontalRv;
        @BindView(R.id.first_point)
        ImageView firstPoint;
        @BindView(R.id.second_point)
        ImageView secondPoint;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
