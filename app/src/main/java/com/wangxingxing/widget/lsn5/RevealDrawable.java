package com.wangxingxing.widget.lsn5;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RevealDrawable extends Drawable {

    private Drawable mUnselecteDrawable;
    private Drawable mSelecteDrawable;
    private int mOrientation;

    private Rect mTempRect = new Rect();

    public static final int HORIZONTAL = 1;
    public static final int VERTICAL = 2;

    public RevealDrawable(Drawable mUnselecteDrawable, Drawable mSelecteDrawable, int mOrientation) {
        this.mUnselecteDrawable = mUnselecteDrawable;
        this.mSelecteDrawable = mSelecteDrawable;
        this.mOrientation = mOrientation;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        //绘制
        //0-10000
        int level = getLevel();
        //三个区间
        //右边区间和左边区间---设置成灰色
        if (level == 10000 || level == 0) {
            mUnselecteDrawable.draw(canvas);
        } else if (level == 5000) {
            //全部选中---设置成彩色
            mSelecteDrawable.draw(canvas);
        } else {
            //混合效果Drawable
            final Rect r = mTempRect;
            //将画板切割为两块：左边和右边
            //得到当前自身Drawable的矩形区域
            Rect bounds = getBounds();

            {
                /**
                 * 1.先绘制灰色部分
                 * level 0~5000~10000
                 * 比例
                 * 4680 / 5000 -1f
                 */
                float ratio = (level / 5000f) - 1f;
                int w = bounds.width();
                if (mOrientation == HORIZONTAL) {
                    //我们要扣的宽度
                    w = (int) (w * Math.abs(ratio));
                }
                int h = bounds.height();
                if (mOrientation == VERTICAL) {
                    h = (int) (h * Math.abs(ratio));
                }

                int gravity = ratio < 0 ? Gravity.LEFT : Gravity.RIGHT;
                //从一个已有的bounds矩形边界范围中抠出一个矩形r
                Gravity.apply(
                        //从左边还是右边开始抠
                        gravity,
                        //目标矩形的宽
                        w,
                        //目标矩形的高
                        h,
                        //被抠出来的rect
                        bounds,
                        //目标rect
                        r
                );

                //保存画布
                canvas.save();
                //切割
                canvas.clipRect(r);
                //画
                mUnselecteDrawable.draw(canvas);
                //恢复之前保存的画布
                canvas.restore();
            }

            {
                /**
                 * 2.再绘制彩色部分
                 * level 0~5000~10000
                 * 比例
                 */
                float ratio = (level / 5000f) - 1f;
                int w = bounds.width();
                if (mOrientation == HORIZONTAL) {
                    w -= (int) (w * Math.abs(ratio));
                }
                int h = bounds.height();
                if (mOrientation == VERTICAL) {
                    h -= (int) (h * Math.abs(ratio));
                }

                int gravity = ratio < 0 ? Gravity.RIGHT : Gravity.LEFT;

                //从一个已有的bounds矩形边界范围中抠出一个矩形r
                Gravity.apply(
                        //从左边还是右边开始抠
                        gravity,
                        //目标矩形的宽
                        w,
                        //目标矩形的高
                        h,
                        //被抠出来的rect
                        bounds,
                        //目标rect
                        r
                );

                //保存画布
                canvas.save();
                //切割
                canvas.clipRect(r);
                //画
                mSelecteDrawable.draw(canvas);
                //恢复之前保存的画布
                canvas.restore();
            }
        }
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        //定好两个Drawable图片的宽高---边界bounds
        mUnselecteDrawable.setBounds(bounds);
        mSelecteDrawable.setBounds(bounds);
    }

    @Override
    public int getIntrinsicWidth() {
        //得到Drawable的实际宽度
        return Math.max(mSelecteDrawable.getIntrinsicWidth(),
                mUnselecteDrawable.getIntrinsicWidth());
    }

    @Override
    public int getIntrinsicHeight() {
        //得到Drawable的实际高度
        return Math.max(mSelecteDrawable.getIntrinsicHeight(),
                mUnselecteDrawable.getIntrinsicHeight());
    }

    @Override
    protected boolean onLevelChange(int level) {
        //当设置level的时候回调---提醒自己重新绘制
        invalidateSelf();
        return true;
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
