package com.wangxingxing.widget.lsn6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.wangxingxing.widget.R;

public class DragBubbleActivity extends AppCompatActivity {

    private DragBubbleView dragBubbleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_bubble);

        dragBubbleView = findViewById(R.id.dbv);
    }

    public void reset(View view) {
        dragBubbleView.reset();
    }
}
