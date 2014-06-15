package com.xieon.utility;

import android.graphics.drawable.Drawable;
import android.text.Html.ImageGetter;

import com.xieon.main.BaseActivity;
import com.xieon.main.R;

public class ImageGetterUtility implements ImageGetter {
	BaseActivity baseActivity;

	public Drawable getDrawable(String source) {
		Drawable d = null;
		if ("shyam".equals(source)) {
			d = baseActivity.getResources().getDrawable(R.drawable.key);
		} else {
			d = baseActivity.getResources().getDrawable(R.drawable.home);
		}

		d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
		return d;
	}

	public static ImageGetterUtility getImageGetterUtility(
			BaseActivity baseActivity) {
		ImageGetterUtility img = new ImageGetterUtility();
		img.baseActivity = baseActivity;
		return img;
	}

}
