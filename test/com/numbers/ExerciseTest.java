package com.numbers;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExerciseTest {

	@Test
	public void testCheckResult() {
		Exercise ex = Exercise.newInstance();
		System.out.println(ex.getExercise());
//		throw new RuntimeException("DDD");
	}

	@Test
	public void testGetExercise() {
		fail("Not yet implemented");
	}

	@Test
	public void testNewInstance() {
		fail("Not yet implemented");
		assertEquals(10.0, 12.2);
	}

}
