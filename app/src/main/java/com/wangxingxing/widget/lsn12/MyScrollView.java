package com.wangxingxing.widget.lsn12;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {

    private MyLinearLayout mContent;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContent = (MyLinearLayout) getChildAt(0);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        for (int i = 0; i < mContent.getChildCount(); i++) {
            //MyFrameLayout
            View childAt = mContent.getChildAt(i);

            if (!(childAt instanceof DiscroIllnterface)) {
                continue;
            }
            DiscroIllnterface discroIllnterface = (DiscroIllnterface) childAt;

            int top = childAt.getTop();
            //view离parent顶部的高度
            int absoluteTop = top - t;
            if (absoluteTop <= getHeight()) {
                //ratio = childAt浮现高度/childAt高度
                int visibleGap = getHeight() - absoluteTop;
                float ratio = visibleGap / (float) childAt.getHeight();
                //确保ratio是在0-1f
                discroIllnterface.onDiscroll(clamp(ratio, 1f, 0f));
            } else {
                discroIllnterface.onResetDiscroll();
            }
        }
    }

    //求三个数的中间大小的一个数。
    public static float clamp(float value, float max, float min){
        return Math.max(Math.min(value, max), min);
    }
}
