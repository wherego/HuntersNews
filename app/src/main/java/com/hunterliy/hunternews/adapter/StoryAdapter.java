package com.hunterliy.hunternews.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hunterliy.hunternews.R;
import com.hunterliy.hunternews.bean.Story;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

    private List<Story> list;
    private OnItemClickListener itemClickListener = null;


    public StoryAdapter(List<Story> list){
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Story story = list.get(position);
        holder.tv_title.setText(story.getTitle());
        Glide.clear(holder.item_image);
        Glide.with(holder.item_image.getContext())
                .load(story.getImages().get(0))
                .fitCenter()
                .into(holder.item_image);
        if (itemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(v, holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView item_image;
        TextView tv_title;

        public  ViewHolder(View view){
            super(view);
            item_image = (ImageView) view.findViewById(R.id.item_image);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
        }

    }
}
