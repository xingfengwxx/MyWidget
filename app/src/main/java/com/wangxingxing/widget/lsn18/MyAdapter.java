package com.wangxingxing.widget.lsn18;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.wangxingxing.widget.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MyAdapter extends DelegateAdapter.Adapter<MyAdapter.ViewHolder>{
    // 数据源
    private ArrayList<HashMap<String, Object>> listItem;
    //上下文
    private Context context;
    //数据总数量
    private int count = 0;
    //layoutHelper对象
    LayoutHelper layoutHelper;

    public MyAdapter(Context context, LayoutHelper layoutHelper,int count,
                     ArrayList<HashMap<String, Object>> listItem) {
        this.context = context;
        this.layoutHelper = layoutHelper;
        this.count = count;
        this.listItem = listItem;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_vlayout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Text.setText((String) listItem.get(position).get("ItemTitle"));
        holder.image.setImageResource((Integer) listItem.get(position).get("ItemImage"));
    }

    @Override
    public int getItemCount() {
        return count;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Text;
        public ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Text = itemView.findViewById(R.id.Item);
            this.image = itemView.findViewById(R.id.Image);
        }

        public TextView getText() {
            return Text;
        }
    }
}
