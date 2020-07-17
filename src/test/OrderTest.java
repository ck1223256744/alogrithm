package test;

import java.util.HashMap;
import java.util.Map;

public class OrderTest {
    public static class TreeNode {
        String val;
        TreeNode left;
        TreeNode right;

        TreeNode(String x) {
            val = x;
        }
    }
    public static TreeNode buildTree(String[] preorder, String[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Map<String, Integer> indexMap = new HashMap<String, Integer>();
        int length = preorder.length;
        for (int i = 0; i < length; i++) {
            indexMap.put(inorder[i], i);
        }
        TreeNode root = buildTree(preorder, 0, length - 1, inorder, 0, length - 1, indexMap);
        return root;
    }

    public static TreeNode buildTree(String[] preorder, int preorderStart, int preorderEnd, String[] inorder, int inorderStart, int inorderEnd, Map<String, Integer> indexMap) {
        if (preorderStart > preorderEnd) {
            return null;
        }
        String rootVal = preorder[preorderStart];
        TreeNode root = new TreeNode(rootVal);
        if (preorderStart == preorderEnd) {
            return root;
        } else {
            int rootIndex = indexMap.get(rootVal);
            int leftNodes = rootIndex - inorderStart, rightNodes = inorderEnd - rootIndex;
            TreeNode leftSubtree = buildTree(preorder, preorderStart + 1, preorderStart + leftNodes, inorder, inorderStart, rootIndex - 1, indexMap);
            TreeNode rightSubtree = buildTree(preorder, preorderEnd - rightNodes + 1, preorderEnd, inorder, rootIndex + 1, inorderEnd, indexMap);
            root.left = leftSubtree;
            root.right = rightSubtree;
            return root;
        }
    }
    private static void postErgodic(TreeNode x){
        if(x==null){
            return;
        }
        if(x.left!=null) {
            postErgodic(x.left);
        }

        if(x.right!=null){
            postErgodic(x.right);
        }
        System.out.println(x.val);

    }

    public static void main(String[] args) {
        String[] preorder={"a","b","g","d","e","c","f","h"};
        String[] inorder={"g","b","e","d","a","f","c","h"};
        TreeNode root = buildTree (preorder, inorder);
        postErgodic(root);
    }
}
