package com.itheima.zhbj74.base;

import com.itheima.zhbj74.R;
import com.itheima.zhbj74.activities.MainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author 官珺伟
 *
 * 2016-3-19 上午11:25:24
 */
public abstract class BasePager {
	
	public Activity mActivity;
	public TextView mTv_title;
	public ImageView mBtn_menu;
	public FrameLayout mFl_content;
	public View mRootView;
	
	public BasePager (Activity activity){
		
		mActivity=activity;
		
		mRootView = initView();
		
		
	}
	
	public  View initView(){
		
		
		View view = View.inflate(mActivity, R.layout.base_pager, null);
		
		mTv_title = (TextView) view.findViewById(R.id.tv_title);
		
		mBtn_menu = (ImageView) view.findViewById(R.id.btn_menu);
		
		mFl_content = (FrameLayout) view.findViewById(R.id.fl_content);
		
		
		mBtn_menu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				toggle();
			}
		});
		return view;
	};
	
	/**
	 * 打开或者关闭侧边栏
	 */
	protected void toggle() {
		MainActivity mainActivity=(MainActivity) mActivity;
		SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
		slidingMenu.toggle();//如果当前状态是开，调用后就关闭，反之亦然
		
		
	}
	
	
	public void initData(){
		
	};
	
	
}
