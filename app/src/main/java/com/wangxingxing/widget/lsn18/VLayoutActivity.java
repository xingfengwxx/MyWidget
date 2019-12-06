package com.wangxingxing.widget.lsn18;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.wangxingxing.widget.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class VLayoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<HashMap<String, Object>> listItem;
    private MyAdapter linearLayoutAdapter,stickyLayoutAdapter,gridLayoutAdapter,scrollFixLayoutAdapter,columnLayoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlayout);

        initData();
        recyclerView = findViewById(R.id.recyclerView);
        //以前的做法
        //recyclerView.setLayoutManager(new LinearLayoutManager());
        //VLayout对LayoutManager的封装
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(virtualLayoutManager);
        //给recyclerView设置回收池
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(recycledViewPool);
        //设置recyclerView的item类型 以及最大item类型数量
        recycledViewPool.setMaxRecycledViews(0, 10);

        // 线性布局----------------------------------->
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        //设置数据的总行数
        linearLayoutHelper.setItemCount(4);
        //其实这个就是设置itemView的padding值 它不等同于RecyclerView的padding值
        linearLayoutHelper.setPadding(20, 20, 20, 20);
        linearLayoutHelper.setMargin(20, 20, 20, 20);
        //设置宽高比  就是itemView的宽度和高度的比例 比如宽度是6   那么高度就是1
        linearLayoutHelper.setAspectRatio(6);
        //设置分割线高度
        linearLayoutHelper.setDividerHeight(10);
        //设置下边距
        linearLayoutHelper.setMarginBottom(10);
        linearLayoutAdapter = new MyAdapter(this, linearLayoutHelper, 20, listItem);


        //组合 --->顺序
        //创建一个适配器的集合
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        adapters.add(linearLayoutAdapter);
        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        delegateAdapter.setAdapters(adapters);


        // 定格布局----------------------------------->
        StickyLayoutHelper stickyLayoutHelper = new StickyLayoutHelper();
        stickyLayoutHelper.setItemCount(1);
        stickyLayoutHelper.setAspectRatio(3);
        //定格布局的控件是在顶部还是在底部  true为头部  false为底部
        stickyLayoutHelper.setStickyStart(true);
        //设置偏移值
        //stickyLayoutHelper.setOffset(100);
        stickyLayoutAdapter = new MyAdapter(this, stickyLayoutHelper, 1, listItem) {
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position == 0) {
                    holder.Text.setText("Stick");
                }
            }
        };
        delegateAdapter.addAdapter(stickyLayoutAdapter);
        delegateAdapter.addAdapter(linearLayoutAdapter);


        //--------------网格布局-------------------->
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);
        gridLayoutHelper.setAspectRatio(6);
        //设置权重 就是一行中多个控件的比例  注意 加起来要等于100
        gridLayoutHelper.setWeights(new float[]{30, 20, 30, 20});
        //设置垂直边距
        gridLayoutHelper.setVGap(20);
        //设置水平边距
        gridLayoutHelper.setHGap(20);
        //设置是否自动填充空白区域
        gridLayoutHelper.setAutoExpand(true);

        gridLayoutAdapter = new MyAdapter(this, gridLayoutHelper, 20, listItem) {
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                //为了展示效果 将布局的不同位置的item进行背景颜色设置
                if (position < 2) {
                    holder.itemView.setBackgroundColor(0x66cc0000 + (position-6)*128);
                }else if(position%2 == 0){
                    holder.itemView.setBackgroundColor(0xaa22ff22);
                }else{
                    holder.itemView.setBackgroundColor(0xccff22ff);
                }
                if(position == 0){
                    holder.Text.setText("Grid");
                }
            }
        };
        delegateAdapter.addAdapter(gridLayoutAdapter);


        //-----------------固定布局------------------>
        //第一个参数 是固定的位置  后面两个参数是固定之后的偏移量
        ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(ScrollFixLayoutHelper.BOTTOM_RIGHT, 10, 10);
        // 参数说明:
        // 公共属性
        // 设置布局里Item个数
        scrollFixLayoutHelper.setItemCount(1);
        scrollFixLayoutHelper.setPadding(20, 20, 20, 20);
        scrollFixLayoutHelper.setMargin(20, 20, 20, 20);
        scrollFixLayoutHelper.setBgColor(Color.GRAY);
        scrollFixLayoutHelper.setAspectRatio(6);
        //重要参数  显示类型   一直显示SHOW_ALWAYS  滑动到位置开始位置显示SHOW_ON_ENTER  滑动到结束位置显示SHOW_ON_LEAVE 后面这个参数一开始是不显示的
        scrollFixLayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ALWAYS);
        scrollFixLayoutAdapter = new MyAdapter(this, scrollFixLayoutHelper, 1, listItem) {
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position == 0) {
                    holder.Text.setText("scrollFix");
                }
            }
        };
        delegateAdapter.addAdapter(scrollFixLayoutAdapter);


        //栅格栏布局------------------------------->
        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
        columnLayoutHelper.setPadding(20, 20, 20, 20);
        columnLayoutHelper.setMargin(20, 20, 20, 20);
        columnLayoutHelper.setBgColor(Color.GRAY);
        columnLayoutHelper.setAspectRatio(6);
        // columnLayoutHelper特有属性  设置该行每个Item占该行总宽度的比例
        columnLayoutHelper.setWeights(new float[] {30, 30, 10, 30});
        columnLayoutAdapter = new MyAdapter(this, columnLayoutHelper, 4, listItem) {
            // 设置需要展示的数据总数,此处设置是3
            // 为了展示效果,通过重写onBindViewHolder()将布局的第一个数据设置为Column
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position == 0) {
                    holder.Text.setText("Column");
                    holder.itemView.setBackgroundColor(Color.RED);
                }else if(position == 1){
                    holder.itemView.setBackgroundColor(Color.YELLOW);
                }else{
                    holder.itemView.setBackgroundColor(Color.BLUE);
                }
            }
        };
        delegateAdapter.addAdapter(columnLayoutAdapter);


        //一盒元素布局 一个元素布局------------------------------>
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        singleLayoutHelper.setPadding(20, 20, 20, 20);
        singleLayoutHelper.setMargin(20, 20, 20, 20);
        singleLayoutHelper.setBgColor(Color.GRAY);
        singleLayoutHelper.setAspectRatio(6);
        MyAdapter singleLayoutAdapter = new MyAdapter(this, singleLayoutHelper, 1, listItem) {
            // 设置需要展示的数据总数,此处设置是1
            // 为了展示效果,通过重写onBindViewHolder()将布局的第一个数据设置为Single
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position == 0) {
                    holder.Text.setText("Single");
                }
            }
        };
        delegateAdapter.addAdapter(singleLayoutAdapter);


        //1拖N布局------------------------------->
        OnePlusNLayoutHelper onePlusNLayoutHelper = new OnePlusNLayoutHelper();
        //设置行比重
        onePlusNLayoutHelper.setColWeights(new float[]{40, 60, 30, 30});
        //设置高比重
        onePlusNLayoutHelper.setRowWeight(60);
        onePlusNLayoutHelper.setPadding(20, 20, 20, 20);
        onePlusNLayoutHelper.setMargin(20, 20, 20, 20);
        onePlusNLayoutHelper.setBgColor(Color.GRAY);
        onePlusNLayoutHelper.setAspectRatio(3);
        MyAdapter onePlusNLayoutAdapter = new MyAdapter(this, onePlusNLayoutHelper,4, listItem) {
            // 设置需要展示的数据总数,此处设置是5,即1拖4
            // 为了展示效果,通过重写onBindViewHolder()将布局的第一个数据设置为onePlus
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position == 0) {
                    holder.Text.setText("onePlus"+position);
                    holder.itemView.setBackgroundColor(Color.RED);
                }else if (position == 1) {
                    holder.Text.setText("onePlus"+position);
                    holder.itemView.setBackgroundColor(Color.BLUE);
                }else if (position == 2) {
                    holder.Text.setText("onePlus"+position);
                    holder.itemView.setBackgroundColor(Color.BLACK);
                }else if (position == 3) {
                    holder.Text.setText("onePlus"+position);
                    holder.itemView.setBackgroundColor(Color.GREEN);
                }
            }
        };
        delegateAdapter.addAdapter(onePlusNLayoutAdapter);

        //瀑布流布局------------------------------------------------------->
        StaggeredGridLayoutHelper staggeredGridLayoutHelper = new StaggeredGridLayoutHelper();
        // 公有属性
        staggeredGridLayoutHelper.setItemCount(20);// 设置布局里Item个数
        staggeredGridLayoutHelper.setPadding(20, 20, 20, 20);
        staggeredGridLayoutHelper.setMargin(20, 20, 20, 20);
        staggeredGridLayoutHelper.setBgColor(Color.GRAY);
        staggeredGridLayoutHelper.setAspectRatio(3);

        // 特有属性
        // 设置控制瀑布流每行的Item数
        staggeredGridLayoutHelper.setLane(3);
        // 设置子元素之间的水平间距
        staggeredGridLayoutHelper.setHGap(20);
        // 设置子元素之间的垂直间距
        staggeredGridLayoutHelper.setVGap(15);
        MyAdapter staggeredGridLayoutAdapter = new MyAdapter(this, staggeredGridLayoutHelper,20, listItem) {
            // 设置需要展示的数据总数,此处设置是20
            // 通过重写onBindViewHolder()设置更加丰富的布局
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,150 +position % 5 * 20);
                holder.itemView.setLayoutParams(layoutParams);
                // 为了展示效果,设置不同位置的背景色
                if (position > 10) {
                    holder.itemView.setBackgroundColor(0x66cc0000 );
                } else if (position % 2 == 0) {
                    holder.itemView.setBackgroundColor(0xaa22ff22);
                } else {
                    holder.itemView.setBackgroundColor(0xccff22ff);
                }
                // 为了展示效果,通过将布局的第一个数据设置为staggeredGrid
                if (position == 0) {
                    holder.Text.setText("staggeredGrid");
                }
            }
        };
        delegateAdapter.addAdapter(staggeredGridLayoutAdapter);

        recyclerView.setAdapter(delegateAdapter);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        /**
         * 步骤1设置需要存放的数据
         * */
        listItem = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("ItemTitle", "第" + i + "行");
            map.put("ItemImage", R.mipmap.ic_launcher);
            listItem.add(map);
        }
    }
}
