package com.numbers;

import java.io.Console;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    protected static final int RESULT_SPEECH = 1;
    private static final String MODEL_STATE = "exercise";
    private Exercise exercise;
	private TextView textView;
	private ImageButton buttonSpeak;
	private RatingBar rating;
	private EditText answerField;

	/** persists the exercise state **/
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putSerializable(MODEL_STATE, exercise);
	}
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if(savedInstanceState != null && savedInstanceState.getSerializable(MODEL_STATE) != null) {
       		this.exercise = (Exercise) savedInstanceState.getSerializable(MODEL_STATE);
        } else {
        	exercise = Exercise.newInstance();
        }
        
        textView = (TextView) findViewById(R.id.textView);
        answerField = (EditText) findViewById(R.id.editText1);
//        buttonSpeak = (ImageButton) findViewById(R.id.buttonSpeak);
        rating = (RatingBar) findViewById(R.id.ratingBar1); 
        rating.setIsIndicator(Boolean.TRUE);
        rating.setMax(20); //bad, I know.. magic number from the ratingbar conf
        rating.setRating(0.0f);
        
        textView.setText(exercise.getExercise() + " = ?");
        answerField.setRawInputType(Configuration.KEYBOARD_12KEY);
        answerField.setOnEditorActionListener(new TextView.OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if(actionId == EditorInfo.IME_ACTION_DONE) {
					Integer answer;
					try {
						answer = Integer.valueOf(answerField.getText().toString());
					}
					catch(NumberFormatException e) {
						answerField.getText().clear();
						return true;
					}

					answerField.getText().clear();
					if(exercise.checkResult(answer)) {
						animationRightAnswer();
					} else {
						animationWrongAnswer();
					}
					
					exercise = Exercise.newInstance();
					textView.setText(exercise.getExercise());
					
					return true;
				}
				return true;
			}
        	
        });
        
        Log.d("Numbers", "Language: " + getString(R.string.language));
        
        
//        buttonSpeak.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//				speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, getString(R.string.language));
//				speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, getString(R.string.language));
//				
//				try {
//					startActivityForResult(speechIntent, RESULT_SPEECH);
//				} catch ( ActivityNotFoundException e ) {
//					Toast t = Toast.makeText(getApplicationContext(), "Device should support speech recognition", Toast.LENGTH_SHORT);
//					t.show();
//				}
//				
//			}
//		});
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	
    	switch(requestCode) {
    	case RESULT_SPEECH: handleSpeechReconitionResult(resultCode, data);
    		break;
    	default: break;
    	}
    }

    /**
     * Displays an animation for right answers, increase the score 
     */
    private void animationRightAnswer() {
    	rating.setRating(rating.getRating()+1.0f);
    	
    }
    /**
     * Displays an animation for right answers, increase the score 
     */
    private void animationWrongAnswer() {
    	rating.setRating(rating.getRating()-1.0f);
    	
    }
    
	private void handleSpeechReconitionResult(int resultCode, Intent data) {
		if(resultCode == RESULT_OK && data != null) {
			ArrayList<String> recognizedText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			
			if(recognizedText.size() > 0 ) {
				int answer;
				String msg = "";
				try {
					Log.d("Number", "RecognizedText" + recognizedText.toString());
					
					answer = Integer.valueOf(recognizedText.get(0));

					
					if(exercise.checkResult(answer)) {
						msg = "UHU CERTINHO :)";
						animationRightAnswer();
					} else {
						msg = "pena :(, correto eh: " + String.valueOf(exercise.getAnswer());
						
						if(rating.getRating() >= 15) {
							animationWrongAnswer();
						}
					}
				} catch( NumberFormatException e ) {
					msg = "Som RUIM, resposta eh: " + String.valueOf(exercise.getAnswer());
				}
				
				
				Toast t = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
				t.show();
				
				exercise = Exercise.newInstance();
				textView.setText(exercise.getExercise());
			} else {
				textView.setText("Speech NOT recognized");
			}
		}
	}
}