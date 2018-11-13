package it.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.ai.Constants.Direction;

public class TestHelper {
	public static List<Integer> getBoardConfiguration(String numbers) {
		return Stream.of(numbers.split(" "))
			      .map(Integer::parseInt)
			      .collect(Collectors.toList());
	}
	
	public static List<Direction> getSequenceOfSteps(String sequence) {
		List<Direction> steps = new ArrayList<>();
		for (int i = 0; i < sequence.length(); i++) {
			String step = (sequence.charAt(i) + "").toLowerCase();
			if (step.equals("r")) {
				steps.add(Direction.RIGHT);
			} else if (step.equals("l")) {
				steps.add(Direction.LEFT);
			} else if (step.equals("u")){
				steps.add(Direction.UP);
			} else if (step.equals("d")) {
				steps.add(Direction.DOWN);
			}
		}
		return steps;
	}
}
