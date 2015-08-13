package com.example.activity;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.adapter.HorizontalScrollViewAdapter;
import com.example.view.MyHorizontalScrollView;
import com.example.view.MyHorizontalScrollView.CurrentImageChangeListener;
import com.example.view.MyHorizontalScrollView.OnItemClickListener;
import com.example.view.zoomimage.ImageViewTouch;
import com.nostra13.universalimageloader.core.ImageLoader;

public class HorizontalScrollViewActivity extends Activity {

//	String imageUri = "http://site.com/image.png"; // 网络图片  
//	String imageUri = "file:///mnt/sdcard/image.png"; //SD卡图片  
//	String imageUri = "content://media/external/audio/albumart/13"; // 媒体文件夹  
//	String imageUri = "assets://image.png"; // assets  
//	String imageUri = "drawable://" + R.drawable.image; //  drawable文件   
	
	private MyHorizontalScrollView mHorizontalScrollView;
	private HorizontalScrollViewAdapter mAdapter;
	private ImageViewTouch mImg;
	private static final String LOADER_BY_INT_RESOURCE = "drawable://";

//	private ArrayList<Integer> mDatas = new ArrayList<Integer>(Arrays.asList(
//			R.drawable.p01, R.drawable.p02, R.drawable.p03, R.drawable.p04,
//			R.drawable.p05, R.drawable.p06, R.drawable.p07, R.drawable.p08,
//			R.drawable.p09,R.drawable.p10,R.drawable.p11));
	//实验代码
	private ArrayList<Integer> mDatas = new ArrayList<Integer>(Arrays.asList(
			R.drawable.p01));

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_horizontalscrollview);

		mImg = (ImageViewTouch) findViewById(R.id.id_content);
		mHorizontalScrollView = (MyHorizontalScrollView) findViewById(R.id.id_horizontalScrollView);
		mAdapter = new HorizontalScrollViewAdapter(this, mDatas);
		// 添加滚动回调
		mHorizontalScrollView
				.setCurrentImageChangeListener(new CurrentImageChangeListener() {
					@Override
					public void onCurrentImgChanged(int position,
							View viewIndicator) {
						setImageResource(mDatas.get(position), mImg);
						viewIndicator.setBackgroundColor(Color.parseColor("#AA024DA4"));
					}
				});
		// 添加点击回调
		mHorizontalScrollView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onClick(View view, int position) {
				//mImg.setImageResource(mDatas.get(position));
				setImageResource(mDatas.get(position), mImg);
				view.setBackgroundColor(Color.parseColor("#AA024DA4"));
			}
		});
		// 设置适配器
		mHorizontalScrollView.initDatas(mAdapter);
		setCurrentIndex(5);
	}
	
	//scrollview滑动到第几个元素
	public void setCurrentIndex(int count){
		int width = mHorizontalScrollView.getChildWidth();
		mHorizontalScrollView.fullScroll(ScrollView.FOCUS_RIGHT);
		mHorizontalScrollView.smoothScrollBy(width, 0);
	}

	
	private void setImageResource(int resource, ImageView imageAware){
		String url = LOADER_BY_INT_RESOURCE + resource;
		ImageLoader.getInstance().displayImage(url, imageAware);
	}

}
