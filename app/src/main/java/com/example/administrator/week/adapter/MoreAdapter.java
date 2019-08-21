package com.example.administrator.week.adapter;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.week.ImageDonwload;
import com.example.administrator.week.R;
import com.example.administrator.week.bean.ImageList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/5/16 0016.
 */

public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.ViewHolder>{
    private Context context;
    private List<ImageList> lists;
    private List<String> permissionList;
    private int position;
    public MoreAdapter(Context context,List<ImageList> list)
    {
        this.context=context;
        this.lists=list;
    }
    class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView mimage;
        private ImageView download;
        public ViewHolder(View itemView)
        {
            super(itemView);
            mimage=(ImageView)itemView.findViewById(R.id.iamge_m);
            download=(ImageView)itemView.findViewById(R.id.dowmload);
        }
    }
    public MoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.picture_image_item,parent,false);
        final MoreAdapter.ViewHolder holder=new MoreAdapter.ViewHolder(view);
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionList=new ArrayList<String>();
                position=holder.getAdapterPosition();
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED)
                {
                    permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
                if (!permissionList.isEmpty())
                {
                    String[] permissions=permissionList.toArray(new String[permissionList.size()]);
                    ActivityCompat.requestPermissions((Activity) context,permissions,2);
                }
                else
                {
                    ImageDonwload.donwloadImage(context,lists.get(position).getImagelist());
                    ObjectAnimator ani = ObjectAnimator.ofFloat(holder.download,"translationY",0f,10f,20f,-10f,0f);
                    ani.setDuration(500);
                    ani.start();
                }
            }
        });
        return holder;
    }
    public void onBindViewHolder(MoreAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(lists.get(position).getImagelist()).placeholder(R.mipmap.load4).into(holder.mimage);
    }
    public int getItemCount()
    {
        return lists.size();
    }
}
