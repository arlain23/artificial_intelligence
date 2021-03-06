package common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.puzzle.Board;

public class Constants {
	public static enum Direction {
		LEFT, RIGHT, UP, DOWN
	}
	
	public static enum Solver {
		BFS, DFS, BestFS, IDDFS, AStar, SMAStar
	}
	
	public static enum PuzzleType {
		eight, fifteen
	}
	
	public static Map<Direction, Direction> REVERSE_DIRECTION = new HashMap<Direction, Direction> ();
	
	static {
		REVERSE_DIRECTION.put(Direction.LEFT, Direction.RIGHT);
		REVERSE_DIRECTION.put(Direction.RIGHT, Direction.LEFT);
		REVERSE_DIRECTION.put(Direction.UP, Direction.DOWN);
		REVERSE_DIRECTION.put(Direction.DOWN, Direction.UP);
	}
	
	public static List<Direction> DIRECTION_ORDER = Arrays.asList(new Direction[] {Direction.RIGHT, Direction.DOWN, Direction.LEFT, Direction.UP});
	
	public static int MAX_DEPTH = 34;

	public static Board correctBoardBeginningZero8Puzzle = new Board(PuzzleType.eight, Arrays.asList(new Integer[] {0,1,2,3,4,5,6,7,8}));
	public static Board correctBoardEndingZero8Puzzle = new Board(PuzzleType.eight, Arrays.asList(new Integer[] {1,2,3,4,5,6,7,8,0}));
	
	public static Board correctBoardBeginningZero15Puzzle = new Board(PuzzleType.fifteen, Arrays.asList(new Integer[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}));;
	public static Board correctBoardEndingZero15Puzzle = new Board(PuzzleType.fifteen, Arrays.asList(new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0}));
	
}
