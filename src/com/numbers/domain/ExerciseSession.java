package com.numbers.domain;

import java.io.Serializable;

import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomData;
import org.apache.commons.math3.random.RandomDataImpl;


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
	private static final RandomData random = new RandomDataImpl(new MersenneTwister());
	private SessionStrategy sessionStrategy;
	private Exercise currentExercise;
	private Boolean mistake = Boolean.FALSE;

	private ExerciseSession(SessionStrategy ss) {
		this.sessionStrategy = ss;
	}
	
	public Boolean checkResult(int answer) {
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

	private void generateExercise() {
		this.currentExercise = sessionStrategy.next();
	}
	
	private Exercise getCurrentExercise() {
		return this.currentExercise;
	}
	

	/**
	 * creates a new calculation
	 * 
	 * @return
	 */
	public static ExerciseSession newInstance() {
		SessionStrategy ss = new SessionStrategy() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Exercise next() {
				int a, b;
				Operation operation = null;
				
				
				double operationType = random.nextUniform(0.0, 1.0);
				if(operationType >= 0.80) {
					operation = Operation.ADDITION;
				} else if (operationType >= 0.20 ){
					operation = Operation.MULTIPLICATION;
				} else {
					operation = Operation.SUBTRACTION;
				}
				
				if(operation == Operation.SUBTRACTION) { 
					a = random.nextInt(10, 100);
					b = random.nextInt(0, a);
				} else if (operation == Operation.MULTIPLICATION) {
					a = random.nextInt(6, 9);
					b = random.nextInt(0, 10);
					
					if (random.nextUniform(0.0, 1.0) <= 0.5) {
						int temp = a;
						a = b;
						b = temp;
					}
				} else {
					a = random.nextInt(0, 100);
					b = random.nextInt(0, 100 - a);
				}
				
				Exercise ex = Exercise.newInstance(a, b, operation);
				return ex;
			}
		};
		
		ExerciseSession es = new ExerciseSession(ss);
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
