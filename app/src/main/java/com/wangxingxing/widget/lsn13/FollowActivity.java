package com.wangxingxing.widget.lsn13;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.wangxingxing.widget.R;

public class FollowActivity extends AppCompatActivity {

    Button btn, btn1;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);

        btn = findViewById(R.id.btn);
        btn1 = findViewById(R.id.btn1);

        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    v.setX(event.getRawX()-v.getWidth()/2);
                    v.setY(event.getRawY() - v.getHeight()/2);
                }
                return true;
            }
        });

        btn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE){
                    v.setX(event.getRawX()-v.getWidth()/2);
                    v.setY(event.getRawY() - v.getHeight()/2);
                }
                return true;
            }
        });
    }
}
