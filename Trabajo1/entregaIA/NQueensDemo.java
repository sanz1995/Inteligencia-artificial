package tp6;

import java.util.Random;
import aima.core.environment.nqueens.AttackingPairsHeuristic;
import aima.core.environment.nqueens.NQueensBoard;
import aima.core.environment.nqueens.NQueensFunctionFactory;
import aima.core.environment.nqueens.NQueensGoalTest;
import aima.core.search.framework.Problem;
import aima.core.search.framework.SearchAgent;
import aima.core.search.local.HillClimbingSearch;
import aima.core.search.local.HillClimbingSearchFlat;
import aima.core.search.local.HillClimbingSearchFlat2;

public class NQueensDemo {

	public static void main(String[] args){
		
		
		long t1=System.currentTimeMillis();
		/*
		int exitos=0;
		int pasosExito=0;
		int pasosFallo=0;
		Resultados r=null;
		for(int i=0;i<1000;i++){
			r=nQueens(new HillClimbingSearchFlat(new AttackingPairsHeuristic()),8);
			if(r.exito()){
				exitos++;
				pasosExito+=r.pasos();
			}else{
				pasosFallo+=r.pasos();
			}
			//System.out.println(i);
			//pasos+=r.pasos();
		}
		System.out.println(exitos+" exitos de "+1000+" intentos");
		System.out.println("Numero medio de pasos en exito " + pasosExito/exitos);
		System.out.println("Numero medio de pasos en fallo " + pasosFallo/(1000-exitos));
		*/
		
		double intentos=0;
		Resultados r=null;
		for(int i=0;i<1000;i++){
			intentos+=RandomRestartHillClimbingFlat2(8);
			//System.out.println(i);
			//pasos+=r.pasos();
		}
		System.out.println(intentos/1000);
		long t2=System.currentTimeMillis();
		System.out.println(t2-t1);
	}
	private static NQueensBoard random(int n){
		int[][] t=new int[n][n];
		Random r=new Random();
		for(int i=0;i<n;i++){
			t[i][r.nextInt(n)]=1;
		}
		return new NQueensBoard(t);
	}
	private static Resultados nQueens(HillClimbingSearch search,int n) {
		try {
			Problem problem = new Problem(random(n), 
					NQueensFunctionFactory.getCActionsFunction(),
					NQueensFunctionFactory.getResultFunction(), 
					new NQueensGoalTest());
			new SearchAgent(problem, search);
			return new Resultados(
					(search.getOutcome().toString()).equals("SOLUTION_FOUND"),
						search.getPasos(),search.getLastSearchState());
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	private static Resultados nQueens(HillClimbingSearchFlat2 search,int n) {
		try {
			Problem problem = new Problem(random(n), 
					NQueensFunctionFactory.getCActionsFunction(),
					NQueensFunctionFactory.getResultFunction(), 
					new NQueensGoalTest());
			new SearchAgent(problem, search);
			return new Resultados(
					(search.getOutcome().toString()).equals("SOLUTION_FOUND"),
						search.getPasos(),search.getLastSearchState());
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static Resultados nQueens(HillClimbingSearchFlat search,int n) {
		try {
			Problem problem = new Problem(random(n), 
					NQueensFunctionFactory.getCActionsFunction(),
					NQueensFunctionFactory.getResultFunction(), 
					new NQueensGoalTest());
			new SearchAgent(problem, search);
			return new Resultados(
					(search.getOutcome().toString()).equals("SOLUTION_FOUND"),
						search.getPasos(),search.getLastSearchState());
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static int RandomRestartHillClimbing(int n){
		Resultados res=null;
		boolean exito=false;
		int intentos=0;
		int total=0;
		while(!exito){
			intentos++;
			res=nQueens(new HillClimbingSearch(new AttackingPairsHeuristic()),n);
			if(res!=null){
				exito=res.exito();
				total+=res.pasos();
			}
		}
		System.out.println(res.estadoFinal());
		System.out.println("Exito en el intento "+ intentos);
		System.out.println("Numero medio de pasos "+ total/intentos);
		return intentos;
	}
	private static int RandomRestartHillClimbingFlat(int n){
		Resultados res=null;
		boolean exito=false;
		int intentos=0;
		int total=0;
		while(!exito){
			intentos++;
			res=nQueens(new HillClimbingSearchFlat(new AttackingPairsHeuristic()),n);
			if(res!=null){
				exito=res.exito();
				total+=res.pasos();
			}
		}
		System.out.println(res.estadoFinal());
		System.out.println("Exito en el intento "+ intentos);
		System.out.println("Numero medio de pasos "+ total/intentos);
		return intentos;
	}
	private static int RandomRestartHillClimbingFlat2(int n){
		Resultados res=null;
		boolean exito=false;
		int intentos=0;
		int total=0;
		while(!exito){
			intentos++;
			res=nQueens(new HillClimbingSearchFlat2(new AttackingPairsHeuristic()),n);
			if(res!=null){
				exito=res.exito();
				total+=res.pasos();
			}
		}
		System.out.println(res.estadoFinal());
		System.out.println("Exito en el intento "+ intentos);
		System.out.println("Numero medio de pasos "+ total/intentos);
		return intentos;
	}
}
