package com.example.damai.ui.topic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.damai.R;
import com.example.damai.net.NetConfig;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by guodazhao on 2018/1/30 0030.
 */

public class DaMaiAdapter extends RecyclerView.Adapter<DaMaiAdapter.DaMaiViewHolder> {


    private LayoutInflater inflater;
    private Context context;
    private ArrayList<TopicBean> data;

    public DaMaiAdapter(Context context, ArrayList<TopicBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    //创建viewholder
    @Override
    public DaMaiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_topic_damai, parent, false);
        return new DaMaiViewHolder(view);
    }

    //绑定viewholder
    @Override
    public void onBindViewHolder(DaMaiViewHolder holder, int position) {
        //从集合中得到bean
        TopicBean bean = data.get(position);
        holder.itemTopicTitle.setText(bean.getN());
//图片接口拼串
        String i = bean.getI() + "";
        String imageURI = NetConfig.BASR_IMG + i.substring(0, i.length() - 2) + "/" + i + "_n.jpg";
        //glide加载图片
        Glide.with(context).load(imageURI).into(holder.itemTopicIcon);
    }


    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    static class DaMaiViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_topic_icon)
        ImageView itemTopicIcon;
        @BindView(R.id.item_topic_title)
        TextView itemTopicTitle;

        public DaMaiViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
