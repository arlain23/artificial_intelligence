package ai.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ai.puzzle.Board;
import ai.puzzle.BoardHelper;
import it.ai.Constants.DIRECTION;

public class BFS {
	private Board initBoard;
	
	public BFS(Board initBoard) {
		this.initBoard = initBoard;
	}
	
	public Board solve() throws NotSolvableException {
		List<Board> row = new ArrayList<>();
		row.add(initBoard);
		
		int iterator = 0;
		while (true) {
			System.out.println("I " + iterator);
			Board correctBoard = checkCorrectness(row);
			if (correctBoard == null) {
				ArrayList<Board> newRow = new ArrayList<>();
				for (Board board : row) {
					List<Board> children = getChildren(board);
					newRow.addAll(children);
				}
				
				row = newRow;
			} else {
				return correctBoard;
			}
			if ((iterator++) > 200) throw new NotSolvableException("Exceeded number of iterations \n" + this.initBoard);
		}
	}
	
	private Board checkCorrectness(List<Board> row) {
		for (Board board : row) {
			boolean isCorrect = BoardHelper.isArrangementCorrect(board);
			if (isCorrect) return board;
		}
		return null;
	}

	private List<Board> getChildren (Board board) {
		List<Board> children = new ArrayList<>();
		Set<DIRECTION> availableMoves = BoardHelper.getAvailableMoves(board);
		for (DIRECTION direction : availableMoves) {
			Board child = BoardHelper.moveNode(direction, board);
			children.add(child);
		}
		
		return children;
	}
	
	
}
