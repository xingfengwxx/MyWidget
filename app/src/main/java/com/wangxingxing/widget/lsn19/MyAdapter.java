package com.wangxingxing.widget.lsn19;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.wangxingxing.widget.R;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    String[] data = new String[300];

    public MyAdapter(){
        initData();

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_pager,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.btn.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        Button btn;
        public ViewHolder(View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn);
        }
    }
    private void initData(){
        String[] str = new String[]{"adf","gfgfadfaf","gfgfadfafadf","gfgfadfafdfa","gfgfadfafadffad","gfgfadfafadfasfsfd","gfg","gfgfadfafadfadfafadfa"};
        for (int i  = 0;i<data.length;i++){
            data[i] = str[(int) (Math.random()*str.length)];
        }
    }
}
