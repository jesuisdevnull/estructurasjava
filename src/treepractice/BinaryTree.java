package treepractice;

import java.lang.Math;
import java.util.LinkedList;

public class BinaryTree {

    //Testing rotations
    public void LR(TreeNode fulcrum) {
        if (fulcrum==root){
            root=fulcrum.right;
        }
        TreeNode temp = fulcrum.right.left;
        fulcrum.right.left = fulcrum;
        fulcrum.right = temp;
        
    }

    public void RR(TreeNode fulcrum) {
        if (fulcrum==root){
            root=fulcrum.left;
        }
        TreeNode temp = fulcrum.left.right;
        fulcrum.left.right = fulcrum;
        fulcrum.left = temp;
    }

    //End of rotations
    TreeNode root;

    public BinaryTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(int key) {
        TreeNode newE = new TreeNode(key, null, null);
        if (root == null) { //Root is null
            root = newE;
        } else { //Root is not null
            if (root.hasNoChildren() || root.left == null) { //Either has no children or left is null
                root.left = newE;
            } else {
                if (root.left != null && root.right == null) { //Left is not null, but right is
                    root.right = newE;
                } else { //Both children are not null;
                    LinkedList<TreeNode> queue = new LinkedList();
                    TreeNode temp;
                    queue.add(root);
                    boolean added = false;
                    while (!added) {
                        if (queue.getFirst().left != null) {
                            queue.add(queue.getFirst().left);
                        }
                        if (queue.getFirst().right != null) {
                            queue.add(queue.getFirst().right);
                        }
                        temp = queue.removeFirst();
                        if (temp.hasNoChildren() || (temp.left == null)) {
                            temp.left = newE;
                            added = true;
                        } else {
                            if (temp.left != null && temp.right == null) {
                                temp.right = newE;
                                added = true;
                            }
                        }
                    }
                }
            }
        }
    }

    public void swap(TreeNode a, TreeNode b) {
        int temp = a.data;
        a.data = b.data;
        b.data = temp;
    }

    public TreeNode getRightMost() {
        if (root == null) {
            return null;
        } else {

            if (root.hasNoChildren()) {
                return root;
            } else if (root.left != null && root.right == null) {
                return root.left;
            } else {
                LinkedList<TreeNode> queue = new LinkedList();
                queue.add(root);
                TreeNode temp = root;
                while (!queue.isEmpty()) {
                    temp = queue.getFirst();
                    queue.removeFirst();
                    if (temp.left != null) {
                        queue.add(temp.left);
                    }
                    if (temp.right != null) {
                        queue.add(temp.right);
                    }

                }
                return temp;
            }

        }
    }

    public void deleteRightMost() {
        if (root == null) {
            return;
        }
        if (root.hasNoChildren()) {
            root = null;
            return;
        }
        if (root.right == null && root.left != null) {
            root.left = null;
        } else {
            LinkedList<TreeNode> queue = new LinkedList();
            TreeNode del = getRightMost();
            TreeNode temp = root;
            queue.add(root);
            while (true) {
                temp = queue.getFirst();
                queue.removeFirst();
                if (temp.right != null) {
                    if (temp.right.data == del.data) {
                        temp.right = null;
                        break;
                    } else {
                        queue.add(temp.right);
                    }

                }
                if (temp.left != null) {
                    if (temp.left.data == del.data) {
                        temp.left = null;
                        break;
                    } else {
                        queue.add(temp.left);
                    }

                }
            }
        }
    }

    public void delete(int key) {
        if (key == root.data && root.hasNoChildren()) {
            root = null;
            return;
        }
        TreeNode a = BFS(key);
        TreeNode b = getRightMost();
        swap(a, b);
        deleteRightMost();
    }

    public TreeNode BFS(int key) {
        TreeNode temp = null;
        LinkedList<TreeNode> queue = new LinkedList();
        if (root == null) {
            return null;
        } else {
            queue.add(root);
            while (!queue.isEmpty()) {
                if (queue.getFirst().left != null) {
                    queue.add(queue.getFirst().left);
                }
                if (queue.getFirst().right != null) {
                    queue.add(queue.getFirst().right);
                }
                temp = queue.removeFirst();
                if (temp.data == key) {
                    queue.clear();
                }
            }
            if (temp == null) {
                return null;
            }
            return temp;
        }
    }

    private void inOrder(StringBuilder sb, TreeNode start) {
        if (start != null) {
            inOrder(sb, start.left);
            sb.append(start.data).append(" ");
            inOrder(sb, start.right);
        }
    }

    public void inOrder() {
        StringBuilder stB = new StringBuilder();
        stB.append("[ ");
        inOrder(stB, root);
        stB.append(" ]");
        System.out.println(stB.toString());
    }

    public void levelPrint() {
        levelPrint(root);
    }

    private void levelPrint(TreeNode start) {
        LinkedList<TreeNode> queue = new LinkedList();
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

    public static void main(String[] args) {
        BinaryTree t = new BinaryTree();
        t.insert(19);
        t.insert(26);
        t.insert(22);
        t.insert(41);
        t.insert(33);
        t.insert(29);
        t.levelPrint();
        t.LR(t.root);
        t.levelPrint();
    }
}
