package com.wangxingxing.widget.lsn8.notch;

import android.os.Bundle;

import com.wangxingxing.widget.R;
import com.wangxingxing.widget.lsn8.notch.notch.StatusBarUtil;
import com.wangxingxing.widget.lsn8.pixel.ScreenAdapterLayout;

public class NotchActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notch);


    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(getIsHasCutout()){
            ScreenAdapterLayout layout = findViewById(R.id.layout);

            layout.setPadding(0, StatusBarUtil.getStatusBarHeight(this)
                    , 0, 0);
        }
    }

}
