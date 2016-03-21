package com.itheima.zhbj74.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author 官珺伟
 *
 * 2016-3-19 下午12:07:11
 */
public class NoScrollViewPager extends ViewPager {

	
	
	
	public NoScrollViewPager(Context context) {
		super(context);
		
	}

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}
	
	//重写此方法，触摸时什么都不做
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		
		return true;
	}
	
	
	//事件拦截
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		//不拦截事件
		return false;
	}
}
