package com.wangxingxing.widget.lsn10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.wangxingxing.widget.R;
import com.wangxingxing.widget.lsn10.menu.DNItemTouchHelpCallback;
import com.wangxingxing.widget.lsn10.menu.DividerItemDecoration;
import com.wangxingxing.widget.lsn10.menu.DNItemTouchHelper;
import com.wangxingxing.widget.lsn10.menu.MainRecyclerAdapter;
import com.wangxingxing.widget.lsn10.menu.TestModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerMenuActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MainRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_menu);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mAdapter.updateData(createTestDatas());

        DNItemTouchHelpCallback callback = new DNItemTouchHelpCallback();
        DNItemTouchHelper DNItemTouchHelper = new DNItemTouchHelper(callback);
        DNItemTouchHelper.attachToRecyclerView(mRecyclerView);

//		new ItemTouchHelper(new ItemTouchHelper.Callback() {
//			@Override
//			public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
//				return 0;
//			}
//
//			@Override
//			public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//				return false;
//			}
//
//			@Override
//			public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//
//			}
//		}).attachToRecyclerView(mRecyclerView);
    }

    private List<TestModel> createTestDatas() {
        List<TestModel> result = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TestModel testModel= new TestModel(i,":Item Swipe Action Button Container Width");
            if (i == 1) {
                testModel = new TestModel(i, "Item Swipe with Action container width and no spring");
            }
            if (i == 2) {
                testModel = new TestModel(i, "Item Swipe with RecyclerView Width");
            }
            result.add(testModel);
        }
        return result;
    }
}
