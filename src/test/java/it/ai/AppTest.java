package it.ai;

import java.util.ArrayList;
import java.util.List;

import ai.puzzle.Board;
import ai.puzzle.BoardHelper;
import ai.solver.BFS;
import ai.solver.NotSolvableException;
import it.ai.Constants.DIRECTION;
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
		List<DIRECTION> sequenceOfSteps;
		
		board = new Board(3, 3, TestHelper.getBoardConfiguration("123405786"));
		sequenceOfSteps = TestHelper.getSequenceOfSteps("RD");
		TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
		
		board = new Board(3, 3, TestHelper.getBoardConfiguration("413726580"));
		sequenceOfSteps = TestHelper.getSequenceOfSteps("LLUURDDR");
		TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
		
		board = new Board(3, 3, TestHelper.getBoardConfiguration("123480765"));
		sequenceOfSteps = TestHelper.getSequenceOfSteps("DLURD");
		TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
		
		board = new Board(3, 3, TestHelper.getBoardConfiguration("126350478"));
		sequenceOfSteps = TestHelper.getSequenceOfSteps("ULDLDRRULURDD");
		TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
		
		board = new Board(3, 3, TestHelper.getBoardConfiguration("436871052"));
		sequenceOfSteps = TestHelper.getSequenceOfSteps("URRULDDRULDLUURDRD");
		TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
		
		board = new Board(3, 3, TestHelper.getBoardConfiguration("436871052"));
		sequenceOfSteps = TestHelper.getSequenceOfSteps("URRULDDRULDLUURDRD");
		TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
		
		board = new Board(3, 3, TestHelper.getBoardConfiguration("876543021"));
		sequenceOfSteps = TestHelper.getSequenceOfSteps("UURDRDLLUURDRULDDRULDLUURDRD");
		TEST_BOARDS.add(new TestSolution(board, sequenceOfSteps));
		
		
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
    		System.out.println("TEST " + (++iterator));
    		Board board = testSolution.getBoard();
    		List<DIRECTION> sequenceOfSteps = testSolution.getSequenceOfSteps();
    		BFS bfs = new BFS(board);
    		try {
				Board solvedBoard = bfs.solve();
				testSingleBoard(solvedBoard, sequenceOfSteps);
			} catch (NotSolvableException e) {
				fail();
			}
    		
    	}
    }
    private void testSingleBoard(Board board, List<DIRECTION> sequenceOfSteps) {
    	boolean isSolved = BoardHelper.isArrangementCorrect(board);
    	boolean areSequencesEqual = areSequencesEqual(sequenceOfSteps, board.getSequenceOfSteps());
    	System.out.println("SEQUENCES EQUAL " + areSequencesEqual);
    	assertTrue(isSolved);
    }

    private boolean areSequencesEqual(List<DIRECTION> sequence, List<DIRECTION> sequence2) {
    	if (sequence.size() != sequence2.size()) return false;
    	for (int i = 0; i < sequence.size(); i++) {
    		if (sequence.get(i) != sequence2.get(i)) return false;
    	}
    	return true;
    }
}
