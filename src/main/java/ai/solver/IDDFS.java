package ai.solver;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import ai.puzzle.Board;
import ai.puzzle.BoardHelper;
import it.ai.Constants.Direction;

public class IDDFS implements PuzzleSolver {
	private Board initBoard;
	private List<Direction> directionOrder;
	
	public IDDFS(Board initBoard, List<Direction> directionOrder) {
		this.initBoard = initBoard;
		this.directionOrder = directionOrder;
	}
	
	@Override
	public Board solve() throws NotSolvableException {
		Queue<Board> queue = new ArrayDeque<>();
		
		queue.add(this.initBoard);
		
		int iterator = 0;
		
		int depth = 0;
		while (!queue.isEmpty()) {
			iterator++;
			depth++;
			if (depth > 200) throw new NotSolvableException("Exceeded number of iterations");
			Board currentBoardBFS = queue.poll();
			
			// run DFS for current board
			Stack<Board> stack = new Stack<>();
			stack.push(currentBoardBFS);
			
			while (!stack.isEmpty()) {
				Board currentBoardDFS = stack.pop();
				boolean isCorrect = BoardHelper.checkCorrectness(currentBoardDFS);
				if (isCorrect) {
					return currentBoardDFS;
				} else {
					// check of depth
					if (currentBoardDFS.getSequenceOfSteps().size() < depth) {
						List<Board> children = BoardHelper.getChildren(currentBoardDFS, directionOrder);
						for (Board child : children) {
							stack.add(child);
						}
					}
				}
			}
			
			List<Board> children = BoardHelper.getChildren(currentBoardBFS, directionOrder);
			for (Board child : children) {
				queue.add(child);
			}
		}
		throw new NotSolvableException("Puzzle is not solvable");
	}

}
