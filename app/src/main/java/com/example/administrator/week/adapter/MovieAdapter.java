package com.example.administrator.week.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.week.MovieMoreActivity;
import com.example.administrator.week.R;
import com.example.administrator.week.bean.MovieBean;

import java.util.List;


/**
 * Created by Administrator on 2019/4/20 0020.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    private List<MovieBean> mlist;
    private Context mcontext;
    public MovieAdapter(Context mcontext,List<MovieBean> movielist)
    {
        this.mlist=movielist;
        this.mcontext=mcontext;
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView movieIV;
        TextView movieName;
        TextView actorName;
        TextView average;
        TextView type;
        TextView summary;
        public ViewHolder(View view)
        {
            super(view);
            movieName=(TextView)view.findViewById(R.id.movie_title);
            actorName=(TextView) view.findViewById(R.id.movie_actor);
            movieIV=(ImageView) view.findViewById(R.id.movie_image);
            average=(TextView)view.findViewById(R.id.movie_average);
            type=(TextView)view.findViewById(R.id.movie_type);

        }
    }
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);
        MovieAdapter.ViewHolder viewHolder=new MovieAdapter.ViewHolder(view);
        return viewHolder;
    }
    public int getItemCount()
    {
        return mlist.size();
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, final int position) {
        MovieBean movieBean=mlist.get(position);
        holder.actorName.setText(movieBean.getActor());
        holder.movieName.setText(movieBean.getTitle());
        holder.type.setText(movieBean.getType());
        holder.average.setText(movieBean.getAverager());
        Glide.with(mcontext).load(movieBean.getMoviePicUrl()).into(holder.movieIV);
        holder.movieIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=mlist.get(position).getId();

                Intent mIntent=new Intent(mcontext,MovieMoreActivity.class);
                mIntent.putExtra("id",id);

                mcontext.startActivity(mIntent);
            }
        });
    }

}
