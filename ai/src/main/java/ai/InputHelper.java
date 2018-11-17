package ai;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ai.exception.UnsupportedOrderException;
import common.Constants.Direction;

public class InputHelper {
	public static List<Integer> getBoardConfiguration(String numbers) {
		return Stream.of(numbers.split(" "))
			      .map(Integer::parseInt)
			      .collect(Collectors.toList());
	}
	
	public static List<Direction> getSequenceOfSteps(String sequence) throws UnsupportedOrderException {
		List<Direction> steps = new ArrayList<>();
		for (int i = 0; i < sequence.length(); i++) {
			String step = (sequence.charAt(i) + "");
			if (step.equalsIgnoreCase("R")) {
				steps.add(Direction.RIGHT);
			} else if (step.equalsIgnoreCase("L")) {
				steps.add(Direction.LEFT);
			} else if (step.equalsIgnoreCase("U")){
				steps.add(Direction.UP);
			} else if (step.equalsIgnoreCase("D")) {
				steps.add(Direction.DOWN);
			} else {
				throw new UnsupportedOrderException(sequence);
			}
		}
		return steps;
	}
}
