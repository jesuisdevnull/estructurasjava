package graphpractice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * @author JESUS
 */
public class AlgoritmosGrafos {

    private class VerticeKruskall implements Comparable<VerticeKruskall>, Comparator<VerticeKruskall>{
        int origen;
        int destino;
        float peso;
        
        public VerticeKruskall(int orig, int dest, float pes){
            origen = orig;
            destino = dest;
            peso = pes;
        }
        
        @Override 
        public String toString(){
            return "("+this.origen+","+this.destino+","+this.peso+")";
        }

        @Override
        public int compareTo(VerticeKruskall o) {
            Float f = peso;
            Float f2 = o.peso;
            return f.compareTo(f2);
        }

        @Override
        public int compare(VerticeKruskall o1, VerticeKruskall o2) {
            return (int) ((int) o1.peso - o2.peso);
        }
    }
    static final int INFINITO = 1000000;
    public static int[][] warshall(GrafoM g) throws Exception {
        int n = g.numeroVertices;
        int[][] cierreTransitivo = generarAdyacencia(g);
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    cierreTransitivo[i][j] = Math.min(cierreTransitivo[i][j] + cierreTransitivo[i][k] * cierreTransitivo[k][j], 1);
                }
            }
        }
        return cierreTransitivo;
    }
    public static int[][] generarAdyacencia(GrafoM g) throws Exception {
        int n = g.numeroVertices;
        int[][] adyacencia = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adyacencia[i][j] = (g.sonAdyacentes(i, j)) ? 1 : 0;
            }
        }
        return adyacencia;
    }
    public static int[][] copiarMatriz(GrafoM g) {
        return copiarMatriz(g.matrizAdyacencia);
    }
    public static int[][] copiarMatriz(int[][] matrizOriginal) {
        if (matrizOriginal == null) {
            return null;
        }
        int n = matrizOriginal.length;
        int[][] matrizCopia = new int[n][n];
        for (int i = 0; i < n; i++) {
            matrizCopia[i] = Arrays.copyOf(matrizOriginal[i], n);
        }
        return matrizCopia;
    }
    public static void grafoInvertido(GrafoM original, GrafoM invertido) throws Exception {
        int n = original.numeroVertices;
        invertido.setListaVertices(original.vertices);
        invertido.setNumeroVertices(original.numeroVertices);
        int[][] temp = new int[n][n];
        invertido.setMatriz(temp);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(original.sonAdyacentes(i, j)){
                    invertido.añadirArista(j, i);
                }
            }
        }
    }
    static int todosLosArbolesIndividuales(int[] bosque, int numVert) {
        int indice, siguienteARevisar;
        siguienteARevisar = indice = -1;
        do {
            if (bosque[++indice] == 0) {
                siguienteARevisar = indice;
            }
        } while ((indice < numVert - 1) && (siguienteARevisar == -1));
        return siguienteARevisar;
    }
    public static void componentesFuertementeConexos(GrafoM original) throws Exception {
        GrafoM invertido = new GrafoM("vacio");
        grafoInvertido(original, invertido);
        int numeroVertices = original.numeroVertices;

        int[] recorridoEnProfundidadIterado = new int[numeroVertices];
        int[] sePuedenViajarHacia = new int[numeroVertices];
        int[] sePuedenLlegarDesde = new int[numeroVertices];
        int[] yaFormanParteDeComponentes = new int[numeroVertices];

        Vertice[] verticesDeAmbosGrafos = original.vertices;

        int origen;
        origen = 0;

        do {
            recorridoEnProfundidadIterado = RecorridosGrafos.profundidad(original, verticesDeAmbosGrafos[origen].getNombre());
            for (int i = 0; i < numeroVertices; i++) {
                sePuedenViajarHacia[i] = (recorridoEnProfundidadIterado[i] != INFINITO) ? 1 : 0;
            }
            recorridoEnProfundidadIterado = RecorridosGrafos.profundidad(invertido, verticesDeAmbosGrafos[origen].getNombre());
            for (int i = 0; i < numeroVertices; i++) {
                sePuedenLlegarDesde[i] = (recorridoEnProfundidadIterado[i] != INFINITO) ? 1 : 0;
            }
            System.out.print("\nComponente conexa {");
            for (int i = 0; i < numeroVertices; i++){
                    if(sePuedenViajarHacia[i] * sePuedenLlegarDesde[i] == 1){
                    System.out.print(" " + verticesDeAmbosGrafos[i].getNombre());
                    yaFormanParteDeComponentes[i] = 1;
                }
            }
            System.out.println(" }");
            origen = todosLosArbolesIndividuales(yaFormanParteDeComponentes, numeroVertices);
        } while (origen != -1);
    }
    
    public static LinkedList<String> puentes(GrafoM grafo) throws Exception{
        int n = grafo.numeroVertices;
        boolean[] visitados = new boolean[n];
        int[] llaveMenor = new int[n];
        int[] IDs = new int[n];
        LinkedList<String> puentesL = new LinkedList();
        puentesL = puentesHelper(grafo, n, 0, -1, puentesL, visitados, llaveMenor, IDs);
        return puentesL;
        
         
    }
    
    private static LinkedList<String> puentesHelper(GrafoM graf, int numVert, int origen, int padre, LinkedList<String> puentes, boolean[] visitados, int[] bajos, int[] ids) throws Exception{
        visitados[origen] = true;
        ids[origen] = ++padre;
        bajos[origen] = ids[origen];
        for (int hacia = 0; hacia < numVert; hacia++){
            if(graf.sonAdyacentes(origen, hacia)){
                if(!visitados[hacia]){
                    puentesHelper(graf, numVert, hacia, origen, puentes, visitados, bajos, ids);
                    bajos[origen] = Math.min(bajos[origen], bajos[hacia]);
                    if(bajos[origen] < bajos[hacia]){
                        puentes.add("{"+graf.vertices[origen].getNombre()+","+graf.vertices[hacia].getNombre()+"}");
                    }
                } else {
                    bajos[origen] = Math.min(bajos[origen], ids[hacia]);
                }
            }
        }
        return puentes;
    }
    
    public static LinkedList<Integer> articulacion(GrafoM grafo) throws Exception{
        int n = grafo.numeroVertices;
        boolean[] visitados = new boolean[n];
        int[] llaveMenor = new int[n];
        int[] IDs = new int[n];
        LinkedList<Integer> articL = new LinkedList();
        articL = articulacionHelper(grafo, n, 0, -1, articL, visitados, llaveMenor, IDs);
        return articL;
        
         
    }
    
    private static LinkedList<Integer> articulacionHelper(GrafoM graf, int numVert, int origen, int padre, LinkedList<Integer> artic, boolean[] visitados, int[] bajos, int[] ids) throws Exception{
        visitados[origen] = true;
        ids[origen] = ++padre;
        bajos[origen] = ids[origen];
        for (int hacia = 0; hacia < numVert; hacia++){
            if(graf.sonAdyacentes(origen, hacia)){
                if(!visitados[hacia]){
                    articulacionHelper(graf, numVert, hacia, origen, artic, visitados, bajos, ids);
                    bajos[origen] = Math.min(bajos[origen], bajos[hacia]);
                    if(bajos[origen] < bajos[hacia]){
                        artic.add(origen);
                    }
                } else {
                    bajos[origen] = Math.min(bajos[origen], ids[hacia]);
                }
            }
        }
        return artic;
    }
    
    public int encontrarPadre(int[] padres, int nodo){
        if(padres[nodo]==nodo){
            return nodo;
        }
        return encontrarPadre(padres, padres[nodo]);
    }
    
    public void unir(int[] padres, int origen, int destino){
        int padreOrigen = encontrarPadre(padres, origen);
        int padreDestino = encontrarPadre(padres, destino);
        padres[padreOrigen] = padreDestino;
    }
    
    public void kruskall(GrafoM g) throws Exception{
        int n = g.numeroVertices;
        int[] padres = new int[n];
        for (int i = 0; i < n; i++){
            padres[i]=i;
        }
        //Primero, obtenemos todas las aristas no negativas del grafo, y las metemos en una heap.
        PriorityQueue<VerticeKruskall> monticulo = new PriorityQueue();
        for(int i = -1; i < n-1; i++){
            for(int j = i+1; j < n; j++){
                if (g.sonAdyacentes(i+1, j)){
                    monticulo.add(new VerticeKruskall(i+1, j, g.matrizAdyacencia[i+1][j]));
                }
            }
        }
        LinkedList<VerticeKruskall> MST = new LinkedList();
        ///
        while(MST.size() != n-1 || !monticulo.isEmpty()){
            VerticeKruskall temp = monticulo.remove();
            if(encontrarPadre(padres,temp.origen)!=encontrarPadre(padres,temp.destino)){
                unir(padres,temp.origen,temp.destino);
                MST.add(temp);
            }
        }
        
        ///
        VerticeKruskall temp;
        System.out.print("{");
        float count = 0;
        if (monticulo.isEmpty() && MST.size() != n-1) {
            System.out.println("ARBOL NO VALIDO:");
        }
        while (!MST.isEmpty()){
            temp = MST.removeFirst();
            System.out.print(temp.toString());
            if (!MST.isEmpty()){
                System.out.print(";");
            }
            count = count + temp.peso;
        }
        System.out.println("}");
        System.out.println("Peso total del MST: " + count);
        
        
    }

    
}
