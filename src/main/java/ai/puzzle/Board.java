package ai.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.management.RuntimeErrorException;

import ai.graph.EmptyNode;
import ai.graph.Node;
import ai.graph.NumberNode;
import it.ai.Constants.DIRECTION;

public class Board implements Cloneable{
	private Node [][] board;
	private EmptyNode emptyNode;
	private List<DIRECTION> sequenceOfSteps = new ArrayList<>();
	
	public Board (Node[][] board, EmptyNode emptyNode, List<DIRECTION> sequenceOfSteps) {
		this.board = board;
		this.emptyNode = emptyNode;
		this.sequenceOfSteps = sequenceOfSteps;
	}
	
	public Board(int r, int c, List<Integer> numbers) {
		this.board = new Node[r][c];
		
		int x = 0; 
		int y = 0;
		
		for (int i = 0; i < numbers.size(); i++) {
			int number = numbers.get(i);
			if (number == 0) {
				EmptyNode emptyNode = new EmptyNode(x,y);
				this.board[y][x] = emptyNode;
				this.emptyNode = emptyNode;
			} else {
				this.board[y][x] = new NumberNode(number, x, y);
			}
			x++;
			if (x >= c) {
				x = 0;
				y++;
			}
		}
		
	}
	
	private Integer[] initArray(int size) {
		Integer[] a = new Integer[size];
	    for (int i = 0; i < 100; ++i) {
	        a[i] = i;
	    }
	    return a;
	}
	public List<Integer> getBoardConfiguration() {
		List<Integer> boardConfiguration = new ArrayList<Integer>();
		for (int y = 0; y < this.board.length; y++) {
			for (int x = 0; x < this.board[0].length; x++) {
				boardConfiguration.add(this.board[y][x].getValue());
			}
		}
		return boardConfiguration;
	}
	public EmptyNode getEmptyNode() {
		return emptyNode;
	}
	
	public int getBoardWidth() {
		return this.board[0].length;
	}
	public int getBoardHeight() {
		return this.board.length;
	}
	
    public void setSequenceOfSteps(List<DIRECTION> sequenceOfSteps) {
		this.sequenceOfSteps = sequenceOfSteps;
	}
	public List<DIRECTION> getSequenceOfSteps() {
		return sequenceOfSteps;
	}
	public Node[][] getBoard() {
		return board;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < this.board.length; y++) {
			for (int x = 0; x < this.board[0].length; x++) {
				int number = this.board[y][x].getValue();
				sb.append(number);
				sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
    protected Object clone() throws CloneNotSupportedException {
    	int r = board.length;
    	int c = board[0].length;
    	List<Integer> numbers = getBoardConfiguration();
    	
    	Node[][] newBoard = new Node[r][c];
    	EmptyNode emptyNode = null;
    	int index = 0;
    	for (int y = 0; y < r; y++) {
			for (int x = 0; x < c; x++) {
				int number = numbers.get(index++);
				if (number == 0) {
					emptyNode = new EmptyNode(x,y);
					newBoard[y][x] = emptyNode;
				} else {
					newBoard[y][x] = new NumberNode(number, x, y);
				}
			}
    	}
    	return new Board(newBoard, emptyNode, new ArrayList<>(sequenceOfSteps));
    }

	public void addDirection(DIRECTION direction) {
		this.sequenceOfSteps.add(direction);
		
	}
}
