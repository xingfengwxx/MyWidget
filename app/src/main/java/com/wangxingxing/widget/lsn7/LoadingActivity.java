package com.wangxingxing.widget.lsn7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.wangxingxing.widget.R;

public class LoadingActivity extends AppCompatActivity implements View.OnClickListener {

    private LoadingView circle;
    private FaceLoadingView face;
    private PathMeasureView circle_arrows;
    private WaveView wave;

    private float progress = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        findViewById(R.id.btn_circle_loading).setOnClickListener(this);
        findViewById(R.id.btn_face_loading).setOnClickListener(this);
        findViewById(R.id.btn_wave_loading).setOnClickListener(this);
        findViewById(R.id.btn_circle_arrows_loading).setOnClickListener(this);

        circle = findViewById(R.id.circle);
        face = findViewById(R.id.face);
        circle_arrows = findViewById(R.id.circle_arrows);
        wave = findViewById(R.id.wave);

        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progress <= 100){
                            progress += 2;
                            face.setProgress(progress / 100);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();
            }
        });
    }

    @Override
    public void onClick(View v) {
        hideAllViews();
        switch (v.getId()) {
            case R.id.btn_circle_loading:
                circle.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_face_loading:
                face.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_wave_loading:
                wave.setVisibility(View.VISIBLE);
                wave.startAnimation();
                break;
            case R.id.btn_circle_arrows_loading:
                circle_arrows.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void hideAllViews() {
        circle.setVisibility(View.GONE);
        face.setVisibility(View.GONE);
        circle_arrows.setVisibility(View.GONE);
        wave.setVisibility(View.GONE);

        face.loadComplete();
    }
}
