package com.example.util;

import com.example.activity.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ToastUtils {

	private static Toast toast = null;

	public static void showToastByViewOffset(Activity context, View view,
			String text) {
		if (toast == null) {
			toast = new Toast(context);
		} else {
			toast.cancel();
			toast = new Toast(context);
		}

		int[] location = new int[2];
		view.getLocationOnScreen(location);

		Paint pFont = new Paint();
		Rect rect = new Rect();
		pFont.getTextBounds(text, 0, text.length(), rect);
		int w = AndroidSystemHelper.dp2px(rect.width(), context);
		int h = AndroidSystemHelper.dp2px(rect.height(), context);

		Log.d("MainActivity", " height: " + h + "   width: " + w);

		int width = view.getWidth();
		int height = view.getHeight();

		// 默认屏幕中间位置
		int screenHeight = AndroidSystemHelper.getScreenHeight(context) / 2;
		toast.setGravity(Gravity.NO_GRAVITY,
				AndroidSystemHelper.getScreenWidth(context) + (width - w)
						- (w),
				-(screenHeight - (location[1] + (height / 6))));

		View parentView = LayoutInflater.from(context).inflate(R.layout.item_popup, null);
		TextView tv = (TextView) parentView.findViewById(R.id.id_text);
		tv.setText(text);
		toast.setView(parentView);
		toast.setDuration(2000);
		toast.setMargin(AndroidSystemHelper.dp2px(5, context), 0);
		toast.show();
	}
}
