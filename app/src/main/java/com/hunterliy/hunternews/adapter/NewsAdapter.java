package com.hunterliy.hunternews.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.hunterliy.hunternews.R;
import com.hunterliy.hunternews.bean.NewsListEntity;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private List<NewsListEntity> list = null;
    private OnItemClickListener itemClickListener = null;

    public NewsAdapter(List<NewsListEntity> list){
        this.list = list;
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(parent, R.layout.item);
    }


    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {
        holder.setData(list.get(position));

        if (itemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(v,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void fillList(List<NewsListEntity> list) {
        list.clear();
        list.addAll(list);
    }


}
