package ai.puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.RuntimeErrorException;

import com.google.common.collect.Ordering;
import com.google.common.collect.Streams.DoubleFunctionWithIndex;

import ai.graph.EmptyNode;
import ai.graph.Node;
import it.ai.Constants;
import it.ai.Constants.Direction;

public class BoardHelper {
	public static boolean isArrangementCorrect(Board board) {
		List<Integer> boardConfiguration = board.getBoardConfiguration();
		if (boardConfiguration.get(0) == 0) {
			return Ordering.natural().isOrdered(boardConfiguration);
			
		} else if (boardConfiguration.get(boardConfiguration.size() - 1) == 0) {
			List<Integer> sequence = boardConfiguration.subList(0, boardConfiguration.size() - 1);
			return Ordering.natural().isOrdered(sequence);
		}
		
		return false;
		
		
		
	}
	
	public static List<Direction> getAvailableMoves (Board board, List<Direction> directionOrder) {
		EmptyNode emptyNode = board.getEmptyNode();
		int emptyNodeX = emptyNode.getX();
		int emptyNodeY = emptyNode.getY();
		
		int leftBorder = 0;
		int rightBorder = board.getBoardWidth();
		int topBorder = 0;
		int bottomBorder = board.getBoardHeight();
		
		int step = 1;
		
		Set<Direction> availableDirectionsSet = new HashSet<> ();
		if (emptyNodeX - step >= leftBorder) {
			availableDirectionsSet.add(Direction.LEFT);
		}
		if (emptyNodeX + step < rightBorder) {
			availableDirectionsSet.add(Direction.RIGHT);
		}
		if (emptyNodeY - step >= topBorder) {
			availableDirectionsSet.add(Direction.UP);
		}
		if (emptyNodeY + step < bottomBorder) {
			availableDirectionsSet.add(Direction.DOWN);
		}
		
		List<Direction> sequenceOfSteps = board.getSequenceOfSteps();
		if (sequenceOfSteps.size() > 0) {
			Direction lastDirection = sequenceOfSteps.get(sequenceOfSteps.size() - 1);
			availableDirectionsSet.remove(Constants.REVERSE_DIRECTION.get(lastDirection));
		}
		
		List<Direction> availableDirections = new ArrayList<>();
		
		// get directions according to order
		if (Constants.DIRECTION_ORDER == null) {
			availableDirections.addAll(availableDirectionsSet);
			Collections.shuffle(availableDirections);
		} else {
			for (Direction direction : directionOrder) {
				if (availableDirectionsSet.contains(direction)) {
					availableDirections.add(direction);
				}
			}
		}
		
		return availableDirections;
		
	}
	
	public static Board moveNode(Direction direction, Board board) {
		Board newBoard = null;
		try {
			newBoard = (Board) board.clone();
			newBoard.addDirection(direction);
			int x = newBoard.getEmptyNode().getX();
			int y = newBoard.getEmptyNode().getY();
			if (direction.equals(Direction.LEFT)) {
				int newX = x - 1;
				int newY = y;
				switchNodes(x, y, newX, newY, newBoard);
				
			} else if (direction.equals(Direction.RIGHT)) {
				int newX = x + 1;
				int newY = y;
				switchNodes(x, y, newX, newY, newBoard);
				
			} else if (direction.equals(Direction.UP)) {
				int newX = x;
				int newY = y - 1;
				switchNodes(x, y, newX, newY, newBoard);
				
			} else if (direction.equals(Direction.DOWN)) {
				int newX = x;
				int newY = y + 1;
				switchNodes(x, y, newX, newY, newBoard);
			} else {
				throw new RuntimeErrorException(null);
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			throw new RuntimeErrorException(null);
		}
		return newBoard;
	}
	private static void switchNodes(int x, int y, int newX, int newY, Board board) {
		EmptyNode emptyNode = board.getEmptyNode();
		Node [][] nodes = board.getBoard();
		Node nodeToSwitch = nodes[newY][newX];
		nodes[newY][newX] = emptyNode;
		nodes[y][x] = nodeToSwitch;
		
		emptyNode.setX(newX);
		emptyNode.setY(newY);
	}
	
	public static void printSequence(Board initBoard, Board board ) {
		System.out.println("/");
		System.out.println(initBoard);
		List<Direction> steps = board.getSequenceOfSteps();
		Board currentBoard = initBoard;
		for (Direction step : steps) {
			System.out.println(step);
			currentBoard = moveNode(step, currentBoard);
			System.out.println(currentBoard);
		}
	}
	
	public static Board checkCorrectness(List<Board> row) {
		for (Board board : row) {
			boolean isCorrect = BoardHelper.isArrangementCorrect(board);
			if (isCorrect) return board;
		}
		return null;
	}
	public static boolean checkCorrectness(Board board) {
		return BoardHelper.isArrangementCorrect(board);
	}
	public static List<Board> getChildren (Board board, List<Direction> directionOrder) {
		List<Board> children = new ArrayList<>();
		List<Direction> availableMoves = BoardHelper.getAvailableMoves(board, directionOrder);
		for (Direction direction : availableMoves) {
			Board child = BoardHelper.moveNode(direction, board);
			children.add(child);
		}
		
		return children;
	}
	
	
}
