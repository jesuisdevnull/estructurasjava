package graphpractice;

import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author JESUS
 */
public class RecorridosGrafos {

    final static int INFINITO = 1000000;

    public static int[] anchura(GrafoM g, String nodoOrigen) throws Exception { //Devuelve una matriz con el camino minimo entre el nodo inicial y cada otro nodo.
        {

            int actual, vertOrig;
            int[] m;
            vertOrig = g.numVertice(nodoOrigen);
            if (vertOrig < 0) {
                throw new Exception("Vértice origen no existe");
            }
            LinkedList<Integer> cola = new LinkedList();
            m = new int[g.numeroVertices];
            for (int i = 0; i < g.numeroVertices; i++) {
                m[i] = INFINITO;
            }
            m[vertOrig] = 0;
            cola.add(vertOrig);
            while (!cola.isEmpty()) {
                actual = cola.removeFirst();
                for (int destino = 0; destino < g.numeroVertices; destino++) {
                    if ((g.matrizAdyacencia[actual][destino] == 1) && (m[destino] == INFINITO)) {
                        m[destino] = m[actual] + 1;
                        cola.add(destino);
                    }
                }
            }
            return m;
        }
    }

    public static int[] profundidad(GrafoM g, String nodoOrigen) throws Exception {
        int actual, vertOrig;
        int[] m;
        vertOrig = g.numVertice(nodoOrigen);
        if (vertOrig < 0) {
            throw new Exception("Vértice origen no existe");
        }
        Stack<Integer> pila = new Stack();
        m = new int[g.numeroVertices];
        for (int i = 0; i < g.numeroVertices; i++) {
            m[i] = INFINITO;
        }
        m[vertOrig] = 0;
        pila.add(vertOrig);
        while (!pila.isEmpty()) {
            actual = pila.pop();
            for (int destino = 0; destino < g.numeroVertices; destino++) {
                if ((g.matrizAdyacencia[actual][destino] == 1) && (m[destino] == INFINITO)) {
                    pila.add(destino);
                    m[destino] = 1;
                }
            }
        }
        return m;
    }

    public static void impresionAnchura(GrafoM grafo, String nodoOrigen) throws Exception { //Imprime un recorrido en anchura
        LinkedList<Integer> cola = new LinkedList();
        int n = grafo.numeroVertices;
        boolean[] visitados = new boolean[n];
        int[][] ady = grafo.matrizAdyacencia;
        StringBuilder sb = new StringBuilder();
        int origen = grafo.numVertice(nodoOrigen);
        if (origen < 0) {
            throw new Exception("Vertice inexistente");
        }
        cola.add(origen);
        visitados[origen] = true;
        while (!cola.isEmpty()) {
            int actual = cola.removeFirst();
            for (int j = 0; j < n; j++) {
                if (actual != j && (ady[actual][j] == 1 && visitados[j] != true)) {
                    cola.add(j);
                    visitados[j] = true;
                }
            }
            sb.append(grafo.vertices[actual].getNombre()).append("->");
        }
        sb.deleteCharAt(sb.lastIndexOf("-"));
        sb.deleteCharAt(sb.lastIndexOf(">"));
        System.out.println(sb.toString());
    }

    public static void impresionProfundidad(GrafoM grafo, String nodoOrigen) throws Exception {
        Stack<Integer> pila = new Stack();
        int n = grafo.numeroVertices;
        boolean[] visitados = new boolean[n];
        int[][] ady = grafo.matrizAdyacencia;
        StringBuilder sb = new StringBuilder();
        int origen = grafo.numVertice(nodoOrigen);
        if (origen < 0) {
            throw new Exception("Vertice inexistente");
        }
        pila.add(origen);
        while (!pila.isEmpty()) {
            int actual = pila.pop();
            visitados[actual] = true;
            for (int j = 0; j < n; j++) {
                if (actual != j && (ady[actual][j] == 1 && visitados[j] != true)) {
                    pila.add(j);
                }
            }
            sb.append(grafo.vertices[actual].getNombre()).append("->");

        }
        sb.deleteCharAt(sb.lastIndexOf("-"));
        sb.deleteCharAt(sb.lastIndexOf(">"));
        System.out.println(sb.toString());
    }
    
    
}
