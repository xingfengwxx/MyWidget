package com.wangxingxing.widget.lsn8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wangxingxing.widget.R;
import com.wangxingxing.widget.lsn8.density.DensityActivity;
import com.wangxingxing.widget.lsn8.notch.NotchActivity;
import com.wangxingxing.widget.lsn8.percent.PercentActivity;
import com.wangxingxing.widget.lsn8.pixel.PixelActivity;

public class ScreenAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_adapter);

    }

    public void pixel(View view) {
        Intent intent = new Intent(ScreenAdapterActivity.this, PixelActivity.class);
        startActivity(intent);
    }

    public void percent(View view) {
        Intent intent = new Intent(ScreenAdapterActivity.this, PercentActivity.class);
        startActivity(intent);
    }

    public void density(View view) {
        Intent intent = new Intent(ScreenAdapterActivity.this, DensityActivity.class);
        startActivity(intent);
    }

    public void notch(View view) {
        Intent intent = new Intent(ScreenAdapterActivity.this, NotchActivity.class);
        startActivity(intent);
    }
}
