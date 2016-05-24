package Practica1;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.ResultFunction;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.TreeSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.local.SimulatedAnnealingSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;
import aima.core.search.uninformed.DepthFirstSearch;
import aima.core.search.uninformed.UniformCostSearch;
import aima.core.search.uninformed.BreadthFirstSearch;

/**
 * @author Jorge Sanz
 * 
 */

public class FichasDemo {
	static Fichas inicial = new Fichas(new int[] { 1, 1, 1, 0, 2, 2, 2});;

	public static void main(String[] args) {
		double t1=System.currentTimeMillis();
		fichas(new BreadthFirstSearch(new GraphSearch()),inicial);
		//Funciona
		//eightPuzzle(new BreadthFirstSearch(new GraphSearch()),boardWithThreeMoveSolution);
		//eightPuzzle(new BreadthFirstSearch(new GraphSearch()),random1);
		//eightPuzzle(new BreadthFirstSearch(new GraphSearch()),extreme);
		
		//Peta en tiempo
		//eightPuzzle(new BreadthFirstSearch(new TreeSearch()),boardWithThreeMoveSolution);
		//eightPuzzle(new BreadthFirstSearch(new TreeSearch()),random1);
		//eightPuzzle(new BreadthFirstSearch(new TreeSearch()),extreme);
		
		
		//eightPuzzle(new DepthFirstSearch(new GraphSearch()),boardWithThreeMoveSolution);
		//eightPuzzle(new DepthFirstSearch(new GraphSearch()),random1);
		//eightPuzzle(new DepthFirstSearch(new GraphSearch()),extreme);
		 
		//Peta en tiempo
		//eightPuzzle(new DepthFirstSearch(new TreeSearch()),boardWithThreeMoveSolution);
		//eightPuzzle(new DepthFirstSearch(new TreeSearch()),random1);
		//eightPuzzle(new DepthFirstSearch(new TreeSearch()),extreme);
		
		
		//eightPuzzle(new DepthLimitedSearch(9),boardWithThreeMoveSolution);
		//eightPuzzle(new DepthLimitedSearch(9),random1);
		//eightPuzzle(new DepthLimitedSearch(9),extreme);
		
		
		
		//eightPuzzle(new IterativeDeepeningSearch(),boardWithThreeMoveSolution);
		//eightPuzzle(new IterativeDeepeningSearch(),random1);
		//eightPuzzle(new IterativeDeepeningSearch(),extreme);
		
		double t2=System.currentTimeMillis();
		System.out.println(t2-t1);
		
	}

	public void executeActions(List<Action> actions,Problem problem){
		Object initialState=problem.getInitialState();
		ResultFunction resultFunction=problem.getResultFunction();
		System.out.println("GOAL STATE");
		System.out.println(FichasGoalTest.getGoalState());
		Object state =initialState;
		System.out.println(state);
		
		for(Action action : actions){
			System.out.println(action.toString());
			state=resultFunction.result(state, action);
			System.out.println(state);
			System.out.println("- - -");
		}
	}
	private static void fichas(Search search,Fichas inicial) {
		try {
			Problem problem = new Problem(inicial, FichasFunctionFactory
					.getActionsFunction(), FichasFunctionFactory
					.getResultFunction(), new FichasGoalTest());
			System.out.println("algo2");
			SearchAgent agent = new SearchAgent(problem, search);
			System.out.println("algo3");
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private static void printInstrumentation(Properties properties) {
		Iterator<Object> keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			System.out.println(key + " : " + property);
		}

	}

	private static void printActions(List<Action> actions) {
		for (int i = 0; i < actions.size(); i++) {
			String action = actions.get(i).toString();
			System.out.println(action);
		}
	}

}