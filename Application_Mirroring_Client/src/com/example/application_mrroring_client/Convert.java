package com.example.application_mrroring_client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Convert {
	public Bitmap byteArrayToBitmap(Queue<byte[]> queue) {
		byte[] byteArray = queue.poll();
		Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
				byteArray.length);

		return bitmap;
	}

	public Queue<byte[]> inputStreamToByteArray(InputStream is) {
		Queue<byte[]> queue = new LinkedList<byte[]>();
		int read_length = 0;

		byte[] resBytes = null;
		byte[] buffer = new byte[1024];

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			while (read_length < MyClientTask.bodysize) {
				int rsize;
				try {
					rsize = is.read(buffer);
					bos.write(buffer, 0, rsize);
					read_length += rsize;
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
				}

				resBytes = bos.toByteArray();
				bos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		queue.offer(resBytes);

		return queue;
	}
}
