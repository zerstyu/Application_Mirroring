package com.example.application_mirroring_server;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import android.graphics.Bitmap;
import android.util.Log;

public class ViewUpdateThread extends Thread {
	final int SocketServerPORT = 8260;

	private MainActivity mMainActivity = new MainActivity();
	private Bitmap2ByteArray mBitmap2ByteArray = new Bitmap2ByteArray();
	
	private InputStream inputStream;
	private OutputStream outputStream;
	private DataRecvThread mrecvThread;
	private FileInputStream fis;

	public ServerSocket serverSocket;
	private Bitmap tempBitmap;
	private String filesize = null;
	
	int count = 0;
	int tempNum = 1;


	public void run() {
		try {
			if (serverSocket == null) {
				serverSocket = new ServerSocket();
				serverSocket.setReuseAddress(true);
				serverSocket.bind(new InetSocketAddress(SocketServerPORT));
			}

			while (true) {
				Socket socket = serverSocket.accept();
				inputStream = socket.getInputStream();
				outputStream = socket.getOutputStream();

				try{
					DataOutputStream dos = new DataOutputStream(outputStream);
					
					if(MainActivity.flag1 == 0){
						byte[] byteArray = mBitmap2ByteArray.bitmapToByteArray(tempBitmap);
						filesize = String.valueOf(byteArray.length);
						String header = "0000000000".substring(0, 10-filesize.length()) + filesize;
						
						dos.write(header.getBytes());
						dos.write(byteArray);
						dos.flush();
						
						tempNum = 1;
					} else {
						String header = "0000099999";
						
						dos.write(header.getBytes());
						dos.flush();
						
						MainActivity.flag1 = 0;
					}
					
					mrecvThread = new DataRecvThread(inputStream);
					mrecvThread.start();
				} catch (IOException e){
					Log.e("Socket","Exception during write",e);
				}
				
				mMainActivity.check = 1;

				mMainActivity.dos = new DataOutputStream(outputStream);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}