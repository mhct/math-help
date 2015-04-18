package com.numbers.domain;

import java.util.List;

import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomData;
import org.apache.commons.math3.random.RandomDataImpl;

public class DropboxSessionStrategy implements SessionStrategy {

	private static final long serialVersionUID = 20150331L;
	
	private static final RandomData random = new RandomDataImpl(new MersenneTwister());
	private List<Exercise> exercises;
	
	private DropboxSessionStrategy(List<Exercise> exercises) {
		this.exercises = exercises;
	}

	@Override
	public Exercise next() {
		return exercises.get(random.nextInt(0, exercises.size()-1));
	}
	
	@Override
	public int numberOfExercises() {
		return exercises.size();
	}

	public static DropboxSessionStrategy newInstance(List<Exercise> exercises) {
		if (exercises == null) {
			throw new IllegalArgumentException("Exercises can not be null");
		}
		return new DropboxSessionStrategy(exercises);
	}

	
}
