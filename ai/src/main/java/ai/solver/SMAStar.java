package ai.solver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import ai.exception.NotSolvableException;
import ai.heuristics.Heuristics;
import common.Constants;
import common.Constants.Direction;
import common.puzzle.Board;
import common.puzzle.BoardHelper;

public class SMAStar implements PuzzleSolver{
	private Board initBoard;
	private List<Direction> directionOrder;
	
	public SMAStar(Board initBoard, List<Direction> directionOrder) {
		this.initBoard = initBoard;
		this.directionOrder = directionOrder;
	}
	
	@Override
	public Board solve(Heuristics heuristics) throws NotSolvableException {
		Comparator<Board> comparator = heuristics.getSMAStarComparator();
		Comparator<Board> reversedComparator = heuristics.getSMAStarComparator().reversed();
		
		Queue<Board> open = new PriorityQueue<Board>(comparator); 
		Queue<Board> closed = new PriorityQueue<Board>(reversedComparator); 
		
		
		int memoryLimit = Constants.MAX_DEPTH;
		
		open.add(this.initBoard);
		int currentDepth = 1;

		
		Map<Board, List<Board>> availableSuccessorsMap = new HashMap<Board, List<Board>> ();
//		availableSuccessorsMap.put(this.initBoard, BoardHelper.getChildren(initBoard, directionOrder));
		
		Map<Board, List<Board>> successorMap = new HashMap<Board, List<Board>> ();
		int iterator = 0;
		while (!open.isEmpty()) {
			iterator++;
			Board currentBoard = open.peek();
			
			boolean isCorrect = BoardHelper.checkCorrectness(currentBoard);
			if (isCorrect) {
				System.out.println("I " + iterator);
				return currentBoard;
			} else {
				Board successor = null;

				if (successorMap.containsKey(currentBoard)) {
					List<Board> children = successorMap.get(currentBoard);
					if (children.size() > 0) {
						successor = children.get(0);
						children.remove(successor);
						successorMap.put(currentBoard, children);
					}
				} else {
					List<Board> children = new ArrayList<Board>();
					if (availableSuccessorsMap.containsKey(currentBoard)) {
						children.addAll(availableSuccessorsMap.get(currentBoard));
						
					} else {
						List<Board> successorList = BoardHelper.getChildren(currentBoard, directionOrder);
						children.addAll(successorList);
						availableSuccessorsMap.put(currentBoard, successorList);
					}
					successor = children.get(0);
					children.remove(successor);
					successorMap.put(currentBoard, children);
				}
				if (successor != null) {
					boolean isChildCorrect = BoardHelper.checkCorrectness(successor);
					int childDepth = successor.getSequenceOfSteps().size();
					
					if (!isChildCorrect && childDepth >= memoryLimit) {
						// f(s) infinity
						successor.setF_x(Integer.MAX_VALUE);
					} else {
						// f(s) max(f(n), g(s) + h(s))
						int f_n = heuristics.getHeuristicsValueAStar(currentBoard);
						int f_s = heuristics.getHeuristicsValueAStar(successor);
						if (f_n > f_s) {
							successor.setF_x(f_n);
						} else {
							successor.setF_x(f_s);
						}
					}
					open.add(successor);
					closed.add(successor);
				} else {
					// all children processed 
					backupProcedure(currentBoard, availableSuccessorsMap);
					open.remove(currentBoard);
					closed.remove(currentBoard);
					
					currentDepth++;
					if (currentDepth > memoryLimit) {
						
						Board removedBoard = closed.poll();
						open.remove(removedBoard);
						
						Board parentBoard = removedBoard.getParentBoard();
						List<Board> successors = availableSuccessorsMap.get(parentBoard);
						successors.remove(removedBoard);
						availableSuccessorsMap.put(parentBoard, successors);
						
						if (!open.contains(parentBoard)) {
							open.add(parentBoard);
							closed.add(parentBoard);
						} else {
							currentDepth--;
						}
						if (successors.size() == 0) {
							parentBoard.setF_x(Integer.MAX_VALUE);
						}
					}
					
				}
				
				
			}
		}
		throw new NotSolvableException("Puzzle is not solvable");
	}
	
	private void backupProcedure(Board board, Map<Board, List<Board>> availableSuccessorsMap) {
		List<Board> children = availableSuccessorsMap.get(board);
		int leastFcost = Integer.MAX_VALUE;
		for (Board child : children) {
			int currentFCost = child.getF_x();
			if (currentFCost < leastFcost) {
				leastFcost = currentFCost;
			}
		}
		
		int f_x = board.getF_x();
		if (f_x != leastFcost) {
			board.setF_x(leastFcost);
			Board parentBoard = board.getParentBoard();
			if (parentBoard != null) {
				backupProcedure(parentBoard, availableSuccessorsMap);
			}
		}
		
	}
	

}
