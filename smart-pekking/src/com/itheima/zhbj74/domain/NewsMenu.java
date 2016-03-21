package com.itheima.zhbj74.domain;

import java.util.ArrayList;

import android.R.integer;

/**
 * @author 官珺伟
 *
 *使用json 逢{} 创建对象 ，逢 【】 创建集合arraylist 
 *
 * 2016-3-19 下午2:07:24
 */
public class NewsMenu {
	
	public int retcode;
	
	public ArrayList<Integer> extend;
	
	public ArrayList<NewsMenuData>  data;
	
	
	public class NewsMenuData{
		
		public int id;		
		public String title;		
		public int type;
		
		public ArrayList<NewsTabData> children;

		@Override
		public String toString() {
			return "NewsMenuData [id=" + id + ", title=" + title + ", type="
					+ type + "]";
		}
		
		
	}
	
	public class NewsTabData{
		
		public int id;		
		public String title;		
		public int type;
		public String url;
		@Override
		public String toString() {
			return "NewsTabData [id=" + id + ", title=" + title + ", type="
					+ type + ", url=" + url + "]";
		}
		
		
		
	}

	@Override
	public String toString() {
		return "NewsMenu [retcode=" + retcode + ", extend=" + extend
				+ ", data=" + data + "]";
	}
	
	
	
 }
