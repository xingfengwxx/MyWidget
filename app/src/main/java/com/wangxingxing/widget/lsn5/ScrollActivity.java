package com.wangxingxing.widget.lsn5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.wangxingxing.widget.R;

public class ScrollActivity extends AppCompatActivity {


    private Drawable[] revealDrawables;
    private GalleryHorizontalScrollView hzv;

    private int[] mImgIds = new int[]{ //6个
            R.drawable.avft,
            R.drawable.box_stack,
            R.drawable.bubble_frame,
            R.drawable.bubbles,
            R.drawable.bullseye,
            R.drawable.circle_filled,
            R.drawable.circle_outline,

            R.drawable.avft,
            R.drawable.box_stack,
            R.drawable.bubble_frame,
            R.drawable.bubbles,
            R.drawable.bullseye,
            R.drawable.circle_filled,
            R.drawable.circle_outline
    };
    private int[] mImgIds_active = new int[]{
            R.drawable.avft_active, R.drawable.box_stack_active, R.drawable.bubble_frame_active,
            R.drawable.bubbles_active, R.drawable.bullseye_active, R.drawable.circle_filled_active,
            R.drawable.circle_outline_active,
            R.drawable.avft_active, R.drawable.box_stack_active, R.drawable.bubble_frame_active,
            R.drawable.bubbles_active, R.drawable.bullseye_active, R.drawable.circle_filled_active,
            R.drawable.circle_outline_active
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        initView();
        initData();
    }

    private void initData() {
        revealDrawables = new Drawable[mImgIds.length];

        for (int i = 0; i < mImgIds.length; i++) {
            RevealDrawable rd = new RevealDrawable(
                    getResources().getDrawable(mImgIds[i]),
                    getResources().getDrawable(mImgIds_active[i]),
                    RevealDrawable.HORIZONTAL
            );
            revealDrawables[i] = rd;
        }
        hzv.addImageViews(revealDrawables);
    }

    private void initView() {
        hzv = findViewById(R.id.hsv);
    }
}
