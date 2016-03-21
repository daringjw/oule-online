package com.itheima.zhbj74.fragment;

import java.util.ArrayList;

import com.itheima.zhbj74.R;
import com.itheima.zhbj74.activities.MainActivity;
import com.itheima.zhbj74.base.BasePager;
import com.itheima.zhbj74.base.impl.GovAffairPager;
import com.itheima.zhbj74.base.impl.HomePager;
import com.itheima.zhbj74.base.impl.NewsCenterPager;
import com.itheima.zhbj74.base.impl.SettingPager;
import com.itheima.zhbj74.base.impl.SmartService;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ContentFragment extends BaseFragment {
	
	private com.itheima.zhbj74.view.NoScrollViewPager mVp_content;
	
	private ArrayList<BasePager> mPagers;
	
	private RadioGroup mRg_group;
	
	@Override
	public View initView() {
		
		View view = View.inflate(mActivity, R.layout.fragment_content, null);
		mVp_content = (com.itheima.zhbj74.view.NoScrollViewPager) view
				.findViewById(R.id.vp_content);
		
		mRg_group = (RadioGroup) view.findViewById(R.id.rg_group);
		
		return view;
	}
	
	public NewsCenterPager getNewsCenterPager(){
		
		NewsCenterPager newsCenterPager = (NewsCenterPager) mPagers.get(1);
		
		return newsCenterPager;
	}
	
	@Override
	public void initData() {
		
		mPagers = new ArrayList<BasePager>();
		
		mPagers.add(new HomePager(mActivity));
		mPagers.add(new NewsCenterPager(mActivity));
		mPagers.add(new SmartService(mActivity));
		mPagers.add(new GovAffairPager(mActivity));
		mPagers.add(new SettingPager(mActivity));
		
		mVp_content.setAdapter(new ContentAdapter());
		
		mRg_group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				switch (checkedId) {
					case R.id.rb_home:
						mVp_content.setCurrentItem(0, false);
						break;
					case R.id.rb_newscenter:
						mVp_content.setCurrentItem(1, false);
						break;
					case R.id.rb_smartservice:
						mVp_content.setCurrentItem(2, false);
						break;
					case R.id.rb_govaffairs:
						mVp_content.setCurrentItem(3, false);
						break;
					case R.id.rb_setting:
						mVp_content.setCurrentItem(4, false);
						break;
					
					default:
						break;
				}
			}
		});
		
		mVp_content.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				BasePager pager = mPagers.get(position);
				
				pager.initData();
				
				if (position == 0 || position == mPagers.size() - 1) {
					// 首页和设置要禁用侧边栏
					setSlidingMenuEnable(false);
					
				} else {
					
					setSlidingMenuEnable(true);
					
				}
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				
			}
		});
		
		mPagers.get(0).initData();
		// 首页禁用侧边栏
		setSlidingMenuEnable(false);
	}
	
	/**
	 * @param enable
	 *            开启禁用侧边栏
	 */
	protected void setSlidingMenuEnable(boolean enable) {
		
		MainActivity mainActivity = (MainActivity) mActivity;
		
		SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
		if (enable) {
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		} else {
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
		
	}
	
	class ContentAdapter extends PagerAdapter {
		
		@Override
		public int getCount() {
			
			return mPagers.size();
		}
		
		@Override
		public boolean isViewFromObject(View view, Object object) {
			
			return view == object;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			
			BasePager pager = mPagers.get(position);
			
			View view = pager.mRootView;
			
			// pager.initData();//初始化数据,viewpager
			
			container.addView(view);
			
			return view;
			
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			
			container.removeView((View) object);
		}
	}
}
