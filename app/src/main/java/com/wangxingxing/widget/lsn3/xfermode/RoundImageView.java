package com.wangxingxing.widget.lsn3.xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wangxingxing.widget.R;

/**
 * 使用Xfermode的DST_IN模式实现圆角图片
 */
public class RoundImageView extends View {

    private Paint mBitPaint;
    private Bitmap mBmpDST, mBmpSRC;

    public RoundImageView(Context context) {
        super(context);
        init();
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mBitPaint = new Paint();
        mBmpDST = BitmapFactory.decodeResource(getResources(), R.drawable.xyjy6, null);
        mBmpSRC = BitmapFactory.decodeResource(getResources(), R.drawable.shade, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null);

        canvas.drawBitmap(mBmpDST, 0, 0, mBitPaint);
        mBitPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mBmpSRC, 0, 0, mBitPaint);

        mBitPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }
}
