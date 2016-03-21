package com.itheima.zhbj74.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.itheima.zhbj74.R;
import com.itheima.zhbj74.utils.PrefUtils;

public class SplashActivity extends Activity {

    private RelativeLayout mRl_root;
	private AnimationSet mAnimationSet;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        initView();
        
        initAnimation();
        
        initEvent();
        
        
        
    }

	
	private void initEvent() {
		
		mAnimationSet.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			
			@Override
			public void onAnimationEnd(Animation animation) {
				
				//如果是第一次进入进入新手引导
				
				boolean  isFirstEnter= PrefUtils.getBoolean(SplashActivity.this, "is_first_enter", true);
				
				
				Intent intent;
				
				if (isFirstEnter) {
					//新手引导
					 intent=new Intent(getApplicationContext(),GuideActivity.class);
					
					 
					
				}else {
					
					//主界面
					
					intent=new Intent(getApplicationContext(), MainActivity.class);
				}
				
				startActivity(intent);
				
				finish();
			}
		});
		
	}

	private void initAnimation() {
		
		RotateAnimation rotateAnimation=new RotateAnimation(
        		0, 360,
        		Animation.RELATIVE_TO_SELF, 0.5f,
        		Animation.RELATIVE_TO_SELF, 0.5f);
        
        rotateAnimation.setDuration(1000);
        rotateAnimation.setFillAfter(true);
        
        
        ScaleAnimation scaleAnimation=new ScaleAnimation(
        		0, 1, 
        		0, 1, 
        		Animation.RELATIVE_TO_SELF, 0.5f,
        		Animation.RELATIVE_TO_SELF, 0.5f);
        
        scaleAnimation.setDuration(1000);
        scaleAnimation.setFillAfter(true);
        
        AlphaAnimation alphaAnimation=new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);
        
        mAnimationSet = new AnimationSet(false);
        
        mAnimationSet.addAnimation(rotateAnimation);
        mAnimationSet.addAnimation(scaleAnimation);
        mAnimationSet.addAnimation(alphaAnimation);
        
        mRl_root.startAnimation(mAnimationSet);
	}

	private void initView() {
		
		mRl_root = (RelativeLayout) findViewById(R.id.rl_root);
	}


    
    
}
