package graph;

import java.util.*;

public class DepthFirstSearch {
    private boolean[] marked;
    private int count;
    private Stack<Integer> waitSearch;
    private Map parent;

    private DepthFirstSearch(Graph G,int s){
        this.marked=new boolean[G.V()];
        this.count=0;
        this.waitSearch=new Stack<Integer>();
        this.parent=new HashMap<>();
    }

    private void dfs(Graph G,int v){
        marked[v]=true;
        waitSearch.push(v);
        parent.put(v,null);
        while (!waitSearch.empty()){
            Integer wait = waitSearch.pop();
            for (Object w:G.adj(wait)){
                if (!marked[(int) w]){
                    waitSearch.push((Integer) w);
                    count++;
                    parent.put((Integer) w,wait);
                    marked[(int) w]=true;
                }
            }
            System.out.print(wait+" ");
        }
    }

    private void DFS(Graph G,int v){
        marked[v]=true;
        System.out.print(v+" ");
        for (Object w : G.adj(v)) {
            if(!marked[(int) w]){
                DFS(G, (Integer) w);
            }
        }
    }

    private boolean marked(int w){
        return marked[w];
    }

    public int count(){
        return count;
    }

    private void findPath(int a){
        String str=a+"<-";
        while (true){
            int s= (int) parent.get(a);
            a=s;
            str+=a+"<-";
            if (s==0){
                break;
            }
        }
        String substring = str.substring(0,str.length()-2);
        System.out.println(substring);
    }



    public static void main(String[] args) {
        Graph G = new Graph(13);

        G.addEdge(0,5);
        G.addEdge(0,1);
        G.addEdge(0,2);
        G.addEdge(0,6);
        G.addEdge(5,3);
        G.addEdge(5,4);
        G.addEdge(3,4);
        G.addEdge(4,6);

        G.addEdge(7,8);

        G.addEdge(9,11);
        G.addEdge(9,10);
        G.addEdge(9,12);
        G.addEdge(11,12);

        DepthFirstSearch depthFirstSearch=new DepthFirstSearch(G,0);
        depthFirstSearch.DFS(G,0);
//        depthFirstSearch.dfs(G,0);

//        Map parent = depthFirstSearch.parent;
//        depthFirstSearch.findPath(3);
//        System.out.println(depthFirstSearch.count());


    }
}
