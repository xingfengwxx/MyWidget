package com.wangxingxing.widget.lsn16;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangxingxing.widget.R;
import com.wangxingxing.widget.lsn16.view.MyRecyclerView;
import com.wangxingxing.widget.lsn16.view.MyRecyclerView2;


public class CusRecyclerActivity extends AppCompatActivity {

     MyRecyclerView2 recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_recycler);

        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new MyRecyclerView2.Adapter() {
            @Override
            public View onCreateViewHolder(int position, View convertView, ViewGroup parent) {
                convertView = getLayoutInflater().inflate(R.layout.layout_item,parent,false);
                TextView textView = convertView.findViewById(R.id.textView);
                textView.setText("腾讯课堂"+position);
                return convertView;
            }

            @Override
            public View onBinderViewHolder(int position, View convertView, ViewGroup parent) {
                TextView textView = convertView.findViewById(R.id.textView);
                textView.setText("腾讯课堂"+position);
                return convertView;
            }

            @Override
            public int getItemViewType(int row) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public int getCount() {
                return 40;
            }

            @Override
            public int getHeight(int index) {
                return 100;
            }
        });
    }
}
