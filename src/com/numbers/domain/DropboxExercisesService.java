package com.numbers.domain;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class DropboxExercisesService extends AsyncTask<URL, Void, String> {

	
	public DropboxExercisesService() {
	}

	
	@Override
	protected String
	doInBackground(URL... params) {
		Log.d("Numbers", "Language: Background loading");
		
		if(params == null) {
			throw new IllegalArgumentException("URL can not be null.");
		}
		
		String exercises = null;
		try {
			InputStream is = params[0].openStream();
			if(is == null) {
				Log.d("Numbers", "is is Null");
			}
			
			Scanner s = new Scanner(is).useDelimiter("\\A");
			if (s.hasNext()) {
				exercises = s.next();
			} else {
				exercises = "";
			}
		} catch (MalformedURLException e) {
			Log.d("Numbers", "malformed url");
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("Numbers", "IOExc;etop");
			e.printStackTrace();
		}

		return exercises;
	}

	protected void onPostExecute (String result) {
		Log.d("Numbers", "post result called ");

		if(result != null) {
//			view.setImageBitmap(result);
			//do something
		} else {
			Log.d("Numbers", "result is NULL");
		}
	}
	
}
