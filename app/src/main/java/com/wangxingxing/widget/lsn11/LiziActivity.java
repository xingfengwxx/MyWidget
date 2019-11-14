package com.wangxingxing.widget.lsn11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wangxingxing.widget.R;

public class LiziActivity extends AppCompatActivity {

    Handler handler = new Handler();
    private FrameLayout mMainView;
    private SplashView splashView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_lizi);

        //将动画层盖在实际的操作图层上
        mMainView = new FrameLayout(this);

        ContentView contentView = new ContentView(this);
        mMainView.addView(contentView);

        splashView = new SplashView(this);
        mMainView.addView(splashView);
        setContentView(mMainView);

        //后台开始加载数据
        startLoadData();
    }

    private void startLoadData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //数据加载完毕，进入主界面--->开启后面的两个动画
                splashView.splashDisappear();
            }
        },5000);//延迟时间
    }
}
