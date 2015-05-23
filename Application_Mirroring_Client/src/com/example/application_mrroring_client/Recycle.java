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
		// ����μ��� BitmapDrawable �̿��� drawable�鿡 ���� ��������
		// �޸� ������ �Ұ����ϴ�.

		d.setCallback(null);
	}
}
