package graph;

import linear.Queue;
import priority.MinPriorityQueue;
import uf.UF_tree_Weighted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KruskalMST {

    private Queue<Edge> mst;
    private UF_tree_Weighted uf;
    private MinPriorityQueue<Edge> pq;

    public KruskalMST(EdgeWeightedGraph G) {
        this.mst=new Queue<Edge>();
        this.uf=new UF_tree_Weighted(G.V());
        this.pq=new MinPriorityQueue<>(G.E()+1);

        for (Object edge : G.edges()) {
            pq.insert((Edge) edge);
        }
        while (!pq.isEmpty() && mst.size()<G.V()-1){
            Edge e = pq.delMin();
            int v=e.either();
            int w=e.other(v);
            if(uf.connected(v,w)){
                continue;
            }
            uf.union(v,w);

            mst.enqueue(e);
        }
    }

    public Queue<Edge> edges(){
        return mst;
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
        KruskalMST kruskalMST=new KruskalMST(G);


        //获取最小生成树中的所有边
        Queue<Edge> edges = kruskalMST.edges();


        //遍历打印所有的边
        for (Object e : edges) {
            int v = ((Edge)e).either();
            int w =((Edge)e).other(v);
            double weight = ((Edge)e).getWeight();
            System.out.println(v+"-"+w+" :: "+weight);
        }
    }

}
