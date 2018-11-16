package heuristics;

import java.util.Comparator;

import ai.puzzle.Board;

public class TestZeroHeuristics implements Heuristics {

	private static TestZeroHeuristics heuristics = null;
	
	private TestZeroHeuristics () {
		
	}
	
	public static TestZeroHeuristics getInstance() {
		if (heuristics == null) {
			heuristics = new TestZeroHeuristics();
		}
		return heuristics;
	}
	
	@Override
	public int getHeuristicsValueBFS(Board board) {
		return 0;
	}

	@Override
	public int getHeuristicsValueAStar(Board board) {
		return 0;
	}

	
	@Override	
	public Comparator<Board> getBFSComparator() {
	    Comparator<Board> comparator = new Comparator<Board>() {
	        @Override
	        public int compare(Board board1, Board board2) {
	            return 0;
	        }
	    };
	    
	    return comparator;
	}

	@Override
	public Comparator<Board> getAStarComparator() {
		 Comparator<Board> comparator = new Comparator<Board>() {
		        @Override
		        public int compare(Board board1, Board board2) {

		        	return 0;
		        }
		    };
		    
	    return comparator;
	}

	@Override
	public Comparator<Board> getSMAStarComparator() {
		 Comparator<Board> comparator = new Comparator<Board>() {
		        @Override
		        public int compare(Board board1, Board board2) {

		        	return 0;
		        }
		    };
		    
	    return comparator;	}

}
