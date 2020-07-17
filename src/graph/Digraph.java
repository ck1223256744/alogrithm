package graph;

import linear.Queue;

public class Digraph {
    private final int V;
    private int E;
    private Queue<Integer>[] adj;

    public Digraph(int v){
        this.V=v;
        this.E=0;
        this.adj=new Queue[v];

        for (int i = 0; i < adj.length; i++) {
            adj[i]=new Queue<Integer>();
        }
    }

    public void addEdge(int v,int w){
        adj[v].enqueue(w);
        E++;
    }

    public int V(){
        return V;
    }

    public Queue<Integer> adj(int v){
        return adj[v];
    }

    public Digraph reverse(){
        Digraph r=new Digraph(V);
        for(int v=0;v<V;v++){
            for (Object w : adj[v]) {
                r.addEdge(v, (Integer) w);
            }
        }
        return r;
    }

}
