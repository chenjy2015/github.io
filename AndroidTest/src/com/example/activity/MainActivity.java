package com.example.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{

	Button btn,btn2,btn3,btn4;
	ViewStub vs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		vs = (ViewStub) findViewById(R.id.viewStub);
		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(this);
		btn2 = (Button) findViewById(R.id.btn2);
		btn2.setOnClickListener(this);
		btn3 = (Button) findViewById(R.id.btn3);
		btn3.setOnClickListener(this);
		btn4 = (Button) findViewById(R.id.btn4);
		btn4.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn://��ʾ���ص�ViewStub 
			
			if(vs != null){
				View inflatedView  = vs.inflate();//��̬����xml�����趨�õĲ���
				EditText mEditNum = (EditText) inflatedView.findViewById(R.id.number);
				EditText mEditUserName = (EditText) inflatedView.findViewById(R.id.username);
				EditText mEditPassWord = (EditText) inflatedView.findViewById(R.id.password);
			}
			break;

		case R.id.btn2:
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), RecyclerViewActivity.class);
			startActivity(intent);
			break;
			
		case R.id.btn3:
			startActivity(new Intent(getApplicationContext(), XMLParseActivity.class));
			break;
		case R.id.btn4:
			startActivity(new Intent(getApplicationContext(), HorizontialListViewActivity.class));
			break;
		}
	}
}
