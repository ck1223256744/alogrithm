package graph;

import linear.Queue;

public class Graph {
    //Vertex number
    private final int V;
    //Edge number
    private int E;
    private Queue<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E=0;
        this.adj=new Queue[V];

        for (int i = 0; i < adj.length; i++) {
            adj[i]=new Queue<Integer>();
        }
    }

    public int V(){
        return V;
    }

    private int E(){
        return E;
    }

    public void addEdge(int v,int w){
        adj[v].enqueue(w);
        adj[w].enqueue(v);
        E++;
    }

    protected Queue<Integer> adj(int v){
        return adj[v];
    }

}
