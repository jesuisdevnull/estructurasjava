package avlpractice;

import java.util.LinkedList;

/**
 *
 * @author JESUS
 */
public class ArbolAVL<E> {

    NodoAVL root;

    public ArbolAVL() {
        root = null;
    }
    
    public NodoAVL getRoot() {
        return root;
    }

    private void inOrder(StringBuilder sb, NodoAVL inicio) {
        if (inicio != null) {
            inOrder(sb, inicio.left);
            sb.append(inicio.key).append(" ");
            inOrder(sb, inicio.right);
        }
    }

    public void update(NodoAVL node) {
        node.calculateBalance();
    }

    public String inOrder() {
        StringBuilder stB = new StringBuilder();
        stB.append("[ ");
        inOrder(stB, root);
        stB.append("]");
        return stB.toString();
    }
    
    public boolean isEmpty() {
        return root == null;
    }

    public NodoAVL getMax() {
        return getMax(root);
    }

    private NodoAVL getMax(NodoAVL start) {
        if (root == null) {
            return null;
        }
        if (root.right == null) {
            return root;
        }
        return getMax(root.right);
    }

    public void RR(NodoAVL fulcrum, NodoAVL parent) {
        NodoAVL ref1, ref2, ref3;
        ref1 = fulcrum;
        ref2 = fulcrum.left;
        ref3 = fulcrum.left.right;
        if (fulcrum == root) {
            root = fulcrum.left;
        }
        NodoAVL temp = fulcrum.left.right;
        fulcrum.left.right = fulcrum;
        fulcrum.left = temp;
        if (parent != null) {
            if (parent.left == fulcrum) {
                parent.left = ref2;
            } else if (parent.right == fulcrum) {
                parent.right = ref2;
            }
        }
        if (ref1 != null) {
            ref1.calculateBalance();
        }
        if (ref2 != null) {
            ref2.calculateBalance();
        }
        if (ref3 != null) {
            ref3.calculateBalance();
        }
    }

    public void LR(NodoAVL fulcrum, NodoAVL parent) {
        NodoAVL ref1, ref2, ref3;
        ref1 = fulcrum;
        ref2 = fulcrum.right;
        ref3 = fulcrum.right.left;
        if (fulcrum == root) {
            root = fulcrum.right;
        }
        NodoAVL temp = fulcrum.right.left;
        fulcrum.right.left = fulcrum;
        fulcrum.right = temp;
        if (parent != null) {
            if (parent.left == fulcrum) {
                parent.left = ref2;
            } else if (parent.right == fulcrum) {
                parent.right = ref2;
            }
        }
        if (ref1 != null) {
            ref1.calculateBalance();
        }
        if (ref2 != null) {
            ref2.calculateBalance();
        }
        if (ref3 != null) {
            ref3.calculateBalance();
        }
    }

    private NodoAVL findParent(NodoAVL element, NodoAVL start) {
        if (start == null) {
            return null;
        }
        if (start.data == element.data) {
            return null;
        } else {
            if ((start.left != null && start.left.data == element.data) || (start.right != null && start.right.data == element.data)) {
                return start;
            }
            if (element.key < start.key) {
                return findParent(element, start.left);
            } else {
                return findParent(element, start.right);
            }
        }
    }

    public void LRR(NodoAVL fulcrum) {
        RR(fulcrum.right, fulcrum);
        LR(fulcrum, findParent(fulcrum, root));
    }

    public void RLR(NodoAVL fulcrum) {
        LR(fulcrum.left, fulcrum);
        RR(fulcrum, findParent(fulcrum, root));
    }

    public NodoAVL insert(E nElemento, int valorNum) {
        if (root == null) {
            root = new NodoAVL(nElemento, valorNum, null, null);
            return null;
        }
        NodoAVL temp = insert(new NodoAVL(nElemento, valorNum, null, null), root);
        return temp;
    }

    public NodoAVL insert(NodoAVL newE, NodoAVL start) {
        NodoAVL temp = null;
        if (newE.key < start.key) {
            if (start.left == null) {
                start.left = newE;
                update(start);
                if (start.balance < -1 || start.balance > 1) {
                    rebalance(start);
                }
            } else {
                if (newE.key == start.key) {
                    return start;
                }
                temp = insert(newE, start.left);
                update(start);
                if (start.balance < -1 || start.balance > 1) {
                    rebalance(start);
                }

            }
        } else {
            if (newE.key == start.key) {
                return start;
            }
            if (start.right == null) {
                start.right = newE;
                update(start);
                if (start.balance < -1 || start.balance > 1) {
                    rebalance(start);
                }
            } else {
                temp = insert(newE, start.right);
                update(start);
                if (start.balance < -1 || start.balance > 1) {
                    rebalance(start);
                }
            }
        }
        return temp;
    }

