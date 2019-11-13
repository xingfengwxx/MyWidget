package com.wangxingxing.widget.lsn10.model;

import com.wangxingxing.widget.lsn10.bean.Girl;

import java.util.List;

public interface IGirlModel {

    //获取数据
    void loadGirlData(IListener listener);

    //接口回调方式，返回数据
    interface IListener {
        void onComplete(List<Girl> girls);
    }
}
