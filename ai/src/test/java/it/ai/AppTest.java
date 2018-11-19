package it.ai;

import java.util.ArrayList;
import java.util.List;

import ai.InputHelper;
import ai.exception.NotSolvableException;
import ai.exception.UnsupportedOrderException;
import ai.heuristics.IncorrectPositionHeuristics;
import ai.heuristics.ManhattanDistanceHeuristics;
import ai.heuristics.TestZeroHeuristics;
import ai.solver.AStar;
import ai.solver.BFS;
import ai.solver.BestFS;
import ai.solver.DFS;
import ai.solver.IDDFS;
import ai.solver.SMAStar;
import common.Constants;
import common.Constants.Direction;
import common.Constants.PuzzleType;
import common.puzzle.Board;
import common.puzzle.BoardHelper;
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
//			board = new Board(PuzzleType.eight, InputHelper.getBoardConfiguration("1 2 3 4 0 5 7 8 6"));
//			sequenceOfSteps = InputHelper.getSequenceOfSteps("RD");
//			TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
//			
//			board = new Board(PuzzleType.eight, InputHelper.getBoardConfiguration("4 1 3 7 2 6 5 8 0"));
//			sequenceOfSteps = InputHelper.getSequenceOfSteps("LLUURDDR");
//			TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
//			
//			board = new Board(PuzzleType.eight, InputHelper.getBoardConfiguration("1 2 3 4 8 0 7 6 5"));
//			sequenceOfSteps = InputHelper.getSequenceOfSteps("DLURD");
//			TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
//			
//			board = new Board(PuzzleType.eight, InputHelper.getBoardConfiguration("1 2 6 3 5 0 4 7 8"));
//			sequenceOfSteps = InputHelper.getSequenceOfSteps("ULDLDRRULURDD");
//			TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
//			
//			board = new Board(PuzzleType.eight, InputHelper.getBoardConfiguration("4 3 6 8 7 1 0 5 2"));
//			sequenceOfSteps = InputHelper.getSequenceOfSteps("URRULDDRULDLUURDRD");
//			TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
//			
			board = new Board(PuzzleType.eight, InputHelper.getBoardConfiguration("8 7 6 5 4 3 0 2 1"));
			sequenceOfSteps = InputHelper.getSequenceOfSteps("UURDRDLLUURDRULDDRULDLUURDRD");
			TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
//		
//			board = new Board(PuzzleType.fifteen, InputHelper.getBoardConfiguration("1 2 3 4 5 6 0 8 9 10 7 11 13 14 15 12"));
//			sequenceOfSteps = InputHelper.getSequenceOfSteps("R");
//			TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
			
//			board = new Board(PuzzleType.fifteen, InputHelper.getBoardConfiguration("4 1 2 3 8 5 6 7 9 13 10 11 12 14 0 15"));
//			sequenceOfSteps = InputHelper.getSequenceOfSteps("R");
//			TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
////			
//			board = new Board(PuzzleType.fifteen, InputHelper.getBoardConfiguration("1 3 6 4 9 5 12 7 0 2 10 8 13 14 11 15"));
//			sequenceOfSteps = InputHelper.getSequenceOfSteps("R");
//			TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
//			
//			board = new Board(PuzzleType.fifteen, InputHelper.getBoardConfiguration("4 1 7 2 9 6 14 3 5 8 0 11 12 10 13 15"));
//			sequenceOfSteps = InputHelper.getSequenceOfSteps("R");
//			TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
//			
//			board = new Board(PuzzleType.fifteen, InputHelper.getBoardConfiguration("1 2 4 8 5 7 15 3 9 6 0 12 13 14 10 11"));
//			sequenceOfSteps = InputHelper.getSequenceOfSteps("R");
//			TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
//			
//			board = new Board(PuzzleType.fifteen, InputHelper.getBoardConfiguration("2 10 3 4 1 6 11 8 5 12 7 15 13 9 14 0"));
//			sequenceOfSteps = InputHelper.getSequenceOfSteps("R");
//			TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
		} catch (UnsupportedOrderException e) {
			e.printStackTrace();
		}
	}
    public AppTest( String testName )
    {
        super( testName );
    }

    public void checkMemory(){
//    	 // Get the Java runtime
//        Runtime runtime = Runtime.getRuntime();
//        // Run the garbage collector
//        runtime.gc();
//        // Calculate the used memory
//        long memory = runtime.totalMemory() - runtime.freeMemory();
//
//        System.out.println("Used memory: " + + memory + "B  " + (memory / 1024L) + " KB");
        		
    }
