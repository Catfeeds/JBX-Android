package com.example.zhengdong.base.Section.Work.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.jbx.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkItemAdapter extends RecyclerView.Adapter<WorkItemAdapter.ViewHolder> {
    private final ArrayList<Integer> datas1;
    public ArrayList<String> datas = null;



    /**
     * 修改 增加context
     *
     * @param datas
     * @param context
     */
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void OnItemClick(View view, int position, String name);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public WorkItemAdapter(Context context, ArrayList<String> datas, ArrayList<Integer> datas1) {
        mContext = context;
        this.datas = datas;
        this.datas1 = datas1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.work_list_inner_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.firstPic.setBackgroundResource(datas1.get(position));
        viewHolder.firstTxt.setText(datas.get(position));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.OnItemClick(view,position,datas.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.first_pic)
        ImageView firstPic;
        @BindView(R.id.first_txt)
        TextView firstTxt;
        @BindView(R.id.item_view)
        RelativeLayout itemView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
