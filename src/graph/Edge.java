package graph;

public class Edge implements Comparable<Edge> {

    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if (vertex == v) {
            return w;
        } else {
            return v;
        }
    }

    @Override
    public int compareTo(Edge that) {
        int cmp;
        if (this.getWeight() > that.getWeight()) {
            cmp = 1;
        } else if (this.getWeight() < that.getWeight()) {
            cmp = -1;
        } else {
            cmp = 0;
        }
        return cmp;
    }
}
