package com.numbers.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DropboxSessionStrategyTest {

	@Test(expected=IllegalArgumentException.class)
	public void testNewInstance() {
		assertNotNull(DropboxSessionStrategy.newInstance(null));
	}

	@Test
	public void testNext() {
		List<Exercise> exercises = new ArrayList<Exercise>();
		exercises.add(Exercise.newInstance(1, 2, Operation.ADD));
		exercises.add(Exercise.newInstance(1, 3, Operation.ADD));
		exercises.add(Exercise.newInstance(1, 4, Operation.ADD));
		exercises.add(Exercise.newInstance(1, 5, Operation.ADD));
		exercises.add(Exercise.newInstance(1, 6, Operation.ADD));
		exercises.add(Exercise.newInstance(1, 7, Operation.ADD));
		exercises.add(Exercise.newInstance(1, 8, Operation.ADD));
		
		SessionStrategy s = DropboxSessionStrategy.newInstance(exercises);
		boolean monitor = false;
		
		for (int i=0; i<100; i++) {
			if (s.next().equals(Exercise.newInstance(1, 2, Operation.ADD)) ) {
				monitor = true;
			}
		}
		
		assertTrue(monitor);
	}


}
