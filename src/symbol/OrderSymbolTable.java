package symbol;

public class OrderSymbolTable<Key extends Comparable, Value> {
    private Node head;
    private int N;

    private class Node {
        public Key key;
        public Value value;
        public Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

    }

    public OrderSymbolTable() {
        this.head = new Node(null, null, null);
        this.N = 0;
    }

    public int size() {
        return N;
    }

    public void put(Key key, Value value) {


        Node cur = head.next;
        Node pre = head;
        while (cur != null && key.compareTo(cur.key) > 0) {
            pre = cur;
            cur = cur.next;
        }
        if (cur != null && key.compareTo(cur.key) == 0) {
            cur.value = value;
            return;
        }
        Node newNode = new Node(key, value, cur);
        pre.next=newNode;
        N++;

    }

    public void delete(Key key) {

        Node n = head;
        while (n.next != null) {
            if (n.next.key.equals(key)) {
                n.next = n.next.next;
                N--;
                return;
            }
            n = n.next;
        }
    }

    public Value get(Key key) {
        Node n = head;
        while (n.next != null) {
            n = n.next;
            if (n.key.equals(key)) {
                return n.value;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        OrderSymbolTable<Integer, String> symbolTable = new OrderSymbolTable<>();
        symbolTable.put(1, "IU");
        symbolTable.put(2, "IUIU");
        symbolTable.put(7, "IUIUIU");

//        System.out.println(symbolTable.size());

        symbolTable.put(3, "IU呐！");
//        System.out.println(symbolTable.size());
        System.out.println(symbolTable.get(1));

    }
}
