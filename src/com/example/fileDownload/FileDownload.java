package com.example.fileDownload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;

public class FileDownload extends IntentService {

	public FileDownload() {
		super("FileDownload");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String urlPath = intent.getStringExtra("urlpath");
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
	}
}
