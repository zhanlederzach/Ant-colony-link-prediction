package kbtu.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class Konst {
    public static Graph getTestGraph() {
        int n = 5, m = 3;
        Graph g = new Graph(n + m, m);


        ArrayList<Vertex> vertices = new ArrayList<>();
        for (int i = 1; i <= n; ++i)
            vertices.add(new Vertex(i, false, new Vector<>(Arrays.asList(0, 0, 0))));
        for (int i = n + 1; i <= n + m; ++i)
            vertices.add(new Vertex(i, true));

        int[][] b = new int[][]{ // attributes
                {1, 0, 1},
                {1, 1, 1},
                {1, 0, 0},
                {0, 1, 1},
                {0, 1, 0}
        };

        for (int i = 0; i < b.length; ++i)
            for (int j = 0; j < b[i].length; ++j) {
                // System.out.println(i + " " + j + " " + vertices.get(i).getAttributesVector());
                vertices.get(i)
                        .getAttributesVector()
                        .set(j, b[i][j]);
            }

        int[][] v = new int[][] {
                {1, 2},
                {2, 3},
                {2, 4},
                {3, 4},
                {4, 5}
        };

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                Vertex v1 = vertices.get(i),
                       u = vertices.get(n + j);
                Integer a = v1.getAttributesVector().get(j);
                if (a == 1) {
                    g.addEdge(v1, u);
                }
            }
        }

        for (int[] pair : v) {
            g.addEdge(vertices.get(pair[0]-1), vertices.get(pair[1]-1));
        }

        g.setVertices(vertices);

        return g;
    }
}
