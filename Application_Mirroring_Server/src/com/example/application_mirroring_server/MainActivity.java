package com.example.application_mirroring_server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	// Class
	private ViewTask mViewTask;
	private ViewUpdateThread mViewUpdateThread;
	private IpAddress mIpAddress;
	private ScreenCapture mScreenCapture;
	// Widget
	private TextView infoip;
	private Button SendButton;

	// Variable
	static long temp = 0;
	static int flag1 = 0;
	static int width = 0, height = 0;

	public int check = 0;
	public int flag = 0;
	public byte[] tempArray = null;

	private String filesize = null;

	private View view;
	public ServerSocket serverSocket;
	public DataOutputStream dos;

	final Handler myhandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		infoip = (TextView) findViewById(R.id.infoip);

		SendButton = (Button) findViewById(R.id.sendbutton);
		SendButton.setOnClickListener(SendButtonOnClickListener);

		mIpAddress = new IpAddress();
		infoip.setText(mIpAddress.getIpAddress());

		mScreenCapture = new ScreenCapture();

		mViewTask = new ViewTask(this);
		addContentView(mViewTask, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT));

		Timer myTimer = new Timer();
		myTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				UpdateGUI();
			}
		}, 0, 150);
	}

	private void UpdateGUI() {
		myhandler.post(myRunnable);
	}

	final Runnable myRunnable = new Runnable() {
		public void run() {
			ScreenCapture();

			if (flag == 0) {
				mViewUpdateThread = new ViewUpdateThread();
				mViewUpdateThread.start();
				flag = 1;
			}

			mViewTask.postInvalidate();

		}
	};

	OnClickListener SendButtonOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			flag1 = 1;
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void ScreenCapture() {
		try {
			mScreenCapture.screenshot(MainActivity.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}