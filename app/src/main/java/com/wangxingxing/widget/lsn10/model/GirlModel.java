package com.wangxingxing.widget.lsn10.model;

import com.wangxingxing.widget.R;
import com.wangxingxing.widget.lsn10.bean.Girl;

import java.util.ArrayList;
import java.util.List;

public class GirlModel implements IGirlModel {
    @Override
    public void loadGirlData(IListener listener) {
        List<Girl> data = new ArrayList<>();
        data.add(new Girl("91号", R.drawable.img1, 18));
        data.add(new Girl("92号", R.drawable.img2, 19));
        data.add(new Girl("93号", R.drawable.img3, 18));
        data.add(new Girl("94号", R.drawable.img4, 20));
        data.add(new Girl("95号", R.drawable.img5, 19));
        data.add(new Girl("96号", R.drawable.img6, 18));
        data.add(new Girl("97号", R.drawable.img7, 20));

        listener.onComplete(data);
    }
}
