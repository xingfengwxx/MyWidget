package com.wangxingxing.widget.lsn7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wangxingxing.widget.R;

public class PathMeasureView extends View {

    // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度
    private float currentValue = 0;
    // 当前点的实际位置
    private float[] pos;
    // 当前点的tangent值,用于计算图片所需旋转的角度
    private float[] tan;
    // 箭头图片
    private Bitmap mBitmap;
    private Matrix mMatrix;
    // 矩阵,用于对图片进行一些操作
    private Paint mDeafultPaint;
    private int mViewWidth;
    private int mViewHeight;
    private Paint mPaint;

    public PathMeasureView(Context context) {
        super(context);
        init(context);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 缩放图片
        options.inSampleSize = 8;
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow, options);
        mMatrix = new Matrix();

        mDeafultPaint = new Paint();
        mDeafultPaint.setColor(Color.RED);
        mDeafultPaint.setStrokeWidth(5);
        mDeafultPaint.setStyle(Paint.Style.STROKE);

        mPaint = new Paint();
        mPaint.setColor(Color.DKGRAY);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);
        // 平移坐标系
        canvas.translate(mViewWidth/2,mViewHeight/2);
        // 画坐标线
        canvas.drawLine(-canvas.getWidth(),0,canvas.getWidth(),0,mPaint);
        canvas.drawLine(0,-canvas.getHeight(),0,canvas.getHeight(),mPaint);

        // 创建 Path
        Path path = new Path();
        /**
         * Path.Direction.CCW 逆时针
         * Path.Direction.CW 顺时针
         */
        // 添加一个圆形
        path.addCircle(0, 0, 200, Path.Direction.CCW);
        // 创建 PathMeasure
        PathMeasure measure = new PathMeasure(path, false);

        // 计算当前的位置在总长度上的比例[0,1]
        currentValue += 0.005;
        if (currentValue >= 1) {
            currentValue = 0;
        }

        /*// 方案一
        // 获取当前位置的坐标以及趋势
        measure.getPosTan(measure.getLength() * currentValue, pos, tan);
        canvas.drawCircle(tan[0],tan[1],20,mDeafultPaint);

        // 重置Matrix
        mMatrix.reset();
        // 计算图片旋转角度(或弧度)
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
        // 旋转图片
        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        // 将图片绘制中心调整到与当前点重合
        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);*/

        // 方案二
        // 获取当前位置的坐标以及趋势的矩阵
        measure.getMatrix(measure.getLength() * currentValue, mMatrix,
                PathMeasure.TANGENT_MATRIX_FLAG | PathMeasure.POSITION_MATRIX_FLAG);

        // 将图片绘制中心调整到与当前点重合(偏移加旋转)
        mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);
        canvas.drawPath(path, mDeafultPaint);
        canvas.drawBitmap(mBitmap, mMatrix, mDeafultPaint);

        invalidate();
    }
}
