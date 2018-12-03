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
		
		System.out.println(initBoard);
		System.out.println();
		
		
		Board currentBoard = initBoard;
		for (Direction direction : directionSteps) {
			System.out.println(direction);
			currentBoard = BoardHelper.moveNode(direction, currentBoard);
			System.out.println(currentBoard);
			System.out.println();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
