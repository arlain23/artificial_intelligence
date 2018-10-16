package ai.puzzle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.RuntimeErrorException;

import com.google.common.collect.Ordering;
import com.google.common.collect.Streams.DoubleFunctionWithIndex;

import ai.graph.EmptyNode;
import ai.graph.Node;
import it.ai.Constants;
import it.ai.Constants.DIRECTION;

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
	
	public static Set<DIRECTION> getAvailableMoves (Board board) {
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
		
		List<DIRECTION> sequenceOfSteps = board.getSequenceOfSteps();
		if (sequenceOfSteps.size() > 0) {
			DIRECTION lastDirection = sequenceOfSteps.get(sequenceOfSteps.size() - 1);
			availableDirections.remove(Constants.REVERSE_DIRECTION.get(lastDirection));
		}
		
		
		return availableDirections;
		
	}
	
	public static Board moveNode(DIRECTION direction, Board board) {
		Board newBoard = null;
		try {
			newBoard = (Board) board.clone();
			newBoard.addDirection(direction);
			int x = newBoard.getEmptyNode().getX();
			int y = newBoard.getEmptyNode().getY();
			if (direction.equals(DIRECTION.LEFT)) {
				int newX = x - 1;
				int newY = y;
				switchNodes(x, y, newX, newY, newBoard);
				
			} else if (direction.equals(DIRECTION.RIGHT)) {
				int newX = x + 1;
				int newY = y;
				switchNodes(x, y, newX, newY, newBoard);
				
			} else if (direction.equals(DIRECTION.UP)) {
				int newX = x;
				int newY = y - 1;
				switchNodes(x, y, newX, newY, newBoard);
				
			} else if (direction.equals(DIRECTION.DOWN)) {
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
		List<DIRECTION> steps = board.getSequenceOfSteps();
		Board currentBoard = initBoard;
		for (DIRECTION step : steps) {
			System.out.println(step);
			currentBoard = moveNode(step, currentBoard);
			System.out.println(currentBoard);
		}
	}
	
	
}
