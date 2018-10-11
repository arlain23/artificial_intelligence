package ai.puzzle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.management.RuntimeErrorException;

import ai.graph.EmptyNode;
import ai.graph.Node;
import ai.graph.NumberNode;
import it.ai.Constants;

public class Puzzle {
	private Node [][] puzzle;
	private EmptyNode emptyNode;
	
	public Puzzle (int r, int c) {
		this.puzzle = new Node[r][c];
		
		List<Integer> numbers = Arrays.asList(initArray(r*c));
		Collections.shuffle(numbers);
		
		int x = 0; 
		int y = 0;
		
		for (int i = 0; i < numbers.size(); i++) {
			int number = numbers.get(i);
			if (number == 0) {
				EmptyNode emptyNode = new EmptyNode(x,y);
				this.puzzle[x][y] = emptyNode;
				this.emptyNode = emptyNode;
			} else {
				this.puzzle[x][y] = new NumberNode(number, x, y);
			}
			x++;
			if (x > r) {
				x = 0;
				c++;
			}
		}
		
	}
	public Puzzle(int r, int c, List<List<Integer>> numbers) {
		this.puzzle = new Node[r][c];
		for (int i = 0; i < numbers.size(); i++) {
			for (int j = 0; j < numbers.get(0).size(); j++) {
				int number = numbers.get(i).get(j);
				if (number == 0) {
					EmptyNode emptyNode = new EmptyNode(i,j);
					this.puzzle[i][j] = emptyNode;
					this.emptyNode = emptyNode;
				} else {
					this.puzzle[i][j] = new NumberNode(number, i, j);
				}
			}
		}
	}
	
	
	public void moveNode(Constants.DIRECTION direction) {
		int x = this.emptyNode.getX();
		int y = this.emptyNode.getY();
		if (direction.equals(Constants.DIRECTION.LEFT)) {
			int newX = x - 1;
			int newY = y;
			switchNodes(x, y, newX, newY);
			
		} else if (direction.equals(Constants.DIRECTION.RIGHT)) {
			int newX = x + 1;
			int newY = y;
			switchNodes(x, y, newX, newY);
			
		} else if (direction.equals(Constants.DIRECTION.UP)) {
			int newX = x;
			int newY = y - 1;
			switchNodes(x, y, newX, newY);
			
		} else if (direction.equals(Constants.DIRECTION.DOWN)) {
			int newX = x;
			int newY = y + 1;
			switchNodes(x, y, newX, newY);
			
		} else {
			throw new RuntimeErrorException(null);
		}
			
	}
	private void switchNodes(int x, int y, int newX, int newY) {
		Node nodeToSwitch = this.puzzle[newX][newY];
		this.puzzle[newX][newY] = emptyNode;
		this.puzzle[x][y] = nodeToSwitch;
		
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
	
}
