package graph;

import linear.Queue;
import priority.IndexMinPriority;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPriority<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        this.edgeTo=new DirectedEdge[G.V()];
        this.distTo=new double[G.V()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i]=Double.POSITIVE_INFINITY;
        }
        this.pq=new IndexMinPriority<>(G.V());

        distTo[s]=0.0;
        pq.insert(s,0.0);
        while (!pq.isEmpty()){
            relax(G,pq.delMin());
        }
    }

    private void relax(EdgeWeightedDigraph G,int v){

        for (Object edge : G.adj(v)) {
            int w = ((DirectedEdge) edge).to();
            if(distTo(v)+((DirectedEdge) edge).weight()<distTo(w)){
                distTo[w]=distTo[v]+((DirectedEdge) edge).weight();
                edgeTo[w]= ((DirectedEdge) edge);
                if(pq.contains(w)){
                    pq.changeItem(w,distTo(w));
                }else {
                    pq.insert(w,distTo(w));
                }
            }
        }
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v]<Double.POSITIVE_INFINITY;
    }

    public Queue<DirectedEdge> pathTo(int v){
        if(!hasPathTo(v)){
            return null;
        }
        Queue<DirectedEdge> allEdges = new Queue<>();
        while (true){
            DirectedEdge e = edgeTo[v];
            if(e==null){
                break;
            }
            allEdges.enqueue(e);
            v=e.from();
        }

        return allEdges;
    }

    public static void main(String[] args) throws Exception{
        //创建一副加权有向图
//        BufferedReader br = new BufferedReader(new InputStreamReader(DijkstraSP.class.getClassLoader().getResourceAsStream("graph/min_route_test.txt")));
//        int total = Integer.parseInt(br.readLine());
//        EdgeWeightedDigraph G = new EdgeWeightedDigraph(total);
//
//        int edgeNumbers = Integer.parseInt(br.readLine());
//        for(int i=1;i<=edgeNumbers;i++){
//            String line = br.readLine();//4 5 0.35
//            String[] strs = line.split(" ");
//            int v = Integer.parseInt(strs[0]);
//            int w = Integer.parseInt(strs[1]);
//
//            double weight = Double.parseDouble(strs[2]);
//
//            DirectedEdge e = new DirectedEdge(v, w, weight);
//            G.addEdge(e);
//
//        }
        DirectedEdge e1=new DirectedEdge(1,2,0.2);
        DirectedEdge e2=new DirectedEdge(1,3,0.1);
        DirectedEdge e3=new DirectedEdge(2,4,0.3);
        DirectedEdge e4=new DirectedEdge(4,5,0.6);
        DirectedEdge e5=new DirectedEdge(3,4,0.2);
        DirectedEdge e6=new DirectedEdge(3,5,0.1);
        EdgeWeightedDigraph G=new EdgeWeightedDigraph(6);
        G.addEdge(e1);
        G.addEdge(e2);
        G.addEdge(e3);
        G.addEdge(e4);
        G.addEdge(e5);
        G.addEdge(e6);

        //创建DijkstraSP对象，查找最短路径树
        DijkstraSP dijkstraSP = new DijkstraSP(G, 1);

        //查找最短路径,0->6的最短路径
        Queue<DirectedEdge> edges = dijkstraSP.pathTo(5);


        //遍历打印
        for (Object edge : edges) {

            System.out.println(((DirectedEdge) edge).from()+"->"+((DirectedEdge) edge).to()+" ：： "+((DirectedEdge) edge).weight());
        }

    }
}
