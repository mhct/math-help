package com.numbers;

import java.io.Serializable;

/**
 * Maintains the information about exercises
 * 
 * @author mariohct
 *
 */
public class Exercise implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int a;
	private int b;
	private Operation operation;
	
	private Exercise(int number1, int number2, Operation operation) {
		this.a = number1;
		this.b = number2;
		this.operation = operation;
	}
	
	public Boolean checkResult(int answer) {
		if(answer == operation.calculate(a, b)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public String getExercise() {
		return String.valueOf(a) + " " + operation.toString() + " " + String.valueOf(b);
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
	public static Exercise newInstance() {
		int a = 10 + (int) Math.floor(10 * Math.random());
		int b = (int) Math.floor(10 * Math.random());
		Operation operation = null;
		
		if(Math.random() >= 0.75) {
			operation = Operation.ADDITION;
		} else {
			operation = Operation.SUBTRACTION;
		}
		
		Exercise ex = new Exercise(a, b, operation);
		return ex;
	}

	public int getAnswer() {
		return operation.calculate(getA(), getB());
	}
}
