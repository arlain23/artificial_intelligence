package ai.solver;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import ai.exception.NotSolvableException;
import ai.heuristics.Heuristics;
import common.Constants.Direction;
import common.puzzle.Board;
import common.puzzle.BoardHelper;

public class BFS implements PuzzleSolver{
	private Board initBoard;
	private List<Direction> directionOrder;
	
	public BFS(Board initBoard, List<Direction> directionOrder) {
		this.initBoard = initBoard;
		this.directionOrder = directionOrder;
	}

	@Override
	public Board solve(Heuristics heuristics) throws NotSolvableException {
		Set<Board> history = new HashSet<>();
		Queue<Board> queue = new ArrayDeque<>();
		
		queue.add(this.initBoard);
		
		int iterator = 0;
		while (!queue.isEmpty()) {
			iterator++;
			Board currentBoard = queue.poll();
			
			boolean isCorrect = BoardHelper.checkCorrectness(currentBoard);
			if (isCorrect) {
				System.out.println("I " + iterator);
				return currentBoard;
			} else {
				List<Board> children = BoardHelper.getChildren(currentBoard, directionOrder);
				for (Board child : children) {
					if (!history.contains(child)) {
						queue.add(child);
						history.add(child);
					}
				}
			}
		}
		throw new NotSolvableException("Puzzle is not solvable");
	}
}
