package ai.solver;

import ai.exception.NotSolvableException;
import common.puzzle.Board;
import heuristics.Heuristics;

public interface PuzzleSolver {
	public Board solve(Heuristics heuristics) throws NotSolvableException;
}
