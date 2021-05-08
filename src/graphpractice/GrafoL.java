package graphpractice;

import java.util.Arrays;

/**
 *
 * @author JESUS
 */
public class GrafoL {

    int numeroDeVertices;
    static int MAXVERTICES = 10;
    VerticeL[] tablaVertices;
    int[] gradoEntrada;
    int[] gradoSalida;
    
    public GrafoL(){
        this(MAXVERTICES);
    }
    
    public GrafoL(int numVert){
        int n = numVert;
        numeroDeVertices = 0;
        tablaVertices = new VerticeL[n];
        gradoEntrada = new int[n];
        gradoSalida = new int[n];
    }
    
    public int numVertice(String nombreVertice){
        VerticeL temporal = new VerticeL(nombreVertice);
        boolean encontrado = false;
        int i;
        for(i = 0; i < numeroDeVertices;i++){
            encontrado = tablaVertices[i].comparar(temporal);
            if (encontrado){
                break;
            }
        }
        return (encontrado) ? i:-1;
    }
    
    public void añadirVertice(String nombre){
        if(numeroDeVertices+1 > tablaVertices.length){
            System.out.println("Limite de vertices excedido.");
        } else {
            boolean existeVertice = (numVertice(nombre) >= 0);
            if(!existeVertice){
                VerticeL nuevoVert = new VerticeL(nombre);
                nuevoVert.setNumeroPosicion(numeroDeVertices);
                tablaVertices[numeroDeVertices] = nuevoVert;
                numeroDeVertices++;
            } else {
                System.out.println("El nodo ya existe.");
            }
        }
    }
    
    public void añadirArista(String origen, String destino) throws Exception{
        añadirArista(origen, destino, 0);
    }
    
    public void añadirArista(int origen, int destino, int peso) throws Exception{
        if(origen < 0 || destino < 0){
            throw new Exception("Vertice inexistente.");
        }
        AristaL nuevaA = new AristaL(destino, peso);
        tablaVertices[origen].aristas.agregar(nuevaA);
        gradoSalida[origen]++;
        gradoEntrada[destino]++;
    }
    
    public void añadirArista(String origen, String destino, int peso) throws Exception{
        int numeroOrigen = numVertice(origen);
        int numeroDestino = numVertice(destino);
        if(numeroOrigen < 0 || numeroDestino < 0){
            throw new Exception("Vertice inexistente.");
        }
        AristaL nuevaA = new AristaL(numeroDestino, peso);
        tablaVertices[numeroOrigen].aristas.agregar(nuevaA);
        gradoSalida[numeroOrigen]++;
        gradoEntrada[numeroDestino]++;
    }
    
   
    public void imprimir(){
        if(numeroDeVertices == 0){
            System.out.println("[]");
            return;
        }
        for(VerticeL vert:tablaVertices){
            System.out.print("["+vert.nombre+"]");
            NodoArista puntero = vert.aristas.cabeza;
            if (puntero == null){
                System.out.println("-> []");
            } else {
                while(puntero != null){
                    System.out.print("-> ");
                    System.out.print("["+tablaVertices[puntero.data.destino].getNombre()+"|"+puntero.data.peso+"]");
                    puntero = puntero.siguiente;
                }
                System.out.println("");
            }
            
        }
    }
    
    public static void main(String[] args) throws Exception{
        GrafoL grafo = new GrafoL(5);
        
        grafo.añadirVertice("1");
        grafo.añadirVertice("2");
        grafo.añadirVertice("3");
        grafo.añadirVertice("4");
        grafo.añadirVertice("5");
        
        grafo.añadirArista("1", "3");
        grafo.añadirArista("1", "4");
        grafo.añadirArista("5", "1");
        grafo.añadirArista("5", "2");
        grafo.añadirArista("5", "4");
        grafo.añadirArista("3", "1");
        grafo.añadirArista("2", "3");
        
        System.out.println("Grafo representado en lista de adyacencia:\n");
        grafo.imprimir();
        
        System.out.println("Resultado de recorrido en anchura): ");
        System.out.println(Arrays.toString(RecorridosGrafos.profundidad(grafo, "5")));
    }
}
