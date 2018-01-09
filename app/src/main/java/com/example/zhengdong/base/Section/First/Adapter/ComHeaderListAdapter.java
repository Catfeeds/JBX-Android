package com.example.zhengdong.base.Section.First.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Macro.FlashTextView;
import com.example.zhengdong.base.Section.First.Model.OrganPartmentListModel;
import com.example.zhengdong.jbx.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComHeaderListAdapter extends RecyclerView.Adapter<ComHeaderListAdapter.ViewHolder> {
    public OrganPartmentListModel datas = null;
    public ArrayList<Integer> picArr = null;
    int  colorArr[] = {
            R.color.skyblue,R.color.green
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
        void OnItemClick(View view, String i, String position, String name);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public ComHeaderListAdapter(Context context, OrganPartmentListModel datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.common_header_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        viewHolder.nameTxt.setText(datas.getData().get(position).getName());
        viewHolder.contentRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.OnItemClick(view,datas.getData().get(position).getParent_dept_id(),datas.getData().get(position).getName(),datas.getData().get(position).getDept_id());
            }
        });


    }


    @Override
    public int getItemCount() {
        return datas.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rv_header)
        LinearLayout rvHeader;
        @BindView(R.id.name_txt)
        FlashTextView nameTxt;
        @BindView(R.id.go_pic)
        ImageView goPic;
        @BindView(R.id.number_txt)
        TextView numberTxt;
        @BindView(R.id.content_rel)
        RelativeLayout contentRel;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
