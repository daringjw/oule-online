package com.itheima.zhbj74.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.itheima.zhbj74.base.BasePager;

public class SettingPager extends BasePager {

	public SettingPager(Activity activity) {
		super(activity);
		
	}
	
	
	@Override
	public void initData() {
		
		System.out.println("settingpager初始化了");
		TextView view=new TextView(mActivity);
		
		view.setText("settingpager");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);
		
		mFl_content.addView(view);
		
		mTv_title.setText("设置");
		
		mBtn_menu.setVisibility(View.GONE);
	}
	
	
	
}
