package com.itheima.zhbj74.base.impl.menu;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima.zhbj74.R;
import com.itheima.zhbj74.activities.MainActivity;
import com.itheima.zhbj74.base.BaseMenuDetailPager;
import com.itheima.zhbj74.domain.NewsMenu.NewsTabData;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.viewpagerindicator.TabPageIndicator;

/**
 * @author 官珺伟 菜单详情页面---新闻 2016-3-19 下午8:33:03
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager implements
		OnPageChangeListener {
	
	@ViewInject(R.id.vp_news_menu_detail)
	private ViewPager vp_news_menu_detail;
	
	@ViewInject(R.id.indicator)
	private TabPageIndicator mIndicator;
	
	private ArrayList<NewsTabData> mTabDatas;
	private ArrayList<TabDetailPager> mPagers;
	
	public NewsMenuDetailPager(Activity activity,
			ArrayList<NewsTabData> children) {
		super(activity);
		
		mTabDatas = children;
	}
	
	@Override
	public View initView() {
		
		View view = View.inflate(mActivity, R.layout.pager_news_menu_detail,
				null);
		
		ViewUtils.inject(this, view);
		
		return view;
	}
	
	@Override
	public void initData() {
		
		mPagers = new ArrayList<TabDetailPager>();
		
		for (int i = 0; i < mTabDatas.size(); i++) {
			TabDetailPager pager = new TabDetailPager(mActivity,
					mTabDatas.get(i));
			
			mPagers.add(pager);
		}
		
		vp_news_menu_detail.setAdapter(new NewsMenuDetailAdapter());
		
		mIndicator.setViewPager(vp_news_menu_detail);
		
		// vp_news_menu_detail.setOnPageChangeListener(this);
		mIndicator.setOnPageChangeListener(this);
		
	}
	
	class NewsMenuDetailAdapter extends PagerAdapter {
		
		@Override
		public CharSequence getPageTitle(int position) {
			
			return mTabDatas.get(position).title;
			
		}
		
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
			TabDetailPager pager = mPagers.get(position);
			
			View view = pager.mRootView;
			
			container.addView(view);
			pager.initData();
			
			return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			
			container.removeView((View) object);
		}
		
	}
	
	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
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
	
	@OnClick(R.id.ib_next)
	public void nextPage(View view) {
		
		int currentItem = vp_news_menu_detail.getCurrentItem();
		currentItem++;
		vp_news_menu_detail.setCurrentItem(currentItem);
		
	}
	
	@Override
	public void onPageSelected(int position) {
		
		System.out.println("当前的位置：" + position);
		if (position == 0) {
			// 开启侧边栏
			setSlidingMenuEnable(true);
			
		} else {
			// 关闭侧边栏
			setSlidingMenuEnable(false);
			
		}
		
	}
	
	@Override
	public void onPageScrollStateChanged(int state) {
	}
	
}
