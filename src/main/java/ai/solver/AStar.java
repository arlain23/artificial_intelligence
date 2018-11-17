package ai.solver;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import ai.exception.NotSolvableException;
import common.Constants.Direction;
import common.puzzle.Board;
import common.puzzle.BoardHelper;
import heuristics.Heuristics;

public class AStar implements PuzzleSolver{
	private Board initBoard;
	private List<Direction> directionOrder;
	
	public AStar(Board initBoard, List<Direction> directionOrder) {
		this.initBoard = initBoard;
		this.directionOrder = directionOrder;
	}
	
	@Override
	public Board solve(Heuristics heuristics) throws NotSolvableException {
		Set<Board> history = new HashSet<>();
		Comparator<Board> comparator = heuristics.getAStarComparator();
		Queue<Board> queue = new PriorityQueue<Board>(comparator); 
		 
		
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
