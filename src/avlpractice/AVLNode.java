package avlpractice;

/**
 *
 * @author JESUS
 */

public class AVLNode {
    int data;
    AVLNode left;
    AVLNode right;
    int balance;
    
    public AVLNode(int dat, AVLNode l, AVLNode r){
        data = dat;
        left = l;
        right = r;
        balance = 0;
    };
    
    public boolean hasNoChildren() {
        return (right == null && left == null);
    }

    public AVLNode max() {
        if (this.right == null) {
            return this;
        }
        return this.right.max();
    }

    public AVLNode getAncestor(AVLNode start, AVLNode ancestor, int key) {
        if (key > start.data) {
            return getAncestor(start.right, start, key);
        } else if (key < start.data) {
            return getAncestor(start.left, start, key);
        }
        return ancestor;
    }
    
    public int getHeight(){
        return getHeight(this,0,0);
    }
    
    private int getHeight(AVLNode start, int startLeft, int startRight){
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
    
    public static void main(String[] args){
        
    }
}
