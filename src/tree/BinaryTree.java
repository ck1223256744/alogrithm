package tree;


import linear.Queue;

import java.util.Iterator;


public class BinaryTree<Key extends Comparable<Key>,Value> {
    private Node root;
    private int N;
    private class Node{
        public Key key;
        private Value value;
        private Node left;
        private Node right;

        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    public int size(){
        return N;
    }
    public void put(Key key,Value value){
        root=put(root,key,value);
    }

    private Node put(Node x, Key key, Value value) {
        if(x==null){
            N++;
            return new Node(key,value,null,null);
        }

        int cmp=key.compareTo(x.key);
        if(cmp>0){
            x.right=put(x.right,key,value);
        }else if(cmp<0){
            x.left=put(x.left,key,value);
        }else {
            x.value=value;
        }
        return x;
    }

    public Value get(Key key){
        return get(root,key);
    }

    private Value get(Node x, Key key) {
        if(x==null){
            return null;
        }
        int cmp=key.compareTo(x.key);
        if(cmp>0){
            return get(x.right,key);
        }else if(cmp<0){
            return get(x.left,key);
        }else {
            return x.value;
        }
    }

    private void delete(Key key){
        root=delete(root, key);

    }
    private Node delete(Node x,Key key){
        if(x==null){
            return null;
        }
        int cmp=key.compareTo(x.key);
        if(cmp>0){
            x.right=delete(x.right,key);
        }else if(cmp<0){
            x.left=delete(x.left,key);
        }else {
            N--;
            if(x.right==null){
                return x.left;
            }
            if(x.left==null){
                return x.right;
            }
            Node minNode=x.right;
            while (minNode.left!=null){
                minNode=minNode.left;
            }

            Node n=x.right;
            while (n.left!=null){
                if(n.left.left==null){
                    n.left=null;
                }else {
                    n=n.left;
                }
                minNode.left=x.left;
                minNode.right=x.right;
                x=minNode;
                return x;
            }

            if(n.left==null) {
                minNode.left = x.left;
                minNode.right = n.right;
                x=minNode;
                n = null;
                return x;
            }

        }
        return x;

    }

    public Key min(){
        return min(root).key;
    }

    private Node min(Node x){
        if(x.left!=null){
            return min(x.left);
        }else {
            return x;
        }
    }

    public Key max(){
        return max(root).key;
    }

    private Node max(Node x) {
        if(x.right!=null){
            return max(x.right);
        }else {
            return x;
        }
    }

    private Queue<Key> preErgodic(){
        Queue<Key> keys = new Queue<>();
        preErgodic(root,keys);
        return keys;
    }


    private void preErgodic(Node x,Queue<Key> keys){
        if(x==null){
            return;
        }
        keys.enqueue(x.key);
        if(x.left!=null){
            preErgodic(x.left,keys);
        }
        if(x.right!=null){
            preErgodic(x.right,keys);
        }

    }

    private Queue<Key> midErgodic(){
        Queue<Key> keys = new Queue<>();
        midErgodic(root,keys);
        return keys;
    }


    private void midErgodic(Node x,Queue<Key> keys){
        if(x==null){
            return;
        }
        if(x.left!=null){
            preErgodic(x.left,keys);
        }
        keys.enqueue(x.key);

        if(x.right!=null){
            preErgodic(x.right,keys);
        }

    }

    private Queue<Key> postErgodic(){
        Queue<Key> keys = new Queue<>();
        postErgodic(root,keys);
        return keys;
    }


    private void postErgodic(Node x,Queue<Key> keys){
        if(x==null){
            return;
        }
        if(x.left!=null) {
            postErgodic(x.left, keys);
        }

        if(x.right!=null){
            postErgodic(x.right,keys);
        }
        keys.enqueue(x.key);

    }


    public Queue<Key> LayerErgodic(){
        Queue<Key> keys = new Queue<>();
        Queue<Node> nodes = new Queue<>();

        nodes.enqueue(root);
        while (!nodes.isEmpty()){
            Node n=nodes.dequeue();
            keys.enqueue(n.key);
            if (n.left!=null){
                nodes.enqueue(n.left);
            }
            if (n.right!=null){
                nodes.enqueue(n.right);
            }
        }
        return keys;
    }

    public int maxDepth(){

        return maxDepth(root);

    }

    private int maxDepth(Node x){
        if(x==null){
            return 0;
        }
        int max=0;
        int maxL=0;
        int maxR=0;

        if(x.left!=null){
            maxL=maxDepth(x.left);
        }
        if(x.right!=null){
            maxR=maxDepth(x.right);
        }

        max = maxL > maxR ? maxL + 1 : maxR + 1;
        return max;
    }



    public static void main(String[] args) {
        BinaryTree<Integer,String> binaryTree=new BinaryTree<>();
        binaryTree.put(20, "20");
        binaryTree.put(10, "10");
        binaryTree.put(9, "9");
        binaryTree.put(15, "15");
        binaryTree.put(13, "13");
        binaryTree.put(14, "14");
//        System.out.println(binaryTree.get(7));
        binaryTree.delete(10);
//        System.out.println(binaryTree.get(2));
        Integer min = binaryTree.min();
        Integer max = binaryTree.max();
//        System.out.println(max);

//        Queue<Integer> keys=binaryTree.LayerErgodic();
//        Iterator it=keys.iterator();
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }
//        for (Object key : keys) {
//
//            String s = binaryTree.get((Integer) key);
//
//            System.out.println(key+" "+s);
//        }

        System.out.println(binaryTree.maxDepth());
    }
}
