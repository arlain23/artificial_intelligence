package ai.solver;

import ai.puzzle.Board;

public interface PuzzleSolver {
	public Board solve() throws NotSolvableException;
}
