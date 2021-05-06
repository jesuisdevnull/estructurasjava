package treepractice;

import java.util.LinkedList;

public class RecursiveTree extends Tree {

    public TreeNode root;

    public RecursiveTree() {
        root = null;
    }

    public RecursiveTree(TreeNode newR) {
        root = newR;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private TreeNode getMaxHelper(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.right == null) {
            return root;
        }
        return getMaxHelper(root.right);
    }

    public int getMax() {
        return getMaxHelper(root).data;
    }

    private TreeNode getMinHelper(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            return root;
        }
        return getMinHelper(root.left);
    }

    public int getMin() {
        return getMinHelper(root).data;
    }

    public boolean insert(int nElementInt) {
        if (root == null) {
            root = new TreeNode(nElementInt, null, null);
            return true;
        }
        insertHelper(new TreeNode(nElementInt, null, null), root);
        return true;
    }

    public void insertHelper(TreeNode nElement, TreeNode start) {
        if (nElement.data < start.data) {
            if (start.left == null) {
                start.left = nElement;
            } else {
                insertHelper(nElement, start.left);
            }
        } else {
            if (start.right == null) {
                start.right = nElement;
            } else {
                insertHelper(nElement, start.right);
            }
        }
    }

    private TreeNode findParent(int element, TreeNode start) {
        if (start == null) {
            return null;
        }
        if (start.data == element) {
            return start;
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

    private TreeNode findHelper(int element, TreeNode start) {
        if (start.data == element) {
            return start;
        }
        if (element < start.data) {
            if (start.left == null) {
                return null;
            } else {
                return findHelper(element, start.left);
            }
        } else {
            if (start.right == null) {
                return null;
            } else {
                return findHelper(element, start.right);
            }
        }
    }

    public boolean exists(int key) {
        TreeNode element = find(key);
        return (element != null);
    }

    public TreeNode find(int elementInt) {
        return findHelper(elementInt, root);
    }

    public void inOrderHelper(StringBuilder sb, TreeNode start) {
        if (start != null) {
            inOrderHelper(sb, start.left);
            sb.append(start.data).append(" ");
            inOrderHelper(sb, start.right);
        }
    }

    public void inOrder() {
        StringBuilder stB = new StringBuilder();
        stB.append("[ ");
        inOrderHelper(stB, root);
        stB.append(" ]");
        System.out.println(stB.toString());
    }

    private TreeNode getPredecessor(TreeNode start) {
        if (start == null) {
            return null;
        } else if (start.left != null) {
            return start.left.max();
        } else {
            return start.getAncestor(root, null, start.data);
        }
    }

    public void delete(int key) {
        TreeNode newTree = deleteHelper(root, key);
        if (newTree != null) {
            root = newTree;
        } else {
            System.out.println("Not in tree.");
        }
    }

    public void swap(TreeNode a, TreeNode b) {
        int temp = a.data;
        b.data = a.data;
        a.data = temp;
    }

    public TreeNode deleteHelper(TreeNode start, int key) {
        if (start == null) {
            return null;
        } else if (key < start.data) {
            start.left = deleteHelper(start.left, key);
        } else if (key > start.data) {
            start.right = deleteHelper(start.right, key);
        } else {
            if (start.hasNoChildren()) {
                start = null;
            } else if (start.left == null && start.right != null) {
                start = start.right;
            } else if (start.left != null && start.right == null) {
                start = start.left;
            } else {
                TreeNode predecessor = start.left.max();
                swap(predecessor, start);
                deleteHelper(start.left, key);
            }
        }
        return start;
    }

    public void BFP() {
        BFP(root);
    }

    private void BFP(TreeNode start) {
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

    public int countLeaves() {
        return countLeaves(root, 0);
    }

    public int countLeaves(TreeNode start, int counter) {
        int localCount = counter;
        if (start != null) {
            localCount = countLeaves(start.left, localCount);
            if (start.hasNoChildren()) {
                localCount++;
            }
            localCount = countLeaves(start.right, localCount);
        }
        return localCount;
    }

    public static void main(String[] args) {
        RecursiveTree t = new RecursiveTree();
        t.insert(50);
        t.insert(60);
        t.insert(40);
        t.insert(30);
        t.insert(45);
        System.out.println(t.countLeaves());
    }
}
