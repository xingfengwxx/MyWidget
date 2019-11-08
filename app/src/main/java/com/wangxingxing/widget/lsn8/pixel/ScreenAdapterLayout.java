package com.wangxingxing.widget.lsn8.pixel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class ScreenAdapterLayout extends RelativeLayout {
    private boolean flag;

    public ScreenAdapterLayout(Context context) {
        super(context);
    }

    public ScreenAdapterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScreenAdapterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!flag) { //防止两次测量
            flag = true;
            //获取横竖方向等比
            float scaleX = Utils.getInstance(getContext()).getHorizontalScale();
            float scaleY = Utils.getInstance(getContext()).getVerticalScale();
            //子View个数
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                params.width = (int) (params.width * scaleX);
                params.height = (int) (params.height * scaleY);
                params.leftMargin = (int) (params.leftMargin * scaleX);
                params.rightMargin = (int) (params.rightMargin * scaleX);
                params.topMargin = (int) (params.topMargin * scaleY);
                params.bottomMargin = (int) (params.bottomMargin * scaleY);
                child.setPadding((int) (child.getPaddingLeft() * scaleX), (int) (child.getPaddingTop() * scaleY),
                        (int) (child.getPaddingRight() * scaleX), (int) (child.getPaddingBottom() * scaleY));
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
