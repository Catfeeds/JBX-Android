/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.First.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;

import com.example.zhengdong.base.Macro.CountAnimationTextView;
import com.example.zhengdong.base.Macro.LogUtil;
import com.example.zhengdong.base.Section.First.Model.OrganPartmentListModel;
import com.example.zhengdong.base.Section.First.View.RecyclerBanner;
import com.example.zhengdong.jbx.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class AdvitiseListAdapter extends RecyclerView.Adapter<AdvitiseListAdapter.ViewHolder> {
    public OrganPartmentListModel datas = null;
    private List<RecyclerBanner.BannerEntity> urls = new ArrayList<>();
    /**
     * 修改 增加context
     *
     * @param datas
     * @param context
     */
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void OnItemClick(View view, String i, String position, String name);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public AdvitiseListAdapter(Context context, OrganPartmentListModel datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.advertise_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        if (position == 0) {
            viewHolder.firstCell.setVisibility(View.VISIBLE);
            urls.add(new Entity("", ""));
            urls.add(new Entity("", ""));
            urls.add(new Entity("", ""));
            viewHolder.adBannerRv.setOnPagerClickListener(new RecyclerBanner.OnPagerClickListener() {
                @Override
                public void onClick(RecyclerBanner.BannerEntity entity) {
                    LogUtil.e("网址为" + entity.getLink());

                }
            });
            viewHolder.adBannerRv.setDatas(urls);
            // 文字
            viewHolder.fFirstTxt.setDecimalFormat(new DecimalFormat("###,###,###"))
                    .setAnimationDuration(10000)
                    .countAnimation(0, 100);
            viewHolder.fSecondTxt.setDecimalFormat(new DecimalFormat("###,###,###"))
                    .setAnimationDuration(10000)
                    .countAnimation(0, 99999);
            viewHolder.fThreeTxt.setDecimalFormat(new DecimalFormat("###,###,###"))
                    .setAnimationDuration(10000)
                    .countAnimation(0, 99999);
            viewHolder.fFourTxt.setDecimalFormat(new DecimalFormat("###,###,###"))
                    .setAnimationDuration(10000)
                    .countAnimation(0, 99999);

        } else {
            viewHolder.firstCell.setVisibility(View.GONE);
        }
        if (position == 1) {
            SlideInUpAnimator animator = new SlideInUpAnimator(new OvershootInterpolator(1f));
            viewHolder.secondRv.setItemAnimator(animator);
            viewHolder.secondRv.getItemAnimator().setAddDuration(1000);
            viewHolder.secondCell.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            viewHolder.secondRv.setLayoutManager(linearLayoutManager);
            TradeListAdapter tradeListAdapter = new TradeListAdapter(mContext,null);
            viewHolder.secondRv.setAdapter(tradeListAdapter);
            tradeListAdapter.setOnItemClickListener(new TradeListAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(View view, String i, String position, String name) {

                }
            });
        } else {
            viewHolder.secondCell.setVisibility(View.GONE);
        }
        if (position == 2) {
            viewHolder.threeCell.setVisibility(View.VISIBLE);
        } else {
            viewHolder.threeCell.setVisibility(View.GONE);
        }

    }

    private class Entity implements RecyclerBanner.BannerEntity {

        String url;
        String link;

        public Entity(String url, String link) {
            this.url = url;
            this.link = link;
        }

        @Override
        public String getUrl() {
            return url;
        }

        @Override
        public String getLink() {
            return link;
        }
    }


    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ad_banner_rv)
        RecyclerBanner adBannerRv;
        @BindView(R.id.first_cell)
        LinearLayout firstCell;
        @BindView(R.id.f_first_txt)
        CountAnimationTextView fFirstTxt;
        @BindView(R.id.f_second_txt)
        CountAnimationTextView fSecondTxt;
        @BindView(R.id.f_three_txt)
        CountAnimationTextView fThreeTxt;
        @BindView(R.id.f_four_txt)
        CountAnimationTextView fFourTxt;

        @BindView(R.id.second_rv)
        RecyclerView secondRv;
        @BindView(R.id.second_cell)
        LinearLayout secondCell;
        @BindView(R.id.three_rv)
        RecyclerView threeRv;
        @BindView(R.id.three_cell)
        LinearLayout threeCell;



        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
