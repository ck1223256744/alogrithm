package test;

import linear.Queue;

public class PaperFolding {
    public static void main(String[] args) {
        Node<String> tree = createtree(3);
        printTree(tree);

    }

    public static Node createtree(int N){

        Node<String> root=null;
        for (int i = 0; i < N; i++) {
            if(i==0){
                root=new Node<String>("down",null,null);
                continue;
            }
            Queue<Node> queue=new Queue<>();
            queue.enqueue(root);
            while (!queue.isEmpty()){
                Node<String> tmp = queue.dequeue();
                if(tmp.left!=null){
                    queue.enqueue(tmp.left);
                }
                if(tmp.right!=null){
                    queue.enqueue(tmp.right);
                }

                if(tmp.left==null && tmp.right==null){
                    tmp.left=new Node<String>("down", null,null);
                    tmp.right=new Node<String>("up",null,null);
                }
            }
        }
        return root;
    }

    private static void printTree(Node<String> root){
        if(root==null){
            return;
        }
        if(root.left!=null){
            printTree(root.left);
        }
        System.out.print(root.item+" ");
        if(root.right!=null){
            printTree(root.right);
        }
    }

    public static class Node<T>{
        private T item;
        private Node left;
        private Node right;

        public Node(T item, Node left, Node right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }
}
