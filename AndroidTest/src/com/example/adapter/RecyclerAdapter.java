package com.example.adapter;

import com.example.activity.R;
import com.example.appliction.AndroidTestApplication;
import com.example.appliction.Common;
import com.nostra13.universalimageloader.core.ImageLoader;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class RecyclerAdapter extends
		RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

	// private String[] data;
	private SparseArray<String> data;
	public static final String TAG = "RecyclerAdapter";
	private int index;

	public RecyclerAdapter(SparseArray<String> data) {
		this.data = data;
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return data.size();
	}
	
	public void addItem(int position){
		int size = data.size();
		data.append(size+position, R.drawable.videos_by_google_banner+"");
		notifyItemInserted(position);
		notifyDataSetChanged();
		Log.e(TAG, "data is append Item the Position:"+size+position);
	}
	
	public void removeItem(int position){
		data.removeAt(position);
		notifyItemRemoved(position);
		notifyDataSetChanged();
		Log.e(TAG, "data is remove Item the Position:"+position);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		// TODO Auto-generated method stub
		index = position;
		viewHolder.mTextView.setText("item"+position);
		ImageLoader.getInstance().displayImage(
				Common.loaderfromDrawables + data.get(position),
				viewHolder.mImageView,
				AndroidTestApplication.getOptions(Common.loaderBySDCard));
		viewHolder.mImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(index %2 == 0){
					addItem(index);
				}else{
					removeItem(index);
				}
			}
		});
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int  viewType) {
		// TODO Auto-generated method stub
		// View view = View.inflate(viewGroup.getContext(),
		// android.R.layout.simple_expandable_list_item_1, null);
		View view = View.inflate(viewGroup.getContext(),
				R.layout.item_recycleradapter, null);
		ViewHolder viewHolder = new ViewHolder(view);
				return viewHolder;
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		public TextView mTextView;
		public ImageView mImageView;

		public ViewHolder(View itemView) {
			super(itemView);
			// TODO Auto-generated constructor stub
			mImageView = (ImageView) itemView.findViewById(R.id.iv);
			mTextView = (TextView) itemView.findViewById(R.id.tv);
			mImageView.setClickable(true);
			mTextView.setClickable(true);// 设置TextView可点击
		}
	}

}
