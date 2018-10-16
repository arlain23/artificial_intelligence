package ai.puzzle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Ordering;
import com.google.common.collect.Streams.DoubleFunctionWithIndex;

import ai.graph.EmptyNode;
import it.ai.Constants;
import it.ai.Constants.DIRECTION;

public class BoardHelper {
	public static boolean isArrangementCorrect(Board board) {
		List<Integer> boardConfiguration = board.getBoardConfiguration();
		return Ordering.natural().isOrdered(boardConfiguration);
	}
	
	public Set<DIRECTION> getAvailableMoves (Board board) {
		EmptyNode emptyNode = board.getEmptyNode();
		int emptyNodeX = emptyNode.getX();
		int emptyNodeY = emptyNode.getY();
		
		int leftBorder = 0;
		int rightBorder = board.getBoardWidth();
		int topBorder = 0;
		int bottomBorder = board.getBoardHeight();
		
		int step = 1;
		
		Set<DIRECTION> availableDirections = new HashSet<> ();
		if (emptyNodeX - step >= leftBorder) {
			availableDirections.add(DIRECTION.LEFT);
		}
		if (emptyNodeX + step < rightBorder) {
			availableDirections.add(DIRECTION.RIGHT);
		}
		if (emptyNodeY - step >= topBorder) {
			availableDirections.add(DIRECTION.UP);
		}
		if (emptyNodeY + step < bottomBorder) {
			availableDirections.add(DIRECTION.DOWN);
		}
		
		return availableDirections;
		
	}
	
}
