package tree;

import java.util.Queue;

public class RBT<Key extends Comparable, Value> {
    private Node root;
    private int N;
    private static final boolean RED = true;
    private static final boolean BlACK = false;


    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private boolean color;

        public Node(Key key, Value value, Node left, Node right, boolean color) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.color = color;
        }
    }

    public int size() {
        return N;
    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BlACK;
        h.right.color = BlACK;
    }

    private void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BlACK;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) {
            N++;
            return new Node(key, val, null, null, RED);
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.value = val;
        }

        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }

        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }

        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        return h;
    }

    private Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }

    public static void main(String[] args) {
        RBT<Integer, String> tree = new RBT<>();
        tree.put(1, "ck");
        tree.put(2, "IU");
        tree.put(3, "Obito");

        String r1 = tree.get(1);
        System.out.println(r1);

        String r2 = tree.get(2);
        System.out.println(r2);

        String r3 = tree.get(3);
        System.out.println(r3);

    }
}
