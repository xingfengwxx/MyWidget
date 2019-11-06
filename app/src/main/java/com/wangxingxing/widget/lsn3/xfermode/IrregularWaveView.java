package com.wangxingxing.widget.lsn3.xfermode;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.wangxingxing.widget.R;

/**
 * 使用Xfermode的DST_IN模式实现不规则波浪图
 */
public class IrregularWaveView extends View {

    private Paint mPaint;
    private int mItemWaveLength = 0;
    private int dx = 0;

    private Bitmap mBmpSRC, mBmpDST;

    public IrregularWaveView(Context context) {
        super(context);
        init();
    }

    public IrregularWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IrregularWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public IrregularWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();

        mBmpDST = BitmapFactory.decodeResource(getResources(), R.drawable.wav, null);
        mBmpSRC = BitmapFactory.decodeResource(getResources(), R.drawable.circle_shape, null);
        mItemWaveLength = mBmpDST.getWidth();

        startAnim();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //先画上圆形
        canvas.drawBitmap(mBmpSRC, 0, 0, mPaint);

        //在画上结果
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(mBmpDST, new Rect(dx, 0, dx + mBmpSRC.getWidth(), mBmpSRC.getHeight()),
                new Rect(0, 0, mBmpSRC.getWidth(), mBmpSRC.getHeight()), mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mBmpSRC, 0, 0, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }

    private void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.setDuration(4000);
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
