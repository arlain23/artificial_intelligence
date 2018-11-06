package heuristics;

import java.util.Comparator;

import ai.puzzle.Board;

public interface Heuristics {
	
	public Comparator<Board> getComparator();
}
