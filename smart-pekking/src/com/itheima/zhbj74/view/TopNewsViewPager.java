package com.itheima.zhbj74.view;

import android.R.integer;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author 官珺伟
 *
 * 2016-3-20 下午2:49:33
 */
public class TopNewsViewPager extends ViewPager {

	
	private int mStartX;
	private int mStartY;
	private int mEndX;
	private int mEndY;


	public TopNewsViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}

	public TopNewsViewPager(Context context) {
		super(context);
		
	}
	
	
	/**
	 * 1,上下滑动,拦截
	 * 2，向右滑动并且当前是第一个页面，拦截
	 * 3，向左滑动并且当前是最后一个页面，拦截
	 * 
	 * 
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		
		//请求不要拦截事件
		getParent().requestDisallowInterceptTouchEvent(true);
		
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mStartX = (int) ev.getX();
				mStartY = (int) ev.getY();
				
				
				break;
			case MotionEvent.ACTION_MOVE:
				mEndX = (int) ev.getX();
				mEndY = (int) ev.getY();
				
				int dx=mEndX-mStartX;
				int dy=mEndY-mEndY;
				
				if (Math.abs(dy)<Math.abs(dx)) {
					int currentItem = getCurrentItem();
					//属于左右滑动
					if (dx>0) {
						//向右滑
						
						if (currentItem==0) {
							getParent().requestDisallowInterceptTouchEvent(false);
						}
						
					}else {
						//向左滑
						int count = getAdapter().getCount();
						
						if (currentItem==count-1) {
							getParent().requestDisallowInterceptTouchEvent(false);
						}
					}
					
				}else {
					//上下滑动,拦截
					
					getParent().requestDisallowInterceptTouchEvent(false);
					
				}
				break;
			
			default:
				break;
		}
		
		return super.dispatchTouchEvent(ev);
		
	}
}
