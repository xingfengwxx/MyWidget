package com.wangxingxing.widget.lsn18;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.chad.library.adapter.base.BaseViewHolder;

public class BaseDelegateAdapter extends DelegateAdapter.Adapter<BaseViewHolder> {
    //helper类
    private LayoutHelper mLayoutHelper;
    //总数量
    private int mCount = -1;
    //xmllayout的id
    private int mLayoutId = -1;
    //上下文
    private Context mContext;
    //itemView 类型
    private int mViewTypeItem = -1;

    public BaseDelegateAdapter(Context context, LayoutHelper layoutHelper, int layoutId, int count, int viewTypeItem) {
        this.mContext = context;
        this.mCount = count;
        this.mLayoutHelper = layoutHelper;
        this.mLayoutId = layoutId;
        this.mViewTypeItem = viewTypeItem;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == mViewTypeItem) {
            return new BaseViewHolder(LayoutInflater.from(mContext).inflate(mLayoutId, viewGroup, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {

    }

    @Override
    public int getItemViewType(int position) {
        return  mViewTypeItem;
    }

    @Override
    public int getItemCount() {
        return mCount;
    }
}
