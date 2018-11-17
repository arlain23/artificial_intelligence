package heuristics;

import java.util.Comparator;
import java.util.List;

import common.puzzle.Board;

public class IncorrectPositionHeuristics implements Heuristics{
	
	private static IncorrectPositionHeuristics heuristics = null;
	
	private IncorrectPositionHeuristics () {
		
	}
	
	public static IncorrectPositionHeuristics getInstance() {
		if (heuristics == null) {
			heuristics = new IncorrectPositionHeuristics();
		}
		return heuristics;
	}
	@Override
	public int getHeuristicsValueBFS(Board board) {
		int numberOfIncorrectTilesBeginningEmpty = 0;
		List<Integer> configuration = board.getBoardConfiguration();
		
		List<Integer> correctConfiguration = board.getCorrectBoardBeginningZero().getBoardConfiguration();
		for (int i = 0 ; i < configuration.size(); i++) {
			if (configuration.get(i) != correctConfiguration.get(i)) {
				numberOfIncorrectTilesBeginningEmpty++;
			}
		}
		

		int numberOfIncorrectTilesEndingEmpty = 0;
		correctConfiguration = board.getCorrectBoardEndingZero().getBoardConfiguration();
		for (int i = 0 ; i < configuration.size(); i++) {
			if (configuration.get(i) != correctConfiguration.get(i)) {
				numberOfIncorrectTilesEndingEmpty++;
			}
		}
		
		return numberOfIncorrectTilesBeginningEmpty < numberOfIncorrectTilesEndingEmpty ? numberOfIncorrectTilesBeginningEmpty : numberOfIncorrectTilesEndingEmpty;
	}
	@Override
	public int getHeuristicsValueAStar(Board board) {
		return getHeuristicsValueBFS(board) + board.getSequenceOfSteps().size();
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
