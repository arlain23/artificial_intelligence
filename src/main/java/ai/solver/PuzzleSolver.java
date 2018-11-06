package ai.solver;

import ai.puzzle.Board;
import heuristics.Heuristics;

public interface PuzzleSolver {
	public Board solve(Heuristics heuristics) throws NotSolvableException;
}
