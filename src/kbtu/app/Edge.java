package kbtu.app;

public class Edge implements Comparable<Edge>{ // Edge class for output
    private Vertex v, u;
    private double score;

    public Edge(Vertex v, Vertex u, double score) {
        this.v = v;
        this.u = u;
        this.score = score;
    }

    public Edge(Vertex v, Vertex u) {
        this.v = v;
        this.u = u;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getV() {
        return v.getNumber();
    }

    public int getU() {
        return u.getNumber();
    }

    public Vertex getVertexV() {
        return v;
    }

    public Vertex getVertexU() {
        return u;
    }

    @Override
    public int compareTo(Edge edge) {
        int f = -Double.compare(score, edge.score);
        if (f != 0) return f;
        f = Integer.compare(v.getNumber(), edge.v.getNumber());
        if (f != 0) return f;
        return Integer.compare(u.getNumber(), edge.u.getNumber());
    }
}
