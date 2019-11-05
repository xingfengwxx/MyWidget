package com.wangxingxing.widget.lsn3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * 使用线性渲染完成文字跑马灯效果
 */
public class LinearGradientTextView extends AppCompatTextView {

    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private Paint mPaint;
    private int mViewWidth = 0;
    private int mTranslate = 0;

    private int delta = 15;

    public LinearGradientTextView(Context context) {
        super(context);
    }

    public LinearGradientTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearGradientTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                mPaint = getPaint();
                String text = getText().toString();
                //mViewWidth除字体总数就得到每个字的像素，然后*3 表示3个文字的像素
                int size;
                if (text.length() > 0) {
                    size = mViewWidth / text.length() * 3;
                } else {
                    size = mViewWidth;
                }
                //从左边-size开始，左边看不见的地方开始，滚动扫描过来
                mLinearGradient = new LinearGradient(-size, 0, 0, 0,
                        new int[]{0x33ffffff, 0xffffffff, 0x33ffffff},
                        new float[]{0, 0.2f, 1}, Shader.TileMode.CLAMP); //边缘融合
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float mTextWidth = getPaint().measureText(getText().toString());
        mTranslate += delta;

        /**
         * 如果位置已经移动到了最右边那个文字的位置就得开始往回滚动
         * 但是如果小于1了，那么又开始递增，走另一个逻辑
         */
        if (mTranslate > mTextWidth + 1 || mTranslate < 1) {
            delta = -delta;
        }
        mGradientMatrix.setTranslate(mTranslate, 0);
        mLinearGradient.setLocalMatrix(mGradientMatrix);
        //paint是textview的所以只需要不断控制画笔的shader,然后利用矩阵控制位移即可
        postInvalidateDelayed(30);
    }
}
