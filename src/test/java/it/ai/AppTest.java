package it.ai;

import java.util.ArrayList;
import java.util.List;

import ai.exception.NotSolvableException;
import ai.exception.UnsupportedOrderException;
import ai.solver.AStar;
import ai.solver.BFS;
import ai.solver.BestFS;
import ai.solver.IDDFS;
import ai.solver.SMAStar;
import common.Constants;
import common.Constants.Direction;
import common.Constants.PuzzleType;
import common.puzzle.Board;
import common.puzzle.BoardHelper;
import heuristics.IncorrectPositionHeuristics;
import heuristics.ManhattanDistanceHeuristics;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	private static List<TestSolution> TEST_BOARDS = new ArrayList<>();
	static {
		Board board;
		List<Direction> sequenceOfSteps;
		try {
		board = new Board(PuzzleType.eight, InputHelper.getBoardConfiguration("1 2 3 4 0 5 7 8 6"));
		sequenceOfSteps = InputHelper.getSequenceOfSteps("RD");
		TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
//		
//		board = new Board(3, 3, InputHelper.getBoardConfiguration("4 1 3 7 2 6 5 8 0"));
//		sequenceOfSteps = InputHelper.getSequenceOfSteps("LLUURDDR");
//		TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
//		
//		board = new Board(3, 3, InputHelper.getBoardConfiguration("1 2 3 4 8 0 7 6 5"));
//		sequenceOfSteps = InputHelper.getSequenceOfSteps("DLURD");
//		TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
//		
//		board = new Board(3, 3, InputHelper.getBoardConfiguration("1 2 6 3 5 0 4 7 8"));
//		sequenceOfSteps = InputHelper.getSequenceOfSteps("ULDLDRRULURDD");
//		TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
//		
//		board = new Board(3, 3, InputHelper.getBoardConfiguration("4 3 6 8 7 1 0 5 2"));
//		sequenceOfSteps = InputHelper.getSequenceOfSteps("URRULDDRULDLUURDRD");
//		TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
//		
//		board = new Board(3, 3, InputHelper.getBoardConfiguration("4 3 6 8 7 1 0 5 2"));
//		sequenceOfSteps = InputHelper.getSequenceOfSteps("URRULDDRULDLUURDRD");
//		TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
//		
//		board = new Board(3, 3, InputHelper.getBoardConfiguration("8 7 6 5 4 3 0 2 1"));
//		sequenceOfSteps = InputHelper.getSequenceOfSteps("UURDRDLLUURDRULDDRULDLUURDRD");
//		TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
		
//			board = new Board(PuzzleType.fifteen, InputHelper.getBoardConfiguration("5 1 7 3 9 2 11 4 13 6 15 8 0 10 14 12"));
//			sequenceOfSteps = InputHelper.getSequenceOfSteps("R");
//			TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
//			
//			board = new Board(PuzzleType.fifteen, InputHelper.getBoardConfiguration("2 5 13 12 1 0 3 15 9 7 14 6 10 11 8 4"));
//			sequenceOfSteps = InputHelper.getSequenceOfSteps("R");
//			TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
//			
//			board = new Board(PuzzleType.fifteen, InputHelper.getBoardConfiguration("5 2 4 8 10 0 3 14 13 6 11 12 1 15 9 7"));
//			sequenceOfSteps = InputHelper.getSequenceOfSteps("R");
//			TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
//			
//			board = new Board(PuzzleType.fifteen, InputHelper.getBoardConfiguration("11 4 12 2 5 10 3 15 14 1 6 7 0 9 8 13"));
//			sequenceOfSteps = InputHelper.getSequenceOfSteps("R");
//			TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
			
//			board = new Board(PuzzleType.fifteen, InputHelper.getBoardConfiguration("5 8 7 11 1 6 12 2 9 0 13 10 14 3 4 15"));
//			sequenceOfSteps = InputHelper.getSequenceOfSteps("R");
//			TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
		} catch (UnsupportedOrderException e) {
			e.printStackTrace();
		}
	}
/*
 board  | number of moves | solution(s)
123405786	2		RD
123745086	4		URRD
123480765	5		DLURD
413726580	8		LLUURDDR
162530478	9		LURDLLDRR
512630478	11		LLURRDLLDRR
126350478	13		ULDLDRRULURDD
356148072	16		RRUULLDRDRUULDRD
436871052	18		URRULDDRULDLUURDRD
302651478	21		DRULDLURRDLLDRRULURDD or DLURDRULDLURDRULDLDRR
012345678	22		RDLDRRULLDRUURDDLLURRD or DRRULLDDRUURDLLURRDLDR
503284671	23		LDDRRULLDRRULLDRUULDDRR
874320651	25		DLULURDRULDDLUURDRDLLURRD
876543021	28		UURDRDLLUURDRULDDRULDLUURDRD or UURDLDRURDLLUURDRULDDLUURDDR
876543210
	
 */
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    public void testBFS() {
    	int iterator = 0;
    	for (TestSolution testSolution : TEST_BOARDS) {
    		System.out.println("TEST BFS" + (++iterator));
    		Board board = testSolution.getBoard();
    		List<Direction> sequenceOfSteps = testSolution.getSequenceOfSteps();
    		BFS bfs = new BFS(board, Constants.DIRECTION_ORDER);
    		try {
				Board solvedBoard = bfs.solve(null);
				testSingleBoard(solvedBoard, sequenceOfSteps);
			} catch (NotSolvableException e) {
				e.printStackTrace();
				fail();
			}
    		
    	}
    }
    
