package com.example.postpcservicefiledownload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.widget.Toast;

public class FileDownload extends IntentService {

	private Handler mHandler;

	public FileDownload() {
		super("FileDownload");
	}

	@Override
	public void onCreate() {
	    super.onCreate();
	    mHandler = new Handler();
	}; 
	
	@Override
	protected void onHandleIntent(Intent intent) {
		
		//show start msg, in a new thread that will handle it
	    mHandler.post(new Runnable() {            
	        @Override
	        public void run() {
	            Toast.makeText(FileDownload.this, "text file download started!", Toast.LENGTH_LONG).show();                
	        }
	    });
		
		String urlPath = intent.getStringExtra("urlpath");
		Toast.makeText(this,"asdf",Toast.LENGTH_LONG).show();
		File output = new File(Environment.getExternalStorageDirectory(),intent.getStringExtra("fileName"));
		if (output.exists()) output.delete();
		InputStream stream = null;
		FileOutputStream fos = null;
		try {
			URL url = new URL(urlPath);
			stream = url.openConnection().getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			fos = new FileOutputStream(output.getPath());
			int next = -1;
			//write to file
			while ((next = reader.read()) != -1) fos.write(next);
			// Sucessful finished, close streams
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		//show end msg, in a new thread that will handle it
	    mHandler.post(new Runnable() {            
	        @Override
	        public void run() {
	            Toast.makeText(FileDownload.this, "text file download ended!", Toast.LENGTH_LONG).show();                
	        }
	    });
	}
}
