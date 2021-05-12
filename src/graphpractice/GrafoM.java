package graphpractice;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JESUS
 */

/*
Representacion de un grafo no ponderado utilizando una matriz de adyacencia.
TODO: Metodos de getter para los grados de entrada y de salida
 */
public class GrafoM {

    int[][] matrizAdyacencia; //Matriz de adyacencia del grafo.
    static int maxVertices = 10; //Numero maximo de vertices default en caso de que no se construya con un limite explicito.
    Vertice[] vertices; //Arreglo que contiene a todos los vertices del grafo. Se utiliza para evitar vertices repetidos.
    int numeroVertices; //Numero de vertices que tiene el grafo actualmente.
    int[] gradoEntrada;
    int[] gradoSalida;

    public GrafoM() { //Primer constructor. Utiliza el numero maximo de vertices ya establecido.
        this(maxVertices);
    }
    
    public GrafoM(String modo){
    
    }

    public GrafoM(int maxVert) { //Segundo constructor. Crea un grafo con la cantidad de vertices que se pasa.
        matrizAdyacencia = new int[maxVert][maxVert];
        vertices = new Vertice[maxVert];
        numeroVertices = 0;
        gradoSalida = new int[maxVert];
        gradoEntrada = new int[maxVert];
    }
    
    public void setMatriz(int[][] m){
        matrizAdyacencia = m;
    }
    
    public void setListaVertices(Vertice[] verts){
        vertices = verts;
    }
    
    public void setNumeroVertices(int num){
        numeroVertices = num;
    }

    public int numVertice(String vs) { //Metodo que revisa la existencia de un vertice dentro del grafo, y lo busca.
        Vertice temp = new Vertice(vs); //Vertice temporal para comparar.
        boolean encontrado; //Bandera de salida.
        int i; //Posicion a retornar, posiblemente.
        for (i = 0; (i < numeroVertices); i++) { //La sintaxis aqui es muy confusa. Solamente hay la instruccion 2, la que controla la ejecucion del ciclo. Es decir que este se ejecutara siempre que "i" sea menor al numero de vertices, y que no se haya encontrado el vertice en cuestion.
            encontrado = vertices[i].comparar(temp); //Si el vertice contenido en la posicion i del arreglo de vertices ya existe, bandera falsa.
            if (encontrado) {
                break;
            }
        }
        return (i < numeroVertices) ? i : -1; //Operador ternario. Si (i<numeroVertices) es verdadero, devolver i. Si no, devolver -1.
    }

    public void añadirVertice(String nombreVert) {
        if (!(numeroVertices+1 > vertices.length)) {
            boolean existeVertice = (numVertice(nombreVert) >= 0);
            if (!existeVertice) {
                Vertice nuevoV = new Vertice(nombreVert);
                nuevoV.setNumeroPosicion(numeroVertices);
                vertices[numeroVertices] = nuevoV;
                numeroVertices++;
            } else {
                System.out.println("El vertice ya existe.");
            }
        } else {
            System.out.println("Limite de vertices superado.");
        }
    }

    public void añadirArista(String origen, String destino) {
        int fila = numVertice(origen);
        if (fila < 0) {
            System.out.println("ERROR: El vertice \"" + origen + "\" no existe.");
            return;
        }
        int columna = numVertice(destino);
        if (columna < 0) {
            System.out.println("ERROR: El vertice \"" + destino + "\" no existe.");
            return;
        }
        matrizAdyacencia[fila][columna] = 1;
        gradoSalida[fila]++;
        gradoEntrada[columna]++;
    }

    public void añadirArista(int fila, int columna) {
        if (fila < 0 || columna < 0) {
            System.out.println("ERROR: Por lo menos una de las aristas no existe.");
            return;
        }
        matrizAdyacencia[fila][columna] = 1;
        gradoSalida[fila]++;
        gradoEntrada[columna]++;
    }
    
    public void añadirArista(String origen, String destino, int peso) {
        int fila = numVertice(origen);
        if (fila < 0) {
            System.out.println("ERROR: El vertice \"" + origen + "\" no existe.");
            return;
        }
        int columna = numVertice(destino);
        if (columna < 0) {
            System.out.println("ERROR: El vertice \"" + destino + "\" no existe.");
            return;
        }
        matrizAdyacencia[fila][columna] = peso;
        gradoSalida[fila]++;
        gradoEntrada[columna]++;
    }

    public void añadirArista(int fila, int columna, int peso) {
        if (fila < 0 || columna < 0) {
            System.out.println("ERROR: Por lo menos una de las aristas no existe.");
            return;
        }
        matrizAdyacencia[fila][columna] = peso;
        gradoSalida[fila]++;
        gradoEntrada[columna]++;
    }

