package graphpractice;

/**
 *
 * @author JESUS
 */
public class AristaL {
    int destino;
    float peso;
    
    public AristaL(int nuevoDestino){
        destino = nuevoDestino;
        peso = 0;
    }
    
    public AristaL(int nuevoDestino, float nuevoPeso){
        destino = nuevoDestino;
        peso = nuevoPeso;
    }
    
    public int getDestino(){
        return destino;
    }
    
    public float getPeso(){
        return peso;
    }
    
    
}
