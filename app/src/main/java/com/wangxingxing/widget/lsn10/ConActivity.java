package com.wangxingxing.widget.lsn10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.wangxingxing.widget.R;
import com.wangxingxing.widget.lsn10.bean.Girl;
import com.wangxingxing.widget.lsn10.model.GirlModel;
import com.wangxingxing.widget.lsn10.model.IGirlModel;

import java.util.List;

/**
 * 滑动冲突处理
 */
public class ConActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_con);

        recyclerView = findViewById(R.id.rel_view);
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.rv_divider));
        recyclerView.addItemDecoration(divider);
        IGirlModel girlModel = new GirlModel();
        girlModel.loadGirlData(new IGirlModel.IListener() {
            @Override
            public void onComplete(List<Girl> girls) {
                recyclerView.setAdapter(new GirlAdapter(girls));
            }
        });
    }
}
