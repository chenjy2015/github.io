package com.example.util;

import com.example.service.MyAlarmBroadCast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AlarmUtils {

	private static AlarmManager mAlarm;
	private static PendingIntent mSenderOne;// 一次性闹钟
	private static PendingIntent mSenderRepeat;// 重复性闹钟
	private static Context mContext;
	private static Intent mIntentOne;// 一次性闹钟
	private static Intent mIntentRepeat;// 重复性
	private static AlarmUtils mInstance;

	private AlarmUtils() {

	}

	public static AlarmUtils getInstance(Context context) {
		mContext = context;
		init();
		return mInstance;
	}
	
	private static void init(){
		if (mInstance == null) {
			mInstance = new AlarmUtils();
			mIntentOne = new Intent(mContext, MyAlarmBroadCast.class);
			mIntentRepeat = new Intent(mContext, MyAlarmBroadCast.class);
			mAlarm = (AlarmManager) mContext
					.getSystemService(Context.ALARM_SERVICE);
		}
	}

	/**
	 * 一次性闹钟
	 * 
	 * @param alarmContent
	 *            闹钟响后提示内容
	 * @param triggerAtMillis
	 *            开始时间距离当前时间差
	 */
	public void alarmClock(String alarmContent, long triggerAtMillis) {
		initPendingIntentOne(alarmContent);
		mAlarm.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
				+ triggerAtMillis, mSenderOne);
	}

	/**
	 * 一次性闹钟配置
	 * 
	 * @param alarmContent
	 */
	public void initPendingIntentOne(String alarmContent) {
		mIntentOne.setAction("com.intent.alarmservice");
		Bundle extras = new Bundle();
		extras.putString("alarmContent", alarmContent);
		mIntentOne.putExtras(extras);
		mSenderOne = PendingIntent.getBroadcast(mContext, 0, mIntentOne, 0);
	}

	/**
	 * 重复性闹钟
	 * 
	 * @param alarmContent
	 *            闹钟响后提示信息
	 * @param triggerAtMillis
	 *            闹钟开始时间距离当前时间差
	 * @param intervalMillis
	 *            闹钟间隔时间
	 */
	public void alarmClockRepeat(String alarmContent, long triggerAtMillis,
			long intervalMillis) {
		initPendingIntentRepeat(alarmContent);
		mAlarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
				+ triggerAtMillis, intervalMillis, mSenderRepeat);
	}

	/**
	 * 重复性闹钟配置
	 * 
	 * @param alarmContent
	 */
	public void initPendingIntentRepeat(String alarmContent) {
		mIntentRepeat.setAction("com.intent.alarmservice");
		Bundle extras = new Bundle();
		extras.putString("alarmContent", alarmContent);
		mIntentRepeat.putExtras(extras);
		mSenderRepeat = PendingIntent.getBroadcast(mContext, 0, mIntentRepeat,
				0);
	}

	/**
	 * AlarmManager的取消：（其中需要注意的是取消的Intent必须与启动Intent保持绝对一致才能支持取消AlarmManager）
	 */
	public void cancelAlarm() {
		mAlarm.cancel(mSenderOne);
		mAlarm.cancel(mSenderRepeat);
	}
}
