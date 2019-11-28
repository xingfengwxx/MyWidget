package com.wangxingxing.widget.lsn16.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class MyBehavior extends CoordinatorLayout.Behavior {

    private static final String TAG = "MyBehavior";

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof MyRecyclerView;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        Log.i(TAG, "我到啦我到啦");
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
    }
}
