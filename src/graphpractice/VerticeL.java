package graphpractice;

/**
 *
 * @author JESUS
 */
public class VerticeL {
    String nombre;
    int numeroPosicion;
    ListaAristas aristas;
    
    public VerticeL(String nuevonombre){
        nombre = nuevonombre;
        numeroPosicion = -1;
        aristas = new ListaAristas();
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public int getNumeroPosicion(){
        return numeroPosicion;
    }
    
    public void setNumeroPosicion(int numeroPos){
        numeroPosicion = numeroPos;
    }
    
    public boolean comparar(VerticeL comparando){ //Compara dos vertices. Si los nombres son iguales, los vertices son iguales.
        return nombre.equals(comparando.getNombre());
    }
    
    @Override
    public String toString(){
        String retornable = nombre + " (" + numeroPosicion + ")";
        return retornable;
    }
}
