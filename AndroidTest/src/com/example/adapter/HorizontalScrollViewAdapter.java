package com.example.adapter;

import java.util.List;

import com.example.activity.R;
import com.example.util.ImageLoaderUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sun.imageio.plugins.common.ImageUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class HorizontalScrollViewAdapter {

	@SuppressWarnings("unused")
	private Context mContext;
	private LayoutInflater mInflater;
	private List<Integer> mDatas;
	private ImageLoader mImageLoader;
	//private ImageLoaderUtils mImageLoader;

	public HorizontalScrollViewAdapter(Context context, List<Integer> mDatas) {
		this.mContext = context;
		mInflater = LayoutInflater.from(context);
		this.mDatas = mDatas;
		//mImageLoader = ImageLoaderUtils.getInstance(mContext);
		mImageLoader = ImageLoader.getInstance();
	}

	public int getCount() {
		return mDatas.size();
	}

	public Object getItem(int position) {
		return mDatas.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(
					R.layout.activity_index_gallery_item, parent, false);
			viewHolder.mImg = (ImageView) convertView
					.findViewById(R.id.id_index_gallery_item_image);
			viewHolder.mText = (TextView) convertView
					.findViewById(R.id.id_index_gallery_item_text);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.mText.setText("some info ");
		String url = "drawable://"+ mDatas.get(position);
		mImageLoader.displayImage(url, viewHolder.mImg);

		return convertView;
	}

	private class ViewHolder {
		ImageView mImg;
		TextView mText;
	}

}