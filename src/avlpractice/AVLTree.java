package avlpractice;

import java.util.LinkedList;

/**
 *
 * @author JESUS
 */
public class AVLTree {

    AVLNode root;

    public AVLTree() {
        root = null;
    }

    public void inOrder(StringBuilder sb, AVLNode start) {
        if (start != null) {
            inOrder(sb, start.left);
            sb.append(start.data).append(" ");
            inOrder(sb, start.right);
        }
    }

    public String inOrder() {
        StringBuilder stB = new StringBuilder();
        stB.append("[ ");
        inOrder(stB, root);
        stB.append(" ]");
        return stB.toString();
    }

    public boolean isEmpty() {
        return root == null;
    }

    public AVLNode getMax() {
        return getMax(root);
    }

    public void levelPrint() {
        levelPrint(root);
    }

    private void levelPrint(AVLNode start) {
        LinkedList<AVLNode> queue = new LinkedList();
        if (root == null) {
            System.out.println("Empty tree.");
        } else {
            System.out.print("[ ");
            queue.add(root);
            while (!queue.isEmpty()) {
                if (queue.getFirst().left != null) {
                    queue.add(queue.getFirst().left);
                }
                if (queue.getFirst().right != null) {
                    queue.add(queue.getFirst().right);
                }
                System.out.print(queue.removeFirst().data + " ");
            }
            System.out.println("]");
        }
    }

    private AVLNode getMax(AVLNode start) {
        if (root == null) {
            return null;
        }
        if (root.right == null) {
            return root;
        }
        return getMax(root.right);
    }

    public AVLNode getMin() {
        return getMin(root);
    }

