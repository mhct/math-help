package com.numbers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.numbers.domain.DropboxExercisesService;
import com.numbers.domain.DropboxSessionStrategy;
import com.numbers.domain.ExerciseSession;
import com.numbers.domain.ExercisesFileParser;

public class MainActivity extends Activity {
    protected static final int RESULT_SPEECH = 1;
    private static final String MODEL_STATE = "exercise";
	public static final String EXTRA_MESSAGE = "MainActivity";
    private ExerciseSession exerciseSession;
	private TextView textView;
	private RatingBar rating;
	private EditText answerField;

	/** persists the exercise state **/
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putSerializable(MODEL_STATE, exerciseSession);
	}
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if(savedInstanceState != null && savedInstanceState.getSerializable(MODEL_STATE) != null) {
       		this.exerciseSession = (ExerciseSession) savedInstanceState.getSerializable(MODEL_STATE);
        } else {
        	try {
				AsyncTask<URL, Void, String> task = new DropboxExercisesService().execute(new URL("https://dl.dropboxusercontent.com/u/4294426/math-examples.csv"));
				exerciseSession = ExerciseSession.newInstance(DropboxSessionStrategy.newInstance(ExercisesFileParser.getExercises(task.get())));
			} catch (MalformedURLException e) {
				Log.e("numbers", e.getMessage());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        textView = (TextView) findViewById(R.id.textView);
        answerField = (EditText) findViewById(R.id.editText1);
        rating = (RatingBar) findViewById(R.id.ratingBar1);
        rating.setNumStars(exerciseSession.numberOfExercises());
        rating.setIsIndicator(Boolean.TRUE);
        rating.setMax(exerciseSession.numberOfExercises());
        rating.setRating(0.0f);
        
        showExercise();
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
					if(exerciseSession.isCorrect(answer)) {
						animationRightAnswer();
					} else {
						animationWrongAnswer();
					}
					showExercise();
					
					return true;
				}
				return true;
			}
        	
        });
        
        Log.d("Numbers", "Language: " + getString(R.string.language));
        
    }
    
    private void showExercise() {
    	textView.setText(exerciseSession.getExercise());
    }

    /**
     * Displays an animation for right answers, increase the score 
     */
    private void animationRightAnswer() {
    	rating.setRating(rating.getRating()+1.0f);
    	if(rating.getRating()>=exerciseSession.numberOfExercises() && exerciseSession.noMistakes()) {
    		Intent intent = new Intent(this, CongratulationsActivity.class);
    		startActivity(intent);
    	}
    	
    }
    /**
     * Displays an animation for right answers, increase the score 
     */
    private void animationWrongAnswer() {
    	rating.setRating(rating.getRating()-1.0f);
    }
}