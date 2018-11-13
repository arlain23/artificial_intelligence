package heuristics;

import java.util.Comparator;

import ai.puzzle.Board;

public interface Heuristics {
	
	public int getHeuristicsValueBFS(Board board);
	
	public int getHeuristicsValueAStar(Board board);
	
	public Comparator<Board> getBFSComparator();
	
	public Comparator<Board> getAStarComparator();
	
	public Comparator<Board> getSMAStarComparator();
	
	
	
	
}
