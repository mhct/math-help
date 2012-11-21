package com.numbers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CongratulationsActivity extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_congratulations);
		
//		Intent intent = getIntent();
//		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
	}
	
	public void newExercise(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	
}
