package com.wangxingxing.widget.lsn13;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.wangxingxing.widget.R;

public class FollowBehavior2 extends CoordinatorLayout.Behavior<TextView> {
    //是否是第一次进入
    private boolean isOne = true;
    private Context context;

    /**
     * 一定要记得重写这个构造方法
     *
     * @param context
     * @param attrs
     */
    public FollowBehavior2(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    /**
     * layoutDependsOn()：这个方法在对界面进行布局时至少会调用一次，
     * 用来确定本次交互行为中的dependent view
     * 当dependency的类型匹配的时候返回true，
     * 就可以让系统知道布局文件中的匹配的这个类型的控件就是本次交互行为中的dependent view(被观察者)
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        if (dependency instanceof Button && dependency.getId() == R.id.btn1) {
            return true;
        }
        Log.e("DN------------>", "11111111111111111111");
        return false;
    }

    /**
     * @param parent
     * @param child      观察者
     * @param dependency 被观察者
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        if(!isOne){
            child.setX(dependency.getX()+200);
            child.setY(dependency.getY()+200);
        }
        isOne = false;
//        Log.e("DN------------>","2222222222222222222");
//        if (!isOne) {
//            child.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
//        }
//        isOne = false;
        return true;
    }
}
