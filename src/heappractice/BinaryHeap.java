package heappractice;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementación de un Montículo Binario. Este consiste en una representación
 * en forma de arreglo de un arbol binario; en el cual... Cada nodo i tiene dos
 * hijos o menos, siendo estos encontrados segun el indice del nodo. Nodo: i
 * Hijo izquierdo: (2*i+1) Hijo derecho: (2*i+1) En un monticulo, cada nodo es
 * menor que sus hijos. Operaciones principales: insertar, removerPrimero,
 * verPrimero, esVacio Operaciones auxiliares: trickle
 *
 * @author JESUS
 */
public class BinaryHeap {

    private static int TAMAXIMO;
    private int[] monticulo;
    private int numElem;

    public BinaryHeap(int tam) {
        numElem = 0;
        monticulo = new int[tam];
        TAMAXIMO = tam;
    }

    public boolean isEmpty() {
        return numElem == 0;
    }

    public boolean isFull() {
        return numElem == TAMAXIMO;
    }

    public int parent(int index) {
        return ((index - 1) / 2);
    }

    public int leftC(int index) {
        return (index * 2 + 1);
    }

    public int rightC(int index) {
        return (index * 2 + 2);
    }

    public boolean insert(int newElem) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            monticulo[0] = newElem;
            numElem++;
            return true;
        }
        monticulo[numElem] = newElem;
        trickleUp(numElem);
        numElem++;
        return true;
    }

    public void swap(int i1, int i2) {
        int temp = monticulo[i1];
        monticulo[i1] = monticulo[i2];
        monticulo[i2] = temp;
    }

    public int removeMin() {
        if (isEmpty()) {
            try {
                throw new Exception("Acceso a pila vacía");
            } catch (Exception ex) {
                Logger.getLogger(BinaryHeap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int element = monticulo[0];
        monticulo[0] = monticulo[numElem-1];
        trickleDown(0);
        numElem--;
        return element;
    }

    public void trickleDown(int elemIndex) {
        int element = monticulo[elemIndex];
        int index = elemIndex;
        int leftC;
        int rightC;
        int smaller;
        while (index < numElem / 2) { //Has at least one child
            leftC = leftC(index);
            rightC = leftC + 1;
            if (leftC(index) == numElem - 1) {
                smaller = leftC;
            } else {
                if (monticulo[leftC] < monticulo[rightC]) {
                    smaller = leftC;
                } else {
                    smaller = rightC;
                }
            }

            if (element <= monticulo[smaller]) {
                break;
            }
            swap(index, smaller);
            index = smaller;
        }
        monticulo[index] = element;
    }

    public void trickleUp(int elemIndex) {
        int parent = parent(elemIndex);
        int index = elemIndex;
        int element = monticulo[elemIndex];
        while (index > 0 && monticulo[parent] > element) {
            swap(parent, index);
            parent = parent(parent(index));
            index = parent(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        if (!isEmpty()) {
            for (int i = 0; i < numElem; i++) {
                sb.append(monticulo[i]).append(" ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void print(BinaryHeap entry) {
        System.out.println(entry.toString());
    }

    public static void main(String[] args) {
        BinaryHeap test = new BinaryHeap(10);
        System.out.println(test.insert(7));
        System.out.println(test.insert(19));
        System.out.println(test.insert(22));
        System.out.println(test.insert(26));
        System.out.println(test.insert(33));
        System.out.println(test.insert(29));
        System.out.println(test.insert(41));
        BinaryHeap.print(test);
        System.out.println("Removed node: " + test.removeMin());
        BinaryHeap.print(test);
    }
}
