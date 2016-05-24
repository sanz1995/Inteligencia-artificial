package Practica1;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.eightpuzzle.EightPuzzleFunctionFactory;
import aima.core.environment.eightpuzzle.EightPuzzleGoalTest;
import aima.core.environment.eightpuzzle.ManhattanHeuristicFunction;
import aima.core.environment.eightpuzzle.MisplacedTilleHeuristicFunction;
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

public class EightPuzzleDemo {
	static EightPuzzleBoard boardWithThreeMoveSolution = new EightPuzzleBoard(
			new int[] { 1, 2, 5, 3, 4, 0, 6, 7, 8 });;

	static EightPuzzleBoard random1 = new EightPuzzleBoard(new int[] { 1, 4, 2,
			7, 5, 8, 3, 0, 6 });

	static EightPuzzleBoard extreme = new EightPuzzleBoard(new int[] { 0, 8, 7,
			6, 5, 4, 3, 2, 1 });

	public static void main(String[] args) {
		double t1=System.currentTimeMillis();
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
		System.out.println(EightPuzzleGoalTest.getGoalState());
		Object state =initialState;
		System.out.println(state);
		
		for(Action action : actions){
			System.out.println(action.toString());
			state=resultFunction.result(state, action);
			System.out.println(state);
			System.out.println("- - -");
		}
	}
	private static void eightPuzzle(Search search,EightPuzzleBoard inicial) {
		System.out.println("\nEightPuzzleDemo recursive DLS (9) -->");
		try {
			Problem problem = new Problem(inicial, EightPuzzleFunctionFactory
					.getActionsFunction(), EightPuzzleFunctionFactory
					.getResultFunction(), new EightPuzzleGoalTest());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
/**
	private static void eightPuzzleIDLSDemo() {
		System.out.println("\nEightPuzzleDemo Iterative DLS -->");
		try {
			Problem problem = new Problem(random1, EightPuzzleFunctionFactory
					.getActionsFunction(), EightPuzzleFunctionFactory
					.getResultFunction(), new EightPuzzleGoalTest());
			Search search = new IterativeDeepeningSearch();
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void eightPuzzleGreedyBestFirstDemo() {
		System.out
				.println("\nEightPuzzleDemo Greedy Best First Search (MisplacedTileHeursitic)-->");
		try {
			Problem problem = new Problem(boardWithThreeMoveSolution,
					EightPuzzleFunctionFactory.getActionsFunction(),
					EightPuzzleFunctionFactory.getResultFunction(),
					new EightPuzzleGoalTest());
			Search search = new GreedyBestFirstSearch(new GraphSearch(),
					new MisplacedTilleHeuristicFunction());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void eightPuzzleGreedyBestFirstManhattanDemo() {
		System.out
				.println("\nEightPuzzleDemo Greedy Best First Search (ManhattanHeursitic)-->");
		try {
			Problem problem = new Problem(boardWithThreeMoveSolution,
					EightPuzzleFunctionFactory.getActionsFunction(),
					EightPuzzleFunctionFactory.getResultFunction(),
					new EightPuzzleGoalTest());
			Search search = new GreedyBestFirstSearch(new GraphSearch(),
					new ManhattanHeuristicFunction());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void eightPuzzleAStarDemo() {
		System.out
				.println("\nEightPuzzleDemo AStar Search (MisplacedTileHeursitic)-->");
		try {
			Problem problem = new Problem(random1, EightPuzzleFunctionFactory
					.getActionsFunction(), EightPuzzleFunctionFactory
					.getResultFunction(), new EightPuzzleGoalTest());
			Search search = new AStarSearch(new GraphSearch(),
					new MisplacedTilleHeuristicFunction());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void eightPuzzleSimulatedAnnealingDemo() {
		System.out.println("\nEightPuzzleDemo Simulated Annealing  Search -->");
		try {
			Problem problem = new Problem(random1, EightPuzzleFunctionFactory
					.getActionsFunction(), EightPuzzleFunctionFactory
					.getResultFunction(), new EightPuzzleGoalTest());
			SimulatedAnnealingSearch search = new SimulatedAnnealingSearch(
					new ManhattanHeuristicFunction());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			System.out.println("Search Outcome=" + search.getOutcome());
			System.out.println("Final State=\n" + search.getLastSearchState());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleAStarManhattanDemo() {
		System.out
				.println("\nEightPuzzleDemo AStar Search (ManhattanHeursitic)-->");
		try {
			Problem problem = new Problem(random1, EightPuzzleFunctionFactory
					.getActionsFunction(), EightPuzzleFunctionFactory
					.getResultFunction(), new EightPuzzleGoalTest());
			Search search = new AStarSearch(new GraphSearch(),
					new ManhattanHeuristicFunction());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
*/
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