    public void removerArista(String origen, String destino) {
        int fila = numVertice(origen);
        if (fila < 0) {
            System.out.println("ERROR: El vertice \"" + origen + "\" no existe.");
            return;
        }
        int columna = numVertice(destino);
        if (columna < 0) {
            System.out.println("ERROR: El vertice \"" + destino + "\" no existe.");
            return;
        }
        matrizAdyacencia[fila][columna] = 0;
        gradoSalida[fila]--;
        gradoEntrada[columna]--;
    }

    public void removerArista(int fila, int columna) {
        if (fila < 0 || columna < 0) {
            System.out.println("ERROR: Por lo menos una de las aristas no existe.");
            return;
        }
        matrizAdyacencia[fila][columna] = 0;
        gradoSalida[fila]--;
        gradoEntrada[columna]--;
    }

    public boolean sonAdyacentes(String origen, String destino) throws Exception {
        int fila = numVertice(origen);
        if (fila < 0) {
            throw new Exception("Vertice inexistente.");
        }
        int columna = numVertice(destino);
        if (columna < 0) {
            throw new Exception("Vertice inexistente.");
        }
        return matrizAdyacencia[fila][columna] != 0;
    }

    public boolean sonAdyacentes(int fila, int columna) throws Exception {
        if (fila < 0 || columna < 0) {
            throw new Exception("Vertice inexistente.");
        }
        return matrizAdyacencia[fila][columna] != 0;
    }

    public String matrizCaminos() throws Exception {
        if (numeroVertices == 0) {
            return "[]";
        } else {
            int[][] warshall = AlgoritmosGrafos.warshall(this);
            StringBuilder sb = new StringBuilder();
            int espacios = vertices[0].getNombre().length();
            for (int i = 0; i <= espacios; i++) {
                sb.append(" ");
            }
            for (int i = 0; i < numeroVertices; i++) {
                sb.append(vertices[i].getNombre().charAt(0)).append(" ");
            }
            sb.deleteCharAt(sb.lastIndexOf(" "));
            sb.append("\n");
            for (int i = 0; i < numeroVertices; i++) {
                sb.append(vertices[i].getNombre()).append(" ");
                for (int j = 0; j < numeroVertices; j++) {
                    sb.append(warshall[i][j]).append(" ");
                }
                sb.deleteCharAt(sb.lastIndexOf(" "));
                sb.append("\n");
            }
            sb.deleteCharAt(sb.lastIndexOf("\n"));
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        if (numeroVertices == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder();
            int espacios = vertices[0].getNombre().length();
            for (int i = 0; i <= espacios; i++) {
                sb.append(" ");
            }
            for (int i = 0; i < numeroVertices; i++) {
                sb.append(vertices[i].getNombre().charAt(0)).append(" ");
            }
            sb.deleteCharAt(sb.lastIndexOf(" "));
            sb.append("\n");
            for (int i = 0; i < numeroVertices; i++) {
                sb.append(vertices[i].getNombre()).append(" ");
                for (int j = 0; j < numeroVertices; j++) {
                    sb.append(matrizAdyacencia[i][j]).append(" ");
                }
                sb.deleteCharAt(sb.lastIndexOf(" "));
                sb.append("\n");
            }
            sb.deleteCharAt(sb.lastIndexOf("\n"));
            return sb.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        
        GrafoM grafo = new GrafoM(7);
        
        
        grafo.añadirVertice("1");
        grafo.añadirVertice("2");
        grafo.añadirVertice("3");
        grafo.añadirVertice("4");
        grafo.añadirVertice("5");
        grafo.añadirVertice("6");
        grafo.añadirVertice("7");
        
        grafo.añadirArista("1","6",10);
        grafo.añadirArista("6","1",10);
        
        grafo.añadirArista("6","5",25);
        grafo.añadirArista("5","6",25);
        
        grafo.añadirArista("5","4",22);
        grafo.añadirArista("4","5",22);
        
        grafo.añadirArista("4","3",12);
        grafo.añadirArista("3","4",12);
        
        grafo.añadirArista("4","7",18);
        grafo.añadirArista("7","4",18);
        
        grafo.añadirArista("5","7",24);
        grafo.añadirArista("7","5",24);
        
        grafo.añadirArista("7","2",14);
        grafo.añadirArista("2","7",14);
        
        grafo.añadirArista("2","3",16);
        grafo.añadirArista("3","2",16);
        
        grafo.añadirArista("1","2",28);
        grafo.añadirArista("2","1",28);
        
        
        AlgoritmosGrafos ag = new AlgoritmosGrafos();
        
        ag.kruskall(grafo);
    }
}
