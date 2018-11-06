package heuristics;

import java.util.Comparator;

import ai.graph.Node;
import ai.puzzle.Board;
import it.ai.Constants;

public class ManahatanDistanceHeuristics implements Heuristics{
	
	private static ManahatanDistanceHeuristics heuristics = null;
	
	private ManahatanDistanceHeuristics () {
		
	}
	
	public static ManahatanDistanceHeuristics getInstance() {
		if (heuristics == null) {
			heuristics = new ManahatanDistanceHeuristics();
		}
		return heuristics;
	}
	
	
	
	private int getHeuristicsValue(Board puzzle) {
		Node[][] board = puzzle.getBoard();
		int totalDistanceBeginingZero = 0;
		Board correctBoardBeginningZero = Constants.correctBoardBeginningZero;
		
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board.length; x++) {
				Node correctNode = correctBoardBeginningZero.findTileByValue(board[y][x].getValue());
				totalDistanceBeginingZero += getManhattanDistance(board[y][x], correctNode);
			}
		}
		
		
		int totalDistanceEndingZero = 0;
		Board correctBoardEndingZero = Constants.correctBoardEndingZero;
		
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board.length; x++) {
				Node correctNode = correctBoardEndingZero.findTileByValue(board[y][x].getValue());
				totalDistanceEndingZero += getManhattanDistance(board[y][x], correctNode);
			}
		}
		
		
		return totalDistanceBeginingZero < totalDistanceEndingZero ? totalDistanceBeginingZero : totalDistanceEndingZero;
	}
	
	private static int getManhattanDistance(Node node1, Node node2) {
		int distance = 0;
		distance += Math.abs(node1.getX() - node2.getX());
		distance += Math.abs(node1.getY() - node2.getY());
		return distance;
	}
	
	
	@Override	
	public Comparator<Board> getComparator() {
	    Comparator<Board> comparator = new Comparator<Board>() {
	        @Override
	        public int compare(Board board1, Board board2) {
	            return getHeuristicsValue(board1) - getHeuristicsValue(board2);
	        }
	    };
	    
	    return comparator;
	}
}
