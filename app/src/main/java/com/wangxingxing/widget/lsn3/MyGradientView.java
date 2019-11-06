package com.wangxingxing.widget.lsn3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wangxingxing.widget.R;

public class MyGradientView extends View {

    private Paint mPaint;
    private Bitmap mBitMap = null;

    private int mWidth;
    private int mHeight;
    private int[] mColors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};

    public MyGradientView(Context context) {
        super(context);
        init();
    }

    public MyGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mBitMap = ((BitmapDrawable) getResources().getDrawable(R.drawable.xyjy2)).getBitmap();
        mPaint = new Paint();
        mWidth = mBitMap.getWidth();
        mHeight = mBitMap.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        /**
         * TileMode.CLAMP 拉伸最后一个像素去铺满剩下的地方
         * TileMode.MIRROR 通过镜像翻转铺满剩下的地方。
         * TileMode.REPEAT 重复图片平铺整个画面（电脑设置壁纸）
         * 在图片和显示区域大小不符的情况进行扩充渲染
         */

        /**
         * 位图渲染，BitmapShader(@NonNull Bitmap bitmap, @NonNull TileMode tileX, @NonNull TileMode tileY)
         * Bitmap:构造shader使用的bitmap
         * tileX：X轴方向的TileMode
         * tileY:Y轴方向的TileMode
         */
//        BitmapShader bitMapShader = new BitmapShader(mBitMap, Shader.TileMode.REPEAT,
//                Shader.TileMode.REPEAT);
//        mPaint.setShader(bitMapShader);
//        mPaint.setAntiAlias(true);

        //设置像素矩阵，来调整大小，为了解决宽高不一致的问题。
//        float scale = Math.max(mWidth, mHeight) / Math.min(mWidth, mHeight);
//        Matrix matrix = new Matrix();
//        matrix.setScale(scale, scale);
//        bitMapShader.setLocalMatrix(matrix);

        //绘制圆
//        canvas.drawCircle(mHeight / 2, mHeight / 2, mHeight / 2, mPaint);
        //通过shapeDrawable也可以实现  圆图（如：头像）
//        ShapeDrawable shapeDrawble = new ShapeDrawable(new OvalShape());
//        shapeDrawble.getPaint().setShader(bitMapShader);
//        shapeDrawble.setBounds(0,0,mWidth,mWidth);
//        shapeDrawble.draw(canvas);

        //椭圆
//        canvas.drawOval(new RectF(0, 0, mWidth, mHeight), mPaint);

        //正方形
//        canvas.drawRect(new Rect(0, 0, 1000, 1600), mPaint);

        /**线性渲染
         * x0, y0, 起始点
         *  x1, y1, 结束点
         * int[]  mColors, 中间依次要出现的几个颜色
         * float[] positions 位置数组，position的取值范围[0,1]，作用是指定几个颜色分别放置在那个位置上，
         * 如果传null，渐变就线性变化。
         *    tile 用于指定控件区域大于指定的渐变区域时，空白区域的颜色填充方法
         */
//		LinearGradient linearGradient = new LinearGradient( 0, 0,800, 800,
//                mColors, null, Shader.TileMode.CLAMP);
//        // linearGradient = new LinearGradient(0, 0, 400, 400, mColors, null, Shader.TileMode.REPEAT);
//		mPaint.setShader(linearGradient);
//		canvas.drawRect(0, 0, 800, 800, mPaint);

        /**
         * 环形渲染
         * centerX ,centerY：shader的中心坐标，开始渐变的坐标
         * radius:渐变的半径
         * centerColor,edgeColor:中心点渐变颜色，边界的渐变颜色
         * colors:渐变颜色数组
         * stops:渐变位置数组，类似扫描渐变的positions数组，取值[0,1],中心点为0，半径到达位置为1.0f
         * tileMode:shader未覆盖以外的填充模式
         */
//        RadialGradient mRadialGradient = new RadialGradient(300, 300, 100,
//         mColors, null, Shader.TileMode.REPEAT);
//		mPaint.setShader(mRadialGradient);
//		canvas.drawCircle(300, 300, 300, mPaint);

        /**
         * 扫描渲染
         * cx,cy 渐变中心坐标
         * color0,color1：渐变开始结束颜色
         * colors，positions：类似LinearGradient,用于多颜色渐变,positions为null时，根据颜色线性渐变
         */
//        SweepGradient mSweepGradient = new SweepGradient(300, 300, mColors, null);
//		mPaint.setShader(mSweepGradient);
//		canvas.drawCircle(300, 300, 300, mPaint);

        /**
         * 组合渲染，
         * ComposeShader(@NonNull Shader shaderA, @NonNull Shader shaderB, Xfermode mode)
         * ComposeShader(@NonNull Shader shaderA, @NonNull Shader shaderB, PorterDuff.Mode mode)
         * shaderA,shaderB:要混合的两种shader
         * Xfermode mode： 组合两种shader颜色的模式
         * PorterDuff.Mode mode: 组合两种shader颜色的模式
         */
        /*ComposeShader mComposeShader = new ComposeShader(linearGradient, mBitmapShader, PorterDuff.Mode.SRC_OVER);
        mPaint.setShader(mComposeShader);
        canvas.drawRect(0, 0, 800, 1000, mPaint);*/

        /***************用ComposeShader即可实现心形图渐变效果*********************************/
        //创建BitmapShader，用以绘制心
        Bitmap mBitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.heart)).getBitmap();
        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //创建LinearGradient，用以产生从左上角到右下角的颜色渐变效果
        LinearGradient linearGradient = new LinearGradient(0, 0, mWidth, mHeight,
                Color.GREEN, Color.BLUE, Shader.TileMode.CLAMP);
        //bitmapShader对应目标像素，linearGradient对应源像素，像素颜色混合采用MULTIPLY模式
        ComposeShader composeShader = new ComposeShader(linearGradient, bitmapShader, PorterDuff.Mode.MULTIPLY);
//         ComposeShader composeShader2 = new ComposeShader(composeShader, linearGradient, PorterDuff.Mode.MULTIPLY);
        //将组合的composeShader作为画笔paint绘图所使用的shader
        mPaint.setShader(composeShader);
        //用composeShader绘制矩形区域
        canvas.drawRect(0, 0, mBitmap.getWidth(), mBitmap.getHeight(), mPaint);


        //所谓渲染就是对于我们绘制区域进行按照上诉渲染规则进行色彩的填充
    }
}
