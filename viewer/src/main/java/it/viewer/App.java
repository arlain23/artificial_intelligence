package it.viewer;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

import common.Constants.Direction;
import common.Constants.PuzzleType;
import common.exception.UnsupportedInputException;
import common.exception.UnsupportedPuzzleTypeException;
import common.puzzle.Board;


public class App 
{
    public static void main( String[] args ) throws UnsupportedInputException, UnsupportedPuzzleTypeException {
    	Scanner scanner = new Scanner(System.in);
    	
    	Board board = getBoard(scanner);
    	List<Direction> steps = getDirectionSteps(scanner);
    	
    	Viewer.viewSteps(board, steps);
    	scanner.close();
    
    }
    
    
    private static List<Direction> getDirectionSteps (Scanner scanner) {
    	System.out.println("NUMBER OF STEPS " + scanner.nextLine());
    	return Arrays.stream(scanner.nextLine().split(","))
				.map(direction -> Direction.valueOf(direction))
			 	.collect(Collectors.toList());
    }
    private static Board getBoard(Scanner scanner) throws UnsupportedInputException, UnsupportedPuzzleTypeException {
		PuzzleType puzzleType;
		String[] size = scanner.nextLine().split(" ");
		if (size.length < 2) {
			scanner.close();
			throw new UnsupportedInputException("");
		}
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
			
			List<Integer> puzzleConfig = Arrays.stream(scanner.nextLine().split(","))
		            .map(Integer::parseInt)
		            .collect(Collectors.toList());
			
			return new Board(puzzleType, puzzleConfig);
		} catch (NumberFormatException e) {
			scanner.close();
			throw new UnsupportedInputException("");
		} catch (NoSuchElementException e) {
			scanner.close();
			throw new UnsupportedInputException("");
		}
    }
}
