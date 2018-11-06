package heuristics;

import java.util.Comparator;
import java.util.List;

import ai.puzzle.Board;
import it.ai.Constants;

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
	
	private int getHeuristicsValue(Board board) {
		int numberOfIncorrectTilesBeginningEmpty = 0;
		List<Integer> configuration = board.getBoardConfiguration();
		
		List<Integer> correctConfiguration = Constants.correctBoardBeginningZero.getBoardConfiguration();
		for (int i = 0 ; i < configuration.size(); i++) {
			if (configuration.get(i) != correctConfiguration.get(i)) {
				numberOfIncorrectTilesBeginningEmpty++;
			}
		}
		

		int numberOfIncorrectTilesEndingEmpty = 0;
		correctConfiguration = Constants.correctBoardEndingZero.getBoardConfiguration();
		for (int i = 0 ; i < configuration.size(); i++) {
			if (configuration.get(i) != correctConfiguration.get(i)) {
				numberOfIncorrectTilesEndingEmpty++;
			}
		}
		
		return numberOfIncorrectTilesBeginningEmpty < numberOfIncorrectTilesEndingEmpty ? numberOfIncorrectTilesBeginningEmpty : numberOfIncorrectTilesEndingEmpty;
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
