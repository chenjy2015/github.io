package com.example.view;

import com.example.activity.R;
import com.example.util.AndroidSystemHelper;

import android.app.Activity;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

public class CustomPopupWindow extends PopupWindow {

	public Activity mContext;
	public View mParentView;
	public View mLayout;
	public String text;

	public CustomPopupWindow(Activity context, String text, View layout) {
		this.mContext = context;
		this.text = text;
		this.mLayout = layout;
		init();
	}

	public void init() {
		mParentView = LayoutInflater.from(mContext).inflate(
				R.layout.item_popup, null);
		TextView tv = (TextView) mParentView.findViewById(R.id.id_text);
		tv.setText(text);
		setContentView(mParentView);
		setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
		setHeight(AndroidSystemHelper.dp2px(35, mContext));
	}

	public void show(String showType) {
		int[] location = new int[2];
		mParentView.getLocationOnScreen(location);

		Paint pFont = new Paint();
		Rect rect = new Rect();
		pFont.getTextBounds(text, 0, text.length(), rect);
		int w = AndroidSystemHelper.dp2px(rect.width(), mContext);
		int h = AndroidSystemHelper.dp2px(rect.height(), mContext);

		int width = mParentView.getWidth();
		int height = mParentView.getHeight();

		if (showType.equals("left")) {
			// 在控件右边
			showAtLocation(mLayout, Gravity.NO_GRAVITY,
					AndroidSystemHelper.getScreenWidth(mContext) + (width - w)
							- (w / 3), location[1] + (height / 6));
		} else if (showType.endsWith("top")) {
			// 设置SelectPicPopupWindow弹出窗体动画效果
			setAnimationStyle(R.style.Animtop);
			showAtLocation(mLayout, Gravity.TOP, 0,
					AndroidSystemHelper.dp2px(35 / 2, mContext));
		}
		mHandler.sendEmptyMessageDelayed(0, 2000);
	}

	public Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			dismiss();
		};
	};

}
