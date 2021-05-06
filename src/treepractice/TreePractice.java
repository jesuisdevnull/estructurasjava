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
public class TreePractice {

    public void isTreeBinarySearch(RecursiveTree tree){
        boolean flag = isTreeBinarySearch(tree.root,true);
        System.out.println(flag);
    }
    
    
    public boolean isTreeBinarySearch(TreeNode tree, boolean flag){
        boolean local = flag;
        if (local==false){
            return flag;
        }
        if (tree == null){
            local = true;
        } else if(tree.hasNoChildren()){
            local = true;
        } else {
            local = isTreeBinarySearch(tree.left, flag);
            if (local==false){
                return local;
            }
            local = tree.isBinarySearch(tree);
            if (local==false){
                return local;
            }
            local = tree.isBinarySearch(tree);
            isTreeBinarySearch(tree.right, flag);
            if (local==false){
                return local;
            }
        }
        return local;
    }
    
    
    
    public static void main(String[] args){
        TreePractice tp = new TreePractice();
        RecursiveTree t = new RecursiveTree();
	t.insert(50);
	t.insert(60);
	t.insert(40);
	t.insert(30);
	t.insert(45);
        tp.isTreeBinarySearch(t);
    }
}
