package com.hunterliy.hunternews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hunterliy.hunternews.R;
import com.hunterliy.hunternews.bean.NewsListEntity;

public class NewsViewHolder extends RecyclerView.ViewHolder{

    ImageView item_image;
    TextView tv_title;
    TextView tv_description;
    TextView tv_time;
    public NewsViewHolder(ViewGroup parent,int resId) {
        super(LayoutInflater.from(parent.getContext()).inflate(resId,parent,false));
        item_image = (ImageView) itemView.findViewById(R.id.item_image);
        tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        tv_description = (TextView) itemView.findViewById(R.id.tv_description);
        tv_time = (TextView) itemView.findViewById(R.id.tv_time);
    }


    public Context getContext(){
        return itemView.getContext();
    }
    public void setData(NewsListEntity data) {
        tv_title.setText(data.getTitle());
        tv_description.setText(data.getDescription());
        tv_time.setText(data.getCtime());
        Glide.with(getContext())
                .load(data.getPicUrl())
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(item_image);
    }
}