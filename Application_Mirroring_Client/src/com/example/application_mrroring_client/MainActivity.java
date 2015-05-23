package com.example.application_mrroring_client;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	// Static
	static int checknum = 0;
	static int flag = 0;
	
	// Widget
	public TextView textResponse, mtextView1;
	private EditText editTextAddress, editTextPort;
	private Button buttonConnect;

	// Variable
	public int width = 0, height = 0;
	public long temp = 0, fps = 0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		editTextAddress = (EditText) findViewById(R.id.address);
		editTextPort = (EditText) findViewById(R.id.port);
		buttonConnect = (Button) findViewById(R.id.connect);
		textResponse = (TextView) findViewById(R.id.response);

		mtextView1 = (TextView) findViewById(R.id.textView1);

		buttonConnect.setOnClickListener(buttonConnectOnClickListener);

		editTextAddress.setText("172.20.10.4");
		editTextPort.setText("8260");

		Timer timeTimer = new Timer();
		timeTimer.schedule(timeTimerTask, 30, 700);

	}

	TimerTask timeTimerTask = new TimerTask() {
		public void run() {
			Handler timeHandler = mtextView1.getHandler();
			timeHandler.post(new Runnable() {
				public void run() {
					String str = "#00FF00";
					mtextView1.setTextColor(Color.parseColor(str));
					mtextView1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 23.5f);
					mtextView1.setText("FPS ==> " + fps);
				}
			});
		}
	};

	OnClickListener buttonConnectOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			MyClientTask myClientTask = new MyClientTask(editTextAddress
					.getText().toString(), Integer.parseInt(editTextPort
					.getText().toString()));
			myClientTask.execute();
		}

	};
	
	OnClickListener mButtonOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			flag = 1;
		}

	};


}
