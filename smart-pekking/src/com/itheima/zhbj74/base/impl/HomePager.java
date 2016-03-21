package com.itheima.zhbj74.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itheima.zhbj74.activities.MainActivity;
import com.itheima.zhbj74.base.BasePager;
import com.itheima.zhbj74.domain.NewsMenu;
import com.itheima.zhbj74.fragment.LeftMenuFragment;
import com.itheima.zhbj74.global.GlobalConstants;
import com.itheima.zhbj74.utils.CacheUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class HomePager extends BasePager {

	public HomePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		System.out.println("首页初始化啦...");

		// 要给帧布局填充布局对象
		TextView view = new TextView(mActivity);
		view.setText("首页");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);

		mFl_content.addView(view);

		// 修改页面标题
		mTv_title.setText("智慧北京");

		// 隐藏菜单按钮
		mBtn_menu.setVisibility(View.GONE);
	}
	
	
	
}
