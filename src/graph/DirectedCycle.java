package graph;

public class DirectedCycle {
    private boolean[] marked;
    private boolean hasCycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph G){
        this.marked=new boolean[G.V()];
        this.hasCycle=false;

        this.onStack=new boolean[G.V()];
        for(int v=0;v<G.V();v++){
            if(!marked[v]){
                dfs(G,v);
            }
        }
    }

    private void dfs(Digraph G,int v){
        marked[v]=true;
        onStack[v]=true;
        for(Object w:G.adj(v)){
            if (!marked[(int) w]){
                dfs(G, (Integer) w);
            }
            if(onStack[(int) w]){
                hasCycle=true;
                return;
            }
        }
        onStack[v]=false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }


}
