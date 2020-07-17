package priority;

public class MaxPriorityQueue<T extends Comparable<T>> {
    private T[] items;
    private int N;

    public MaxPriorityQueue(int Capacity){
        this.items= (T[]) new Comparable[Capacity+1];
        this.N=0;
    }

    private int size(){
        return N;
    }

    private boolean isEmpty(){
        return N==0;
    }

    private boolean less(int i,int j){
        return items[i].compareTo(items[j])<0;
    }

    private void exch(int i,int j){
        T tmp=items[i];
        items[i]=items[j];
        items[j]=tmp;
    }

    private void insert(T t){
        items[++N]=t;
        swim(N);
    }

    private void swim(int k) {
        while (k>1){
            if(less(k/2,k)){
                exch(k/2,k);
            }
            k=k/2;
        }
    }

    private T delMax(){
        T max=items[1];
        exch(1,N);
        N--;
        sink(1);
        return max;
    }

    private void sink(int k) {
        while (2*k<=N){
            int max;
            if(2*k+1<=N){
                if(less(2*k,2*k+1)){
                    max=2*k+1;
                }else {
                    max=2*k;
                }
            }else {
                max=2*k;
            }
            if(!less(k,max)){
                break;
            }
            exch(k,max);
            k=max;
        }
    }

    public static void main(String[] args) {
        MaxPriorityQueue<Integer> maxPriorityQueue=new MaxPriorityQueue(10);
        maxPriorityQueue.insert(2);
        maxPriorityQueue.insert(3);
        maxPriorityQueue.insert(1);
        maxPriorityQueue.insert(8);
        maxPriorityQueue.insert(5);
        while (!maxPriorityQueue.isEmpty()){
            Integer max = maxPriorityQueue.delMax();
            System.out.print(max+" ");
        }
    }
}
