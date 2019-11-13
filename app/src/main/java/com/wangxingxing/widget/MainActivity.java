package com.wangxingxing.widget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wangxingxing.widget.lsn10.ConActivity;
import com.wangxingxing.widget.lsn10.RecyclerDelActivity;
import com.wangxingxing.widget.lsn10.RecyclerMenuActivity;
import com.wangxingxing.widget.lsn2.WaterfallFlowLayoutActivity;
import com.wangxingxing.widget.lsn3.FilterActivity;
import com.wangxingxing.widget.lsn3.GradientActivity;
import com.wangxingxing.widget.lsn3.MarqueeActivity;
import com.wangxingxing.widget.lsn3.RadarScanActivity;
import com.wangxingxing.widget.lsn3.ZoomImageActivity;
import com.wangxingxing.widget.lsn3.xfermode.XfermodeActivity;
import com.wangxingxing.widget.lsn3.xfermode.XfermodeDTSActivity;
import com.wangxingxing.widget.lsn3.xfermode.XfermodeSRCActivity;
import com.wangxingxing.widget.lsn4.LayerActivity;
import com.wangxingxing.widget.lsn4.WatchViewActivity;
import com.wangxingxing.widget.lsn5.ScrollActivity;
import com.wangxingxing.widget.lsn6.BezierActivity;
import com.wangxingxing.widget.lsn6.DragBubbleActivity;
import com.wangxingxing.widget.lsn6.PathActivity;
import com.wangxingxing.widget.lsn7.LoadingActivity;
import com.wangxingxing.widget.lsn8.ScreenAdapterActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TitleAdapter.IListener {

    private RecyclerView mRecyclerView;
    private List<String> mData;
    private TitleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mData = getData();
        mAdapter = new TitleAdapter(mData, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private List<String> getData() {
        List<String> titles = new ArrayList<>();
        titles.add("瀑布流式布局");
        titles.add("文字跑马灯");
        titles.add("雷达扫描");
        titles.add("放大镜");
        titles.add("滤镜");
        titles.add("Xfermode");
        titles.add("Xfermode_DTS");
        titles.add("Xfermode_SRC");
        titles.add("渲染模式");
        titles.add("表盘");
        titles.add("图层");
        titles.add("滚动视图");
        titles.add("Path绘制");
        titles.add("多阶贝塞尔曲线");
        titles.add("QQ气泡");
        titles.add("LoadingView");
        titles.add("屏幕适配");
        titles.add("滑动冲突");
        titles.add("RecycleView左滑删除");
        titles.add("RecycleView左滑显示菜单");
        return titles;
    }

    @Override
    public void onClick(View view, String title) {
        if ("瀑布流式布局".equals(title)) {
            startActivity(new Intent(MainActivity.this, WaterfallFlowLayoutActivity.class));
        } else if ("文字跑马灯效果".equals(title)) {
            startActivity(new Intent(MainActivity.this, MarqueeActivity.class));
        } else if ("雷达扫描".equals(title)) {
            startActivity(new Intent(MainActivity.this, RadarScanActivity.class));
        } else if ("放大镜".equals(title)) {
            startActivity(new Intent(MainActivity.this, ZoomImageActivity.class));
        } else if ("滤镜".equals(title)) {
            startActivity(new Intent(MainActivity.this, FilterActivity.class));
        } else if ("Xfermode".equals(title)) {
            startActivity(new Intent(MainActivity.this, XfermodeActivity.class));
        } else if ("Xfermode_DTS".equals(title)) {
            startActivity(new Intent(MainActivity.this, XfermodeDTSActivity.class));
        } else if ("Xfermode_SRC".equals(title)) {
            startActivity(new Intent(MainActivity.this, XfermodeSRCActivity.class));
        } else if ("渲染模式".equals(title)) {
            startActivity(new Intent(MainActivity.this, GradientActivity.class));
        } else if ("表盘".equals(title)) {
            startActivity(new Intent(MainActivity.this, WatchViewActivity.class));
        } else if ("图层".equals(title)) {
            startActivity(new Intent(MainActivity.this, LayerActivity.class));
        } else if ("滚动视图".equals(title)) {
            startActivity(new Intent(MainActivity.this, ScrollActivity.class));
        } else if ("Path绘制".equals(title)) {
            startActivity(new Intent(MainActivity.this, PathActivity.class));
        } else if ("多阶贝塞尔曲线".equals(title)) {
            startActivity(new Intent(MainActivity.this, BezierActivity.class));
        } else if ("QQ气泡".equals(title)) {
            startActivity(new Intent(MainActivity.this, DragBubbleActivity.class));
        } else if ("LoadingView".equals(title)) {
            startActivity(new Intent(MainActivity.this, LoadingActivity.class));
        } else if ("屏幕适配".equals(title)) {
            startActivity(new Intent(MainActivity.this, ScreenAdapterActivity.class));
        } else if ("滑动冲突".equals(title)) {
            startActivity(new Intent(MainActivity.this, ConActivity.class));
        } else if ("RecycleView左滑删除".equals(title)) {
            startActivity(new Intent(MainActivity.this, RecyclerDelActivity.class));
        } else if ("RecycleView左滑显示菜单".equals(title)) {
            startActivity(new Intent(MainActivity.this, RecyclerMenuActivity.class));
        }
    }
}
