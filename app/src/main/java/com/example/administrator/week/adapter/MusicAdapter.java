package com.example.administrator.week.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.week.R;
import com.example.administrator.week.bean.MusicBean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/13 0013.
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder>
{
    private List<MusicBean> mlist;
    private Context mcontext;
    public MusicAdapter(Context context,List<MusicBean> musiclist)
    {
        this.mlist=musiclist;
        this.mcontext=context;
    }

   static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView musicIV;
        TextView musicName;
        TextView singerName;
        public ViewHolder(View view)
        {
            super(view);
            musicName=(TextView)view.findViewById(R.id.musicname);
            singerName=(TextView) view.findViewById(R.id.singername);
            musicIV=(ImageView) view.findViewById(R.id.music_iv);

        }
    }
    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    public int getItemCount()
    {
        return mlist.size();
    }

    @Override
    public void onBindViewHolder(MusicAdapter.ViewHolder holder, int position) {
        MusicBean musicBean=mlist.get(position);
        holder.singerName.setText(musicBean.getSinggerName());
        holder.musicName.setText(musicBean.getSongname());
        Glide.with(mcontext).load(musicBean.getSongPicUrl()).into(holder.musicIV);

    }
}
