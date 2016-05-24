package tp6;

public class Resultados {

	private boolean exito;
	private int pasos;
	private Object estadoFinal;
	public Resultados(boolean ex,int p,Object est){
		exito=ex;
		pasos=p;
		estadoFinal=est;
	}
	public boolean exito(){
		return exito;
	}
	
	public int pasos(){
		return pasos;
	}
	public Object estadoFinal(){
		return estadoFinal;
	}
}
