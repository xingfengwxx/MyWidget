package com.wangxingxing.widget.lsn12;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.wangxingxing.widget.R;

public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MyLayoutParames(getContext(), attrs);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        MyLayoutParames p = (MyLayoutParames) params;
        if (!isDescrollvable(p)) {
            //判断是否有自定义属性，不存在则不包裹MyFrameLayout
            super.addView(child, params);
        } else {
            //偷天换日
            MyFrameLayout mf = new MyFrameLayout(getContext());
            mf.setmDiscrollveAlpha(p.mDiscrollveAlpha);
            mf.setmDiscrollveFromBgColor(p.mDiscrollveFromBgColor);
            mf.setmDiscrollveToBgColor(p.mDiscrollveToBgColor);
            mf.setmDiscrollveScaleX(p.mDiscrollveScaleX);
            mf.setmDisCrollveTranslation(p.mDisCrollveTranslation);
            mf.addView(child);
            super.addView(mf, params);
        }
    }

    private boolean isDescrollvable(MyLayoutParames p) {
        return p.mDiscrollveAlpha ||
                p.mDiscrollveScaleX ||
                p.mDiscrollveScaleY ||
                p.mDisCrollveTranslation != -1 ||
                (p.mDiscrollveFromBgColor != -1 &&
                        p.mDiscrollveToBgColor != -1);
    }

    public class MyLayoutParames extends LayoutParams {
        public int mDiscrollveFromBgColor;//背景颜色变化开始值
        public int mDiscrollveToBgColor;//背景颜色变化结束值
        public boolean mDiscrollveAlpha;//是否需要透明度动画
        public int mDisCrollveTranslation;//平移值
        public boolean mDiscrollveScaleX;//是否需要x轴方向缩放
        public boolean mDiscrollveScaleY;//是否需要y轴方向缩放

        public MyLayoutParames(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.DiscrollView_LayoutParams);
            mDiscrollveAlpha = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_alpha, false);
            mDiscrollveScaleX = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_scaleX, false);
            mDiscrollveScaleY = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_scaleY, false);
            mDisCrollveTranslation = a.getInt(R.styleable.DiscrollView_LayoutParams_discrollve_translation, -1);
            mDiscrollveFromBgColor = a.getColor(R.styleable.DiscrollView_LayoutParams_discrollve_fromBgColor, -1);
            mDiscrollveToBgColor = a.getColor(R.styleable.DiscrollView_LayoutParams_discrollve_toBgColor, -1);
            a.recycle();
        }
    }
}
