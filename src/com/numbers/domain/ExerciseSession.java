package com.numbers.domain;

import java.io.Serializable;

import android.util.Log;


/**
 * Maintains the information about exercises
 * 
 * @author mariohct
 *
 */
public class ExerciseSession implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String TAG = "MathHelp";
	
	private SessionStrategy sessionStrategy;
	private Exercise currentExercise;
	private Boolean mistake = Boolean.FALSE;

	private ExerciseSession(SessionStrategy ss) {
		this.sessionStrategy = ss;
	}
	
	public Boolean isCorrect(int answer) {
		if(getCurrentExercise().checkResult(answer)) {
			return Boolean.TRUE;
		} else {
			setMistake(); //TODO add counter for number of times there was a mistake, if strategy needs it
			return Boolean.FALSE;
		}
	}

	/**
	 * Generates the next exercise and returns a representation to the UI
	 * 
	 * @return
	 */
	public String getExercise() {
		generateExercise();
		
		return String.valueOf(getCurrentExercise().getA()) + " " + getCurrentExercise().getOperation().toString() + " " + String.valueOf(getCurrentExercise().getB());
	}
	
	public int numberOfExercises() {
		return sessionStrategy.numberOfExercises();
	}

	private void generateExercise() {
		this.currentExercise = sessionStrategy.next();
	}
	
	private Exercise getCurrentExercise() {
		return this.currentExercise;
	}
	

	/**
	 * creates a new calculation
	 * @param sessionStrategy TODO
	 * 
	 * @return
	 */
	public static ExerciseSession newInstance(SessionStrategy sessionStrategy) {
		if (sessionStrategy == null) {
			Log.d(TAG, "No Exercise Strategy was selected");
		}
		ExerciseSession es = new ExerciseSession(sessionStrategy);
		return es;
	}

    private void setMistake() {
    	mistake = Boolean.TRUE;
    }
    
    /**
     * Says if there was a "considerable" mistake or not.
     * The strategy is responsible for defining what is/should a mistake or not.
     * 
     * @return
     */
    public Boolean noMistakes() {
    	return ! mistake;
    }
    
   
//    private void writeObject(ObjectOutputStream out) throws IOException {
//    	Log.d("numbers", "Serializing ExerciseSession");
//    	out.writeObject(sessionStrategy);
//    	out.writeObject(currentExercise);
//    	out.writeBoolean(mistake);
//    }
//    
//    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
//    	Log.d("numbers", "DE-Serializing ExerciseSession");
//    	this.sessionStrategy = (SessionStrategy) in.readObject();
//    	this.currentExercise = (Exercise) in.readObject();
//    	this.mistake = in.readBoolean();
//    }
}
