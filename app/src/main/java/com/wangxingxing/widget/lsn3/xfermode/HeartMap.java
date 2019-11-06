package com.wangxingxing.widget.lsn3.xfermode;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.wangxingxing.widget.R;

/**
 * 使用Xfermode的DST_IN模式实现心电图
 */
public class HeartMap extends View {

    private Paint mPaint;
    private int mItemWaveLength = 0;
    private int dx = 0;

    private Bitmap mBmpSRC, mBmpDST;

    public HeartMap(Context context) {
        super(context);
        init();
    }

    public HeartMap(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeartMap(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public HeartMap(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);

        mBmpDST = BitmapFactory.decodeResource(getResources(), R.drawable.heartmap, null);
        mBmpSRC = Bitmap.createBitmap(mBmpDST.getWidth(), mBmpDST.getHeight(), Bitmap.Config.ARGB_8888);

        mItemWaveLength = mBmpDST.getWidth();
        startAnim();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Canvas c = new Canvas(mBmpSRC);
        //清空bitmap
        c.drawColor(Color.RED, PorterDuff.Mode.CLEAR);
        //画上矩形
        c.drawRect(mBmpDST.getWidth() - dx, 0, mBmpDST.getWidth(), mBmpDST.getHeight(), mPaint);

        //模式合成
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(mBmpDST, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mBmpSRC, 0, 0, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }

    private void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.setDuration(6000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }
}
