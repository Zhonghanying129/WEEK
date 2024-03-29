package com.example.administrator.week.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.week.R;
import com.example.administrator.week.bean.SystemInfoModel;
import com.example.administrator.week.bean.Type;
import com.example.administrator.week.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/6/9 0009.
 */

public class SystemInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mcontext;
    private LayoutInflater inflater;
    private List<SystemInfoModel> mlist;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public SystemInfoAdapter(Context mContext, List<SystemInfoModel> mList) {
        this.mcontext = mContext;
        this.mlist = mList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtils.i("viewType:" + viewType);
        if (viewType == Type.LAYOUT_TITLE) {
            return new TitleViewHolder(inflater.inflate(R.layout.layout_title_item, null));
        } else if (viewType == Type.LAYOUT_CONTENT_TEXT) {
            return new ContentTextViewHolder(inflater.inflate(R.layout.layout_content_text_item, null));
        } else if (viewType == Type.LAYOUT_CONTENT_BUTTON) {
            return new ContentButtonViewHolder(inflater.inflate(R.layout.layout_content_button_item, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        SystemInfoModel model = mlist.get(position);
        if (holder instanceof TitleViewHolder) {
            LogUtils.i("TitleViewHolder itemView init" + model.getTitle());
            ((TitleViewHolder) holder).tv_title.setText(model.getTitle());
        } else if (holder instanceof ContentTextViewHolder) {
            ((ContentTextViewHolder) holder).tv_content_text_key.setText(model.getKey());
            ((ContentTextViewHolder) holder).tv_content_text_value.setText(model.getValue());
        } else if (holder instanceof ContentButtonViewHolder) {
            ((ContentButtonViewHolder) holder).tv_content_button_key.setText(model.getButtonkey());
            ((ContentButtonViewHolder) holder).btn_content_button_value.setText(model.getButtonvalue());

            ((ContentButtonViewHolder) holder).btn_content_button_value.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onClick(position);
                    }
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return mlist.size();
    }

    @Override
    public int getItemViewType(int position) {
        int type = mlist.get(position).getType();
        return type;
    }
    public class TitleViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;

        public TitleViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            LogUtils.i("TitleViewHolder itemView init");
        }
    }
    public class ContentTextViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_content_text_key;
        private TextView tv_content_text_value;

        public ContentTextViewHolder(View itemView) {
            super(itemView);

            tv_content_text_key = (TextView) itemView.findViewById(R.id.tv_content_text_key);
            tv_content_text_value = (TextView) itemView.findViewById(R.id.tv_content_text_value);
        }
    }
    public class ContentButtonViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_content_button_key;
        private Button btn_content_button_value;

        public ContentButtonViewHolder(View itemView) {
            super(itemView);

            tv_content_button_key = (TextView) itemView.findViewById(R.id.tv_content_button_key);
            btn_content_button_value = (Button) itemView.findViewById(R.id.btn_content_button_value);
        }
    }
    public interface OnItemClickListener {
        void onClick(int position);
    }

}
