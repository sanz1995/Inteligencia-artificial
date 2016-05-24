package aima.core.util.math;
/**
 *
 * @author JAB
 */
public class Biseccion {
 
    /**
    * Este metodo crea un funcion a la cual se le aplicara el m�todo de
    * Biseccion teniendo como parametro de entrada un double x, el cual
    * sirve para la construccion de la funcion dentro del metodo
    * @param x
    * @return
    */
	int generated_nodes=1;
	int depth =1;
	
   private double funcion(double b){
        return ((b*(1-Math.pow(b, depth))/(1-b))-generated_nodes);
    }
    /**
     * Metodo de Biseccion el cual le halla las raices de una funciones en un intervalo
     * ingresado como  parametro de entrada [a, b] y un el error con el cual
     * deseamos hallar nuestra funcion
     * @param a
     * @param b
     * @param error
     * @return
     */
    public double metodoDeBiseccion(double a, double b, double error){
        double m = 0.0;
  
        if((funcion(a) * funcion(b)) > 0){
            System.out.println("Error en el intervalo, en ese intervalo no existen raices");
        }else{

             m = (a + b) /(double) 2;

            do{
             //   System.out.println("["+a+","+b+"]"+"f("+a+")="+funcion(a)+"  f("+b+")="+funcion(b));

                 if((funcion(a) * funcion(m)) >0){ // cero en intervalo medio b 
                     a = m;
                 }else if((funcion(b) * funcion(m)) >0 ){ // cero en intervalo a medio
                    b = m;
                 }
                 m = (a + b) /(double) 2; 

                } while(Math.abs(b-a) >= error);
        }
         //System.out.println("valor de la funcion: "+funcion(m));
        return  m;
    }
    public void setDepth (int depth){
    	this.depth = depth;
    }
    
    public void setGeneratedNodes (int generated_nodes){
    	this.generated_nodes = generated_nodes;
    }
    
    public int getDepth (){
    	return depth;  
    }
    
    public int getGeneratedNodes (){
    	return generated_nodes;
    }
 
 
}