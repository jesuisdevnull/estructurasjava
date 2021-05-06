package treepractice;
import java.util.LinkedList;
import java.lang.Math;

public class IterativeTree{
    public class TreeNode{
        private int data;
        private TreeNode leftC;
        private TreeNode rightC;
        
        public TreeNode(int input, TreeNode left, TreeNode right){
            data = input;
            leftC = left;
            rightC = right;
        }
        
        public TreeNode(){};
        
        public int getData(){
            return data;
        }
        
        public TreeNode getLeftChild(){
            return leftC;
        }
        
        public TreeNode getRightChild(){
            return rightC;
        }
        
        public void setData(int newD){
            data = newD;
        }
        
        public void setLeftChild(TreeNode newL){
            leftC = newL;
        }
        
        public void setRightChild(TreeNode newR){
            rightC = newR;
        }
        
        public boolean isLeftChild(TreeNode parent){
            return (this == parent.leftC);
        }
        
        public boolean isRightChild(TreeNode parent){
            return (this == parent.rightC);
        }
        
        public boolean hasBothChildren(){
            return ((leftC!=null)&&(rightC!=null));
        }
        
        public boolean hasNoChildren(){
            return ((leftC==null)&&(rightC==null));
        }
        
    }
    private TreeNode root;
    
    public IterativeTree(){
        root = null;
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    public TreeNode getRoot(){
        return root;
    }
    
    public void insert(int key){
        TreeNode aux = new TreeNode(key, null, null);
        if (isEmpty()){
            root = aux;
            return;
        }
        TreeNode current;
        TreeNode parent;
        current = root;
        while (true){
            parent = current;
            if (key < current.getData()){
                current = current.getLeftChild();
                if (current == null){
                    parent.setLeftChild(aux);
                    return;
                }
            } else {
                current = current.getRightChild();
                if (current == null){
                    parent.setRightChild(aux);
                    return;
                }
            }
        }
    }
    
    public TreeNode find(int key){
        if (root.getData() == key){
            return root;
        }
        TreeNode current = root;
        while(true){
            if (key < current.getData()){
                current = current.getLeftChild();     
            } else {
                current = current.getRightChild();
            }
            if (current == null){
                return null;
            } else if (current.getData() == key) {
                return current;
            }  
        }
    }
    
    public boolean deleteWithSuccesor(int key){
       TreeNode current;
       TreeNode parent;
       current = root;
       parent = current;
       while(current.getData() != key){
           parent = current;
           if (key < current.getData()){
               current = current.getLeftChild();
           } else {
               current = current.getRightChild();
           }
           if(current == null){
               return false;
           }
       }
       if (current.hasNoChildren()){
           if (current==root){
               root = null;
           } else {
               if(current.isLeftChild(parent)){
                   parent.setLeftChild(null);
                   return true;
               } else{
                   parent.setRightChild(null);
                   return true;
               }
           }
       }
       if(current.getRightChild()==null){
           if (current==root){
               root = current.getLeftChild();
               return true;
           } else if (current.isLeftChild(parent)){
               parent.setLeftChild(current.getLeftChild());
               return true;
           } else {
               parent.setRightChild(current.getRightChild());
               return true;
           }
       }
       if(current.getLeftChild()==null){
           if (current==root){
               root = current.getRightChild();
               return true;
           } else if (current.isLeftChild(parent)){
               parent.setLeftChild(current.getLeftChild());
               return true;
           } else {
               parent.setRightChild(current.getRightChild());
               return true;
           }
       }
       
       if(current.hasBothChildren()){
           TreeNode succesor = getSuccesorForDeletion(current);
           if (current == root){
               root = succesor;
               
           } else {
               if (current.isLeftChild(parent)){
                   parent.setLeftChild(succesor);
                   
               } else {
                   parent.setRightChild(succesor);
               }
               succesor.setLeftChild(current.getLeftChild());
           }
           return true;
       }
       return false;
    }
    public boolean deleteWithPredecessor(int key){
       TreeNode current;
       TreeNode parent;
       current = root;
       parent = current;
       while(current.getData() != key){
           parent = current;
           if (key < current.getData()){
               current = current.getLeftChild();
           } else {
               current = current.getRightChild();
           }
           if(current == null){
               return false;
           }
       }
       if (current.hasNoChildren()){
           if (current==root){
               root = null;
           } else {
               if(current.isLeftChild(parent)){
                   parent.setLeftChild(null);
                   return true;
               } else{
                   parent.setRightChild(null);
                   return true;
               }
           }
       }
       if(current.getRightChild()==null){
           if (current==root){
               root = current.getLeftChild();
               return true;
           } else if (current.isLeftChild(parent)){
               parent.setLeftChild(current.getLeftChild());
               return true;
           } else {
               parent.setRightChild(current.getRightChild());
               return true;
           }
       }
       if(current.getLeftChild()==null){
           if (current==root){
               root = current.getRightChild();
               return true;
           } else if (current.isLeftChild(parent)){
               parent.setLeftChild(current.getLeftChild());
               return true;
           } else {
               parent.setRightChild(current.getRightChild());
               return true;
           }
       }
       
       if(current.hasBothChildren()){
           TreeNode predecessor = getPredecessorForDeletion(current);
           if (current == root){
               root = predecessor;
           } else {
               if (current.isLeftChild(parent)){
                   parent.setLeftChild(predecessor);
                   
               } else {
                   parent.setRightChild(predecessor);
               }
               predecessor.setRightChild(current.getRightChild());
           }
           return true;
       }
       return false;
    }  
    public int countLeaves(int counter, TreeNode start){
        int localC = counter;
        if(start != null){
            localC = countLeaves(localC, start.getLeftChild());
            if (start.hasNoChildren()){
                localC++;
            }
            localC = countLeaves(localC, start.getRightChild());   
        }
        return localC;
    }
    private TreeNode getSuccesorForDeletion(TreeNode aSerBorrado){
        TreeNode succesor;
        TreeNode succesorParent;
        TreeNode current;
        succesor = aSerBorrado;
        succesorParent = succesor;
        current = succesor.getRightChild();
        while(current!=null){
            succesorParent = succesor;
            succesor = current;
            current = current.getLeftChild();
            
            if (!succesor.isRightChild(aSerBorrado)){
            succesorParent.setLeftChild(succesor.getRightChild());
            succesor.setRightChild(aSerBorrado.getRightChild());
        }
        }
        return succesor;
    }
    private TreeNode getPredecessorForDeletion(TreeNode aSerBorrado){
        TreeNode pre;
        TreeNode prePa;
        TreeNode curr;
        pre = aSerBorrado;
        prePa = pre;
        curr = pre.getLeftChild();
        while(curr != null){
            prePa = pre;
            pre = curr;
            curr = curr.getRightChild();
            
            if (!pre.isLeftChild(aSerBorrado)){
                prePa.setRightChild(pre.getLeftChild());
                pre.setLeftChild(aSerBorrado.getLeftChild());
            }
        }
        return pre;
    }
    
    public static int getSuccesor(TreeNode starterPoint){
        TreeNode succesor;
        TreeNode succesorParent;
        TreeNode current;
        succesor = starterPoint;
        succesorParent = succesor;
        current = succesor.getRightChild();
        while(current!=null){
            succesorParent = succesor;
            succesor = current;
            current = current.getLeftChild();
        }
        return succesor.getData();
    }
   
    public static int getPredecessor(TreeNode starterPoint){
        TreeNode predecessor;
        TreeNode predecessorParent;
        TreeNode current;
        predecessor = starterPoint;
        predecessorParent = predecessor;
        current = predecessor.getLeftChild();
        while(current!=null){
            predecessorParent = predecessor;
            predecessor = current;
            current = current.getRightChild();
        }
        return predecessor.getData();
    }
    
    public void inOrderPrint(){
        inOrder(root);
    }
    
    private void inOrder(TreeNode start){
        if(start != null){
            inOrder(start.getLeftChild());
            System.out.println(start.getData());
            inOrder(start.getRightChild());
        }
    }
    
    public int getHeight(){
        return getPHeight(root, 0, 0);
    }
    
    private int getPHeight(TreeNode start, int startLeft, int startRight){
        int lR = startRight;
        int lL = startLeft;
        if (start == null){
            return 0;
        }
        if (start.hasNoChildren()){
            return 1;
        }
        if(start.getLeftChild()!=null){
           lR = lR + getPHeight(start.getLeftChild(), 0, 0);
        }
        if(start.getRightChild()!=null){
            lL = lL + getPHeight(start.getRightChild(), 0, 0);
        }
        return (Math.max(lR, lL)+1);
    }
    
    public void breadth(){
        printBreadth(root);
    }
    
    private void printBreadth(TreeNode start){
        if (root == null) {
            System.out.println("Empty tree");
            return;
        } 
        if(root.hasNoChildren()){
            System.out.println(root.getData());
        }
        
        LinkedList<TreeNode> queue = new LinkedList();
        queue.add(root);
        System.out.print("[ ");
        while(!queue.isEmpty()){
            if(queue.getFirst().getLeftChild() != null){
                queue.add(queue.getFirst().getLeftChild());
            }
            if(queue.getFirst().getRightChild() != null){
                queue.add(queue.getFirst().getRightChild());
            }
            System.out.print(queue.getFirst().getData() + " ");
            queue.removeFirst();
        }
        System.out.println("]");
        
    }
    
    public static void main(String[] args){
        IterativeTree tree = new IterativeTree();
        tree.inOrderPrint();
        tree.insert(50);
        tree.insert(25);
        tree.insert(15);
        tree.insert(5);
        tree.insert(20);
        tree.insert(35);
        tree.insert(30);
        tree.insert(40);
        tree.deleteWithPredecessor(25);
        tree.inOrderPrint();
        System.out.println(tree.root.getLeftChild().getRightChild().getData());
        
    }
}