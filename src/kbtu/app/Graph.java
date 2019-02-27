package kbtu.app;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Edge> edges;
    private ArrayList<Vertex> vertices;
    private int n, m; // number of vertices, number of attributes

    public Graph(int n, int m) {
        this.n = n;
        this.m = m;
        edges = new ArrayList<>();
    }

    public Graph(int n, int m, ArrayList<Edge> clone, ArrayList<Vertex> vertices) {
        this.n = n;
        this.m = m;
        this.edges = clone;
        this.vertices = vertices;
    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public Graph(int n, int m, ArrayList<Edge> edges) {
        this.n = n;
        this.m = m;
        this.edges = edges;
    }

    public void addEdge(Vertex v, Vertex u) {
        edges.add(
                new Edge(v,
                        u));
    }

    public int[][] generateMatrix() {
        int[][] g = new int[n + 1][n + 1];
        for (Edge e : edges)
            g[e.getV()][e.getU()] = g[e.getU()][e.getV()] = 1;
        return g;
    }

    public int getVerticesNumber() {
        return n;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public int getAttributesNumber() {
        return m;
    }

    public Graph clone() {
        return new Graph(n, m, (ArrayList<Edge>) edges.clone(), (ArrayList<Vertex>) vertices.clone());
    }

    public Graph generateFull() {
        Graph result = this;
        int[][] a = result.generateMatrix();
        for (int i = 1; i <= n; ++i)
            for (int j = 1; j <= n; ++j)
                if (i != j && a[i][j] == 0)
                    result.addEdge(vertices.get(i-1), vertices.get(j-1));
        return result;
    }
}
