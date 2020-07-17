package graph;

import linear.Queue;
import priority.IndexMinPriority;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PrimMST {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPriority<Double> pq;

    public PrimMST(EdgeWeightedGraph G){
        this.edgeTo=new Edge[G.V()];
        this.distTo=new double[G.V()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i]=Double.POSITIVE_INFINITY;
        }
        this.marked=new boolean[G.V()];
        this.pq=new IndexMinPriority<Double>(G.V());
        distTo[0]=0.0;
        pq.insert(0,0.0);
        while (!pq.isEmpty()){
            visit(G,pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph G,int v){
        marked[v]=true;
        for (Object e : G.adj(v)) {
            int w = ((Edge) e).other(v);
            if(marked[w]){
                continue;
            }
            if(((Edge) e).getWeight()<distTo[w]){
                edgeTo[w]= (Edge) e;
                distTo[w]=((Edge) e).getWeight();
                if(pq.contains(w)){
                    pq.changeItem(w,((Edge) e).getWeight());
                }else {
                    pq.insert(w,((Edge) e).getWeight());
                }
            }
        }
    }

    private Queue<Edge> edges(){
        Queue<Edge> allEdges = new Queue<>();
        for (int i = 0; i < edgeTo.length; i++) {
            if(edgeTo[i]!=null){
                allEdges.enqueue(edgeTo[i]);
            }
        }
        return allEdges;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(PrimMST.class.getClassLoader().getResourceAsStream("graph/min_create_tree_test.txt")));
        int total = Integer.parseInt(br.readLine());
        EdgeWeightedGraph G = new EdgeWeightedGraph(total);

        int edgeNumbers = Integer.parseInt(br.readLine());
        for (int e = 1;e<=edgeNumbers;e++){
            String line = br.readLine();//4 5 0.35

            String[] strs = line.split(" ");

            int v = Integer.parseInt(strs[0]);
            int w = Integer.parseInt(strs[1]);

            double weight = Double.parseDouble(strs[2]);

            //构建加权无向边
            Edge edge = new Edge(v, w, weight);
            G.addEdge(edge);

        }

        //创建一个PrimMST对象，计算加权无向图中的最小生成树
        PrimMST primMST = new PrimMST(G);


        //获取最小生成树中的所有边
        Queue<Edge> edges = primMST.edges();


        //遍历打印所有的边
        for (Object e : edges) {
            int v = ((Edge)e).either();
            int w =((Edge)e).other(v);
            double weight = ((Edge)e).getWeight();
            System.out.println(v+"-"+w+" :: "+weight);
        }
    }
}
