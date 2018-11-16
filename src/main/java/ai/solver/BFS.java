package ai.solver;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import ai.exception.NotSolvableException;
import ai.puzzle.Board;
import ai.puzzle.BoardHelper;
import heuristics.Heuristics;
import it.ai.Constants.Direction;

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
			Board currentBoard = queue.poll();
			
			boolean isCorrect = BoardHelper.checkCorrectness(currentBoard);
			if (isCorrect) {
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
//			if ((iterator++) > 200) throw new NotSolvableException("Exceeded number of iterations \n" + this.initBoard);
		}
		throw new NotSolvableException("Puzzle is not solvable");
	}
}
