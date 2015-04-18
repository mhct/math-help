package com.numbers.domain;

import junit.framework.Assert;

import org.junit.Test;


public class ExerciseTest {

	@Test
	public void testNewInstance() {
		System.out.println("A");
		Exercise ex = Exercise.newInstance(1, 1, Operation.ADD);
		
		Assert.assertNotNull(null);
	}

	public void testCheckResult() {
		Assert.fail("Not yet implemented");
	}

	public void testGetExercise() {
		Assert.fail("Not yet implemented");
	}

	public void testGetA() {
		Assert.fail("Not yet implemented");
	}

	public void testGetB() {
		Assert.fail("Not yet implemented");
	}

	public void testGetOperation() {
		Assert.fail("Not yet implemented");
	}


}
