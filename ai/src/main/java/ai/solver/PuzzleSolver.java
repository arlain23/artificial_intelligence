package ai.solver;

import ai.exception.NotSolvableException;
import ai.heuristics.Heuristics;
import common.puzzle.Board;

public interface PuzzleSolver {
	public Board solve(Heuristics heuristics) throws NotSolvableException;
}
