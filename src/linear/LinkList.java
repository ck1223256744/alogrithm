package linear;

import java.util.Iterator;

public class LinkList<T> implements Iterable {

    private Node head;
    private int N;

    public class Node {

        T item;
        Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }

    }

    public LinkList() {
        this.head = new Node(null, null);
        this.N = 0;
    }

    public void clear() {
        head.next = null;
        this.N = 0;
    }

    public int length() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public T get(int index) {
        Node n = head.next;
        for (int i = 0; i < index; i++) {
            n = n.next;
        }
        return n.item;
    }

    public void insert(T t) {
        Node n = head;
        while (n.next != null) {
            n = n.next;
        }
        Node nextNode = new Node(t, null);
        n.next = nextNode;
        N++;
    }

    public void insert(T t, int index) {
        Node pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node cur = pre.next;
        Node nextNode = new Node(t, cur);
        pre.next = nextNode;
        N++;
    }

    public T remove(int index) {
        Node pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node cur = pre.next;
        Node nextNode = cur.next;
        pre.next = nextNode;
        N--;
        return cur.item;
    }

    public int indexOf(T t) {
        Node n = head;
        for (int i = 0; n.next != null; i++) {
            n = n.next;
            if (n.item.equals(t)) {
                return i;
            }
        }
        return -1;
    }

    public void reverse() {
        if (isEmpty()) {
            return;
        }
        reverse(head.next);
    }

    public Node reverse(Node cur) {
        if (cur.next == null) {
            head.next = cur;
            return cur;
        }
        Node pre = reverse(cur.next);
        pre.next = cur;
        cur.next = null;
        return cur;
    }

    public void rev(){
        if(isEmpty()){
            return;
        }
        Node pre=null;
        Node n=head.next;
        Node tmp;
        while (n!=null){
            tmp=n.next;
            n.next=pre;
            pre=n;
            n=tmp;
        }
        head.next=pre;
    }

    @Override
    public Iterator iterator() {
        return new SIterator();
    }

    private class SIterator implements Iterator {

        private Node n;

        public SIterator() {
            this.n = head;
        }

        @Override
        public boolean hasNext() {
            return n.next != null;
        }

        @Override
        public Object next() {
            n = n.next;
            return n.item;
        }

    }

    public static void main(String[] args) {
        LinkList<String> l = new LinkList<>();
        l.insert("s");
        l.insert("a");
        l.insert("r",1);

         for (Object s : l) {
         System.out.println(s);
         }
        l.rev();
        Iterator it = l.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }
}