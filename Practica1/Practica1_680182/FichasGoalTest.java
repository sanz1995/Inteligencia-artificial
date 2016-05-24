package Practica1;

import aima.core.search.framework.GoalTest;

/**
 * @author Ravi Mohan
 * 
 */
public class FichasGoalTest implements GoalTest {
	static Fichas goal = new Fichas(new int[] { 2 ,2 ,2 ,0 ,1 ,1 ,1 });

	public boolean isGoalState(Object state) {
		Fichas board = (Fichas) state;
		return board.equals(goal);
	}

	public static String getGoalState() {
		// TODO Auto-generated method stub
		System.out.println("asdf");
		return goal.toString();
	}
}