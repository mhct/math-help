package com.numbers;

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
public class Exercise implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final RandomData random = new RandomDataImpl(new MersenneTwister());
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
			a = random.nextInt(10, 17);
			b = random.nextInt(5, 10);
		} else {
			a = random.nextInt(8, 9);
			b = random.nextInt(0, 10);
			
			if (random.nextUniform(0.0, 1.0) <= 0.5) {
				int temp = a;
				a = b;
				b = temp;
			}
		}
		
		Exercise ex = new Exercise(a, b, operation);
		return ex;
	}

	public int getAnswer() {
		return operation.calculate(getA(), getB());
	}
	
}
