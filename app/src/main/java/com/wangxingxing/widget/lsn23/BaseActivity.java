package com.wangxingxing.widget.lsn23;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.fragment.app.FragmentActivity;

/**
 * 基类
 * */
public abstract class BaseActivity extends FragmentActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutId());
		initBase();
		initView();
		initData();
		initListener();
	}

	/**
	 * 设置子类getLayoutId
	 * */
	public abstract int getLayoutId();
	
	/**
	 * 基类初始化
	 * */
	public void initBase() {
	}
	
	/**
	 * 子类初始化View
	 * */
	public void initView() {
	}

	/**
	 * 子类初始化数据
	 * */
	public void initData() {
	}
	
	/**
	 * 子类初始化监听
	 * */
	public void initListener() {
	}
	
	@Override
	public void onClick(View v) {
	}
}