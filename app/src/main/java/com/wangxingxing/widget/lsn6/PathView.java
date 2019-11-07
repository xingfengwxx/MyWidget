package com.wangxingxing.widget.lsn6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PathView extends View {

    private Path mPath1 = new Path();
    private Path mPath2 = new Path();
    private Paint mPaint = new Paint();

    public PathView(Context context) {
        super(context);
        init();
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        /**
         //         * Path.Direction绘制方向
         //         * Path.Direction.CCW   逆时针
         //         * Path.Direction.CW  顺时针
         //         */
//        mPath1.addCircle(200, 200, 100, Path.Direction.CW); //绘制圆
//        mPath2.addCircle(300, 300, 100, Path.Direction.CW);
//        /**
//         * Path.op对两个Path进行布尔运算(即取交集、并集等操作)
//         * Path.Op.DIFFERENCE 减去path1中path1与path2都存在的部分;
//         * path1 = (path1 - path1 ∩ path2)
//         * Path.Op.INTERSECT 保留path1与path2共同的部分;
//         * path1 = path1 ∩ path2
//         * Path.Op.UNION 取path1与path2的并集;
//         * path1 = path1 ∪ path2
//         * Path.Op.REVERSE_DIFFERENCE 与DIFFERENCE刚好相反;
//         * path1 = path2 - (path1 ∩ path2)
//         * Path.Op.XOR 与INTERSECT刚好相反;
//         * path1 = (path1 ∪ path2) - (path1 ∩ path2)
//         */
//        mPath1.op(mPath2,Path.Op.DIFFERENCE);
////        mPath1.op(mPath2,Path.Op.INTERSECT);
////        mPath1.op(mPath2,Path.Op.UNION);
////        mPath1.op(mPath2,Path.Op.XOR);
////        mPath1.op(mPath2,Path.Op.REVERSE_DIFFERENCE);
//        canvas.drawPath(mPath1, mPaint);
//        canvas.drawPath(mPath2, mPaint);


//        mPath1.moveTo(100, 100);//将路径的绘制位置定在（x,y）的位置
//
//        /**
//         * 在前一个点的基础上开始绘制，如果前面一个点是（x,y）,
//         * rMoveTo(dx,dy)相当于moveTo(x+dx,y+dy),如果前面没有调用moveTo，
//         * 相当于从(dx,dy)开始绘制
//         */
//        mPath1.rMoveTo(100, 100);
////        mPath1.lineTo(200, 300);//连线
//        mPath1.rLineTo(100, 100);
////        mPath1.lineTo(100, 300);
//        mPath1.close();//闭合
//        canvas.drawPath(mPath1, mPaint);

//        //添加(矩形， 圆角矩形， 椭圆， 圆， 路径， 圆弧)
//        mPath1.addCircle(100, 100, 50, Path.Direction.CW);
//        mPath1.addArc(200, 200, 300, 300, 0, -90);
//        mPath1.arcTo(200, 200, 300, 300, 0, 90, true);
//        mPath1.addOval(300, 300, 400, 450, Path.Direction.CW);
//        mPath1.addRect(100, 400, 300, 500, Path.Direction.CW);
//        mPath1.addRoundRect(100, 600, 300, 700, 20, 40, Path.Direction.CW);
//        canvas.drawPath(mPath1, mPaint);

//        //二阶贝塞尔曲线
        mPath1.moveTo(100, 100);
        mPath1.quadTo(400, 200, 10, 500);//二阶贝塞尔曲线
        canvas.drawCircle(100, 100, 10, mPaint);
        canvas.drawCircle(400, 200, 10, mPaint);
        canvas.drawCircle(10, 500, 10, mPaint);
        canvas.drawCircle(300, 700, 10, mPaint);
        //rQuadTo方法是基于当前点坐标系(偏移量)
        mPath1.rQuadTo(300, 100, -90, 400);
        canvas.drawPath(mPath1, mPaint);

//        //三阶贝塞尔曲线
//        mPath1.moveTo(100, 100);
//        mPath1.cubicTo(400, 200,10, 500,300, 700);
//        canvas.drawPath(mPath1, mPaint);
    }
}
