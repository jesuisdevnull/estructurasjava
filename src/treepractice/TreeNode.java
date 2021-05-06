/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treepractice;

/**
 *
 * @author JESUS
 */
public class TreeNode {

    public int data;
    public TreeNode right;
    public TreeNode left;

    public TreeNode(int newD, TreeNode newL, TreeNode newR) {
        data = newD;
        right = newR;
        left = newL;
    }

    public boolean isBinarySearch(TreeNode root) {

        if (root.hasNoChildren()) {
            return true;
        } else if (root.left != null && root.right == null) {
            return  root.data > root.left.data;
        } else if (root.right != null && root.left == null) {
            return root.data < root.right.data;
        } else {
            return  ((root.left.data < root.data) == (root.data < root.right.data));
        }
        
    }

    public boolean hasNoChildren() {
        return (right == null && left == null);
    }

    public TreeNode max() {
        if (this.right == null) {
            return this;
        }
        return this.right.max();
    }

    public TreeNode getAncestor(TreeNode start, TreeNode ancestor, int key) {
        if (key > start.data) {
            return getAncestor(start.right, start, key);
        } else if (key < start.data) {
            return getAncestor(start.left, start, key);
        }
        return ancestor;
    }
    
    
}
