package com.wangxingxing.widget.lsn16;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;

public class TextViewBehavior extends MyBehavior {

    public TextViewBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public boolean layoutDependsOn(@NonNull View parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof NestedScrollView;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull View parent, @NonNull View child, @NonNull View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public void onNestedScroll(@NonNull View parent, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        //向下滑动了 滑动距离是负数 就是向下
        if(dyConsumed<0){
            //当前观察者控件的Y坐标小于等于0   并且 被观察者的Y坐标不能超过观察者控件的高度
            if(child.getY()<=0 && target.getY()<=child.getHeight()){
                child.setTranslationY(-(target.getScrollY()>child.getHeight()?
                        child.getHeight():target.getScrollY()));
                target.setTranslationY(-(target.getScrollY()>child.getHeight()?
                        child.getHeight():target.getScrollY()));
                ViewGroup.LayoutParams layoutParams = target.getLayoutParams();
                layoutParams.height= (int) (parent.getHeight()-child.getHeight()-child.getTranslationY());
                target.setLayoutParams(layoutParams);
            }
        }else{
            //向上滑动了 被观察者的Y坐标不能小于或者等于0
            if(target.getY()>0){
                //设置观察者的Y坐标的偏移  1.不能超过观察者自己的高度
                child.setTranslationY(-(target.getScrollY()>child.getHeight()?
                        child.getHeight():target.getScrollY()));
                target.setTranslationY(-(target.getScrollY()>child.getHeight()?
                        child.getHeight():target.getScrollY()));
                //获取到被观察者的LayoutParams
                ViewGroup.LayoutParams layoutParams = target.getLayoutParams();
                //当我们向上滑动的时候  被观察者的高度 就等于 它父亲的高度 减去观察者的高度 再减去观察者Y轴的偏移值
                layoutParams.height= (int) (parent.getHeight()-child.getHeight()-child.getTranslationY());
                target.setLayoutParams(layoutParams);
            }
        }
    }

    /**
     *在target每次滑动之前会调用这个方法，。
     * @param target 发出NestedScroll事件的子view
     * @param dx 这次滑动事件在x方向上滑动的距离
     * @param dy 这次滑动事件在y方向上滑动的距离
     * @param consumed 一个长度为2的数组。第0位时我们在x方向消耗的滑动距离，第1位是我们在y方向上消耗的滑动距离。子view会根据这个和dx/dy来计算余下的滑动量，来决定自己是否还要进行剩下的滑动。
     *                 比如我们使consumed[1] = dy，那么子view在y方向上就不会滑动。
     * */
    @Override
    public void onNestedPreScroll(@NonNull View target, View child, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(target, child, dx, dy, consumed, type);
    }
}
