package com.example.application_mrroring_client;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

public class ImageTask extends AsyncTask<Integer, Void, Bitmap>{
	static long temp = 0, fps = 0;
	private ImageView mIV;	
	
	@Override
	protected void onPostExecute(Bitmap result){
		long time = System.currentTimeMillis();
		mIV.setImageBitmap(TaskThread.mPhotoBitmap);
		mIV.invalidate();
		
		if((time - temp) != 0)
			fps = 1000 / (time - temp);
		
		temp = time;
	}
	
	@Override
	protected Bitmap doInBackground(Integer... params) {
		// TODO Auto-generated method stub
		return null;
	}
	
}



