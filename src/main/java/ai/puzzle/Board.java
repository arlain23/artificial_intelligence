package ai.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.management.RuntimeErrorException;

import ai.graph.EmptyNode;
import ai.graph.Node;
import ai.graph.NumberNode;
import it.ai.Constants;
import it.ai.Constants.DIRECTION;

public class Board {
	private Node [][] board;
	private EmptyNode emptyNode;
	
	public Board (int r, int c) {
		this.board = new Node[r][c];
		
		List<Integer> numbers = Arrays.asList(initArray(r*c));
		Collections.shuffle(numbers);
		
		int x = 0; 
		int y = 0;
		
		for (int i = 0; i < numbers.size(); i++) {
			int number = numbers.get(i);
			if (number == 0) {
				EmptyNode emptyNode = new EmptyNode(x,y);
				this.board[x][y] = emptyNode;
				this.emptyNode = emptyNode;
			} else {
				this.board[x][y] = new NumberNode(number, x, y);
			}
			x++;
			if (x > r) {
				x = 0;
				c++;
			}
		}
		
	}
	public Board(int r, int c, List<List<Integer>> numbers) {
		this.board = new Node[r][c];
		for (int i = 0; i < numbers.size(); i++) {
			for (int j = 0; j < numbers.get(0).size(); j++) {
				int number = numbers.get(i).get(j);
				if (number == 0) {
					EmptyNode emptyNode = new EmptyNode(i,j);
					this.board[i][j] = emptyNode;
					this.emptyNode = emptyNode;
				} else {
					this.board[i][j] = new NumberNode(number, i, j);
				}
			}
		}
	}
	
	
	public void moveNode(DIRECTION direction) {
		int x = this.emptyNode.getX();
		int y = this.emptyNode.getY();
		if (direction.equals(DIRECTION.LEFT)) {
			int newX = x - 1;
			int newY = y;
			switchNodes(x, y, newX, newY);
			
		} else if (direction.equals(DIRECTION.RIGHT)) {
			int newX = x + 1;
			int newY = y;
			switchNodes(x, y, newX, newY);
			
		} else if (direction.equals(DIRECTION.UP)) {
			int newX = x;
			int newY = y - 1;
			switchNodes(x, y, newX, newY);
			
		} else if (direction.equals(DIRECTION.DOWN)) {
			int newX = x;
			int newY = y + 1;
			switchNodes(x, y, newX, newY);
			
		} else {
			throw new RuntimeErrorException(null);
		}
			
	}
	private void switchNodes(int x, int y, int newX, int newY) {
		Node nodeToSwitch = this.board[newX][newY];
		this.board[newX][newY] = emptyNode;
		this.board[x][y] = nodeToSwitch;
		
		this.emptyNode.setX(newX);
		this.emptyNode.setY(newY);
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
				boardConfiguration.add(this.board[x][y].getValue());
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
	
}
