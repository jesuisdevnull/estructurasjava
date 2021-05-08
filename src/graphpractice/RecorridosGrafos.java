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

    public static int[] anchura(GrafoL g, String nodoOrigen) throws Exception {
        int vertOrig;
        int[] m;
        vertOrig = g.numVertice(nodoOrigen);
        if (vertOrig < 0) {
            throw new Exception("Vértice origen no existe");
        }
        m = new int[g.numeroDeVertices];
        for (int i = 0; i < g.numeroDeVertices; i++) {
            m[i] = INFINITO;
        }
        LinkedList<Integer> cola = new LinkedList();
        boolean[] visitado = new boolean[g.numeroDeVertices];
        m[vertOrig] = 0;
        cola.add(vertOrig);
        while (!cola.isEmpty()) {
            int indice = cola.remove();
            visitado[indice] = true;
            if (g.tablaVertices[indice].tieneConexiones()) {
                ListaAristas temporal = g.tablaVertices[indice].aristas;
                NodoArista puntero = temporal.cabeza;
                while (puntero != null) {
                    int indiceDestino = puntero.data.destino;
                    if (!visitado[indiceDestino]) {
                        m[indiceDestino] = m[indice] + 1;
                        cola.add(indiceDestino);
                    }
                    puntero = puntero.siguiente;
                }
            }

        }
        return m;
    }

    public static int[] profundidad(GrafoL g, String nodoOrig) throws Exception {
        int vertOrig = g.numVertice(nodoOrig);
        if (vertOrig < 0) {
            throw new Exception("Vértice origen no existe");
        }
        int n = g.numeroDeVertices;
        boolean[] visitados = new boolean[n];
        int[] m = new int[n];
        for (int i = 0; i < g.numeroDeVertices; i++) {
            m[i] = INFINITO;
        }
        profundidadHelper(m, visitados, n, g, vertOrig);
        m[vertOrig] = 0;
        return m;
    }

    public static void profundidadHelper(int[] m, boolean[] visitados, int n, GrafoL g, int vertOrig) {
        if (visitados[vertOrig]) {
            return;
        }
        visitados[vertOrig] = true;
        if (g.tablaVertices[vertOrig].tieneConexiones()) {
            ListaAristas temporal = g.tablaVertices[vertOrig].aristas;
            NodoArista puntero = temporal.cabeza;
            while (puntero != null) {
                int indiceDestino = puntero.data.destino;
                if (!visitados[indiceDestino]) {
                    profundidadHelper(m, visitados, n, g, indiceDestino);
                }
                puntero = puntero.siguiente;
            }
        }
        m[vertOrig] = 1;

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
