package com.wangxingxing.widget.lsn3.xfermode;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wangxingxing.widget.R;

public class XfermodeDTSActivity extends AppCompatActivity implements View.OnClickListener {

    private RoundImageView roundImageView;
    private InvertedImageView invertedImageView;
    private IrregularWaveView irregularWaveView;
    private HeartMap heartMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfermode_dts);

        findViewById(R.id.rounddstinbtn).setOnClickListener(this);
        findViewById(R.id.invertdstinbtn).setOnClickListener(this);
        findViewById(R.id.irregularwavebtn).setOnClickListener(this);
        findViewById(R.id.heartbitbtn).setOnClickListener(this);

        roundImageView = findViewById(R.id.roundImageView);
        invertedImageView = findViewById(R.id.invertedImageView);
        irregularWaveView = findViewById(R.id.irregularWaveView);
        heartMap = findViewById(R.id.heartMap);
    }

    @Override
    public void onClick(View v) {
        hideAllViews();
        switch (v.getId()) {
            case R.id.rounddstinbtn:
                roundImageView.setVisibility(View.VISIBLE);
                break;
            case R.id.invertdstinbtn:
                invertedImageView.setVisibility(View.VISIBLE);
                break;
            case R.id.irregularwavebtn:
                irregularWaveView.setVisibility(View.VISIBLE);
                break;
            case R.id.heartbitbtn:
                heartMap.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void hideAllViews() {
        roundImageView.setVisibility(View.GONE);
        invertedImageView.setVisibility(View.GONE);
        irregularWaveView.setVisibility(View.GONE);
        heartMap.setVisibility(View.GONE);
    }


}
