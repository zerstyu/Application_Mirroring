package com.example.application_mrroring_client;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import android.os.AsyncTask;
import android.util.Log;

public class MyClientTask extends AsyncTask<Void, Void, Void> {
	// Static
	static int bodysize= 0;
	static int tempBodysize = 0;
	
	// Class
	private MainActivity mMainActivity;
	private ImageTask mImageTask;
	private TaskThread mTaskThread;
	private SendThread mSendThread;

	// Variable
	private int dstPort;
	private String dstAddress;
	private String response = "";
	private String header;
	
	private InputStream inputStream;
	private OutputStream outputStream;
	private DataInputStream dis;
	

	MyClientTask(String addr, int port) {
		dstAddress = addr;
		dstPort = port;
		
		mMainActivity = new MainActivity();

	}

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub

		Socket socket = null;

		while (true) {
			try {

				socket = new Socket(dstAddress, dstPort);
				inputStream = socket.getInputStream();
				outputStream = socket.getOutputStream();

				byte[] buf = new byte[10];

				dis = new DataInputStream(inputStream);
				dis.read(buf, 0, 10);
				header = new String(buf, 0, 10);

				bodysize = 0;
				try {
					bodysize = Integer.parseInt(header);
					if (bodysize == 99999) {
						if (MainActivity.checknum == 0)
							MainActivity.checknum = 1;
						else
							MainActivity.checknum = 0;
						bodysize = tempBodysize;
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					dis.close();
				}

				mTaskThread = new TaskThread(inputStream);
				mTaskThread.start();

				mImageTask = new ImageTask();
				mImageTask.execute(1);

				mSendThread = new SendThread(outputStream);
				mSendThread.start();
				
			} catch (IOException e) {
				Log.e("Socket", "disconnected", e);
				
				try{
					socket.close();
					dis.close();
					inputStream.close();
					
				} catch(IOException e1){
					e1.printStackTrace();
				}
				break;
			}
		}

		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		mMainActivity.textResponse.setText(response);
		super.onPostExecute(result);
	}
}
