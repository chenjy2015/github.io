package com.example.appliction;

public class Common {

	/** ImageLoader 加载不同路径图片对应的示例或前缀 */
	final public static String loaderfromWeb = "http://";
	final public static String loaderfromSDCard = "file:///";
	final public static String loaderfromProvider = "content://media/external/audio/albumart/13";
	final public static String loaderfromAssets = "assets://image.png";
	final public static String loaderfromDrawables = "drawable://";
	final public static int loaderBySDCard = 1010;// 标识url路径来源与本地
	final public static int loaderBySDCardAndNoCache = 1011;// 标识url路径来源与本地
															// 并且不需要缓存到内存中
															// （注：在图片方向有或其他属性随时会所改变的时候使用
															// ）
	final public static int loaderByWeb = 1012;// 标识url路径来源与网络
	/** ImageLoader */

}
