package it.viewer;

import java.util.ArrayList;
import java.util.List;

import common.Constants.Direction;
import common.puzzle.Board;
import common.puzzle.BoardHelper;

public class Viewer {
	public static void viewSteps(Board initBoard, List<Direction> directionSteps) {

		List<Board> allBoards = new ArrayList<>();
		allBoards.add(initBoard);
		
		Board currentBoard = initBoard;
		for (Direction direction : directionSteps) {
			currentBoard = BoardHelper.moveNode(direction, currentBoard);
			allBoards.add(currentBoard);
		}
		
		
		for (Board board : allBoards) {
			System.out.println(board);
			System.out.println();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		
	}
	
}
