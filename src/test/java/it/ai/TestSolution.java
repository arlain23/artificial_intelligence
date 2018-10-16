package it.ai;

import java.util.List;

import ai.puzzle.Board;
import it.ai.Constants.DIRECTION;

public class TestSolution {
	private Board board;
	private List<DIRECTION> sequenceOfSteps;
	public TestSolution(Board board, List<DIRECTION> sequenceOfSteps) {
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
	public List<DIRECTION> getSequenceOfSteps() {
		return sequenceOfSteps;
	}
	public void setSequenceOfSteps(List<DIRECTION> sequenceOfSteps) {
		this.sequenceOfSteps = sequenceOfSteps;
	}
	
	
}
