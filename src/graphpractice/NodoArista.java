package graphpractice;

/**
 *
 * @author JESUS
 */
public class NodoArista {
    AristaL data;
    NodoArista siguiente;
    
    public NodoArista(){
        data = null;
        siguiente = null;
    }
    
    public NodoArista(AristaL newData){
        data = newData;
        siguiente = null;
    }
    
    public AristaL getData(){
        return data;
    }
    
    public NodoArista getSiguiente(){
        return siguiente;
    }
    
    public void setData(AristaL newData){
        data = newData;
    }
    
    public void setSiguiente(NodoArista sig){
        siguiente = sig;
    }
}
