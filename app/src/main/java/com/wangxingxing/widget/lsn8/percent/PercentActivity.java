package com.wangxingxing.widget.lsn8.percent;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wangxingxing.widget.R;


public class PercentActivity extends AppCompatActivity {
    private static final String TAG = "PercentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent);
        final TextView textView = findViewById(R.id.textView);
        textView.post(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, textView.getWidth() + "  " + textView.getHeight()+"=="+textView.getPaddingLeft());
                Log.e(TAG, ((PercentLayout) textView.getParent()).getWidth() +
                        "  " + ((PercentLayout) textView.getParent()).getHeight());
            }
        });
    }

}
