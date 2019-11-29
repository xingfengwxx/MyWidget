package com.wangxingxing.widget.lsn17;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.wangxingxing.widget.R;

public class ImmersionActivity extends AppCompatActivity {

    private View statusBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersion);

        initStatus();
        initView();
    }

    private void initStatus() {
        //版本大于等于4.4
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //获取到状态栏设置的两条属性
            int flagTransluncentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            int flagTransluncentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            //在4.4之后又有两种情况，第一种：4.4-5.0，第二种：5.0以上
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //第二种：5.0以上
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.flags |= flagTransluncentNavigation;
                window.setAttributes(attributes);
                window.setStatusBarColor(0);
            } else {
                //第一种：4.4-5.0
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.flags |= flagTransluncentStatus | flagTransluncentNavigation;
                window.setAttributes(attributes);
            }
        }
    }

    private void initView() {
        statusBar = findViewById(R.id.statusBar);
    }

    public void setStatusColor1(View view) {
        statusBar.setVisibility(View.VISIBLE);
        ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        //设置他的高度
        layoutParams.height = getStatusHeight();
        //设置layoutParams
        statusBar.setLayoutParams(layoutParams);
        statusBar.setBackgroundColor(Color.RED);
    }

    public void setStatusColor2(View view) {
        statusBar.setVisibility(View.GONE);
        //获取到根布局的view
        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        //给根布局设置padding值
        rootView.setPadding(0, getStatusHeight(), 0, getNavigationBarHeight());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上
            getWindow().setStatusBarColor(Color.YELLOW);
        } else {
            //4.4-5.0
            //获取到根布局
            ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
            View statusBar = new View(this);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusHeight());
            statusBar.setBackgroundColor(Color.YELLOW);
            statusBar.setLayoutParams(layoutParams);
            decorView.addView(statusBar);
        }
    }

    /**
     * 获取状态栏的高度
     * @return
     */
    public int getStatusHeight() {
        //获取到状态栏的资源ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        //如果取到了
        if (resourceId > 0) {
            //就返回它的高度
            return getResources().getDimensionPixelSize(resourceId);
        }
        //否则返回0
        return 0;
    }

    /**
     * 获取到底部虚拟按键的高度
     * @return
     */
    public int getNavigationBarHeight() {
        //获取到虚拟按键的资源ID
        int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        //如果获取到了
        if(resourceId>0){
            //就返回它的高度
            return getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public void toFixDrawerLayout(View view) {
        Intent intent = new Intent(ImmersionActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}
