package com.example.appliction;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Application;

public class AndroidTestApplication extends Application {

	private static ImageLoaderConfiguration config;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		/**
		 * initUniversal(this); 1.初始化universal配置信息 ImageLoaderConfig();
		 * 2.初始化universal加载图片时调用display方法的配置信息
		 * */
		config = Init.getInstance().initUniversal(this);
	}
	
	/**
	 * universal
	 * */
	public static  DisplayImageOptions getOptions(int loaderBy) {
		return Init.getInstance().ImageLoaderConfig(loaderBy);
	}

}
