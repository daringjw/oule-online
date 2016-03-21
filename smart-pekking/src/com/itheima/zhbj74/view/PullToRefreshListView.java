package com.itheima.zhbj74.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.R.integer;
import android.content.Context;
import android.opengl.Visibility;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.itheima.zhbj74.R;

/**
 * @author 官珺伟
 * 
 *         下拉刷新的自定义控件 2016-3-21 上午11:35:09
 */
public class PullToRefreshListView extends ListView {
	
	private static final int STATE_PULL_TO_REFRESH = 1;
	private static final int STATE_RELEASE_TO_REFRESH = 2;
	private static final int STATE_REFRESHING = 3;
	
	private int mCurrentState = STATE_PULL_TO_REFRESH;
	
	private View mHeaderView;
	private int mHeaderViewHeight;
	private int mStartY = -1;
	private int mEndY;
	private TextView mTv_pull_to_refresh;
	private TextView mTv_time;
	private ImageView mIv_arrow;
	private RotateAnimation mAnimUp;
	private RotateAnimation mAnimDown;
	private ProgressBar mPbProgress;
	
	public PullToRefreshListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		initHeaderView();
	}
	
	public PullToRefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initHeaderView();
	}
	
	public PullToRefreshListView(Context context) {
		super(context);
		initHeaderView();
	}
	
	/**
	 * 
	 */
	private void initHeaderView() {
		
		mHeaderView = View
				.inflate(getContext(), R.layout.pull_to_refresh, null);
		
		this.addHeaderView(mHeaderView);
		
		mTv_pull_to_refresh = (TextView) mHeaderView
				.findViewById(R.id.tv_pull_to_refresh);
		mTv_time = (TextView) mHeaderView.findViewById(R.id.tv_time);
		mIv_arrow = (ImageView) mHeaderView.findViewById(R.id.iv_arrow);
		
		mHeaderView.measure(0, 0);
		mHeaderViewHeight = mHeaderView.getMeasuredHeight();
		
		mPbProgress = (ProgressBar) mHeaderView.findViewById(R.id.pb_loading);
		
		// 隐藏头布局
		mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
		
		initAnim();
		
		setCurrentTime();
	}
	
	/**
	 * 设置刷新时间
	 */
	public void setCurrentTime() {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(new Date());
		
		mTv_time.setText(time);
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		
			case MotionEvent.ACTION_DOWN:
				
				mStartY = (int) ev.getY();
				
				break;
			
			case MotionEvent.ACTION_MOVE:
				
				if (mStartY == -1) {
					// 当用户按住头条新闻的viewpager进行下拉时，action_down会被viewpager消费掉，导致mstartY
					// 没有被赋值，这里需要重新赋值一下。
					mStartY = (int) ev.getY();
					
				}
				
				if (mCurrentState == STATE_REFRESHING) {
					
					break;
				}
				
				mEndY = (int) ev.getY();
				
				int dy = mEndY - mStartY;
				
				int firstVisiblePosition = getFirstVisiblePosition();
				if (dy > 0 && firstVisiblePosition == 0) {
					
					int padding = dy - mHeaderViewHeight;// 计算当前下拉控件的padding 值
					
					mHeaderView.setPadding(0, padding, 0, 0);
					
					if (padding > 0
							&& mCurrentState != STATE_RELEASE_TO_REFRESH) {
						// 改为松开刷新
						mCurrentState = STATE_RELEASE_TO_REFRESH;
						refreshState();
						
					} else if (padding < 0
							&& mCurrentState != STATE_PULL_TO_REFRESH) {
						
						// 改为下拉刷新
						mCurrentState = STATE_PULL_TO_REFRESH;
						
						refreshState();
						
					}
					
					return true;
				}
				
				break;
			
			case MotionEvent.ACTION_UP:
				
				mStartY = -1;
				
				if (mCurrentState == STATE_RELEASE_TO_REFRESH) {
					mCurrentState = STATE_REFRESHING;
					refreshState();
					
					// 完整展示头布局
					mHeaderView.setPadding(0, 0, 0, 0);
					
					// 4,进行回调
					if (mListener != null) {
						
						mListener.onRefresh();
					}
					
				} else if (mCurrentState == STATE_PULL_TO_REFRESH) {
					
					// 隐藏头布局
					mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
					
				}
				
				break;
			
			default:
				break;
		}
		
		return super.onTouchEvent(ev);
		
	}
	
	/**
	 * 初始化箭头动画
	 */
	private void initAnim() {
		
		mAnimUp = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		
		mAnimUp.setDuration(200);
		mAnimUp.setFillAfter(true);
		
		mAnimDown = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		
		mAnimDown.setDuration(200);
		mAnimDown.setFillAfter(true);
		
	}
	
	private void refreshState() {
		
		switch (mCurrentState) {
		
			case STATE_PULL_TO_REFRESH:
				
				mTv_pull_to_refresh.setText("下拉刷新");
				mPbProgress.setVisibility(View.INVISIBLE);
				mIv_arrow.setVisibility(View.VISIBLE);
				
				mIv_arrow.startAnimation(mAnimDown);
				
				break;
			case STATE_RELEASE_TO_REFRESH:
				
				mTv_pull_to_refresh.setText("松开刷新");
				
				mPbProgress.setVisibility(View.INVISIBLE);
				mIv_arrow.setVisibility(View.VISIBLE);
				
				mIv_arrow.startAnimation(mAnimUp);
				
				break;
			case STATE_REFRESHING:
				
				mTv_pull_to_refresh.setText("正在刷新。。。");
				
				mIv_arrow.clearAnimation();// 清除动画，否则无法隐藏
				
				mPbProgress.setVisibility(View.VISIBLE);
				
				mIv_arrow.setVisibility(View.INVISIBLE);
				
				break;
			
			default:
				break;
		}
	}
	
	/**
	 * 刷新结束，收起控件
	 */
	public void onRefreshComplete(boolean success) {
		
		mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
		
		mCurrentState = STATE_PULL_TO_REFRESH;
		mTv_pull_to_refresh.setText("下拉刷新");
		mPbProgress.setVisibility(View.INVISIBLE);
		mIv_arrow.setVisibility(View.VISIBLE);
		
		if (success) {
			setCurrentTime();
		}
		
	}
	
	public OnRefreshListener mListener;
	
	/**
	 * 2,暴露接口，设置监听
	 */
	public void setOnRefreshListener(OnRefreshListener listener) {
		
		mListener = listener;
	}
	
	/**
	 * @author 官珺伟
	 * 
	 *         2016-3-21 下午6:53:13
	 * 
	 *         下拉刷新的回调接口
	 */
	public interface OnRefreshListener {
		
		public void onRefresh();
		
	}
}
