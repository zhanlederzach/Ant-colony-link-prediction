package kbtu.app;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Graph g = Konst.getTestGraph(); // our testing graph
        AntColony colony = new AntColony(g); // Ant Colony class with scoring implementation

        Graph result = colony.getScore(100);

        Collections.sort(result.getEdges());
        for (Edge e : result.getEdges()) {
            Vertex v = e.getVertexV(),
                    u = e.getVertexU();
            if (!v.isAttribute() && !u.isAttribute())
               System.out.println(String.format("%d %d %f", v.getNumber(), u.getNumber(), e.getScore())); // vertices and score between them
        }
    }
}


