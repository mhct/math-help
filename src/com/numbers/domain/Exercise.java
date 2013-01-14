package com.numbers.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Maintains the information about exercises
 * 
 * @author mariohct
 *
 */
public class Exercise implements Serializable {

	private static final long serialVersionUID = 1L;
	private int a;
	private int b;
	private Operation operation;
	private long startTime;
	private List<Attempt> attemptsRecord;
	
	private Exercise(int number1, int number2, Operation operation) {
		this.a = number1;
		this.b = number2;
		this.operation = operation;
		this.attemptsRecord = Lists
	}
	
	public Boolean checkResult(int answer) {
		Boolean isCorrectAnswer = Boolean.TRUE;
		
		if(answer != operation.calculate(a, b)) {
			isCorrectAnswer = Boolean.FALSE;
		}

		addAtempt(timeToSolve(), isCorrectAnswer);
		
		return isCorrectAnswer; 
	}

	private void addAtempt(long timeToSolve, Boolean isCorrectAnswer) {
		
	}

	public String getExercise() {
		startTimeMeasuring();
		return String.valueOf(a) + " " + operation.toString() + " " + String.valueOf(b);
	}

	private void startTimeMeasuring() {
		this.startTime = System.currentTimeMillis();
	}
	
	/**
	 * Time to solve an exercise, measured in seconds
	 * 
	 * @return int, seconds to solve an exercise
	 */
	private long timeToSolve() {
		return System.currentTimeMillis() - startTime/1000;
	}

	int getA() {
		return a;
	}

	int getB() {
		return b;
	}

	Operation getOperation() {
		return operation;
	}

	/**
	 * creates a new calculation
	 * 
	 * @return
	 */
	public static Exercise newInstance(int a, int b, Operation operation) {
		Exercise ex = new Exercise(a, b, operation);

		return ex;
	}
}
