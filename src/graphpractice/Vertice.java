package graphpractice;

/**
 *
 * @author JESUS
 */

/*
Esta clase representa a cada vertice dentro de un grafo.
Un vertice tiene un numero (su posicion dentro del arreglo de vertices del grafo) y un nombre (su identificador) correspondientes.
Al construir un vertice, su numero es -1. El grafo en si le asignara una posicion dependiendo del problema.
*/
public class Vertice {
    int numeroPosicion;
    String nombre;
    
    public Vertice(String nuevoNombre){
        nombre = nuevoNombre;
        numeroPosicion = -1;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public int getNumeroPosicion(){
        return numeroPosicion;
    }
    
    public boolean comparar(Vertice comparando){ //Compara dos vertices. Si los nombres son iguales, los vertices son iguales.
        return nombre.equals(comparando.getNombre());
    }
    
    public void setNumeroPosicion(int numeroPos){
        numeroPosicion = numeroPos;
    }
    
    @Override
    public String toString(){
        String retornable = nombre + " (" + numeroPosicion + ")";
        return retornable;
    }
}
