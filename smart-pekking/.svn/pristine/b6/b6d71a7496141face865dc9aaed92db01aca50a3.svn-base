package com.itheima.zhbj74.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.itheima.zhbj74.R;
import com.itheima.zhbj74.utils.PrefUtils;

/**
 * @author 官珺伟
 * 
 *         2016-3-18 下午7:41:43
 * 
 *         新手引导页面
 * 
 */
public class GuideActivity extends Activity {
	
	private ViewPager mVp_guide;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_guide);
		
		initView();
		
		initData();
		
		initEvent();
		
	}
	
	private void initEvent() {
		
		mVp_guide.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				
				if (position == mImageViewsList.size() - 1) {
					mBtn_start.setVisibility(View.VISIBLE);
				} else {
					mBtn_start.setVisibility(View.GONE);
				}
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				
				int leftMargin = (int) (mPointDis * (positionOffset + position));
				
				RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) mIv_red_point
						.getLayoutParams();
				
				params.leftMargin = leftMargin;
				
				mIv_red_point.setLayoutParams(params);
				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				
			}
		});
		
		// measure > layout> draw(activity 的oncreate方法结束之后才会走这个流程)
		/*
		 * mPointDis = mLl_container.getChildAt(1).getLeft() -
		 * mLl_container.getChildAt(0).getLeft();
		 * System.out.println("圆点的距离:"+mPointDis);
		 */
		
		mIv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
					
					@Override
					public void onGlobalLayout() {
						
						mIv_red_point.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
						
						mPointDis = mLl_container.getChildAt(1).getLeft()
								- mLl_container.getChildAt(0).getLeft();
						System.out.println("圆点的距离:" + mPointDis);
					}
				});
		
		
		mBtn_start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//跳到主页面
				PrefUtils.setBoolean(getApplicationContext(), "is_first_enter", false);
				
				startActivity(new Intent(getApplicationContext(),MainActivity.class));
				finish();
				
			}
		});
		
	}
	
	private void initView() {
		
		mVp_guide = (ViewPager) findViewById(R.id.vp_guide);
		
		mLl_container = (LinearLayout) findViewById(R.id.ll_container);
		
		mBtn_start = (Button) findViewById(R.id.btn_start);
		
		mIv_red_point = (ImageView) findViewById(R.id.iv_red_point);
		
	}
	
	private int[] imageIds = new int[] { R.drawable.guide_1,
			R.drawable.guide_2, R.drawable.guide_3 };
	
	private ArrayList<ImageView> mImageViewsList;
	
	private LinearLayout mLl_container;
	
	private Button mBtn_start;
	
	private int mPointDis;
	
	private ImageView mIv_red_point;
	
	private void initData() {
		
		mImageViewsList = new ArrayList<ImageView>();
		for (int i = 0; i < imageIds.length; i++) {
			
			ImageView view = new ImageView(this);
			view.setBackgroundResource(imageIds[i]);
			
			mImageViewsList.add(view);
			
			// 初始化小圆点
			ImageView point = new ImageView(this);
			point.setImageResource(R.drawable.shape_point_gray);
			
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			
			if (i > 0) {
				params.leftMargin = 10;
			}
			
			point.setLayoutParams(params);
			
			mLl_container.addView(point);
		}
		
		mVp_guide.setAdapter(new GuideAdapter());
	}
	
	class GuideAdapter extends PagerAdapter {
		
		@Override
		public int getCount() {
			
			return mImageViewsList.size();
		}
		
		@Override
		public boolean isViewFromObject(View view, Object object) {
			
			return view == object;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			
			ImageView view = mImageViewsList.get(position);
			
			container.addView(view);
			
			return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			
			container.removeView((View) object);
			
		}
		
	}
	
}
