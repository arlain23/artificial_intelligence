package it.ai;

import java.util.Arrays;

import ai.puzzle.Board;
import ai.puzzle.BoardHelper;
import ai.solver.BFS;
import ai.solver.NotSolvableException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        Board rootBoard = new Board(3, 3, Arrays.asList(new Integer[] {1,2,3,4,8,0,7,6,5}));
        System.out.println(rootBoard);
        BFS bfs = new BFS(rootBoard);
        try {
			Board board = bfs.solve();
			System.out.println();
			System.out.println(board);
			System.out.println(board.getSequenceOfSteps());
			BoardHelper.printSequence(rootBoard, board);
			;
		} catch (NotSolvableException e) {
			e.printStackTrace();
		}
        
    }
}
