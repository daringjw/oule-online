package com.itheima.zhbj74.base.impl.menu;

import java.util.ArrayList;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itheima.zhbj74.R;
import com.itheima.zhbj74.base.BaseMenuDetailPager;
import com.itheima.zhbj74.domain.NewsMenu.NewsTabData;
import com.itheima.zhbj74.domain.NewsTabBean;
import com.itheima.zhbj74.domain.NewsTabBean.NewsData;
import com.itheima.zhbj74.domain.NewsTabBean.TopNews;
import com.itheima.zhbj74.global.GlobalConstants;
import com.itheima.zhbj74.utils.CacheUtils;
import com.itheima.zhbj74.view.PullToRefreshListView.OnRefreshListener;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * @author 官珺伟
 * 
 *         页签页面对象 2016-3-19 下午9:41:44
 */
public class TabDetailPager extends BaseMenuDetailPager {
	
	/**
	 * 单个页签的网络数据
	 */
	private NewsTabData mTabData;
	
	@ViewInject(R.id.vp_top_news)
	private com.itheima.zhbj74.view.TopNewsViewPager mViewPager;
	
	@ViewInject(R.id.tv_topnews_title)
	private TextView tv_topnews_title;
	
	@ViewInject(R.id.cpi_circle)
	private CirclePageIndicator cpi_circle;
	
	@ViewInject(R.id.lv_list_news)
	private com.itheima.zhbj74.view.PullToRefreshListView lv_list_news;
	
	private String mUrl;
	
	private ArrayList<TopNews> mTopnews;
	
	private ArrayList<NewsData> mNewslist;
	
	private NewsAdapter mNewsAdapter;
	
	public TabDetailPager(Activity activity, NewsTabData newsTabData) {
		super(activity);
		
		mTabData = newsTabData;
		
		mUrl = GlobalConstants.SERVER_URL + mTabData.url;
		
	}
	
	@Override
	public View initView() {
		
		View view = View.inflate(mActivity, R.layout.pager_tab_detail, null);
		
		ViewUtils.inject(this, view);
		
		View headerView=View.inflate(mActivity, R.layout.list_item_header, null);
		
		ViewUtils.inject(this, headerView);
		
		lv_list_news.addHeaderView(headerView);
		
		//5,设置回调
		lv_list_news.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				
				//刷新数据
				getDataFromServer();
				
			}
		});
		return view;
	}
	
	@Override
	public void initData() {
		
		String cache = CacheUtils.getCache(mUrl, mActivity);
		
		if (!TextUtils.isEmpty(cache)) {
			processData(cache);
		}
		
		getDataFromServer();
		
	}
	
	private void getDataFromServer() {
		
		HttpUtils utils = new HttpUtils();
		
		utils.send(HttpMethod.GET, mUrl, new RequestCallBack<String>() {
			
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				
				String result = responseInfo.result;
				
				processData(result);
				
				CacheUtils.setCache(mUrl, result, mActivity);
				
				lv_list_news.onRefreshComplete(true);
				
			}
			
			@Override
			public void onFailure(HttpException error, String msg) {
				
				error.printStackTrace();
				Toast.makeText(mActivity, msg, 0).show();
				
				lv_list_news.onRefreshComplete(false);
			}
			
		});
	}
	
	protected void processData(String result) {
		
		Gson gson = new Gson();
		
		NewsTabBean newsTabBean = gson.fromJson(result, NewsTabBean.class);
		
		mTopnews = newsTabBean.data.topnews;
		
		if (mTopnews != null) {
			mViewPager.setAdapter(new TopNewsAdapter());
			
			cpi_circle.setViewPager(mViewPager);
			cpi_circle.setSnap(true);
			
			cpi_circle.setOnPageChangeListener(new OnPageChangeListener() {
				
				@Override
				public void onPageSelected(int position) {
					
					TopNews topNews = mTopnews.get(position);
					
					tv_topnews_title.setText(topNews.title);
					
				}
				
				@Override
				public void onPageScrolled(int position, float positionOffset,
						int positionOffsetPixels) {
					
				}
				
				@Override
				public void onPageScrollStateChanged(int state) {
					
				}
			});
			
			tv_topnews_title.setText(mTopnews.get(0).title);
			
			cpi_circle.onPageSelected(0);// 默认让第一个选中
			
		}
		
		mNewslist = newsTabBean.data.news;
		
		if (mNewslist != null) {
			
			mNewsAdapter = new NewsAdapter();
			
			lv_list_news.setAdapter(mNewsAdapter);
			
			
		}
		
	}
	
	class TopNewsAdapter extends PagerAdapter {
		
		private BitmapUtils mBitmapUtils;
		
		public TopNewsAdapter() {
			
			mBitmapUtils = new BitmapUtils(mActivity);
			
			mBitmapUtils
					.configDefaultLoadingImage(R.drawable.topnews_item_default);
		}
		
		@Override
		public int getCount() {
			
			return mTopnews.size();
		}
		
		@Override
		public boolean isViewFromObject(View view, Object object) {
			
			return view == object;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			
			ImageView view = new ImageView(mActivity);
			
			// view.setImageResource(R.drawable.topnews_item_default);
			
			view.setScaleType(ScaleType.FIT_XY);// 设置图片缩放方式，宽高填充父控件
			String imageUrl = mTopnews.get(position).topimage;
			
			mBitmapUtils.display(view, imageUrl);
			
			container.addView(view);
			
			return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			
			container.removeView((View) object);
			
		}
	}
	
	class NewsAdapter extends BaseAdapter {
		
		private BitmapUtils mBitmapUtils;
		
		public NewsAdapter() {
			
			mBitmapUtils = new BitmapUtils(mActivity);
			
			mBitmapUtils.configDefaultLoadingImage(R.drawable.news_pic_default);
		}
		
		@Override
		public int getCount() {
			
			return mNewslist.size();
		}
		
		@Override
		public NewsData getItem(int position) {
			
			return mNewslist.get(position);
		}
		
		@Override
		public long getItemId(int position) {
			
			return position;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			ViewHolder holder;
			if (convertView == null) {
				
				convertView = View.inflate(mActivity, R.layout.list_item_news,
						null);
				
				holder = new ViewHolder();
				
				holder.ivIcon = (ImageView) convertView
						.findViewById(R.id.iv_icon);
				holder.tvTitle = (TextView) convertView
						.findViewById(R.id.tv_news_title);
				holder.tvDate = (TextView) convertView
						.findViewById(R.id.tv_date);
				
				convertView.setTag(holder);
			} else {
				
				holder = (ViewHolder) convertView.getTag();
			}
			
			NewsData news = getItem(position);
			
			holder.tvTitle.setText(news.title);
			holder.tvDate.setText(news.pubdate);
			
			mBitmapUtils.display(holder.ivIcon, news.listimage);
			
			return convertView;
		}
		
	}
	
	static class ViewHolder {
		
		public ImageView ivIcon;
		public TextView tvTitle;
		public TextView tvDate;
		
	}
	
}
