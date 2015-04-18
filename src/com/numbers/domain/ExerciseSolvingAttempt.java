package com.numbers.domain;

import java.io.Serializable;

/**
 * Accumulates statistics about attempts to solve an exercise
 * 
 * @author mario
 *
 */
public class ExerciseSolvingAttempt implements Serializable {
	private static final long serialVersionUID = 2015041801L;
	private final long duration;
	private final Boolean correctness;
	
	private ExerciseSolvingAttempt(long duration, Boolean correctness) {
		this.duration = duration;
		this.correctness = correctness;
	}
	
	public static ExerciseSolvingAttempt newInstance(long duration, Boolean correctness) {
		ExerciseSolvingAttempt esa = new ExerciseSolvingAttempt(duration, correctness);
		
		return esa;
	}
	
	public long getDuration() {
		return duration;
	}
	
	public Boolean getCorrectness() {
		return correctness;
	}
	
	
}
