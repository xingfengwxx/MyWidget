package com.wangxingxing.widget.lsn8.pixel;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wangxingxing.widget.R;


public class PixelActivity extends AppCompatActivity {
    private static final String TAG = "PixelActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixel);
        final TextView textView = findViewById(R.id.textView);
//        textView.post(new Runnable() {
//            @Override
//            public void run() {
//                Log.e(TAG, textView.getWidth() + "  " + textView.getHeight()+"=="+textView.getPaddingLeft());
//                Log.e(TAG, ((ScreenAdapterLayout) textView.getParent()).getWidth() +
//                        "  " + ((ScreenAdapterLayout) textView.getParent()).getHeight());
//            }
//        });
    }

}
