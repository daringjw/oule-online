package com.itheima.zhbj74.fragment;

import java.util.ArrayList;

import com.itheima.zhbj74.R;
import com.itheima.zhbj74.activities.MainActivity;
import com.itheima.zhbj74.base.impl.NewsCenterPager;
import com.itheima.zhbj74.domain.NewsMenu;
import com.itheima.zhbj74.domain.NewsMenu.NewsMenuData;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.R.integer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LeftMenuFragment extends BaseFragment {
	
	@ViewInject(R.id.lv_list)
	private ListView mLv_list;
	
	/**
	 * 当前被选中item的位置
	 */
	private int mCurrentPosition;
	
	@Override
	public View initView() {
		
		View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
		
		// mLv_list = (ListView) view.findViewById(R.id.lv_list);
		
		ViewUtils.inject(this, view);
		return view;
	}
	
	@Override
	public void initData() {
		
	}
	
	private ArrayList<NewsMenuData> mNewsMenuDatas;

	private LeftMenuAdapter mLeftMenuAdapter;
	
	/**
	 * @param data
	 * 
	 */
	public void setMenuData(ArrayList<NewsMenuData> data) {
		
		mCurrentPosition=0;
		
		mNewsMenuDatas = data;
		
		mLeftMenuAdapter = new LeftMenuAdapter();
		
		mLv_list.setAdapter(mLeftMenuAdapter);
		
		mLv_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				mCurrentPosition=position;
				
				mLeftMenuAdapter.notifyDataSetChanged();
				
				//收起侧边栏
				toggle();
				
				//侧边栏点击后，要修改新闻中心页面的frameLayout的内容
				setCurrentDetailPager(position);
			}
		});
	}
	
	/**
	 * 设置当前的详情页面
	 * 
	 * @param position
	 */
	protected void setCurrentDetailPager(int position) {
		//获取新闻中心的对象
		MainActivity mainActivity=(MainActivity) mActivity;
		ContentFragment fragment = mainActivity.getContentFragment();
		
		NewsCenterPager newsCenterPager = fragment.getNewsCenterPager();
		//修改新闻中心的布局
		newsCenterPager.setCurrentDetailPager(position);
		
	}

	/**
	 * 打开或者关闭侧边栏
	 */
	protected void toggle() {
		MainActivity mainActivity=(MainActivity) mActivity;
		SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
		slidingMenu.toggle();//如果当前状态是开，调用后就关闭，反之亦然
		
		
	}

	class LeftMenuAdapter extends BaseAdapter {
		
		private TextView mTv_menu;
		
		@Override
		public int getCount() {
			
			return mNewsMenuDatas.size();
		}
		
		@Override
		public Object getItem(int position) {
			
			return mNewsMenuDatas.get(position);
		}
		
		@Override
		public long getItemId(int position) {
			
			return position;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View view = View.inflate(mActivity, R.layout.list_item_left_menu,
					null);
			
			mTv_menu = (TextView) view.findViewById(R.id.tv_menu);
			
			NewsMenuData item = (NewsMenuData) getItem(position);
			
			mTv_menu.setText(item.title);
			
			if (position == mCurrentPosition) {
				
				mTv_menu.setEnabled(true);
				
			} else {
				
				mTv_menu.setEnabled(false);
				
			}
			return view;
		}
		
	}
}
