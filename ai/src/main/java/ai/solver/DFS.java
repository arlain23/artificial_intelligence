package ai.solver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import ai.exception.NotSolvableException;
import common.Constants;
import common.Constants.Direction;
import common.puzzle.Board;
import common.puzzle.BoardHelper;
import heuristics.Heuristics;

public class DFS implements PuzzleSolver{
	private Board initBoard;
	private List<Direction> directionOrder;
	private int iterator = 0;
	
	public DFS(Board initBoard, List<Direction> directionOrder) {
		this.initBoard = initBoard;
		this.directionOrder = directionOrder;
	}
	
	@Override
	public Board solve(Heuristics heuristics) throws NotSolvableException {
		Set<Board> history = new HashSet<Board>();
		Stack<Board> stack = new Stack<>();
		stack.push(this.initBoard);
		
		System.out.println("Solve with stack");
		while (!stack.isEmpty()) {
			Board currentBoard = stack.pop();
			history.add(currentBoard);
			boolean isCorrect = BoardHelper.checkCorrectness(currentBoard);
			if (isCorrect) {
				return currentBoard;
			} else {
				// check of depth
				if (currentBoard.getSequenceOfSteps().size() < Constants.MAX_DEPTH) {
					List<Board> children = BoardHelper.getChildren(currentBoard, directionOrder);
					for (Board child : children) {
						if (!history.contains(child) && !stack.contains(child)) {
							stack.add(child);
						}
					}
				}
			}
			if ((iterator++) > 181440) throw new NotSolvableException("Exceeded number of iterations \n" + this.initBoard);
		}
		throw new NotSolvableException("Not solvable");
	}
}
