package com.example.activity;

import com.example.util.PopupWindowUtils;
import com.example.util.ToastUtils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity implements OnClickListener {

	Button btn, btn2, btn3, btn4, btn5;
	EditText mEdit;
	ViewStub vs;
	LinearLayout mLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mLayout = (LinearLayout) findViewById(R.id.id_layout);
		vs = (ViewStub) findViewById(R.id.viewStub);
		mEdit = (EditText) findViewById(R.id.id_edit);
		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(this);
		btn2 = (Button) findViewById(R.id.btn2);
		btn2.setOnClickListener(this);
		btn3 = (Button) findViewById(R.id.btn3);
		btn3.setOnClickListener(this);
		btn4 = (Button) findViewById(R.id.btn4);
		btn4.setOnClickListener(this);
		btn5 = (Button) findViewById(R.id.btn5);
		btn5.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn:// ��ʾ���ص�ViewStub

			// if(vs != null){
			// View inflatedView = vs.inflate();//��̬����xml�����趨�õĲ���
			// EditText mEditNum = (EditText)
			// inflatedView.findViewById(R.id.number);
			// EditText mEditUserName = (EditText)
			// inflatedView.findViewById(R.id.username);
			// EditText mEditPassWord = (EditText)
			// inflatedView.findViewById(R.id.password);
			// }
			
			//实验代码 弹出吐司
			PopupWindowUtils.showPopupWindow(this,"请输入.............................", mLayout);
			//ToastUtils.showToastByViewOffset(MainActivity.this, btn, "请输入正确值");
			break;

		case R.id.btn2:
//			Intent intent = new Intent();
//			intent.setClass(getApplicationContext(), RecyclerViewActivity.class);
//			startActivity(intent);
			//ToastUtils.showToastByViewOffset(MainActivity.this, btn2, "请输入正确值");
			PopupWindowUtils.dismiss();
			break;

		case R.id.btn3:
//			startActivity(new Intent(getApplicationContext(),
//					XMLParseActivity.class));
			ToastUtils.showToastByViewOffset(MainActivity.this, btn3, "请输入正确值");
			break;
		case R.id.btn4:
//			startActivity(new Intent(getApplicationContext(),
//					HorizontalScrollViewActivity.class));
			ToastUtils.showToastByViewOffset(MainActivity.this, btn4, "请输入正确值");
			break;
		case R.id.btn5:
//			startActivity(new Intent(getApplicationContext(),
//					AlarmActivity.class));
			ToastUtils.showToastByViewOffset(MainActivity.this, btn5, "请输入正确值");
			break;
		}
	}

}
