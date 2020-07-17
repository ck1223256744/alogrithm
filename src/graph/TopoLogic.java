package graph;

import java.util.Stack;

public class TopoLogic {
    private Stack<Integer> order;
    public TopoLogic(Digraph G){
        DirectedCycle cycle=new DirectedCycle(G);
        if (!cycle.hasCycle()){
            DepthFirstOrder depthFirstOrder=new DepthFirstOrder(G);
            order = depthFirstOrder.reversePost();
        }
    }

    public boolean hasCycle(){
        return order==null;
    }

    public Stack<Integer> order(){
        return order;
    }

    public static void main(String[] args) {
        Digraph d=new Digraph(6);
        d.addEdge(0,2);
        d.addEdge(0,3);
        d.addEdge(2,4);
        d.addEdge(4,5);
        d.addEdge(3,4);
        d.addEdge(1,3);

        TopoLogic t=new TopoLogic(d);
        Stack<Integer> order=t.order();
        StringBuilder sb=new StringBuilder();
        t.hasCycle();
        while (!order.empty()){
            sb.append(order.pop()+"->");
        }
        int index = sb.lastIndexOf("->");
        String substring = sb.substring(0, index);
        System.out.println(substring);
    }
}
