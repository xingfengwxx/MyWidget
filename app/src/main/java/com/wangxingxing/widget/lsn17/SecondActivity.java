package com.wangxingxing.widget.lsn17;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.wangxingxing.widget.R;

public class SecondActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initStatus();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setHeightAndPadding(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //解决4.4以上菜单顶部有白色区域问题
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            drawer.setFitsSystemWindows(true);
            drawer.setClipToPadding(false);
        }
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * 修改toolbar的高度和padding
     * @param view
     */
    private void setHeightAndPadding(View view) {
        //获取到布局属性对象
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        //先让toolbar的高度加上状态的高度
        layoutParams.height+=getStatusHeight();
        //然后设置toolbar的padding值
        view.setPadding(view.getPaddingLeft(),getStatusHeight()+view.getPaddingTop(),
                view.getPaddingRight(),view.getPaddingBottom());
    }


    private void initStatus() {
        //版本大于等于4.4
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            //获取到状态栏设置的两条属性
            int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            //在4.4之后又有两种情况  第一种 4.4-5.0   第二种 5.0以上
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                //第二种 5.0以上
                Window window   = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
                //设置状态栏透明
                window.setStatusBarColor(0);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }else{
                //第一种 4.4-5.0
                Window window   = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.flags |= flagTranslucentStatus|flagTranslucentNavigation;
                window.setAttributes(attributes);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            }
        }

        //获取到根布局的view
        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        //给根布局设置padding值
        rootView.setPadding(0,0,0,getNavigationBarHeight());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 获取状态栏的高度
     * @return
     */
    public int getStatusHeight(){
        //获取到状态栏的资源ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        //如果获取到了
        if(resourceId>0){
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
    public int getNavigationBarHeight(){
        //获取到虚拟按键的资源ID
        int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        //如果获取到了
        if(resourceId>0){
            //就返回它的高度
            return getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
