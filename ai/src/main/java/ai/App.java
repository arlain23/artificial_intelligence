package ai;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.google.common.base.Joiner;

import ai.exception.NotSolvableException;
import ai.exception.UnsupportedArgumentsException;
import ai.exception.UnsupportedHeuristicsException;
import ai.exception.UnsupportedOrderException;
import ai.heuristics.Heuristics;
import ai.heuristics.IncorrectPositionHeuristics;
import ai.heuristics.ManhattanDistanceHeuristics;
import ai.heuristics.TestZeroHeuristics;
import ai.solver.AStar;
import ai.solver.BFS;
import ai.solver.BestFS;
import ai.solver.DFS;
import ai.solver.IDDFS;
import ai.solver.PuzzleSolver;
import ai.solver.SMAStar;
import common.Constants;
import common.Constants.Direction;
import common.Constants.PuzzleType;
import common.Constants.Solver;
import common.exception.UnsupportedInputException;
import common.exception.UnsupportedPuzzleTypeException;
import common.puzzle.Board;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws UnsupportedArgumentsException, UnsupportedHeuristicsException,
    UnsupportedOrderException, UnsupportedInputException, UnsupportedPuzzleTypeException {
    	Solver solverType;
    	List<Direction> directionOrder;
    	Heuristics heuristics = TestZeroHeuristics.getInstance();
    	Board initBoard;
    	
    	checkArgsCorrectness(args);
    	solverType = getSolverType(args);
    	if (solverType == null) return;
    	directionOrder = getOrder(args, solverType);
    	if (directionOrder == null) {
    		directionOrder = getRandomDirectionOrder();
    		heuristics = getHeuristic(args);
    	}
    	initBoard = getBoard();
    	
    	System.out.println(initBoard.getBoardHeight() + " " + initBoard.getBoardWidth());
    	System.out.println(Joiner.on(',').join(initBoard.getBoardConfiguration()));
    	try {
			Board finalBoard = solveBoard(solverType, initBoard, directionOrder, heuristics);
			System.out.println(finalBoard.getSequenceOfSteps().size());
			System.out.println(Joiner.on(',').join(finalBoard.getSequenceOfSteps()));
		} catch (NotSolvableException e) {
			System.out.println("-1");
		}
    			
    	
    }
    
    private static Board solveBoard(Solver solverType, Board initBoard, List<Direction> directionOrder, Heuristics heuristics) throws NotSolvableException {
    	PuzzleSolver puzzleSolver = null;
    	if (solverType == Solver.BFS) {
    		puzzleSolver = new BFS(initBoard, directionOrder);
    	} else if (solverType == Solver.DFS) {
    		puzzleSolver = new DFS(initBoard, directionOrder);
    	} else if (solverType == Solver.IDDFS) {
    		puzzleSolver = new IDDFS(initBoard, directionOrder);
    	} else if (solverType == Solver.BestFS) {
    		puzzleSolver = new BestFS(initBoard, directionOrder);
    	} else if (solverType == Solver.AStar) {
    		puzzleSolver = new AStar(initBoard, directionOrder);
    	} else if (solverType == Solver.SMAStar) {
    		puzzleSolver = new SMAStar(initBoard, directionOrder);
    	}
    	
    	return puzzleSolver.solve(heuristics);
    }
    
    private static void checkArgsCorrectness(String [] args) throws UnsupportedArgumentsException {
    	if (args.length > 2) {
    		throw new UnsupportedArgumentsException("");
    	}
    }
    
    private static Solver getSolverType(String [] args) throws UnsupportedArgumentsException {
    	Solver solver = null;
		// method id
		String methodIdentifier = args[0].trim();
		
		if (methodIdentifier.equalsIgnoreCase("-b") || methodIdentifier.equalsIgnoreCase("--bfs")) {
			solver = Solver.BFS;
		} else if (methodIdentifier.equalsIgnoreCase("-d") || methodIdentifier.equalsIgnoreCase("--dfs")) {
			solver = Solver.DFS;
		} else if (methodIdentifier.equalsIgnoreCase("-i") || methodIdentifier.equalsIgnoreCase("--idfs")) {
			solver = Solver.IDDFS;
		} else if (methodIdentifier.equalsIgnoreCase("-h") || methodIdentifier.equalsIgnoreCase("--bf")) {
			solver = Solver.BestFS;
		} else if (methodIdentifier.equalsIgnoreCase("-a") || methodIdentifier.equalsIgnoreCase("--astar")) {
			solver = Solver.AStar;
		} else if (methodIdentifier.equalsIgnoreCase("-s") || methodIdentifier.equalsIgnoreCase("--sma")) {
			solver = Solver.SMAStar;
		} else if (methodIdentifier.equals("--help")) {
			System.out.println("Puzzle solver arguments: ");
			System.out.println("-b/--bfs order	Breadth-first search");
			System.out.println("-d/--dfs order	Depth-first search");
			System.out.println("-i/--idfs order	Iterative deepenening DFS");
			System.out.println("-h/--bf id_of_heurisic	Best-first strategy");
			System.out.println("-a/--astar id_of_heurisic	A* strategy");
			System.out.println("-s/--sma id_of_heurisic	SMA* strategy");
			System.out.println();
			System.out.println("Available heuristics:");
			System.out.println("0/--zero zero heuristics");
			System.out.println("1/--inpos incorrect position heuristics");
			System.out.println("2/--mand manhattan distance heuristics");
			System.out.println();
			System.out.println("Order should be a string containing permutation of elements of set {R,L,U,D} "
					+ "without separator or letter R if it should be random.");
			System.out.println();
			System.out.println("Input information: In the first line of standard input two integer values R C are given:"
					+ " row count and column count respectively, defining frame size. "
					+ "In each subsequent R lines of standard input contains C space separated integer values"
					+ " describing a piece in the puzzle. Value 0 denotes empty space in the given frame.");
		} else {
			throw new UnsupportedArgumentsException(methodIdentifier);
		}
		return solver;
    }
    private static List<Direction> getOrder(String [] args, Solver solver) throws UnsupportedOrderException {
    	List<Direction> directionOrder = null;
		// order / heuristic
		String methodParameter = args[1].trim();
		if (solver == Solver.BFS || solver == Solver.DFS || solver == Solver.IDDFS) {
			if (methodParameter.equalsIgnoreCase("R")) {
				//random
				directionOrder = getRandomDirectionOrder();
			} else {
				String[] order = methodParameter.split("");
				if (order.length != 4) {
					throw new UnsupportedOrderException(methodParameter);
				}
				directionOrder = InputHelper.getSequenceOfSteps(methodParameter);
			}
			
		}
		return directionOrder;
    }
	private static List<Direction> getRandomDirectionOrder() {
		List<Direction> directionOrder;
		directionOrder = Constants.DIRECTION_ORDER;
		Collections.shuffle(directionOrder);
		return directionOrder;
	}
    private static Heuristics getHeuristic(String [] args) throws UnsupportedHeuristicsException {
    	Heuristics heuristics;

    	String methodParameter = args[1].trim();
    	if (methodParameter.equals("0") || methodParameter.equalsIgnoreCase("--zero")) {
			heuristics = TestZeroHeuristics.getInstance();
		} else if (methodParameter.equals("1") || methodParameter.equalsIgnoreCase("--inpos")) {
			heuristics = IncorrectPositionHeuristics.getInstance();
		} else if (methodParameter.equals("2") || methodParameter.equalsIgnoreCase("--mand")) {
			heuristics = ManhattanDistanceHeuristics.getInstance();
		} else {
			throw new UnsupportedHeuristicsException(methodParameter);
		}
    	return heuristics;
    }
		
    private static Board getBoard() throws UnsupportedInputException, UnsupportedPuzzleTypeException {
    	// input
		PuzzleType puzzleType;
		Scanner scanner = new Scanner(System.in);
		String[] size = scanner.nextLine().split(" ");
		if (size.length < 2) {
			scanner.close();
			throw new UnsupportedInputException("");
		}
		StringBuilder config = new StringBuilder ();
		try {
			int rowCount = Integer.valueOf(size[0]);
			int columnCount = Integer.valueOf(size[1]);
			if (rowCount == 3 && columnCount == 3) {
				puzzleType = PuzzleType.eight;
			} else if (rowCount == 4 && columnCount == 4) {
				puzzleType = PuzzleType.fifteen;
			} else {
				scanner.close();
				throw new UnsupportedPuzzleTypeException("");
			}
			for (int r = 0; r < rowCount; r++) {
				String[] column = scanner.nextLine().split(" ");
				if (column.length != columnCount) {
					scanner.close();
					throw new UnsupportedInputException("");
				}
				for (int c = 0; c < columnCount; c++) {
					config.append(column[c]);
					config.append(" ");
				}
			}
		} catch (NumberFormatException e) {
			scanner.close();
			throw new UnsupportedInputException("");
		} catch (NoSuchElementException e) {
			scanner.close();
			throw new UnsupportedInputException("");
		}
		List<Integer> numbers = InputHelper.getBoardConfiguration(config.toString());
		scanner.close();
		return new Board(puzzleType, numbers);
    }
}
