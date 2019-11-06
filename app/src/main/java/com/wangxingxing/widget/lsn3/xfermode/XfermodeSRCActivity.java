package com.wangxingxing.widget.lsn3.xfermode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.wangxingxing.widget.R;

public class XfermodeSRCActivity extends AppCompatActivity {

    private EraserView eraserView;
    private GuaGuaCardView guaGuaCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfermode_src);

        eraserView = findViewById(R.id.eraserview);
        guaGuaCardView = findViewById(R.id.guaguaview);

        findViewById(R.id.btn_eraserview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAllViews();
                eraserView.setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.btn_guaguaview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAllViews();
                guaGuaCardView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void hideAllViews() {
        eraserView.setVisibility(View.GONE);
        guaGuaCardView.setVisibility(View.GONE);
    }
}
