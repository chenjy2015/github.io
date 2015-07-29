package com.example.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.activity.MainActivity;
import com.example.activity.R;

public class NotifyUtils {

	private static NotificationManager mNotifyManager;
	private static Notification mNotification;
	private static Intent mIntent; //要跳转的动作
	private static PendingIntent mPendingIntent;//显示通知栏具体信息
	private static Context mContext;
	private static NotifyUtils mInstance;
    private static final int ID = 1; 

	private NotifyUtils() {

	}

	public static NotifyUtils getInstance(Context context) {
		mContext = context;
		init();
		return mInstance;
	}

	private static void init() {
		if (mInstance == null) {
			mInstance = new NotifyUtils();
			mNotifyManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
			initIntent();
		}
	}

	public void notifySend(String ticker, int when){
		initNotify(ticker, when);
		mNotifyManager.notify(ID, mNotification);
	}
	
	
	public void initNotify(String ticker, int when){
		mNotification = new Notification();
		mNotification.icon = R.drawable.ic_launcher;
		mNotification.tickerText = ticker;
		mNotification.when = when;
		mNotification.flags |= Notification.FLAG_AUTO_CANCEL; // 点击清除按钮或点击通知后会自动消失  
		mNotification.defaults = Notification.DEFAULT_SOUND; // 调用系统自带声音  
		mNotification.defaults = Notification.DEFAULT_VIBRATE;// 设置默认震动  
		mNotification.defaults = Notification.DEFAULT_ALL; // 设置铃声震动  
		//说明：要是在Notification中加入图标，在状态栏和状态条中显示图标一定要用这个方法，否则报错！
		mNotification.setLatestEventInfo(mContext, "提醒事项", ticker, mPendingIntent);
	}
	
	private static void initIntent(){
		mIntent = new Intent();
		mIntent.setClass(mContext, MainActivity.class);
		mPendingIntent = PendingIntent.getActivity(mContext, 0, mIntent, 0);
	}
}
