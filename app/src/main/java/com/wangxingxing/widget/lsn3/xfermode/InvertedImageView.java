package com.wangxingxing.widget.lsn3.xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wangxingxing.widget.R;

/**
 * 使用Xfermode的DST_IN模式实现倒影图像
 */
public class InvertedImageView extends View {

    private Paint mBitPaint;
    private Bitmap mBmpDST, mBmpSRC, mBmpRevert;

    public InvertedImageView(Context context) {
        super(context);
        init();
    }

    public InvertedImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InvertedImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public InvertedImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mBitPaint = new Paint();
        mBmpDST = BitmapFactory.decodeResource(getResources(), R.drawable.xyjy6, null);
        mBmpSRC = BitmapFactory.decodeResource(getResources(), R.drawable.invert_shade, null);

        Matrix matrix = new Matrix();
        matrix.setScale(1F, -1F);
        //生成倒影图
        mBmpRevert = Bitmap.createBitmap(mBmpDST, 0, 0, mBmpDST.getWidth(), mBmpDST.getHeight(), matrix, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //先画出原图
        canvas.drawBitmap(mBmpDST, 0, 0, mBitPaint);

        //再画出倒影
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(0, mBmpSRC.getHeight());

        canvas.drawBitmap(mBmpRevert, 0, 0, mBitPaint);
        mBitPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mBmpSRC, 0, 0, mBitPaint);

        mBitPaint.setXfermode(null);

        canvas.restoreToCount(layerId);
    }
}
