package com.shao.testdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shao.testdemo.R;
import com.shao.testdemo.adapter.HomeRecyclerViewAdapter.HomeRecyclerViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shao on 2017-02-22 with AndroidStudio
 */

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewHolder> {
    private List<String> mList;
    private OnItemClickListener mOnItemClickListener;

    public HomeRecyclerViewAdapter(List<String> mList, OnItemClickListener onItemClickListener) {
        this.mList = mList;
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public HomeRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final HomeRecyclerViewHolder holder = new HomeRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_recycler, parent, false));
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, position);
                    return false;
                }
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeRecyclerViewHolder holder, int position) {
        if (mList != null)
            holder.tvHomeRecyclerView.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HomeRecyclerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_home_recycler_view)
        TextView tvHomeRecyclerView;
        View rootView;
        public HomeRecyclerViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

  public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
}
