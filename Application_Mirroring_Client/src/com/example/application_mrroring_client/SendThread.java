package com.example.application_mrroring_client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SendThread extends Thread{
	private OutputStream mOutputStream;
	private DataOutputStream dos;
		
	public SendThread(OutputStream out){
		mOutputStream = out;
	}
	
	public void run(){
		if(MainActivity.flag == 1){
			try{
				dos = new DataOutputStream(mOutputStream);
				String str = "1";
				dos.write(str.getBytes());
				dos.flush();
				dos.close();
				
				MainActivity.flag = 0;
			} catch(IOException e){
				e.printStackTrace();
			} catch(NullPointerException e){
				e.printStackTrace();
			}
		}
	}

}