    private AVLNode getMin(AVLNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            return root;
        }
        return getMin(root.left);
    }

    //Left rotation. Performed when: Node is right heavy (balance = 2) and node's right subtree is not left heavy.
    /*
    public void LR(AVLNode fulcrum) {
        AVLNode ref1, ref2, ref3;
        ref1= fulcrum;
        ref2= fulcrum.right;
        ref3=fulcrum.right.left;
        if (fulcrum == root) {
            root = fulcrum.right;
        }
        AVLNode temp = fulcrum.right.left;
        fulcrum.right.left = fulcrum;
        fulcrum.right = temp;
        if(ref1!=null){
            ref1.calculateBalance();
        }
        if(ref2!=null){
            ref2.calculateBalance();
        }
        if(ref3!=null){
            ref3.calculateBalance();
        }
        
    }
    //Right rotation. Performed when: Node is left heavy (balance = -2) and node's left subtree is not right heavy.
    public void RR(AVLNode fulcrum) {
        AVLNode ref1,ref2,ref3;
        ref1=fulcrum;
        ref2=fulcrum.left;
        ref3=fulcrum.left.right;
        if (fulcrum == root) {
            root = fulcrum.left;
        }
        AVLNode temp = fulcrum.left.right;
        fulcrum.left.right = fulcrum;
        fulcrum.left = temp;
        
        if(ref1!=null){
            ref1.calculateBalance();
        }
        if(ref2!=null){
            ref2.calculateBalance();
        }
        if(ref3!=null){
            ref3.calculateBalance();
        }
    }*/
    //Variation of RR to be used in double rotation. 
    public void RR(AVLNode fulcrum, AVLNode parent) {
        AVLNode ref1, ref2, ref3;
        ref1 = fulcrum;
        ref2 = fulcrum.left;
        ref3 = fulcrum.left.right;
        if (fulcrum == root) {
            root = fulcrum.left;
        }
        AVLNode temp = fulcrum.left.right;
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
    //variation of LR to be used in double rotation

    public void LR(AVLNode fulcrum, AVLNode parent) {
        AVLNode ref1, ref2, ref3;
        ref1 = fulcrum;
        ref2 = fulcrum.right;
        ref3 = fulcrum.right.left;
        if (fulcrum == root) {
            root = fulcrum.right;
        }
        AVLNode temp = fulcrum.right.left;
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

    //Left-right rotation. Performed when: Node is right heavy (balance = 2) and node's right subtree is left heavy (-1))
    public void LRR(AVLNode fulcrum) {
        RR(fulcrum.right, fulcrum);
        LR(fulcrum, findParent(fulcrum.data, root));
    }

    //Right-left rotation. Performed when: Node is left heavy (balance = -2) and node's left subtree is right heavy (+1)
    public void RLR(AVLNode fulcrum) {
        LR(fulcrum.left, fulcrum);
        RR(fulcrum, findParent(fulcrum.data, root));
    }

    private AVLNode findParent(int element, AVLNode start) {
        if (start == null) {
            return null;
        }
        if (start.data == element) {
            return null;
        } else {
            if ((start.left != null && start.left.data == element) || (start.right != null && start.right.data == element)) {
                return start;
            }
            if (element < start.data) {
                findParent(element, start.left);
            } else {
                findParent(element, start.right);
            }
        }
        return null;
    }

    public boolean insert(int nElementInt) {
        if (root == null) {
            root = new AVLNode(nElementInt, null, null);
            return true;
        }
        insert(new AVLNode(nElementInt, null, null), root);
        return true;
    }

    public void insert(AVLNode newE, AVLNode start) {
        if (newE.data < start.data) {
            if (start.left == null) {
                start.left = newE;
                update(start);
                if (start.balance < -1 || start.balance > 1) {
                    rebalance(start);
                }
            } else {
                insert(newE, start.left);
                update(start);
                if (start.balance < -1 || start.balance > 1) {
                    rebalance(start);
                }

            }
        } else {
            if (start.right == null) {
                start.right = newE;
                update(start);
                if (start.balance < -1 || start.balance > 1) {
                    rebalance(start);
                }
            } else {
                insert(newE, start.right);
                update(start);
                if (start.balance < -1 || start.balance > 1) {
                    rebalance(start);
                }
            }
        }
    }

    public void rebalance(AVLNode start) {
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
                    RR(start, findParent(start.data, root));
                } else if (start.left.balance > 0) {
                    RLR(start);
                }
                break;
            case "rightheavy":
                if (start.right.balance >= 0) {
                    LR(start, findParent(start.data, root));
                } else if (start.right.balance < 0) {
                    LRR(start);
                }
                break;
        }
    }

    public void balanceAfterDeletion(int key) {
        balanceAfterDeletion(root, key);
    }

    private void balanceAfterDeletion(AVLNode start, int key) {
        if (start == null) {
            return;
        }
        if (key < start.data) {
            balanceAfterDeletion(start.left, key);
            update(start);
            if (start.balance < -1 || start.balance > 1) {
                rebalance(start);
            }
        } else {
            balanceAfterDeletion(start.right, key);
            update(start);
            if (start.balance < -1 || start.balance > 1) {
                rebalance(start);
            }
        }
    }

    public void delete(int key) {
        AVLNode newTree = delete(root, key);
        if (newTree != null) {
            root = newTree;
            balanceAfterDeletion(key);
        } else {
            System.out.println("Not in tree.");
        }
    }

    public void swap(AVLNode a, AVLNode b) {
        int temp = b.data;
        b.data = a.data;
        a.data = temp;
    }

    public AVLNode delete(AVLNode start, int key) {
        if (start == null) {
            return null;
        } else if (key < start.data) {
            start.left = delete(start.left, key);
        } else if (key > start.data) {
            start.right = delete(start.right, key);
        } else {
            if (start.hasNoChildren()) {
                start = null;
            } else if (start.left == null && start.right != null) {
                start = start.right;
            } else if (start.left != null && start.right == null) {
                start = start.left;
            } else {
                AVLNode predecessor = start.left.max();
                swap(predecessor, start);
                delete(start.left, key);
            }
        }
        return start;
    }

    public void update(AVLNode node) {
        node.calculateBalance();
    }
    
    @Override
    public String toString(){
        return inOrder();
    }

    public static void main(String[] args) {
        AVLTree test = new AVLTree();
        test.insert(7);
        test.insert(28);
        test.insert(39);
        test.insert(69);
        test.insert(22);
        test.insert(1);
        test.insert(2);
        test.insert(3);
        test.insert(101);
        test.insert(70);
        test.delete(39);
        test.delete(1);
        test.delete(7);
        System.out.println(test.toString());
    }

}
