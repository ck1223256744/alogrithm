package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderTest2 {
    public static class TreeNode {
        String val;
        TreeNode left;
        TreeNode right;

        TreeNode(String x) {
            val = x;
        }
    }
    public static TreeNode buildTree(String[] postorder, String[] inorder) {

        Map<String, Integer> indexMap = new HashMap<String, Integer>();
        int length = inorder.length;
        for (int i = 0; i < length; i++) {
            indexMap.put(inorder[i], i);
        }
        TreeNode root = buildTree(postorder, 0, length - 1, inorder, 0, length - 1, indexMap);
        return root;
    }

    public static TreeNode buildTree(String[] postorder, int postorderStart, int postorderEnd, String[] inorder, int inorderStart, int inorderEnd, Map<String, Integer> indexMap) {
        if (postorderStart > postorderEnd) {
            return null;
        }
        String rootVal = postorder[postorderEnd];
        TreeNode root = new TreeNode(rootVal);
        if (postorderStart == postorderEnd) {
            return root;
        } else {
            int rootIndex = indexMap.get(rootVal);
            int leftNodes = rootIndex - inorderStart, rightNodes = inorderEnd - rootIndex;
            TreeNode leftSubtree = buildTree(postorder, postorderStart, postorderStart + leftNodes - 1, inorder, inorderStart, rootIndex - 1, indexMap);
            TreeNode rightSubtree = buildTree(postorder, postorderEnd - rightNodes , postorderEnd-1, inorder, rootIndex + 1, inorderEnd, indexMap);
            root.left = leftSubtree;
            root.right = rightSubtree;
            return root;
        }
    }
    private static void preErgodic(TreeNode x){
        if(x==null){
            return;
        }
        System.out.println(x.val);

        if(x.left!=null) {
            preErgodic(x.left);
        }

        if(x.right!=null){
            preErgodic(x.right);
        }

    }

    public static void main(String[] args) {

        String[] postorder={"g","e","d","b","f","h","c","a"};
        String[] inorder={"g","b","e","d","a","f","c","h"};
        TreeNode root = buildTree (postorder, inorder);
        preErgodic(root);
    }
}
