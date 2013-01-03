package com.numbers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class CongratulationsActivity extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		URL url;
		try {
			url = new URL("https://dl.dropbox.com/u/4294426/photo1.png");
			setContentView(R.layout.activity_congratulations);
			ImageButton button = (ImageButton) findViewById(R.id.imageButton1);
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			button.setImageBitmap(BitmapFactory.decodeStream(url.openStream(), null, options));
			
			button.setVisibility(View.VISIBLE);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		Intent intent = getIntent();
//		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
	}
	
	public void newExercise(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	
}
