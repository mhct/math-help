package com.numbers.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExerciseCSVParserTest {

	@Test
	public void testNewInstance() {
		String exercise = "10,ADD,3";
		Exercise e1 = Exercise.newInstance(exercise);
		assertNotNull(e1);
	}
	
	@Test
	public void testParsingExerciseStrings() {
		String t1 = "1,ADD,1";
		String t2 = "1,SUB,3";
		String t3 = "34,TIMES,45";
		
		assertNotNull(Exercise.newInstance(t1));
		assertNotNull(Exercise.newInstance(t2));
		assertNotNull(Exercise.newInstance(t3));
		
	}
	
	@Test
	public void testParsingExerciseFile1() {
		String exercises = "1,ADD,2\n3,ADD,4\n";
		assertNotNull(ExercisesFileParser.getExercises(exercises));
	}

	@Test
	public void testParsingExerciseFile2() {
		String ex1 = "1,ADD,2";
		String ex2 = "3,ADD,4";
		String ex3 = "1,TIMES,6";
		String exercises = ex1 + "\n" + ex2 + "\n" + ex3 + "\n\n\n"; 
				
		assertEquals(3, ExercisesFileParser.getExercises(exercises).size());
		assertEquals(Exercise.newInstance(ex1), ExercisesFileParser.getExercises(exercises).get(0));
		assertEquals(Exercise.newInstance(ex2), ExercisesFileParser.getExercises(exercises).get(1));
		assertEquals(Exercise.newInstance(ex3), ExercisesFileParser.getExercises(exercises).get(2));
	}

}
