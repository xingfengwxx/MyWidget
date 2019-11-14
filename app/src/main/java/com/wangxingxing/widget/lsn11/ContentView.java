package com.wangxingxing.widget.lsn11;

import android.content.Context;

import androidx.appcompat.widget.AppCompatImageView;

import com.wangxingxing.widget.R;

/**
 * 了解 VSync 的同学都知道，Andorid 中的重绘就是由Choreographer在 1 秒内产生
 * 60 个 vsync 来通知 view tree 进行 view 的重绘的。而 vsync 产生后会调用它的监听者
 * 回调接口 Choreographer.FrameCallback，也就是说，只要向Choreographer注册了这个接口，
 * 就会每 1 秒里收到 60 次回调。因此，在这里就实现了不断地调用 doAnimationFrame()
 * 来驱动动画了。想必看到这里，你应该明白了同学们常说的动画掉帧的原因了吧。如果 view
 * 的绘制过于复杂，即在 15 ms 内无法完成，那么就会使得中间某些帧跳过从而造成掉帧。
 */
public class ContentView extends AppCompatImageView {

    public ContentView(Context context) {
        super(context);
        setImageResource(R.mipmap.content);
    }
}
