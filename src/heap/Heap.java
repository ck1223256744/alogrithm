package heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class Heap<T extends Comparable<T>> {
    private T[] items;
    private int N;

    public Heap(int capacity) {
        this.items = (T[]) new Comparable[capacity+1];
        this.N = 0;
    }

    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    private void exch(int i, int j) {
        T tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    private void insert(T t) {
        items[++N] = t;
        swim(N);
    }

    private void swim(int k) {
        while (k>1){
            if(less(k/2,k)){
                exch(k, k/2);
            }
            k=k/2;
        }
    }

    private T delMax(){
        T max=items[1];
        exch(1,N);
        items[N]=null;
        N--;
        sink(1);
        return max;
    }

    private void sink(int k) {
        while (2*k<=N){
            int max;
            //have a right node
            if(2*k+1<=N){
                if(less(2*k, 2*k+1)){
                    max=2*k+1;
                }else {
                    max=2*k;
                }
            }else {
                //have only left node
                max=2*k;
            }


            if(less(k,max)){
                exch(k,max);
                k=max;
            }else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Heap<String> h=new Heap(10);
        h.insert("a");
        h.insert("b");
        h.insert("c");
        h.insert("d");
        h.insert("e");
        h.insert("f");
        h.insert("h");
        h.insert("i");
        h.insert("j");
        h.insert("k");


        String result=null;
        while ((result=h.delMax())!=null){
            System.out.print(result+" ");
        }
    }


}
