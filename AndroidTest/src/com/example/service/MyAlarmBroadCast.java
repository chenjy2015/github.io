package com.example.service;

import com.example.util.NotifyUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MyAlarmBroadCast extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Bundle extras = intent.getExtras();
		String alarmContent = extras.getString("alarmContent","δ���壡");
		NotifyUtils.getInstance(context).notifySend(alarmContent, 2000);
	}
} 