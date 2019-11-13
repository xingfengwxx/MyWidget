package com.wangxingxing.widget.lsn10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.wangxingxing.widget.R;
import com.wangxingxing.widget.lsn10.del.DataUtils;
import com.wangxingxing.widget.lsn10.del.ItemTouchHelper;
import com.wangxingxing.widget.lsn10.del.MessageAdapter;
import com.wangxingxing.widget.lsn10.del.MessageItemTouchCallback;
import com.wangxingxing.widget.lsn10.del.QQMessage;
import com.wangxingxing.widget.lsn10.del.StartDragListener;

import java.util.List;

/**
 * 现在RecyclerView的应用越来越广泛了，不同的应用场景需要其作出不同的改变。
 * 有时候我们可能需要实现侧滑删除的功能，又或者长按Item进行拖动与其他Item
 * 进行位置的交换，但RecyclerView没有提供现成的API供我们操作，但是SDK提供了
 * ItemTouchHelper这样一个工具类帮助我们快速实现以上功能
 */
public class RecyclerDelActivity extends AppCompatActivity implements StartDragListener {

    private RecyclerView recyclerview;
    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_del);

        recyclerview = (RecyclerView)findViewById(R.id.recyclerview);

        List<QQMessage> list = DataUtils.init();

        //recyclerview使用
        MessageAdapter adapter = new MessageAdapter(list,this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);


        ItemTouchHelper.Callback callback= new MessageItemTouchCallback(adapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerview);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }
}