//    public void testBFS() {
//    	int iterator = 0;
//    	for (TestSolution testSolution : TEST_BOARDS) {
//    		System.out.println("TEST BFS" + (++iterator));
//    		Board board = testSolution.getBoard();
//    		List<Direction> sequenceOfSteps = testSolution.getSequenceOfSteps();
//    		BFS bfs = new BFS(board, Constants.DIRECTION_ORDER);
//    		try {
//				Board solvedBoard = bfs.solve(null);
//				testSingleBoard(solvedBoard, sequenceOfSteps);
//			} catch (NotSolvableException e) {
//				e.printStackTrace();
//				fail();
//			}
//    		
//    	}
//    	checkMemory();
//    }
    
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
//    	checkMemory();
//    }
    
//    public void testIDDFS() {
//    	int iterator = 0;
//    	for (TestSolution testSolution : TEST_BOARDS) {
//    		System.out.println("TEST IDDFS " + (++iterator));
//    		Board board = testSolution.getBoard();
//    		List<Direction> sequenceOfSteps = testSolution.getSequenceOfSteps();
//    		IDDFS iddfs = new IDDFS(board, Constants.DIRECTION_ORDER);
//    		try {
//				Board solvedBoard = iddfs.solve(null);
//				testSingleBoard(solvedBoard, sequenceOfSteps);
//			} catch (NotSolvableException e) {
//				fail();
//			}
//    		
//    	}
//    	checkMemory();
//    }
//    
////    public void testBestFSHeuristicsZero() {
////    	int iterator = 0;
////    	for (TestSolution testSolution : TEST_BOARDS) {
////    		System.out.println("TEST BestFS 0" + (++iterator));
////    		Board board = testSolution.getBoard();
////    		List<Direction> sequenceOfSteps = testSolution.getSequenceOfSteps();
////    		BestFS bfs = new BestFS(board, Constants.DIRECTION_ORDER);
////    		try {
////				Board solvedBoard = bfs.solve(TestZeroHeuristics.getInstance());
////				testSingleBoard(solvedBoard, sequenceOfSteps);
////			} catch (NotSolvableException e) {
////				fail();
////			}
////    		
////    	}
////    }
//    public void testBestFSHeuristicsIncorrectPosition() {
//    	int iterator = 0;
//    	for (TestSolution testSolution : TEST_BOARDS) {
//    		System.out.println("TEST BestFS incorrect" + (++iterator));
//    		Board board = testSolution.getBoard();
//    		List<Direction> sequenceOfSteps = testSolution.getSequenceOfSteps();
//    		BestFS bfs = new BestFS(board, Constants.DIRECTION_ORDER);
//    		try {
//				Board solvedBoard = bfs.solve(IncorrectPositionHeuristics.getInstance());
//				testSingleBoard(solvedBoard, sequenceOfSteps);
//			} catch (NotSolvableException e) {
//				fail();
//			}
//    		
//    	}
//    	checkMemory();
//    }
//    public void testBestFSHeuristicsManhattanDistance() {
//    	int iterator = 0;
//    	for (TestSolution testSolution : TEST_BOARDS) {
//    		System.out.println("TEST BestFS manh" + (++iterator));
//    		Board board = testSolution.getBoard();
//    		List<Direction> sequenceOfSteps = testSolution.getSequenceOfSteps();
//    		BestFS bfs = new BestFS(board, Constants.DIRECTION_ORDER);
//    		try {
//				Board solvedBoard = bfs.solve(ManhattanDistanceHeuristics.getInstance());
//				testSingleBoard(solvedBoard, sequenceOfSteps);
//			} catch (NotSolvableException e) {
//				fail();
//			}
//    		
//    	}
//    	checkMemory();
//    }
//    
////    public void testAStarHeuristicsZero() {
////    	int iterator = 0;
////    	for (TestSolution testSolution : TEST_BOARDS) {
////    		System.out.println("TEST A* " + (++iterator));
////    		Board board = testSolution.getBoard();
////    		List<Direction> sequenceOfSteps = testSolution.getSequenceOfSteps();
////    		AStar aStar = new AStar(board, Constants.DIRECTION_ORDER);
////    		try {
////				Board solvedBoard = aStar.solve(TestZeroHeuristics.getInstance());
////				testSingleBoard(solvedBoard, sequenceOfSteps);
////			} catch (NotSolvableException e) {
////				fail();
////			}
////    		
////    	}
////    }
    public void testAStarHeuristicsIncorrectPosition() {
    	int iterator = 0;
    	for (TestSolution testSolution : TEST_BOARDS) {
    		System.out.println("TEST A* incorrect" + (++iterator));
    		Board board = testSolution.getBoard();
    		List<Direction> sequenceOfSteps = testSolution.getSequenceOfSteps();
    		AStar aStar = new AStar(board, Constants.DIRECTION_ORDER);
    		try {
				Board solvedBoard = aStar.solve(IncorrectPositionHeuristics.getInstance());
				testSingleBoard(solvedBoard, sequenceOfSteps);
			} catch (NotSolvableException e) {
				fail();
			}
    		
    	}
    	checkMemory();
    }
