package Practica1;

import java.util.Properties;

import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.eightpuzzle.EightPuzzleFunctionFactory;
import aima.core.environment.eightpuzzle.EightPuzzleGoalTest;
import aima.core.environment.eightpuzzle.ManhattanHeuristicFunction;
import aima.core.environment.eightpuzzle.MisplacedTilleHeuristicFunction;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.informed.AStarSearch;
import aima.core.util.math.Biseccion;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;
import aima.gui.demo.search.GenerateInitialEightPuzzleBoard;
/**
 * @author Jorge Sanz
 * 
 */

public class EightPuzzlePract2 {
	
	
	static Biseccion bf =new Biseccion();
	
	public static void main(String args[]){
		cabecera();
		for (int i=2;i<25;i++){
			experimento(i);
		}
	}
	
	
			
	/**
	 * Calcula los nodos generados y el factor de ramificación para 4 tipos de 
	 * búsqueda y profundidades entre 2 y 24 y lo muestra en una tabla.
	 *
	 */
	private static void experimento(int depth){
		double b1=0;
		double b2=0;
		double b3=0;
		double b4=0;
		Properties p1=null;
		Properties p2=null;
		Properties p3=null;
		Properties p4=null;
		float nG1=0;
		float nG2=0;
		float nG3=0;
		float nG4=0;
		int d;
		
		int i=0;
		
		EightPuzzleBoard initialState;
		
		while(i<100){
			
			initialState=GenerateInitialEightPuzzleBoard.randomIni();
			
			//se intenta generar un ocho estado de profundidad depth desde el inicial
			EightPuzzleGoalTest.setGoalState(GenerateInitialEightPuzzleBoard.random(depth,initialState));
			
			
			//se comprueba la profundidad con la busqueda A* con Manhattan (que es optimo)
			p4=eightPuzzle(new AStarSearch(new GraphSearch(),new ManhattanHeuristicFunction()),initialState);
			d=(int)Double.parseDouble(p4.getProperty("pathCost"));
			
			if(d==depth){//si no es optimo busca otro
				
				//obtenemos nodos generados en BFS
				p1=eightPuzzle(new BreadthFirstSearch(new GraphSearch()),initialState);
				nG1+=Integer.parseInt(p1.getProperty("nodesGenerated"));
				
				
				//obtenemos nodos generados en IDS
				if(d<11){//a partir de 10 tarda demasiado, asi que no seguimos buscando
					p2=eightPuzzle(new IterativeDeepeningSearch(),initialState);
					nG2+=Integer.parseInt(p2.getProperty("nodesGenerated"));
				}else{
					nG2=0;
				}
				
				
				//obtenemos nodos generados en A* Missplaced
				p3=eightPuzzle(new AStarSearch(new GraphSearch(),new MisplacedTilleHeuristicFunction()),initialState);
				nG3+=Integer.parseInt(p3.getProperty("nodesGenerated"));
				
				//obtenemos nodos generados en A* Manhattan
				nG4+=Integer.parseInt(p4.getProperty("nodesGenerated"));
				
				
				i++;
			}
		}
		
		//se marca la profundidad
		bf.setDepth(depth);
		
		//se marca la media de nodos generados en 100 iteraciones con busqueda BFS 
		bf.setGeneratedNodes(Math.round(nG1/100));
		//se obtiene el factor de ramificación efectivo
		b1=bf.metodoDeBiseccion(1.000001,4,0.000001);
		
		
		//se marca la media de nodos generados en 100 iteraciones con busqueda A* Missplaced
		bf.setGeneratedNodes(Math.round(nG3/100));
		//se obtiene el factor de ramificación efectivo
		b3=bf.metodoDeBiseccion(1.000001,4,0.000001);
		
		//se marca la media de nodos generados en 100 iteraciones con busqueda A* Manhattan
		bf.setGeneratedNodes(Math.round(nG4/100));
		//se obtiene el factor de ramificación efectivo
		b4=bf.metodoDeBiseccion(1.000001,4,0.000001);
		
		
		if(depth<11){//solo inferiores a 11 en profundidad
			//se marca la media de nodos generados en 100 iteraciones con busqueda IDS
			bf.setGeneratedNodes(Math.round(nG2/100));
			//se obtiene el factor de ramificación efectivo
			b2=bf.metodoDeBiseccion(1.000001,4,0.000001);
		}
		dibujar(depth,Math.round(nG1/100),Math.round(nG2/100),Math.round(nG3/100),Math.round(nG4/100),b1,b2,b3,b4);

	}
			
	/**
	 * Muestra la cabecera
	 */
	private static void cabecera(){
		System.out.println("---------------------------------------------------------------------------------");
		System.out.printf("||   ||         ");
		System.out.printf("Nodos Generados");
		System.out.printf("           ||                 ");
		System.out.printf("b*");
		System.out.println("                || ");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.printf("||  d||   BFS  |   IDS  | A*h(1) | A*h(2) ||");
		System.out.println("   BFS  |   IDS  | A*h(1) | A*h(2) ||");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------------");
	}
	/**
	 * Muestra una linea con los nodos generados y factores de ramificación que
	 * aparecen en los parametros
	 */
	private static void dibujar(int d,int nG1,int nG2,int nG3,int nG4,double b1,
												double b2,double b3,double b4){
		System.out.printf("|| ");
		System.out.printf("%2d",d);
		System.out.printf("|| ");
		System.out.printf("%7d",nG1);
		System.out.printf("| ");
		if(d<11){
			System.out.printf("%7d",nG2);
		}else{
			System.out.printf("   --- ");
		}
		System.out.printf("| ");
		System.out.printf("%7d",nG3);
		System.out.printf("| ");
		System.out.printf("%7d",nG4);
		System.out.printf("|| ");
		System.out.printf("%2.5f",b1);
		System.out.printf("| ");
		if(d<11){
			System.out.printf("%2.5f",b2);
		}else{
			System.out.printf("   --- ");
		}
		System.out.printf("| ");
		System.out.printf("%2.5f",b3);
		System.out.printf("| ");
		System.out.printf("%2.5f",b4);
		System.out.println("||");
	}
	
	
	/**
	 * Realiza una busqueda a partir de un estado inicial y devuelve las propiedades
	 * de esa búsqueda 
	 * 
	 */
	private static Properties eightPuzzle(Search search,EightPuzzleBoard initialState) {
		try {
			Problem problem = new Problem(initialState, EightPuzzleFunctionFactory
					.getActionsFunction(), EightPuzzleFunctionFactory
					.getResultFunction(), new EightPuzzleGoalTest());
			SearchAgent agent = new SearchAgent(problem, search);
			//esto
			return agent.getInstrumentation();
		} catch (Exception e) {
			e.printStackTrace();
			
			//esto
			return null;
		}
	}
}








