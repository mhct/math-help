package com.numbers.domain;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;


/**
 * Maintains the information about a single exercise
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
	private List<ExerciseSolvingAttempt> attemptsRecord;
	
	private Exercise(int number1, int number2, Operation operation) {
		this.a = number1;
		this.b = number2;
		this.operation = operation;
		this.attemptsRecord = Lists.newArrayList();
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
		attemptsRecord.add(ExerciseSolvingAttempt.newInstance(timeToSolve, isCorrectAnswer));
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

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a;
		result = prime * result
				+ ((attemptsRecord == null) ? 0 : attemptsRecord.hashCode());
		result = prime * result + b;
		result = prime * result
				+ ((operation == null) ? 0 : operation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exercise other = (Exercise) obj;
		if (a != other.a)
			return false;
		if (attemptsRecord == null) {
			if (other.attemptsRecord != null)
				return false;
		} else if (!attemptsRecord.equals(other.attemptsRecord))
			return false;
		if (b != other.b)
			return false;
		if (operation != other.operation)
			return false;
		return true;
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

	public static Exercise newInstance(String exercise) {
		String[] parts = exercise.split(",");
		if (parts.length != 3) {
			throw new RuntimeException("Wrong file format");
		}
		
		int a = Integer.valueOf(parts[0].trim());
		int b = Integer.valueOf(parts[2].trim());
		Operation operation = Operation.valueOf(parts[1].trim());

		return Exercise.newInstance(a, b, operation);
	}
}
