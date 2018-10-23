package it.ai;

import java.util.List;

import ai.puzzle.Board;
import it.ai.Constants.Direction;

public class TestSolution {
	private Board board;
	private List<Direction> sequenceOfSteps;
	public TestSolution(Board board, List<Direction> sequenceOfSteps) {
		super();
		this.board = board;
		this.sequenceOfSteps = sequenceOfSteps;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public List<Direction> getSequenceOfSteps() {
		return sequenceOfSteps;
	}
	public void setSequenceOfSteps(List<Direction> sequenceOfSteps) {
		this.sequenceOfSteps = sequenceOfSteps;
	}
	
	
}
