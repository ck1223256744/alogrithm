package graph;

import java.util.Stack;

public class DepthFirstOrder {

    private boolean[] marked;

    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph G){
        this.marked=new boolean[G.V()];
        this.reversePost=new Stack<Integer>();
        for (int v = 0; v < G.V(); v++) {
            if(!marked[v]){
                dfs(G,v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v]=true;
        for (Object w : G.adj(v)) {
            if(!marked[(int) w]){
                dfs(G, (Integer) w);
            }
        }
        reversePost.push(v);
    }

    public Stack reversePost(){
        return reversePost;
    }
}
