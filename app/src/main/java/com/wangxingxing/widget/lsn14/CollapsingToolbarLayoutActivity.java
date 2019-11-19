package com.wangxingxing.widget.lsn14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.wangxingxing.widget.R;
import com.wangxingxing.widget.lsn14.utils.StatusBarUtils;

public class CollapsingToolbarLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar_layout);

        initView();
    }

    private void initView() {
        final Toolbar toolbar = findViewById(R.id.appbar_layout_toolbar);
        //设置沉浸式状态栏
        StatusBarUtils.setTranslucentImageHeader(this, 0, toolbar);
        //设置标题颜色
        toolbar.setTitleTextColor(Color.TRANSPARENT);
        //加载动作菜单
        toolbar.inflateMenu(R.menu.layout_toolbar_menu);
        AppBarLayout appBarLayout = findViewById(R.id.app_bar);

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapse_layout);
        collapsingToolbarLayout.setTitle("");
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.wheat));
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
        //设置纱布
        collapsingToolbarLayout.setContentScrim(getResources().getDrawable(R.mipmap.meizhi));
        //监听appBarLayout的偏移
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
                    toolbar.setTitleTextColor(getResources().getColor(R.color.white));
                    collapsingToolbarLayout.setTitle("AppbarLayout");
                } else {
                    collapsingToolbarLayout.setTitle("");
                }
            }
        });
    }
}
