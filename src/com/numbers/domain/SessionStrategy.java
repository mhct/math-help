package com.numbers.domain;

import java.io.Serializable;

/**
 * Strategy for creating exercises
 * 
 * @author mario
 *
 */
public interface SessionStrategy extends Serializable {
	int numberOfExercises(); //Number of exercises created by this SessionStrategy
	public Exercise next(); //Retrieves the next exercise
}
