package it.ai;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
	public static enum Direction {
		LEFT, RIGHT, UP, DOWN
	}
	public static Map<Direction, Direction> REVERSE_DIRECTION = new HashMap<Direction, Direction> ();
	
	static {
		REVERSE_DIRECTION.put(Direction.LEFT, Direction.RIGHT);
		REVERSE_DIRECTION.put(Direction.RIGHT, Direction.LEFT);
		REVERSE_DIRECTION.put(Direction.UP, Direction.DOWN);
		REVERSE_DIRECTION.put(Direction.DOWN, Direction.UP);
	}
	
	public static List<Direction> DIRECTION_ORDER = Arrays.asList(new Direction[] {Direction.RIGHT, Direction.DOWN, Direction.LEFT, Direction.UP});
}
