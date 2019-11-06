package com.wangxingxing.widget.lsn4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class LayerView extends View {

    private Paint mPaint;

    public LayerView(Context context) {
        super(context);
        init();
    }

    public LayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public LayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //图层一
        //白色底色图层
        canvas.drawColor(Color.WHITE);
        mPaint.setColor(Color.BLACK);
        //黑色圆形
        canvas.drawCircle(200, 200, 100, mPaint);

        //图层二
        canvas.saveLayerAlpha(0, 0, 400, 400, 127, Canvas.ALL_SAVE_FLAG);
        mPaint.setColor(Color.YELLOW);
        //黄色圆形
        canvas.drawCircle(250, 250, 100, mPaint);
        //合并
        canvas.restore();
    }
}
