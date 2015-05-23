package com.example.application_mrroring_client;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Recycle {
	public static void recycleBitmap(ImageView iv) {
		Drawable d = iv.getDrawable();
		if (d instanceof BitmapDrawable) {
			Bitmap b = ((BitmapDrawable) d).getBitmap();
			b.recycle();
		}
		// 현재로서는 BitmapDrawable 이외의 drawable들에 대한 직접적인
		// 메모리 해제는 불가능하다.

		d.setCallback(null);
	}
}
