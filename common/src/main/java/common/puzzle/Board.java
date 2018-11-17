package common.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.Constants;
import common.Constants.Direction;
import common.Constants.PuzzleType;
import common.graph.EmptyNode;
import common.graph.Node;
import common.graph.NumberNode;

public class Board implements Cloneable{
	private Node [][] board;
	private EmptyNode emptyNode;
	
	private Board correctBoardBeginningZero;
	private Board correctBoardEndingZero;
	
	
	private List<Direction> sequenceOfSteps = new ArrayList<>();
	
	private Board parentBoard;
	
	private int f_x = 0;
	private int forgotten_f_x = 0;
	
	public Board (Node[][] board, EmptyNode emptyNode, List<Direction> sequenceOfSteps, Board correctBoardBeginningZero, Board correctBoardEndingZero) {
		this.board = board;
		this.emptyNode = emptyNode;
		this.sequenceOfSteps = sequenceOfSteps;
		
		this.correctBoardBeginningZero = correctBoardBeginningZero;
		this.correctBoardEndingZero = correctBoardEndingZero;
	}
	
	public Board(PuzzleType puzzleType, List<Integer> numbers) {
		int r = 0;
		int c = 0;
		if (puzzleType == PuzzleType.eight) {
			r = 3;
			c = 3;
			this.correctBoardBeginningZero = Constants.correctBoardBeginningZero8Puzzle;
			this.correctBoardEndingZero = Constants.correctBoardEndingZero8Puzzle;
		} else if (puzzleType == PuzzleType.fifteen) {
			r = 4;
			c = 4;
			this.correctBoardBeginningZero = Constants.correctBoardBeginningZero15Puzzle;
			this.correctBoardEndingZero = Constants.correctBoardEndingZero15Puzzle;
		}
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

	public Node findTileByValue(int value) {
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[0].length; x++) {
				if (value == board[y][x].getValue()) {
					return board[y][x];
				}
			}
		}
		return null;
	}
	
	public void addDirection(Direction direction) {
		this.sequenceOfSteps.add(direction);
		
	}
	
	
	public int getF_x() {
		return f_x;
	}

	public void setF_x(int f_x) {
		this.f_x = f_x;
	}

	public int getForgotten_f_x() {
		return forgotten_f_x;
	}

	public void setForgotten_f_x(int forgotten_f_x) {
		this.forgotten_f_x = forgotten_f_x;
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
	
    public void setSequenceOfSteps(List<Direction> sequenceOfSteps) {
		this.sequenceOfSteps = sequenceOfSteps;
	}
	public List<Direction> getSequenceOfSteps() {
		return sequenceOfSteps;
	}
	public Direction getLastStep() {
		if (sequenceOfSteps.size() == 0) return null;
		return sequenceOfSteps.get(sequenceOfSteps.size() - 1);
	}
	public Node[][] getBoard() {
		return board;
	}
	
	
	public Board getParentBoard() {
		return parentBoard;
	}

	public void setParentBoard(Board parentBoard) {
		this.parentBoard = parentBoard;
	}

	public Board getCorrectBoardBeginningZero() {
		return correctBoardBeginningZero;
	}

	public Board getCorrectBoardEndingZero() {
		return correctBoardEndingZero;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		return true;
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
    	return new Board(newBoard, emptyNode, new ArrayList<>(sequenceOfSteps), this.correctBoardBeginningZero, this.correctBoardEndingZero);
    }
}
