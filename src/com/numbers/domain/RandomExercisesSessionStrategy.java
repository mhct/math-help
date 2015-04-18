package com.numbers.domain;

import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomData;
import org.apache.commons.math3.random.RandomDataImpl;

public class RandomExercisesSessionStrategy implements SessionStrategy {

	private static final long serialVersionUID = 20150331L;
	private static final RandomData random = new RandomDataImpl(new MersenneTwister());
	private static final int NUMBER_OF_EXERCISES = 100;
	
	public static RandomExercisesSessionStrategy getInstance() {
		return new RandomExercisesSessionStrategy();
	}
	
	@Override
	public Exercise next() {
		int a, b;
		Operation operation = null;
		
		
		double operationType = random.nextUniform(0.0, 1.0);
		if(operationType >= 0.80) {
			operation = Operation.ADD;
		} else if (operationType >= 0.20 ){
			operation = Operation.TIMES;
		} else {
			operation = Operation.SUB;
		}
		
		if(operation == Operation.SUB) { 
			a = random.nextInt(10, 100);
			b = random.nextInt(0, a);
		} else if (operation == Operation.TIMES) {
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

	@Override
	public int numberOfExercises() {
		return NUMBER_OF_EXERCISES;
	}
}