    public void rebalance(NodoAVL start) {
        int balanceRef = start.balance;
        String stat = "undefined";
        if (balanceRef < -1) {
            stat = "leftheavy";
        } else if (balanceRef > 1) {
            stat = "rightheavy";
        }
        switch (stat) {
            case "leftheavy":
                if (start.left.balance <= 0) {
                    RR(start, findParent(start, root));
                } else if (start.left.balance > 0) {
                    RLR(start);
                }
                break;
            case "rightheavy":
                if (start.right.balance >= 0) {
                    LR(start, findParent(start, root));
                } else if (start.right.balance < 0) {
                    LRR(start);
                }
                break;
        }
    }

    public LinkedList<E> getValuesAsList() {
        LinkedList<E> list = new LinkedList<>();
        if (isEmpty()) {
            return list;
        }
        getValuesAsList(root, list);
        return list;
    }

    public LinkedList<E> getValuesAsList(NodoAVL start, LinkedList<E> list) {
        if (start != null) {
            getValuesAsList(start.left, list);
            list.add((E) start.data);
            getValuesAsList(start.right, list);
        }
        return list;
    }
    
    public int valorMasCercano(int valor, String modo){
        return encontrarNodoMasCercano(valor, modo).key;
    }

    public NodoAVL encontrarNodoMasCercano(int valor, String modo) {
        LinkedList<NodoAVL> valores = encontrarNodoMasCercano(root, valor);
        if (valores.size() == 1) {
            return valores.getFirst();
        } else {
            if (modo.equals("inicio")) {
                return valores.get(0);
            } else {
                return valores.get(1);
            }
        }
    }

    private LinkedList<NodoAVL> encontrarNodoMasCercano(NodoAVL puntoInicio, int valor) {

        if (puntoInicio == null) {
            return null;
        }
        NodoAVL nodoActual = puntoInicio;
        NodoAVL nodoMasCercano;
        LinkedList<NodoAVL> colaResp = new LinkedList();
        int diferenciaMinima = 2147483647; //Valor maximo de un entero.
        int diferenciaActual = Math.abs(nodoActual.key - valor);
        while (nodoActual != null) {
            diferenciaActual = Math.abs(nodoActual.key - valor);
            if (diferenciaActual < diferenciaMinima) {
                if (colaResp.isEmpty()) {
                    diferenciaMinima = diferenciaActual;
                    nodoMasCercano = nodoActual;
                    colaResp.add(nodoMasCercano);
                } else {
                    diferenciaMinima = diferenciaActual;
                    colaResp.clear();
                    nodoMasCercano = nodoActual;
                    colaResp.add(nodoMasCercano);
                }
            } else {
                if (diferenciaActual == diferenciaMinima) {
                    nodoMasCercano = nodoActual;
                    colaResp.add(nodoMasCercano);
            }
            }
            
            if (valor < nodoActual.key) {
                nodoActual = nodoActual.left;
            } else {
                if (valor > nodoActual.key) {
                    nodoActual = nodoActual.right;
                } else {
                    break;
                }
            }
        }

        return colaResp;
    }
    
    @Override
    public String toString(){
        return inOrder();
    }
    
    public void balanceAfterDeletion(int key) {
        balanceAfterDeletion(root, key);
    }

    private void balanceAfterDeletion(NodoAVL start, int value) {
        if (start == null) {
            return;
        }
        if (value < start.key) {
            balanceAfterDeletion(start.left, value);
            update(start);
            if (start.balance < -1 || start.balance > 1) {
                rebalance(start);
            }
        } else {
            balanceAfterDeletion(start.right, value);
            update(start);
            if (start.balance < -1 || start.balance > 1) {
                rebalance(start);
            }
        }
    }

    public void swap(NodoAVL a, NodoAVL b) {
        E tempOb = (E) b.data;
        int tempKey = b.key;
        b.data = a.data;
        b.key = a.key;
        a.key = tempKey;
        a.data = tempOb;
    }

    public void delete(int key) {
        NodoAVL newTree = delete(root, key);
        if (newTree != null) {
            root = newTree;
            balanceAfterDeletion(key);
        } else {
            System.out.println("Not in tree.");
        }
    }
    

    public NodoAVL delete(NodoAVL start, int key) {
        if (start == null) {
            return null;
        } else if (key < start.key) {
            start.left = delete(start.left, key);
        } else if (key > start.key) {
            start.right = delete(start.right, key);
        } else {
            if (start.hasNoChildren()) {
                start = null;
            } else if (start.left == null && start.right != null) {
                start = start.right;
            } else if (start.left != null && start.right == null) {
                start = start.left;
            } else {
                NodoAVL predecessor = start.left.max();
                swap(predecessor, start);
                delete(start.left, key);
            }
        }
        return start;
    }

    public static void main(String[] args) {
        ArbolAVL<Integer> n = new ArbolAVL<>();
        n.insert(64, 64);
        n.insert(207, 207);
        n.insert(209, 209);
        n.insert(22, 22);
        n.insert(35, 35);
        n.insert(28, 28);
        n.insert(1, 1);
        n.insert(6, 6);
        n.insert(0, 0);
        n.insert(11, 11);
        n.delete(207);
        System.out.println(n.toString());
        
    }
}
