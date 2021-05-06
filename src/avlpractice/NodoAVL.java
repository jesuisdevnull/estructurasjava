package avlpractice;

/**
 *
 * @author JESUS
 * @param <E>
 */
public class NodoAVL<E> {
    E data;
    int key;
    NodoAVL left;
    NodoAVL right;
    int balance;
    
    public E getData(){
        return data;
    }
    
    public int getKey(){
        return key;
    }
    
    public NodoAVL getRight(){
        return right;
    }
    
    public NodoAVL getLeft(){
        return left;
    }
    
    public NodoAVL(E dat, int keyL, NodoAVL l, NodoAVL r){
        data = dat;
        key = keyL;
        left = l;
        right = r;
        balance = 0;
    }
    
    public boolean hasNoChildren() {
        return (right == null && left == null);
    }
    
    public NodoAVL max() {
        if (this.right == null) {
            return this;
        }
        return this.right.max();
    }
    
    public NodoAVL getAncestor(NodoAVL start, NodoAVL ancestor, int value) {
        if (value > start.key) {
            return getAncestor(start.right, start, key);
        } else if (value < start.key) {
            return getAncestor(start.left, start, key);
        }
        return ancestor;
    }
    
    public int getHeight(){
        return getHeight(this,0,0);
    }
    
    private int getHeight(NodoAVL start, int startLeft, int startRight){
        int lR = startRight;
        int lL = startLeft;
        if (start == null){
            return 0;
        }
        if (start.hasNoChildren()){
            return 1;
        }
        if(start.left!=null){
           lR = lR + getHeight(start.left, 0, 0);
        }
        if(start.right!=null){
            lL = lL + getHeight(start.right, 0, 0);
        }
        return (Math.max(lR, lL)+1);
    }
    
    public void calculateBalance(){
        if(hasNoChildren()){
            balance = 0;
        } else {
            if(right == null && left != null){
                balance = 0 - left.getHeight();
            } else if (right!=null && left==null){
                balance = 0 + right.getHeight();
            } else {
                balance = right.getHeight()-left.getHeight();
            }
        }
    }
}
