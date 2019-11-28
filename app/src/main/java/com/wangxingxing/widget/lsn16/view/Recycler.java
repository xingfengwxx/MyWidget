package com.wangxingxing.widget.lsn16.view;

import android.view.View;

import java.util.Stack;

public class Recycler {

    //回收池的容器，存储所有的回收了的View
    private Stack<View>[] views;

    public Recycler(int viewTypeCount) {
        //根据类型的种类的数量来创建数组
        views = new Stack[viewTypeCount];
        //初始化数组中的每一个Stack
        for (int i = 0; i < viewTypeCount; i++) {
            views[i] = new Stack<>();
        }
    }

    /**
     * 将View放入到对应类型的Stack中
     * @param itemView
     * @param viewType
     */
    public void put(View itemView, int viewType) {
        views[viewType].push(itemView);
    }

    public View get(int viewType) {
        try {
            return views[viewType].pop();
        } catch (Exception e) {
            return null;
        }
    }
}
