package heuristics;

import java.util.Comparator;

import common.graph.Node;
import common.puzzle.Board;

public class ManhattanDistanceHeuristics implements Heuristics{
	
	private static ManhattanDistanceHeuristics heuristics = null;
	
	private ManhattanDistanceHeuristics () {
		
	}
	
	public static ManhattanDistanceHeuristics getInstance() {
		if (heuristics == null) {
			heuristics = new ManhattanDistanceHeuristics();
		}
		return heuristics;
	}
	
	
	@Override
	public int getHeuristicsValueBFS(Board puzzle) {
		Node[][] board = puzzle.getBoard();
		int totalDistanceBeginingZero = 0;
		Board correctBoardBeginningZero = puzzle.getCorrectBoardBeginningZero();
		
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board.length; x++) {
				Node correctNode = correctBoardBeginningZero.findTileByValue(board[y][x].getValue());
				totalDistanceBeginingZero += getManhattanDistance(board[y][x], correctNode);
			}
		}
		
		
		int totalDistanceEndingZero = 0;
		Board correctBoardEndingZero = puzzle.getCorrectBoardEndingZero();
		
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board.length; x++) {
				Node correctNode = correctBoardEndingZero.findTileByValue(board[y][x].getValue());
				totalDistanceEndingZero += getManhattanDistance(board[y][x], correctNode);
			}
		}
		
		
		return totalDistanceBeginingZero < totalDistanceEndingZero ? totalDistanceBeginingZero : totalDistanceEndingZero;
	}
	
	@Override
	public int getHeuristicsValueAStar(Board board) {
		return getHeuristicsValueBFS(board) + board.getSequenceOfSteps().size();
	}
	
	private static int getManhattanDistance(Node node1, Node node2) {
		int distance = 0;
		distance += Math.abs(node1.getX() - node2.getX());
		distance += Math.abs(node1.getY() - node2.getY());
		return distance;
	}
	
	
	@Override	
	public Comparator<Board> getBFSComparator() {
	    Comparator<Board> comparator = new Comparator<Board>() {
	        @Override
	        public int compare(Board board1, Board board2) {
	            return getHeuristicsValueBFS(board1) - getHeuristicsValueBFS(board2);
	        }
	    };
	    
	    return comparator;
	}

	@Override
	public Comparator<Board> getAStarComparator() {
		 Comparator<Board> comparator = new Comparator<Board>() {
		        @Override
		        public int compare(Board board1, Board board2) {
		        	int heuristicsValueBoard1 = getHeuristicsValueAStar(board1);
		        	int heuristicsValueBoard2 = getHeuristicsValueAStar(board2);
		        	
					return heuristicsValueBoard1 - heuristicsValueBoard2;
		        }
		    };
		    
	    return comparator;
	}

	@Override
	public Comparator<Board> getSMAStarComparator() {
		Comparator<Board> comparator = new Comparator<Board>() {
	        @Override
	        public int compare(Board board1, Board board2) {
				return board1.getF_x() - board2.getF_x();
	        }
	    };
	    
	    return comparator;
	}
}
