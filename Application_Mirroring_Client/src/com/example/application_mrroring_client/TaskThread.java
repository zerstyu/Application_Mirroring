package com.example.application_mrroring_client;

import java.io.InputStream;

import android.graphics.Bitmap;

public class TaskThread extends Thread {
	static Bitmap mPhotoBitmap;
	
	private InputStream mInputStream;
	private Convert mConvert;
		
	public TaskThread (InputStream in){
		mInputStream = in;
		mConvert = new Convert();
	}
	
	public void run(){
		mPhotoBitmap = mConvert.byteArrayToBitmap(mConvert.inputStreamToByteArray(mInputStream));
	}

}
