package com.example.zhengdong.base.Section.Four.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Section.Four.Model.NewsListModel;
import com.example.zhengdong.base.Section.First.View.GlideApp;
import com.example.zhengdong.jbx.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {
    public List<NewsListModel.DataBean.EcInformationBean> datas = null;


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


    public NewsListAdapter(Context context, List<NewsListModel.DataBean.EcInformationBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.titleTxt.setText(datas.get(position).getTitle());
        viewHolder.subTitleTxt.setText(datas.get(position).getAdd_time());
        GlideApp.with(mContext)
                .load(datas.get(position).getImgUrl())
                .placeholder(R.drawable.placerholder)
                .centerCrop()
                .into(viewHolder.rightPic);
        viewHolder.cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.OnItemClick(view,datas.get(position).getContent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

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
