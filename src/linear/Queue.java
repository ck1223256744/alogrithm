package linear;

import java.util.Iterator;

public class Queue<T> implements Iterable {
    private Node head;
    private Node last;
    private int N;

    public int size() {

        return N;

    }

    private class Node {
        public T item;
        public Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public Queue() {
        this.head = new Node(null, null);
        this.last = null;
        this.N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int length() {
        return N;
    }

    public void enqueue(T t) {
        if (last == null) {
            last = new Node(t, null);
            head.next = last;
        } else {
            Node oldLast = last;
            last = new Node(t, null);
            oldLast.next = last;
        }
        N++;
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        Node oldFirst = head.next;
        head.next = oldFirst.next;
        N--;

        if (isEmpty()) {
            last = null;
        }
        return oldFirst.item;
    }

    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.dequeue();
        q.enqueue(4);
        Iterator it = q.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }

    @Override
    public Iterator iterator() {
        // TODO Auto-generated method stub
        return new QIterator();
    }

    public class QIterator implements Iterator {

        private Node n;

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub

            return n.next != null;
        }

        @Override
        public Object next() {
            // TODO Auto-generated method stub
            n = n.next;
            return n.item;
        }

        public QIterator() {
            this.n = head;
        }

    }
}