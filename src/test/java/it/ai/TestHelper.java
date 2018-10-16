package it.ai;

import java.util.ArrayList;
import java.util.List;

import it.ai.Constants.DIRECTION;

public class TestHelper {
	public static List<Integer> getBoardConfiguration(String numbers) {
		List<Integer> config = new ArrayList<>();
		for (int i = 0; i < numbers.length(); i++) {
			int number = Integer.valueOf(numbers.charAt(i) + "");
			config.add(number);
		}
		return config;
	}
	
	public static List<DIRECTION> getSequenceOfSteps(String sequence) {
		List<DIRECTION> steps = new ArrayList<>();
		for (int i = 0; i < sequence.length(); i++) {
			String step = (sequence.charAt(i) + "").toLowerCase();
			if (step.equals("r")) {
				steps.add(DIRECTION.RIGHT);
			} else if (step.equals("l")) {
				steps.add(DIRECTION.LEFT);
			} else if (step.equals("u")){
				steps.add(DIRECTION.UP);
			} else if (step.equals("d")) {
				steps.add(DIRECTION.DOWN);
			}
		}
		return steps;
	}
}
