package graph;


import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BreadthFirstSearch {
    private boolean[] marked;
    private int count;
    private Queue<Integer> waitSearch;

    public BreadthFirstSearch(Graph G, int s) {
        this.count = 0;
        this.marked = new boolean[G.V()];
        this.waitSearch = new ArrayBlockingQueue<Integer>(G.V());

        bfs(G, s);
    }

    private void bfs(Graph G, int v) {
        marked[v] = true;
        waitSearch.add(v);
        while(!waitSearch.isEmpty()){
            Integer wait = waitSearch.poll();
            for (Object w : G.adj(wait)) {
                if (!marked[(int) w]){
                    waitSearch.add((Integer) w);
                    count++;
                    marked[(int) w]=true;
                }
            }
            System.out.print(wait+" ");
        }
    }

    public static void main(String[] args) {
        Graph G = new Graph(13);

        G.addEdge(0, 5);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(0, 6);
        G.addEdge(5, 3);
        G.addEdge(5, 4);
        G.addEdge(3, 4);
        G.addEdge(4, 6);

        G.addEdge(7, 8);

        G.addEdge(9, 11);
        G.addEdge(9, 10);
        G.addEdge(9, 12);
        G.addEdge(11, 12);

        BreadthFirstSearch search = new BreadthFirstSearch(G, 0);

        System.out.println(search.count);

    }
}
