package com.wangxingxing.widget.lsn2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 瀑布流布局
 */
public class WaterfallFlowLayout extends ViewGroup {

    /**
     * 用来保存行高的列表
     */
    private List<Integer> listLineHeight = new ArrayList<>();

    /**
     * 用来保存每行views的列表
     */
    private List<List<View>> listLineView = new ArrayList<>();

    private boolean flag;

    public WaterfallFlowLayout(Context context) {
        super(context);
    }

    public WaterfallFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WaterfallFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public WaterfallFlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //防止测量两次
        if (flag) {
            return;
        }
        flag = true;

        //1.先完成自己的宽高测量
        //需要得到mode进行判断我的显示模式是怎样的
        //得到父容器宽高
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //父容器模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //获取当前控件宽高（自己）
        int measureWidth = 0;
        int measureHeight = 0;

        //当前行宽，行高，因为存在多行，下一行数据要放到下方，行高需要保存
        int iCurLineW = 0;
        int iCurLineH = 0;

        //1.确认自己当前空间的宽高，这里因为会有两次OnMeasure,进行二级测量优化，所以采用IF_ELSE结构
        //二级优化原理在源码具体Draw时，第一次不会直接进行performDraw的调用反而是在下面重新进行了一次scheduleTraversals
        //在ViewRootImpl源码2349-2372之中我门会看到  scheduleTraversals在我们的2363
        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            measureWidth = widthSize;
            measureHeight = heightSize;
        } else {
            //当前View宽高
            int iChildWidth = 0;
            int iChildHeight = 0;

            //获取子view数量用于迭代
            int childCount = getChildCount();

            //当行信息容器
            List<View> viewList = new ArrayList<>();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                //1.测量自己
                measureChild(child, widthMeasureSpec, heightMeasureSpec);

                //2.获取getLayoutParams 即XML资源
                MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();

                //3.获得实际宽度和高度（MARGIN+WIDTH）
                iChildWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                iChildHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

                //4.是否需要换行
                if (iCurLineW + iChildWidth > widthSize) {
                    //4.1.记录当前行信息
                    //4.1.1.记录当前行最大宽度，高度累加
                    measureWidth = Math.max(measureWidth, iCurLineW);
                    measureHeight += iCurLineH;

                    //4.1.2.保存这一行数据，及行高
                    listLineHeight.add(iCurLineH);
                    listLineView.add(viewList);
                    
                    //4.2.记录新的行信息
                    //4.2.1.赋予新行新的宽高
                    iCurLineW = iChildWidth;
                    iCurLineH = iChildHeight;
                    
                    //4.2.2添加新行记录
                    viewList = new ArrayList<>();
                    viewList.add(child);
                } else {
                    //5.1.不换行情况
                    //5.1.1.记录某行内的信息宽度的叠加、高度比较
                    iCurLineW += iChildWidth;
                    iCurLineH = Math.max(iCurLineH, iChildHeight);
                    
                    //5.1.2.添加至当前行的viewList中
                    viewList.add(child);
                }
                
                //6.如果正好是最后一行需要换行
                if (i == childCount - 1) {
                    //6.1.记录当前行的最大宽度，高度累加
                    measureWidth = Math.max(measureWidth, iCurLineW);
                    measureHeight += iCurLineH;
                    
                    //6.2.将当前行的viewList添加至总的mViewsList,将行高添加至总的行高List
                    listLineView.add(viewList);
                    listLineHeight.add(iCurLineH);
                }
                
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //开始布局
        //1.取得所有视图信息
        //与之当前组件上下左右四个边距
        int left, top, right, bottom;
        //当前顶部高度和左边高度
        int curTop = 0;
        int curLeft = 0;
        //开始迭代
        int lineCount = listLineView.size();
        for (int i = 0; i < lineCount; i++) {
            List<View> viewList = listLineView.get(i);
            int lineViewSize = viewList.size();
            for (int j = 0; j < lineViewSize; j++) {
                View childView = viewList.get(j);
                MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();

                left = curLeft + layoutParams.leftMargin;
                top = curTop + layoutParams.topMargin;
                right = left + childView.getMeasuredWidth();
                bottom = top + childView.getMeasuredHeight();
                //同理，通过调用自身的layout进行布局
                childView.layout(left, top, right, bottom);
                //左边部分累加
                curLeft += childView.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            }
            curLeft = 0;
            curTop += listLineHeight.get(i);
        }
        listLineView.clear();
        listLineHeight.clear();
    }
}
