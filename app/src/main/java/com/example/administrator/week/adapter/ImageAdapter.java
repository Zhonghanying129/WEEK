package com.example.administrator.week.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.administrator.week.PictureActivity;
import com.example.administrator.week.R;
import com.example.administrator.week.bean.PictureBean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/16 0016.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private Context mContext;
    private List<PictureBean> mList;

    public ImageAdapter(Context context,List<PictureBean> list)
    {
        this.mContext=context;
        this.mList=list;
    }
    class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;
        private TextView title;
        private RelativeLayout mLayout;

        public ViewHolder(View itemView)
        {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.image_item);
            title=(TextView)itemView.findViewById(R.id.pic_lay_item_title);
            mLayout=(RelativeLayout)itemView;
        }
    }
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        View view= LayoutInflater.from(mContext).inflate(R.layout.picture_layout_item,parent,false);
        final ImageAdapter.ViewHolder holder=new ImageAdapter.ViewHolder(view);
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Intent intent=new Intent (mContext, PictureActivity.class);
                intent.putExtra("Url",mList.get(position).getUrl());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }
    public void onBindViewHolder(ImageAdapter.ViewHolder holder,int position)
    {
        Glide.with(mContext).load(mList.get(position).getImage()).placeholder(R.mipmap.load4).into(holder.image);
        holder.title.setText(mList.get(position).getTitle());
    }
    public int getItemCount()
    {
        return mList.size();
    }
}
