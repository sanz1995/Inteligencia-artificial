package aima.core.environment.eightpuzzle;

import aima.core.search.framework.HeuristicFunction;
import aima.core.util.datastructure.XYLocation;

/**
 * @author Ravi Mohan
 * 
 */
public class ManhattanHeuristicFunction implements HeuristicFunction {

	public double h(Object state) {
		EightPuzzleBoard board = (EightPuzzleBoard) state;
		EightPuzzleBoard goal = (EightPuzzleBoard) EightPuzzleGoalTest.getGoalState();
		int retVal = 0;
		for (int i = 1; i < 9; i++) {
			XYLocation loc = board.getLocationOf(i);
			XYLocation locGoal = goal.getLocationOf(i);
			retVal += evaluateManhattanDistanceOf(i, loc, locGoal);
		}
		return retVal;

	}

	public int evaluateManhattanDistanceOf(int i, XYLocation loc, XYLocation locGoal) {
		int retVal = -1;
		int xpos = loc.getXCoOrdinate();
		int ypos = loc.getYCoOrdinate();
		retVal = Math.abs(xpos - locGoal.getXCoOrdinate()) + Math.abs(ypos - locGoal.getYCoOrdinate());
		return retVal;
	}
}