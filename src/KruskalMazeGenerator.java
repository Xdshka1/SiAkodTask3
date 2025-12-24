import java.util.*;

public final class KruskalMazeGenerator implements MazeGenerator {
    @Override
    public Maze generate(int rows, int cols, Random rnd) {
        Maze maze = new Maze(rows, cols);

        List<Edge> edges = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int a = id(r, c, cols);
                if (c + 1 < cols) edges.add(new Edge(a, id(r, c + 1, cols), rnd.nextInt()));
                if (r + 1 < rows) edges.add(new Edge(a, id(r + 1, c, cols), rnd.nextInt()));
            }
        }

        edges.sort(Comparator.comparingInt(Edge::w));
        DSU dsu = new DSU(rows * cols);

        int need = rows * cols - 1;
        int taken = 0;

        for (Edge e : edges) {
            if (dsu.union(e.a(), e.b())) {
                int ar = e.a() / cols, ac = e.a() % cols;
                int br = e.b() / cols, bc = e.b() % cols;
                maze.removeWallBetween(ar, ac, br, bc);
                if (++taken == need) break;
            }
        }

        maze.openEntranceExit();
        return maze;
    }

    private int id(int r, int c, int cols) {
        return r * cols + c;
    }
}
