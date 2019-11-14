package com.wangxingxing.widget.lsn11;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;

import com.wangxingxing.widget.R;

/**
 * 属性动画，Android 3.0提出的动画模式
 * 优点：
 * 1.不在局限于View 对象，无对象也可以进行动画处理
 * 2.不再局限于4种基本变换：平移、旋转、缩放 、透明度
 * 3.可以灵活的操作任意对象属性，根据自己业务来实现自己想要的结果
 * 核心：
 * 1.ObjectAnimator  对象动画（重点）
 * 2.ValueAnimator	  值动画(重点)
 * 3.PropertyValueHolder 用于多个动画同时执行
 * 4.TypeEvaluator 估值器
 * 5.Interpolator 插值器
 * 6.AnimatorSet 动画集合
 *
 * 属性动画要求动画作用的对象提供该属性的set方法，属性动画根据传递的该属性的初始值和最终值，
 * 以动画的效果多次去调用set方法。每次传给set方法的值都不一样，确切来说是随着时间的推移，所
 * 传递的值越来越接近最终值。
 */
public class PropertyAnimActivity extends AppCompatActivity {

    private View iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim);

        iv = findViewById(R.id.img_iv);
    }

    public void startAnimator(View view) {
        switch (view.getId()) {
            case R.id.btn_object:
                startObjectAnimatorAnim(iv);
                break;
            case R.id.btn_animatorset:
                startAnimatorSet(iv);
                break;
            case R.id.btn_value:
                startValueAnimatorAnim(view);
                break;
            case R.id.btn_interpolator:
                startInterpolatorApply(iv);
                break;
            case R.id.btn_valueholder:
                startPropertyValueHolderAnim(iv);
                break;
            case R.id.btn_evaluator:
                startEvaluator(iv);
                break;
        }
    }

    /**
     * 操作对象属性，不局限于对象
     * 完成透明动画
     *
     * @param v
     */
    public void startObjectAnimatorAnim(View v) {
//        200 1-0.3

        //1.设置参数
        //2.执行动画
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(v, "alpha", 1.0f, 0.3f,1.0f);
        alphaAnim.setDuration(1000);//执行时间
        alphaAnim.setStartDelay(300);//延迟
        alphaAnim.start();
    }

    /**
     * @param v
     */
    public void startValueAnimatorAnim(final View v) {
        //组合使用300
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "hehe", 0f, 100f,50f);

        animator.setDuration(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

//				animation.getAnimatedFraction();//百分比
                float value = (float) animation.getAnimatedValue();//百分比的所对应的值
                Log.d("dn_alan", value + "");
                v.setScaleX(0.5f + value / 200);
                v.setScaleY(0.5f + value / 200);

            }
        });
        animator.start();
//		animator.setRepeatCount(2);//重复次数
//		animator.setRepeatCount(ValueAnimator.INFINITE);//无限次数
    }

    public void startPropertyValueHolderAnim(View v) {
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("alpha", 1f, 0.5f);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.5f);
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.5f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v, holder1, holder2, holder3);
        animator.setDuration(200);
        animator.start();
    }

    public void startAnimatorSet(View v) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(iv, "translationX", 0f, 500F);

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(iv, "alpha", 0f, 1f);

        ObjectAnimator animator3 = ObjectAnimator.ofFloat(iv, "scaleX", 0f, 2f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);

        //animatorSet.play(animator3).with(animator2).after(animator1);//链式调用顺序
        //animatorSet.play(animator3).with(animator2).before(animator1);//animator1
        //animatorSet.playTogether(animator1,animator2,animator3);//一起执行
        animatorSet.playSequentially(animator2, animator1, animator3);//顺序执行
        animatorSet.start();
    }

    /**
     * 估值器
     * 自由落体效果实现
     * 核心目的：自定义变换规则
     * @param v
     */
    public void startEvaluator(final View v) {
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(3000);
        animator.setObjectValues(new PointF(0, 0));
        final PointF point = new PointF();
        //估值
        animator.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                point.x = 100f * (fraction * 5);
                // y=vt=1/2*g*t*t(重力计算)
                point.y = 0.5f * 130f * (fraction * 5) * (fraction * 5);


                return point;
            }
        });

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF p = (PointF) animation.getAnimatedValue();
                Log.d("dn_alan", p.x + "===" + p.y);
                v.setX(p.x);
                v.setY(p.y);
            }
        });
        animator.start();
    }

    /**
     * 插值器
     * 由API提供的一组算法，用来操作动画执行是的变换规则，省去了一些自己写算法的麻烦，大致分为九种
     * @param v
     */
    public void startInterpolatorApply(final View v) {
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(3000);
        animator.setObjectValues(new PointF(0, 0));
        final PointF point = new PointF();
//        //估值
        animator.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                point.x = 100f * (fraction * 5);
                // y=vt=1/2*g*t*t(重力计算)
                point.y = 0.5f * 98f * (fraction * 5) * (fraction * 5);
                return point;
            }
        });

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.setX(point.x);
                v.setY(point.y);
            }
        });

        //详细算法见资料图片
//		加速插值器，公式： y=t^(2f) （加速度参数. f越大，起始速度越慢，但是速度越来越快）
//        animator.setInterpolator(new AccelerateInterpolator(10));

//      减速插值器公式: y=1-(1-t)^(2f) （描述: 加速度参数. f越大，起始速度越快，但是速度越来越慢）
//		animator.setInterpolator(new DecelerateInterpolator());

//      先加速后减速插值器 y=cos((t+1)π)/2+0.5
//		animator.setInterpolator(new AccelerateDecelerateInterpolator());

//      张力值, 默认为2，T越大，初始的偏移越大，而且速度越快 公式：y=(T+1)×t3–T×t2
//		animator.setInterpolator(new AnticipateInterpolator());

//      张力值tension，默认为2，张力越大，起始和结束时的偏移越大，
//      而且速度越快;额外张力值extraTension，默认为1.5。公式中T的值为tension*extraTension
//		animator.setInterpolator(new AnticipateOvershootInterpolator());
//      弹跳插值器
        animator.setInterpolator(new BounceInterpolator());
//      周期插值器 y=sin(2π×C×t)  周期值，默认为1；2表示动画会执行两次
//		animator.setInterpolator(new CycleInterpolator(2));
//		线性插值器，匀速公式：Y=T
//		animator.setInterpolator(new LinearInterpolator());

//      公式: y=(T+1)x(t1)3+T×(t1)2 +1
//      描述: 张力值，默认为2，T越大，结束时的偏移越大，而且速度越快
//		animator.setInterpolator(new OvershootInterpolator());



        animator.start();

    }

}
