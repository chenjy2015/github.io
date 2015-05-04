package com.example.activity;

import com.example.adapter.RecyclerAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.Window;
import android.widget.LinearLayout;

public class RecyclerViewActivity extends Activity {

	private LinearLayout layout;
	private RecyclerView mRecyclerView,mRecyclerView2;
	private RecyclerAdapter adapter;
//	private String[] data;
//	private SparseArray<String> data;
	private SparseArray<String> data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_recycler);
		layout = (LinearLayout) findViewById(R.id.layout);
		mRecyclerView = (RecyclerView) findViewById(R.id.rv);
		mRecyclerView2 = (RecyclerView) findViewById(R.id.rv2);
		mRecyclerView.setHasFixedSize(true);
		mRecyclerView2.setHasFixedSize(true);
		
		//创建布局管理器
		LinearLayoutManager manager = new LinearLayoutManager(this);
		manager.setOrientation(LinearLayoutManager.HORIZONTAL);
		//设置布局管理器
		mRecyclerView.setLayoutManager(manager);
		//设置布局添加或删除时动画
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		
		initData();
		adapter = new RecyclerAdapter(data);
		mRecyclerView.setAdapter(adapter);
		
		/**第二个RecyclerView*/
		LinearLayoutManager manager2 = new LinearLayoutManager(this);
		manager2.setOrientation(LinearLayoutManager.VERTICAL);
		mRecyclerView2.setLayoutManager(manager2);
		mRecyclerView2.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView2.setAdapter(adapter);
		
	}
	
//	public void initData(){
//		data = new String[50];
//		for(int i=0;i<data.length;i++){
//			data[i] = "item" + i;
//		}
//	}
	
//	public void initData(){
//		data = new SparseArray<String>();
//		for(int i=0;i<50;i++){
//			data.append(i, "item"+i);
//		}
//	}
	
	@SuppressLint("UseSparseArrays")
	public void initData(){
		data = new SparseArray<String>();
		data.append(0, R.drawable.ic_pause_playcontrol_focussed+"");
		data.append(1, R.drawable.app_icon_quantum_card+"");
		data.append(2, R.drawable.card_background_default+"");
		data.append(3, R.drawable.default_background+"");
		data.append(4, R.drawable.details_img+"");
		data.append(5, R.drawable.grid_bg+"");
		data.append(6, R.drawable.ic_pause_playcontrol_normal+"");
		data.append(7, R.drawable.ic_play_playcontrol_pressed+"");
	}
}
