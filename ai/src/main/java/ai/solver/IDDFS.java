package ai.solver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import ai.exception.NotSolvableException;
import ai.heuristics.Heuristics;
import common.Constants;
import common.Constants.Direction;
import common.puzzle.Board;
import common.puzzle.BoardHelper;

public class IDDFS implements PuzzleSolver {
	private Board initBoard;
	private List<Direction> directionOrder;
	
	public IDDFS(Board initBoard, List<Direction> directionOrder) {
		this.initBoard = initBoard;
		this.directionOrder = directionOrder;
	}
	
	@Override
	public Board solve(Heuristics heuristics) throws NotSolvableException {
		int depth = 0;
		int iterator = 0;
		while (depth < Constants.MAX_DEPTH) {
			depth++;
			iterator++;
			
			Set<Board> history = new HashSet<Board>();
			Stack<Board> stack = new Stack<>();
			stack.push(this.initBoard);
		
			while (!stack.isEmpty()) {
				Board currentBoard = stack.pop();
				history.add(currentBoard);
				boolean isCorrect = BoardHelper.checkCorrectness(currentBoard);
				if (isCorrect) {
					System.out.println("I: " + iterator);
					return currentBoard;
				} else {
					// check of depth
					if (currentBoard.getSequenceOfStepsSize() < depth) {
						List<Board> children = BoardHelper.getChildren(currentBoard, directionOrder);
						for (Board child : children) {
							if (!history.contains(child) && !stack.contains(child)) {
								stack.add(child);
							}
						}
					}
				}
			}
		}
		throw new NotSolvableException("Not solvable");
	}
}
