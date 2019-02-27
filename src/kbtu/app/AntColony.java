package kbtu.app;

import java.util.Random;
import java.util.Vector;

public class AntColony {
    Graph g; // initial graph
    int m; // number of ants
    int n; // number of nodes in real graph
    int[][] a; // adjacency matrix

    private Random rnd = new Random();
    private double eps = 0.01; // Relative error

    // positive constants
    private final static double lambda = 0.5;
    private final static double gamma = 0.5;
    private final static double alpha = 0.8, beta = 0.7;
    private final static double ro = 0.5;
    private final static double miu = 0.4;
    private final static double C = 0.95; // evaporation constant

    private double[][] tau;
    private double[][] eta;
    private int[][] neighbour; // number of neighbours between vertices
    private double[][] p; // probability
    private int[] d; // degree of vertices

    public AntColony(Graph g) {
        this.g = g;
        this.m = g.getAttributesNumber();
        this.n = g.getVerticesNumber();

        this.d = new int[n + 1];

        this.a = g.generateMatrix();

        for (int i = 1; i <= n; ++i)
            for (int j = 1; j <= n; ++j)
                d[i] += a[i][j];

        this.neighbour = new int[n + 1][n + 1];
        for (int i = 1; i <= n; ++i)
            for (int j = 1; j <= n; ++j)
                for (int k = 1; k <= n; ++k) {
                    if (a[i][k] == 1 && a[j][k] == 1) {
                        ++neighbour[i][j];
                    }
                }
    }

    public AntColony() {}

    private void calcProbability() {
        p = new double[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            double sum = 0;
            for (int k = 1; k <= n; ++k)
                sum += Math.pow(tau[i][k], alpha) * Math.pow(eta[i][k], beta);
            for (int j = 1; j <= n; ++j) {
                p[i][j] = Math.pow(tau[i][j], alpha) * Math.pow(eta[i][j], beta) / sum;
            }
        }
    }

    public Graph getScore(int iterCount) {
        tau = new double[n + 1][n + 1];
        eta = new double[n + 1][n + 1];

        for (Edge e : g.getEdges()) {
            Vertex v = e.getVertexV();
            Vertex u = e.getVertexU();
            int i = v.getNumber(), j = u.getNumber();
            tau[i][j] = lambda * (a[i][j] + eps);
            if (!v.isAttribute() && !u.isAttribute()) {
                eta[i][j] = gamma * neighbour[i][j];
            } else {
                eta[i][j] = miu / d[j];
            }
        }

        for (int t = 0; t < iterCount; ++t) {
            calcProbability();

            double deltaTau = 0;
            for (int k = 1; k <= m; ++k) { // k-th ant traverses graph
                Vector<Integer> s = new Vector<>();

                int v = getRandomNode();
                s.add(v);
                for (int i = 1; i < n; ++i) {
                    v = getNextNode(v);
                    s.add(v);
                }
                deltaTau += response(s);
            }

            double[][] nxtTau = new double[n + 1][n + 1];
            for (int i = 1; i <= n; ++i)
                for (int j = 1; j <= n; ++j)
                    nxtTau[i][j] = ro * tau[i][j] + deltaTau;

            if (getError(nxtTau, tau) > eps) break;

            for (int i = 1; i <= n; ++i)
                 for (int j = 1; j <= n; ++j)
                    tau[i][j] = nxtTau[i][j];
        }
        Graph result = g.clone().generateFull();
        for (Edge e : result.getEdges())
            e.setScore(tau[e.getV()][e.getU()]);
        return result;
    }

    private double response(Vector<Integer> s) {
        double sum = 0;
        for (Integer v : s)
            sum += d[v];
        return C * sum / n;
    }

    private int getNextNode(int v) {
        int mx = 1;
        for (int i = 1; i <= n; ++i)
            if (p[v][i] > p[v][mx])
                mx = i;
        return mx;
    }

    private int getRandomNode() {
        return 1 + rnd.nextInt(n);
    }

    private double getError(double[][] nxtTau, double[][] tau) {
        double mx = 0;
        for (int i = 1; i <= n; ++i)
            for (int j = 1; j <= n; ++j) {
                mx = Math.max(mx, Math.abs(nxtTau[i][j] - tau[i][j]));
            }
        return mx;
    }
}
