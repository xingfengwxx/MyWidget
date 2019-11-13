package com.wangxingxing.widget.lsn10.conflict;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wangxingxing.widget.R;

import java.util.List;

public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.MyViewHolder> {

    private List<Girl> mData;

    public GirlAdapter(List<Girl> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_adapter, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(mData.get(position).getName());

        Glide.with(holder.itemView.getContext()).load(mData.get(position).getIcon()).into(holder.icon);

        holder.age.setText(mData.get(position).getAge() + "");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView age;
        ImageView icon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            icon = itemView.findViewById(R.id.icon);
            age = itemView.findViewById(R.id.tv_age);
        }
    }

    /**
     * 自定义监听回调，RecyclerView 的 单击和长按事件
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
