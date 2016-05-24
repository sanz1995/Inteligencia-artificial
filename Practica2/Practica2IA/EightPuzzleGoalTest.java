package aima.core.environment.eightpuzzle;

import aima.core.search.framework.GoalTest;

/**
 * @author Ravi Mohan
 * 
 */
public class EightPuzzleGoalTest implements GoalTest {
	static EightPuzzleBoard goal = new EightPuzzleBoard(new int[] { 0, 1, 2, 3, 4, 5,
			6, 7, 8 });

	public boolean isGoalState(Object state) {
		EightPuzzleBoard board = (EightPuzzleBoard) state;
		return board.equals(goal);
	}

	public static EightPuzzleBoard getGoalState() {
		// TODO Auto-generated method stub
		return goal;
	}

	public static void setGoalState(EightPuzzleBoard finalState) {
		goal=finalState;
		
	}
}