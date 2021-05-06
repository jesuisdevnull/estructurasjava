package graphpractice;

/**
 *
 * @author JESUS
 */
public class ListaAristas {
    NodoArista cabeza;
    int numeroAristas;
    
    public ListaAristas(){
        cabeza = null;
        numeroAristas = 0;
    }
    
    public boolean estaVacia(){
        return cabeza == null;
    }
    
    public void agregar(AristaL newArista){
        if (estaVacia()) {
            cabeza = new NodoArista(newArista);
            numeroAristas++;
        } else {
            if (cabeza.siguiente==null){
                cabeza.siguiente = new NodoArista(newArista);
                numeroAristas++;
            } else  {
                NodoArista actual = cabeza.siguiente;
                while (actual.siguiente != null){
                    actual = actual.siguiente;
                }
                actual.siguiente = new NodoArista(newArista);
            }
        }
    }
    
    public boolean contieneArista(int destino){
        if(estaVacia()){
            return false;
        }
        if (cabeza.data.destino == destino){
            return true;
        } else {
            NodoArista actual = cabeza;
            while(actual != null){
                actual = actual.siguiente;
                if (actual.data.destino == destino){
                    return true;
                }
            }
        }
        return false;
    }
    
    
    
}
