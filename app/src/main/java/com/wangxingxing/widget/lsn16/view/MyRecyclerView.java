package com.wangxingxing.widget.lsn16.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ViewCompat;

import com.wangxingxing.widget.R;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerView extends ViewGroup implements NestedScrollingChild2 {

    //当前RecyclerView的适配器
    private Adapter adapter;
    //当前显示的view的集合
    private List<View> viewList;
    //当前滑动的Y值
    private int currentY;
    //总行数
    private int rowCount;
    //显示的的第一行在数据源中的position
    private int firstRow;
    //y偏移量
    private int scrollY;
    //初始化  是否是第一屏
    private boolean needRelayout;
    //当前RecyclerView的宽度
    private int width;
    //当前RecyclerView的高度
    private int height;
    //所有ItemView的高度数组
    private int[] heights;
    //View对象回收池
    private Recycler recycler;
    //最小滑动距离
    private int touchSlop;
    //获取到嵌套滑动子控件的helper类
    private NestedScrollingChildHelper nestedScrollingChildHelper;

    public MyRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    /**
     * 初始化RecyclerView的方法
     * @param context
     */
    private void init(Context context) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        //获取到最小的滑动距离
        touchSlop = viewConfiguration.getScaledTouchSlop();
        //初始化viewList
        viewList = new ArrayList<>();
        //初始化需要重新布局
        needRelayout = true;
        //初始化帮助类
        nestedScrollingChildHelper = new NestedScrollingChildHelper(this);
        nestedScrollingChildHelper.setNestedScrollingEnabled(true);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                //取出手指当前移动到的Y轴值 跟之前手指触摸的位置相减
                float y2 = Math.abs(currentY - ev.getY());
                //如果滑动的距离  小于最小滑动的距离 就不滑动  如果大于 就滑动
                if (y2 > touchSlop) {
                    intercept = true;
                }
                break;
        }
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                //先获取当前Y轴的值
                float y2 = event.getRawY();
                //手指触摸点减去滑动到的这个Y轴的值
                float diffY = currentY - y2;
                //不加会影响反应速度
                currentY = (int) y2;
                //滑动的方法
                scrollBy(0, (int) diffY);
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL,ViewCompat.TYPE_TOUCH);
                stopNestedScroll();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void scrollBy(int x, int y) {
        scrollY += y;
        //纠正scrollY
        scrollY = scrollBounds(scrollY);
        if (scrollY > 0) {
            //向上滑
            //1 将上面的移除掉
            while (scrollY > heights[firstRow]) {
                //删除第一行 就是删除当前布局中的第0个Item
                removeView(viewList.remove(0));
                //改变scrollY scrollY要减去这一行的高度
                scrollY -= heights[firstRow];
                //当前显示的行标
                firstRow++;
            }

            //给下面添加一个新的进来
            //判断当前所显示的view的高度是不是小于RecyclerView的高度  如果小于  就添加新的item
            while (getFillHeight() < height) {
                //首先  当前第一行的行标 加上以及显示的itemView的长度 其实就是要添加进去的那一行的Item
                int addlast = firstRow + viewList.size();
                //获取到itemView
                View view = obtainView(addlast, width, heights[addlast]);
                //将新的itemView添加进viewList
                viewList.add(view);
            }
        } else if (scrollY < 0) {
            //向下滑
            //下滑过程中 要做两件事情
            //创建一个新的itemView放置在最上面
            while (scrollY < 0) {
                //当前显示的itemView的第一行的行标减去1
                int firstAddRow = firstRow - 1;
                //获取到显示在第一行的itemView的上一个itemView
                View view = obtainView(firstAddRow, width, heights[firstAddRow]);
                //添加到当前可见的item的最上面
                viewList.add(0, view);
                //更新当前显示的第一行的行标
                firstRow--;
                //改变scrollY scrollY要将当前添加进去的行的行高加起来
                scrollY += heights[firstAddRow];
            }
            //把最下面移出了屏幕的item移除掉 判断当前显示的View的总高度是不是大于RecyclerView的高于 如果大于 将最上面的itemView移除掉
            while (sumArray(heights, firstRow, viewList.size()) - scrollY - heights[firstRow + viewList.size() - 1] >= height) {
                //移除当前显示的再最下面的item
                removeView(viewList.remove(viewList.size() - 1));
            }
        } else {

        }
        //重新摆放位置
        rePositionView();
    }

    /**
     * 纠正，避免数组下标越界
     * @param scrollY
     * @return
     */
    private int scrollBounds(int scrollY) {
        if (scrollY > 0) {
            //判断上滑的极限值  防止滚动的距离 大于当前所有内容的高度
            scrollY = Math.min(scrollY, sumArray(heights, firstRow, heights.length - firstRow) - height);
        } else {
            //判断下滑的极限值  防止滚动的距离 小于第0个item的高度
            scrollY = Math.max(scrollY, -sumArray(heights, 0, firstRow));
        }
        return scrollY;
    }

    /**
     * 当我们上拉或者下滑的时候  重新摆放View的位置
     */
    private void rePositionView() {
        int top = 0, right, bottom, left = 0, i;
        top =- scrollY;
        //将当前第一行的行标赋值给i
        i = firstRow;
        for (View view : viewList) {
            //下一一个或者上移一个item
            bottom = top + heights[i++];
            view.layout(0, top, width, bottom);
            top = bottom;
        }
    }

    @Override
    public void removeView(View view) {
        super.removeView(view);
        int key = (int) view.getTag(R.id.tag_type_view);
        //将view添加进回收池中
        recycler.put(view, key);
    }

    /**
     * 获取到显示在控件中的view的总高度
     * @return
     */
    private int getFillHeight() {
        return sumArray(heights, firstRow, viewList.size()) - scrollY;
    }

    /**
     * 获取到数组中数据的高度
     * @param heights   数组
     * @param index 从哪一个item拿起
     * @param count    一共要拿多少个item的高度
     * @return
     */
    private int sumArray(int[] heights, int index, int count) {
        int sum = 0;
        count += index;
        for (int i = 0; i < count; i++) {
            sum += heights[i];
        }
        return sum;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //当前内容的高度
        int h = 0;
        //判断适配器是否为空
        if (adapter != null) {
            //获取到当前数据的总条数
            rowCount = adapter.getCount();
            //根据适配器的数据的总长度来创建数组
            heights = new int[rowCount];
            //循环获取到所有的view的高度
            for (int i = 0; i < heights.length; i++) {
                heights[i] = adapter.getHeight(i);
            }
        }
        //获取到所有数据的高度
        int tempHeight = sumArray(heights, 0, heights.length);
        //判断所有的item的高度 和RecyclerView的高度谁低
        h = Math.min(tempHeight, heightSize);
        setMeasuredDimension(widthSize, h);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //先判断是否需要布局  布局发生改变的时候重新布局
        if (needRelayout || changed) {
            needRelayout = false;
            //清除掉所有的view
            removeAllViews();
            //清除掉当前屏幕中显示的itemView
            viewList.clear();
            //如果适配器不为空  就去摆放itemView
            if (adapter != null) {
                //获取到RecyclerView的宽高
                width = r - l;
                height = b - t;
                //定义布局itemView的四个变量
                int top = 0, right, bottom, left = 0;
                for (int i = 0; i < rowCount; i++) {
                    //获取到绘制宽度的最右边
                    right = width;
                    bottom = top + heights[i];
                    //生成View
                    View view = makeAndStep(i, left, top, right, bottom);
                    //添加到当前的itenView的集合中
                    viewList.add(view);
                    //因为循环 摆放 所以  下一个控件的top就是上一个控件的botton  而且要累加
                    top = bottom;
                }
            }
        }
    }

    private View makeAndStep(int i, int left, int top, int right, int bottom) {
        //生成View
        View view = obtainView(i, right - left, bottom - top);
        //布局ItemView
        view.layout(left, top, right, bottom);
        return view;
    }

    private View obtainView(int row, int width, int height) {
        //首先获取到这一行数的item的布局类型
        int itemViewType = adapter.getItemViewType(row);
        //去栈中拿
        View view = recycler.get(itemViewType);
        //定义一个view
        View itemView = null;
        if (view == null) {
            itemView = adapter.onCreateViewHolder(row, itemView, this);
            if (itemView == null) {
                throw new RuntimeException("onCreateViewHolder 必须要填充布局");
            }
        } else {
            itemView = adapter.onBinderViewHolder(row, view, this);
        }
        //给每个ItemView设置一个tag
        itemView.setTag(R.id.tag_type_view, itemViewType);
        //先测量每个itemView
        itemView.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        //每生成一个itemView  都添加进RecyclerView
        addView(itemView);
        return itemView;
    }

    public Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        //初始化回收池
        if (adapter != null) {
            recycler = new Recycler(adapter.getViewTypeCount());
            scrollY = 0;
            firstRow = 0;
            needRelayout = true;
            //重新测量  重新摆放
            requestLayout();
        }
    }

    @Override
    public boolean startNestedScroll(int axes, int type) {
        return nestedScrollingChildHelper.startNestedScroll(axes, type);
    }

    @Override
    public void stopNestedScroll(int type) {

    }

    @Override
    public boolean hasNestedScrollingParent(int type) {
        return false;
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type) {
        return false;
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow, int type) {
        return nestedScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
    }

    @Override
    public void stopNestedScroll() {
        super.stopNestedScroll();
        nestedScrollingChildHelper.stopNestedScroll();
    }

    public interface Adapter {
        //创建ViewHolder的方法
        View onCreateViewHolder(int position, View convertView, ViewGroup parent);
        
        //绑定ViewHolder的方法
        View onBinderViewHolder(int position, View convertView, ViewGroup parent);

        //获取到当前row item的控件类型
        int getItemViewType(int row);

        //获取当前控件类型的总数量
        int getViewTypeCount();

        //获取当前item的总数量
        int getCount();

        //获取index item的高度
        int getHeight(int index);

    }
}
