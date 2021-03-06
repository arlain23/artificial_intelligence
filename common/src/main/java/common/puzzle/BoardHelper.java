package common.puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.RuntimeErrorException;

import com.google.common.collect.Ordering;

import common.Constants;
import common.Constants.Direction;
import common.graph.EmptyNode;
import common.graph.Node;

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
		
		Direction lastStep = board.getLastStep();
		if (lastStep != null) {
			availableDirectionsSet.remove(lastStep);
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
			newBoard.setLastDirection(direction);
			newBoard.setParentBoard(board);
			
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
	
	
	public static List<Direction> getSequenceOfSteps(Board board) {
		Board parentBoard = board;
		
		List<Direction> steps = new ArrayList<Direction> ();
		steps.add(board.getLastStep());
		while (true) {
			parentBoard = parentBoard.getParentBoard();
			if (parentBoard == null) {
				break;
			} 
			Direction lastStep = parentBoard.getLastStep();
			if (lastStep != null) {
				steps.add(lastStep);
			}
		}
		
		
		Collections.reverse(steps);
		
		return steps;
		
		
	}
	public static void printSequence(Board initBoard, Board board ) {
		System.out.println("/");
		System.out.println(initBoard);
		Board parentBoard = board;
		
		List<Direction> steps = getSequenceOfSteps(parentBoard);
		
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