//    
//    public void testAStarHeuristicsManhattanDistance() {
//    	int iterator = 0;
//    	for (TestSolution testSolution : TEST_BOARDS) {
//    		System.out.println("TEST A* manh" + (++iterator));
//    		Board board = testSolution.getBoard();
//    		List<Direction> sequenceOfSteps = testSolution.getSequenceOfSteps();
//    		AStar aStar = new AStar(board, Constants.DIRECTION_ORDER);
//    		try {
//				Board solvedBoard = aStar.solve(ManhattanDistanceHeuristics.getInstance());
//				testSingleBoard(solvedBoard, sequenceOfSteps);
//			} catch (NotSolvableException e) {
//				fail();
//			}
//    		
//    	}
//    	checkMemory();
//    }
//    
////    public void testSMAStarHeuristicsZero() {
////    	int iterator = 0;
////    	for (TestSolution testSolution : TEST_BOARDS) {
////    		System.out.println("TEST SMA* 0" + (++iterator));
////    		Board board = testSolution.getBoard();
////    		List<Direction> sequenceOfSteps = testSolution.getSequenceOfSteps();
////    		SMAStar smaStar = new SMAStar(board, Constants.DIRECTION_ORDER);
////    		try {
////				Board solvedBoard = smaStar.solve(TestZeroHeuristics.getInstance());
////				testSingleBoard(solvedBoard, sequenceOfSteps);
////			} catch (NotSolvableException e) {
////				System.out.println(e.getLocalizedMessage());
////				fail();
////			}
////    		
////    	}
////    }
//    
//    public void testSMAStarHeuristicsIncorrectPosition() {
//    	int iterator = 0;
//    	for (TestSolution testSolution : TEST_BOARDS) {
//    		System.out.println("TEST SMA* incorr" + (++iterator));
//    		Board board = testSolution.getBoard();
//    		List<Direction> sequenceOfSteps = testSolution.getSequenceOfSteps();
//    		SMAStar smaStar = new SMAStar(board, Constants.DIRECTION_ORDER);
//    		try {
//				Board solvedBoard = smaStar.solve(IncorrectPositionHeuristics.getInstance());
//				testSingleBoard(solvedBoard, sequenceOfSteps);
//			} catch (NotSolvableException e) {
//				System.out.println(e.getLocalizedMessage());
//				fail();
//			}
//    		
//    	}
//    	checkMemory();
//    }
//    
//    public void testSMAStarHeuristicsManhattanDistance() {
//    	int iterator = 0;
//    	for (TestSolution testSolution : TEST_BOARDS) {
//    		System.out.println("TEST SMA* manh" + (++iterator));
//    		Board board = testSolution.getBoard();
//    		List<Direction> sequenceOfSteps = testSolution.getSequenceOfSteps();
//    		SMAStar smaStar = new SMAStar(board, Constants.DIRECTION_ORDER);
//    		try {
//				Board solvedBoard = smaStar.solve(ManhattanDistanceHeuristics.getInstance());
//				testSingleBoard(solvedBoard, sequenceOfSteps);
//			} catch (NotSolvableException e) {
//				System.out.println(e.getLocalizedMessage());
//				fail();
//			}
//    		
//    	}
//    	checkMemory();
//    }
    
    private void testSingleBoard(Board board, List<Direction> sequenceOfSteps) {
    	System.out.println("LENGTH sol: " + board.getSequenceOfSteps().size());
    	boolean isSolved = BoardHelper.isArrangementCorrect(board);
    	boolean areSequencesEqual = areSequencesEqual(sequenceOfSteps, board.getSequenceOfSteps());
//    	System.out.println("SEQUENCES EQUAL " + areSequencesEqual);
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
