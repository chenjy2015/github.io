package com.example.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.activity.MainActivity;
import com.example.activity.R;
import com.example.view.CustomPopupWindow;

public class PopupWindowUtils {

	private static CustomPopupWindow puCustomPopupWindow;

	public static void showPopupWindow(Activity activity, String text, View view) {
		puCustomPopupWindow = new CustomPopupWindow(activity, text, view);
		puCustomPopupWindow.show("top");
	}

	public static void dismiss() {
		if (puCustomPopupWindow != null && puCustomPopupWindow.isShowing()) {
			puCustomPopupWindow.dismiss();
		}
	}
}
