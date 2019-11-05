package com.wangxingxing.widget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wangxingxing.widget.lsn2.WaterfallFlowLayoutActivity;
import com.wangxingxing.widget.lsn3.MarqueeActivity;

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
        titles.add("文字跑马灯效果");
        titles.add("滚动视图");
        return titles;
    }

    @Override
    public void onClick(View view, String title) {
        if ("瀑布流式布局".equals(title)) {
            startActivity(new Intent(MainActivity.this, WaterfallFlowLayoutActivity.class));
        } else if ("文字跑马灯效果".equals(title)) {
            startActivity(new Intent(MainActivity.this, MarqueeActivity.class));
        }
    }
}
