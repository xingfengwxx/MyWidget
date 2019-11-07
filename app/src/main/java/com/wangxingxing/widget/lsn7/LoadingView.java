package com.wangxingxing.widget.lsn7;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;

import java.util.Random;


/**
 * 圆圈加载
 */
public class LoadingView extends View {

    private Path mPath;
    private Paint mPaint;
    private PathMeasure mPathMeasure;
    private float mAnimatorValue;
    private Path mDst;
    private float mLength;

    private int[] colors = {
            Color.BLACK,
            Color.BLUE,
            Color.CYAN,
            Color.GRAY,
            Color.GREEN,
            Color.MAGENTA,
            Color.RED,
            Color.YELLOW
    };

    public LoadingView(Context context) {
        super(context);
        init();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPathMeasure = new PathMeasure();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPath = new Path();
        mPath.addCircle(500, 500, 100, Path.Direction.CCW);
        mPathMeasure.setPath(mPath, true);
        mLength = mPathMeasure.getLength();
        mDst = new Path();

        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatorValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        //设置颜色
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                Random random = new Random();
                int curIndex = random.nextInt(colors.length);
                mPaint.setColor(colors[curIndex]);
            }
        });

        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mDst.reset();

        // 硬件加速的BUG
        /**
         * 由于硬件加速的问题，PathMeasure中的getSegment添加到dst数组中时会被导致一些错误，
         * 需要通过mDst.lineTo(0,0)来避免这样一个Bug。
         */
        mDst.lineTo(0, 0);
        /*float stop = mLength * mAnimatorValue;
        mPathMeasure.getSegment(0, stop, mDst, true);*/

        float stop = mLength * mAnimatorValue;
        float start = (float) (stop - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * mLength));
        mPathMeasure.getSegment(start, stop, mDst, true);

        canvas.drawPath(mDst, mPaint);
    }
}
