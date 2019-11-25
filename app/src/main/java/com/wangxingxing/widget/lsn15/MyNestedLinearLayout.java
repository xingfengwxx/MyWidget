package com.wangxingxing.widget.lsn15;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent2;

import com.wangxingxing.widget.R;

import java.lang.reflect.Constructor;

public class MyNestedLinearLayout extends LinearLayout implements NestedScrollingParent2 {
    public MyNestedLinearLayout(Context context) {
        super(context);
    }

    public MyNestedLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyNestedLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 这个是嵌套滑动控制事件分发的控制方法，只有返回true才能接收到事件分发
     * @param child 包含target的ViewParent的直接子View  嵌套滑動的子控件
     * @param target 嵌套滑動的子控件
     * @param axes 滑动的方向，数值和水平方向  這裡說的不是手勢  而是當前控件的需求  或者環境
     * @param type 发起嵌套事件的类型 分为触摸（ViewParent.TYPE_TOUCH）和非触摸（ViewParent.TYPE_NON_TOUCH）
     * @return
     */
    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        return true;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {

    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
    }

    /**
     * 在子View滑动过程中会通知这个嵌套滑动的方法，要想这里收到嵌套滑动事件必须在onStartNestedScroll返回true
     * @param target 當前滑動的控件
     * @param dxConsumed  滑動的控件在水平方向已经消耗的距离
     * @param dyConsumed 滑動的控件在垂直方法已经消耗的距离
     * @param dxUnconsumed  滑動的控件在水平方向剩下的未消耗的距离
     * @param dyUnconsumed  滑動的控件在垂直方法剩下的未消耗的距离
     * @param type 发起嵌套事件的类型 分为触摸（ViewParent.TYPE_TOUCH）和非触摸（ViewParent.TYPE_NON_TOUCH）
     */
    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        int childCount = getChildCount();
        //遍历直接子控件
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            //当前属性对象是没有自定义属性的!!!!!
            MyLayoutParams lp = (MyLayoutParams) child.getLayoutParams();
            //获取到控件的myBehavior对象
            MyBehavior myBehavior = lp.myBehavior;
            //如果子控件设置了myBehavior
            if (myBehavior != null) {
                //判断当前的滑动的控件是不是当前子控件的被观察者
                if (myBehavior.layoutDependsOn(this, child, target)) {
                    myBehavior.onNestedScroll(this, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
                }
            }
        }
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MyLayoutParams(getContext(), attrs);
    }

    class MyLayoutParams extends LayoutParams {

        //定义我们的Behavior对象
        private MyBehavior myBehavior;

        public MyLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            //将我们自定义的属性组交给一个TypedArray来管理
            final TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.MyNestedLinearLayout);
            //通过TypedArray获取到我们自定义的属性的名字 得到的是layout_behavior属性的值(Behavior的类名)
            String className = a.getString(R.styleable.MyNestedLinearLayout_layout_behavior);
            myBehavior = parseBehavior(c, attrs, className);
        }

        private MyBehavior parseBehavior(Context context, AttributeSet attrs, String className) {
            if (TextUtils.isEmpty(className)) {
                return null;
            }

            try {
                final Class clazz = Class.forName(className, true, context.getClassLoader());
                if (!MyBehavior.class.isAssignableFrom(clazz)) {
                    return null;
                }
                Constructor<? extends MyBehavior> c = clazz.getConstructor(new Class[]{Context.class, AttributeSet.class});
                c.setAccessible(true);
                return c.newInstance(context, attrs);
            } catch (Exception e) {
                throw new RuntimeException("Could not inflate Behavior subclass " + className, e);
            }
        }

        public MyLayoutParams(int width, int height) {
            super(width, height);
        }

        public MyLayoutParams(int width, int height, float weight) {
            super(width, height, weight);
        }

        public MyLayoutParams(ViewGroup.LayoutParams p) {
            super(p);
        }

        public MyLayoutParams(MarginLayoutParams source) {
            super(source);
        }
    }
}
