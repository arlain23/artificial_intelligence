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

import com.google.common.collect.MinMaxPriorityQueue;

import ai.puzzle.Board;
import ai.puzzle.BoardHelper;
import heuristics.Heuristics;
import it.ai.Constants;
import it.ai.Constants.Direction;

public class SMAStar implements PuzzleSolver{
	private Board initBoard;
	private List<Direction> directionOrder;
	
	public SMAStar(Board initBoard, List<Direction> directionOrder) {
		this.initBoard = initBoard;
		this.directionOrder = directionOrder;
	}
	
	@Override
	public Board solve(Heuristics heuristics) throws NotSolvableException {
		Set<Board> history = new HashSet<>();
		Comparator<Board> comparator = heuristics.getSMAStarComparator();
		Comparator<Board> reversedComparator = heuristics.getSMAStarComparator().reversed();
		
		Queue<Board> open = new PriorityQueue<Board>(comparator); 
		Queue<Board> closed = new PriorityQueue<Board>(reversedComparator); 
		
		
		int memoryLimit = Constants.MAX_DEPTH;
		
		open.add(this.initBoard);
		int currentDepth = 1;

		
		int iterator = 0;
		
		Map<Board, List<Board>> availableSuccessorsMap = new HashMap<Board, List<Board>> ();
		Map<Board, List<Board>> successorMap = new HashMap<Board, List<Board>> ();
		Queue<Board> currentSuccessorQueue = null; 

		Set<Board> memory = new HashSet<Board> ();
		
		while (!open.isEmpty()) {
//			System.out.println("################################################## " + open.size());
			// n node
			Board currentBoard = open.peek();
//			System.out.println("I " + (++iterator) + " D: " + currentDepth);
//			System.out.println();
//			System.out.println(currentBoard);
			
			boolean isCorrect = BoardHelper.checkCorrectness(currentBoard);
			if (isCorrect) {
//				System.out.println("CORRECT!! ");
				return currentBoard;
			} else {
				// magic
				Board successor = null;

				if (successorMap.containsKey(currentBoard)) {
					List<Board> children = successorMap.get(currentBoard);
//					System.out.println("IN MAP " + children.size());
					if (children.size() > 0) {
						successor = children.get(0);
						children.remove(successor);
						successorMap.put(currentBoard, children);
					}
				} else {
//					System.out.println("NEW MAP ");
					List<Board> children = new ArrayList<Board>();;
					if (availableSuccessorsMap.containsKey(currentBoard)) {
						children.addAll(availableSuccessorsMap.get(currentBoard));
						
					} else {
						List<Board> successorList = BoardHelper.getChildren(currentBoard, directionOrder);
						children.addAll(successorList);
						availableSuccessorsMap.put(currentBoard, successorList);
					}
					
					currentSuccessorQueue = new PriorityQueue<Board>(comparator); 
					currentSuccessorQueue.addAll(children);
					
					successor = children.get(0);
					children.remove(successor);
					successorMap.put(currentBoard, children);
				}
//				System.out.println("successor");
//				System.out.println(successor);
				
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
					backupProcedure(currentBoard);
					open.remove(currentBoard);
					closed.remove(currentBoard);
//					System.out.println("----------backup---------");
					
					currentDepth++;
//					System.out.println("BP " + currentDepth + " " + currentBoard.getSequenceOfSteps().size());
					if (currentDepth > memoryLimit) {
						System.out.println("MEMORY LIMIT REACHED ");
//						System.out.println("NR OF ELEMETS " + open.size());
						
						Board removedBoard = closed.poll();
						open.remove(removedBoard);
						
//						System.out.println("F(X) " + removedBoard.getF_x());
//						System.out.println("depth " + removedBoard.getSequenceOfSteps().size());
						Board parentBoard = removedBoard.getParentBoard();
						List<Board> successors = availableSuccessorsMap.get(parentBoard);
						successors.remove(removedBoard);
//						System.out.println("SUCC SIZE " + successors.size());
						availableSuccessorsMap.put(parentBoard, successors);
						
						if (successors.size() == 0) {
//							System.out.println("NO GO");
							parentBoard.setF_x(Integer.MAX_VALUE);
						}
						
						
						
						if (!open.contains(parentBoard)) {
							open.add(parentBoard);
							closed.add(parentBoard);
						}
						currentDepth--;
					}
					
				}
				
				
			}
//			if ((iterator++) > 200) throw new NotSolvableException("Exceeded number of iterations \n" + this.initBoard);
		}
		throw new NotSolvableException("Puzzle is not solvable");
	}
	
	private void backupProcedure(Board board) {
		List<Board> children = BoardHelper.getChildren(board, directionOrder);
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
			backupProcedure(board.getParentBoard());
		}
		
	}
	

}
