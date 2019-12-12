package com.wangxingxing.widget.lsn23;

import com.wangxingxing.widget.R;
import com.wangxingxing.widget.lsn23.frags.InteractiveFrag;
import com.wangxingxing.widget.lsn23.frags.LiveFrag;

public class LiveGiftActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_live_gift;
    }

    @Override
    public void initData() {
        super.initData();
        // 加载直播fragment
        LiveFrag liveFrag = new LiveFrag();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_root, liveFrag).commit();
        // 加载
        new InteractiveFrag().show(getSupportFragmentManager(), "InteractiveFrag");
    }
}
