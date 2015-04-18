package com.numbers.domain;

import java.util.ArrayList;
import java.util.List;

public class ExercisesFileParser {

	public static List<Exercise> getExercises(String exercisesFileContents) {
		List<Exercise> exercises = new ArrayList<Exercise>();
		
		String[] exs = exercisesFileContents.split("\n");
		for (String ex: exs) {
			exercises.add(Exercise.newInstance(ex.trim()));
		}
		
		return exercises;
	}

}