//    public void testDFS() {
//    	int iterator = 0;
//    	for (TestSolution testSolution : TEST_BOARDS) {
//    		System.out.println("TEST DFS" + (++iterator));
//    		Board board = testSolution.getBoard();
//    		List<Direction> sequenceOfSteps = testSolution.getSequenceOfSteps();
//    		DFS dfs = new DFS(board, Constants.DIRECTION_ORDER);
//    		try {
//				Board solvedBoard = dfs.solve(null);
//				testSingleBoard(solvedBoard, sequenceOfSteps);
//			} catch (NotSolvableException e) {
//				fail();
//			}
//    		
//    	}
//    }
    
    public void testIDDFS() {
    	int iterator = 0;
    	for (TestSolution testSolution : TEST_BOARDS) {
    		System.out.println("TEST IDDFS " + (++iterator));
    		Board board = testSolution.getBoard();
    		List<Direction> sequenceOfSteps = testSolution.getSequenceOfSteps();
    		IDDFS iddfs = new IDDFS(board, Constants.DIRECTION_ORDER);
    		try {
				Board solvedBoard = iddfs.solve(null);
				testSingleBoard(solvedBoard, sequenceOfSteps);
			} catch (NotSolvableException e) {
				fail();
			}
    		
    	}
    }
    
    public void testBestFS() {
    	int iterator = 0;
    	for (TestSolution testSolution : TEST_BOARDS) {
    		System.out.println("TEST BestFS " + (++iterator));
    		Board board = testSolution.getBoard();
    		List<Direction> sequenceOfSteps = testSolution.getSequenceOfSteps();
    		BestFS bfs = new BestFS(board, Constants.DIRECTION_ORDER);
    		try {
				Board solvedBoard = bfs.solve(IncorrectPositionHeuristics.getInstance());
				testSingleBoard(solvedBoard, sequenceOfSteps);
			} catch (NotSolvableException e) {
				fail();
			}
    		
    	}
    }
    
    public void testAStar() {
    	int iterator = 0;
    	for (TestSolution testSolution : TEST_BOARDS) {
    		System.out.println("TEST A* " + (++iterator));
    		Board board = testSolution.getBoard();
    		List<Direction> sequenceOfSteps = testSolution.getSequenceOfSteps();
    		AStar aStar = new AStar(board, Constants.DIRECTION_ORDER);
    		try {
				Board solvedBoard = aStar.solve(ManhattanDistanceHeuristics.getInstance());
				testSingleBoard(solvedBoard, sequenceOfSteps);
			} catch (NotSolvableException e) {
				fail();
			}
    		
    	}
    }
    
    public void testSMAStar() {
    	int iterator = 0;
    	for (TestSolution testSolution : TEST_BOARDS) {
    		System.out.println("TEST SMA* " + (++iterator));
    		Board board = testSolution.getBoard();
    		List<Direction> sequenceOfSteps = testSolution.getSequenceOfSteps();
    		SMAStar smaStar = new SMAStar(board, Constants.DIRECTION_ORDER);
    		try {
				Board solvedBoard = smaStar.solve(ManhattanDistanceHeuristics.getInstance());
				testSingleBoard(solvedBoard, sequenceOfSteps);
			} catch (NotSolvableException e) {
				System.out.println(e.getLocalizedMessage());
				fail();
			}
    		
    	}
    }
    
    private void testSingleBoard(Board board, List<Direction> sequenceOfSteps) {
    	boolean isSolved = BoardHelper.isArrangementCorrect(board);
    	boolean areSequencesEqual = areSequencesEqual(sequenceOfSteps, board.getSequenceOfSteps());
    	System.out.println("SEQUENCES EQUAL " + areSequencesEqual);
    	assertTrue(isSolved);
    }

    private boolean areSequencesEqual(List<Direction> sequence, List<Direction> sequence2) {
    	if (sequence.size() != sequence2.size()) return false;
    	for (int i = 0; i < sequence.size(); i++) {
    		if (sequence.get(i) != sequence2.get(i)) return false;
    	}
    	return true;
    }
}
