package com.example.appliction;

/**
 * @author：陈家有
 * @Time：2014-11-20
 * @ProjectName：CloudSurveyForGov
 * @Company：房讯通科技有限公司
 * @Description：初始化类 universal
 */

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
//import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

public class Init {

	private static final String fileCachePath = Environment
			.getExternalStorageDirectory() + "/CloudSurveyHybird/";
	private static Init init;

	private Init() {

	}

	public static Init getInstance() {
		if (init == null) {
			init = new Init();
		}
		return init;
	}
	
	public File getFile(){
		File file = new File(fileCachePath);///mnt/sdcard/GovCacheImage
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}

	/**
	 * 初始化Universal配置信息
	 * 如果安装了SD卡指定缓存路径 否则为默认
	 * */
	@SuppressWarnings("deprecation")
	public ImageLoaderConfiguration initUniversal(Context context) {
		ImageLoaderConfiguration config;
		if(getFile() != null){
			config = initHashMounted(context);
		}else{
			config = initDefault(context);
		}
		ImageLoader.getInstance().init(config);// 全局初始化此配置
		return config;
	}
	
	/**
	 * 当安装了SDCrad时缓存到指定路径中
	 * */
	public ImageLoaderConfiguration initHashMounted(Context context){
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				.memoryCacheExtraOptions(480, 800)
				// default = device screen dimensions
				.diskCacheExtraOptions(480, 800, null)
				// .taskExecutor(...)
				// .taskExecutorForCachedImages(...)
				.threadPoolSize(3)
				// 线程池数量
				.threadPriority(Thread.NORM_PRIORITY - 2)
				// default
				.tasksProcessingOrder(QueueProcessingType.FIFO)
				// default
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new LruMemoryCache(2 * 1024 * 1024))
				.memoryCacheSize(2 * 1024 * 1024)
				.memoryCacheSizePercentage(13)
//				.diskCache(new UnlimitedDiscCache(getFile())) // 指定缓存路径
				.diskCacheSize(50 * 1024 * 1024).diskCacheFileCount(100)
				.diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
				.imageDownloader(new BaseImageDownloader(context)) // default
				// .imageDecoder(new BaseImageDecoder()) // Bitmap编码格式 这里我不选择为默认
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
				.writeDebugLogs().build();
		return config;
	}
	
	/**
	 * 如果没有安装SDCrad 缓存路径为默认
	 * */
	public ImageLoaderConfiguration initDefault(Context context){
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				.memoryCacheExtraOptions(480, 800)
				// default = device screen dimensions
				.diskCacheExtraOptions(480, 800, null)
				// .taskExecutor(...)
				// .taskExecutorForCachedImages(...)
				.threadPoolSize(3)
				// 线程池数量
				.threadPriority(Thread.NORM_PRIORITY - 2)
				// default
				.tasksProcessingOrder(QueueProcessingType.FIFO)
				// default
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new LruMemoryCache(2 * 1024 * 1024))
				.memoryCacheSize(2 * 1024 * 1024)
				.memoryCacheSizePercentage(13)
				//.diskCache(new UnlimitedDiscCache(getFile())) // 指定缓存路径
				.diskCacheSize(50 * 1024 * 1024).diskCacheFileCount(100)
				.diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
				.imageDownloader(new BaseImageDownloader(context)) // default
				// .imageDecoder(new BaseImageDecoder()) // Bitmap编码格式 这里我不选择为默认
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
				.writeDebugLogs().build();
		return config;
	}

	/**
	 * 
	 * @param loaderBy
	 * @return Common.loaderBySDCard 从本地加载图片 并设置内存缓存
	 * @return Common.loaderBySDCardAndNoCache 从本地加载图片 不设置内存缓存
	 * @return Common.loaderByWeb 从网络加载图片 本地缓存以及内存缓存
	 * 
	 */
	public DisplayImageOptions ImageLoaderConfig(int loaderBy) {

		if (loaderBy == Common.loaderBySDCard) {
			return getOptionsBySdCard();
		} else if(loaderBy == Common.loaderBySDCardAndNoCache) {
			return getOptionsBySdCardAndNoCache();
		} else{
			return getOptionsByWeb();
			
		}
	}

	public DisplayImageOptions getOptionsBySdCard() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				//.showImageOnLoading(R.drawable.pic_loading) // 设置图片在下载期间显示的图片
				// .showImageOnFail(R.drawable.ic_launcher)
				// //设置图片加载/解码过程中错误时候显示的图片
				.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
				.cacheOnDisk(false)// 设置下载的图片是否缓存在SD卡中
									// cacheOnDisk).cacheOnDisc(true)
				.considerExifParams(false) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
				.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
				// .decodingOptions(android.graphics.BitmapFactory.Options
				// decodingOptions)//设置图片的解码配置
				// .delayBeforeLoading(int delayInMillis)//int
				// delayInMillis为你设置的下载前的延迟时间
				// 设置图片加入缓存前，对bitmap进行设置
				// .preProcessor(BitmapProcessor preProcessor)
				.resetViewBeforeLoading(false)// 设置图片在下载前是否重置，复位
				// .displayer(new RoundedBitmapDisplayer(10))//是否设置为圆角，弧度为多少
				//.displayer(new FadeInBitmapDisplayer(100))// 是否图片加载好后渐入的动画时间
				.build();// 构建完成
		return options;
	}

	public DisplayImageOptions getOptionsBySdCardAndNoCache() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				//.showImageOnLoading(R.drawable.pic_loading) // 设置图片在下载期间显示的图片
				// .showImageOnFail(R.drawable.ic_launcher)
				// //设置图片加载/解码过程中错误时候显示的图片
				.cacheInMemory(false)// 设置下载的图片是否缓存在内存中
				.cacheOnDisk(false)// 设置下载的图片是否缓存在SD卡中
									// cacheOnDisk).cacheOnDisc(true)
				.considerExifParams(false) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
				.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
				// .decodingOptions(android.graphics.BitmapFactory.Options
				// decodingOptions)//设置图片的解码配置
				// .delayBeforeLoading(int delayInMillis)//int
				// delayInMillis为你设置的下载前的延迟时间
				// 设置图片加入缓存前，对bitmap进行设置
				// .preProcessor(BitmapProcessor preProcessor)
				.resetViewBeforeLoading(false)// 设置图片在下载前是否重置，复位
				// .displayer(new RoundedBitmapDisplayer(10))//是否设置为圆角，弧度为多少
				//.displayer(new FadeInBitmapDisplayer(100))// 是否图片加载好后渐入的动画时间
				.build();// 构建完成
		return options;
	}
	
	public DisplayImageOptions getOptionsByWeb() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				// 设置图片在下载期间显示的图片
				// .showImageOnFail(R.drawable.ic_launcher)
				// //设置图片加载/解码过程中错误时候显示的图片
				.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
				.cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
				.considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
				.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
				// .decodingOptions(android.graphics.BitmapFactory.Options
				// decodingOptions)//设置图片的解码配置
				// .delayBeforeLoading(int delayInMillis)//int
				// delayInMillis为你设置的下载前的延迟时间
				// 设置图片加入缓存前，对bitmap进行设置
				// .preProcessor(BitmapProcessor preProcessor)
				.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
				// .displayer(new RoundedBitmapDisplayer(10))//是否设置为圆角，弧度为多少
				//.displayer(new FadeInBitmapDisplayer(300))// 是否图片加载好后渐入的动画时间
				.build();// 构建完成
		return options;
	}
}
