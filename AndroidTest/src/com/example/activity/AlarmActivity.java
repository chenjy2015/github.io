package com.example.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.util.AlarmUtils;


public class AlarmActivity extends Activity implements OnClickListener{
	private Button mSingle;
	private Button mRepeat;
	private Button mCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);
		mSingle = (Button) findViewById(R.id.single);
		mSingle.setOnClickListener(this);

		mRepeat = (Button) findViewById(R.id.repeat);
		mRepeat.setOnClickListener(this);

		mCancel = (Button) findViewById(R.id.cancel);
		mCancel.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.single:
			AlarmUtils.getInstance(AlarmActivity.this).alarmClock(
					"提醒时间到 5秒钟到了!", 1000 * 5);
			break;

		case R.id.repeat:
			AlarmUtils.getInstance(AlarmActivity.this).alarmClockRepeat(
					"代办事项提醒！", 5000, 2000);
			break;
			
		case R.id.cancel:
			AlarmUtils.getInstance(AlarmActivity.this).cancelAlarm();
			break;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		AlarmUtils.getInstance(AlarmActivity.this).cancelAlarm();
	}

}
