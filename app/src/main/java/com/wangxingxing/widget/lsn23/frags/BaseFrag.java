package com.wangxingxing.widget.lsn23.frags;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 *
 * Frag基类
 * */
public abstract class BaseFrag extends Fragment implements OnClickListener {

	public View view;
	public Context myContext;
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		view = inflater.inflate(getLayoutId(), null);
		// 初始化
		initBase();
		initView();
		initData();
		initListener();
		return view;
	}
	
	/**
	 * 设置子类getLayoutId
	 * */
	public abstract int getLayoutId();
	
	/**
	 * 基类初始化
	 * */
	public void initBase() {
		
		myContext = getActivity();
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
