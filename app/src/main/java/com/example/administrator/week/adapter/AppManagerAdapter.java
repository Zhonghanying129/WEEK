package com.example.administrator.week.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.week.R;
import com.example.administrator.week.bean.AppBean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/11 0011.
 */

public class AppManagerAdapter extends RecyclerView.Adapter<AppManagerAdapter.ViewHolder>{
    private Context mcontext;
    private LayoutInflater inflater;
    private List<AppBean> mList;
    private OnItemClickListener onItemClickListener;
    public  void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener=onItemClickListener;
    }
    public AppManagerAdapter(Context context,List<AppBean> mlist)
    {
        this.mcontext=context;
        this.mList=mlist;
        inflater=(LayoutInflater)mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=inflater.inflate(R.layout.app_item,null);
       ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    public void onBindViewHolder(ViewHolder holder,final int position)
    {
        AppBean appBean=mList.get(position);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN)
        {
            holder.iv_app_icon.setBackground(appBean.getIcon());
        }
        else
        {
            BitmapDrawable drawable=(BitmapDrawable) appBean.getIcon();
            Bitmap bitmap=drawable.getBitmap();
            holder.iv_app_icon.setImageBitmap(bitmap);
        }
        holder.tv_app_name.setText(appBean.getAppName());
        holder.tv_app_packagename.setText(appBean.getPackageName());
        holder.tv_app_size.setText(appBean.getAppSize());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(position);
            }
        });
    }
    public int getItemCount()
    {
        return mList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView iv_app_icon;
        private TextView tv_app_name;
        private TextView tv_app_packagename;
        private TextView tv_app_size;
        public ViewHolder(View itemview)
        {
            super(itemview);
            iv_app_icon=(ImageView)itemview.findViewById(R.id.app_item_icon);
            tv_app_name=(TextView)itemview.findViewById(R.id.app_item_name);
            tv_app_packagename=(TextView)itemview.findViewById(R.id.app_item_packagename);
            tv_app_size=(TextView)itemview.findViewById(R.id.app_item_size);
        }
    }
    public interface OnItemClickListener
    {
        void onClick(int position);
    }
}
