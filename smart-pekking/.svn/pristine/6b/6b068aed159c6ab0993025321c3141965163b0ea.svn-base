package com.itheima.zhbj74.utils;

import android.content.Context;

public class CacheUtils {
	
	/**
	 * 写缓存
	 * 
	 * @param url
	 * @param json
	 */
	public static void setCache(String url, String json, Context ctx) {
		
		PrefUtils.setString(ctx, url, json);
	}
	
	/**
	 * @param url
	 * @param ctx
	 * @return
	 * 
	 *         获取缓存
	 */
	public static String getCache(String url, Context ctx) {
		
		return PrefUtils.getString(ctx, url, null);
	}
	
}
