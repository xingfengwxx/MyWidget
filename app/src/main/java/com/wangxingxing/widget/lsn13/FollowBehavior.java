package com.wangxingxing.widget.lsn13;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.wangxingxing.widget.R;

public class FollowBehavior extends CoordinatorLayout.Behavior<TextView> {

    //是否第一次进入
    private boolean isOne = true;
    private Context context;

    /**
     * 一定要记得重写这个构造方法
     * @param context
     * @param attrs
     */
    public FollowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull TextView child, @NonNull View dependency) {
        if (dependency instanceof Button && dependency.getId() == R.id.btn) {
            return true;
        }
        return false;
    }

    /**
     * @param parent
     * @param child      观察者
     * @param dependency 被观察者
     * @return
     */
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull TextView child, @NonNull View dependency) {
//        if (!isOne) {
//            child.setX(dependency.getX() + 200);
//            child.setY(dependency.getY() + 200);
//        }
//        isOne = false;

        child.setText("我改变参数");
        if(!isOne){
            child.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        }
        isOne = false;
        return true;
    }
}
