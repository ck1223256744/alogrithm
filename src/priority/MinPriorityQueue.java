package priority;

public class MinPriorityQueue<T extends Comparable<T>> {
    private T[] items;
    private int N;

    public MinPriorityQueue(int Capacity){
        this.items= (T[]) new Comparable[Capacity+1];
        this.N=0;
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
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

    public void insert(T t){
        items[++N]=t;
        swim(N);
    }

    private void swim(int k) {
        while (k>1){
            if(less(k,k/2)){
                exch(k/2,k);
            }
            k=k/2;
        }
    }

    public T delMin(){
        T min=items[1];
        exch(1,N);
        N--;
        sink(1);
        return min;
    }

    private void sink(int k) {
        while (2*k<=N){
            int min;
            if(2*k+1<=N){
                if(less(2*k,2*k+1)){
                    min=2*k;
                }else {
                    min=2*k+1;
                }
            }else {
                min=2*k;
            }
            if(less(k,min)){
                break;
            }
            exch(k,min);
            k=min;
        }
    }

    public static void main(String[] args) {
        MinPriorityQueue<Integer> minPriority=new MinPriorityQueue(10);
        minPriority.insert(2);
        minPriority.insert(3);
        minPriority.insert(1);
        minPriority.insert(8);
        minPriority.insert(5);
        while (!minPriority.isEmpty()){
            Integer max = minPriority.delMin();
            System.out.print(max+" ");
        }
    }
}
