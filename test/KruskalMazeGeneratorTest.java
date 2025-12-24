import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class KruskalMazeGeneratorTest {

    @Test
    void generatedMazeIsPerfectTreeConnectedAndAcyclic() {
        int rows = 20;
        int cols = 30;
        long seed = 123456789L;

        MazeGenerator gen = new KruskalMazeGenerator();
        Maze maze = gen.generate(rows, cols, new Random(seed));

        int n = rows * cols;

        int edges = countUndirectedEdges(maze);
        assertEquals(n - 1, edges);

        boolean[] vis = bfsReachability(maze, 0, 0);
        for (int i = 0; i < n; i++) {
            assertTrue(vis[i], "Not reachable cell id=" + i);
        }
    }

    @Test
    void entranceAndExitAreOpen() {
        int rows = 7, cols = 9;
        Maze maze = new KruskalMazeGenerator().generate(rows, cols, new Random(42));

        assertFalse(maze.westWall(0, 0));
        assertFalse(maze.eastWall(rows - 1, cols - 1));
    }

    @Test
    void boundariesAreClosedExceptEntranceExit() {
        int rows = 12, cols = 10;
        Maze maze = new KruskalMazeGenerator().generate(rows, cols, new Random(999));

        for (int c = 0; c < cols; c++) {
            assertTrue(maze.northWall(0, c));
            assertTrue(maze.southWall(rows - 1, c));
        }
        for (int r = 0; r < rows; r++) {
            if (!(r == 0)) assertTrue(maze.westWall(r, 0));
            if (!(r == rows - 1)) assertTrue(maze.eastWall(r, cols - 1));
        }

        assertFalse(maze.westWall(0, 0));
        assertFalse(maze.eastWall(rows - 1, cols - 1));
    }

    @Test
    void deterministicForSameSeedProducesSameStructure() {
        int rows = 18, cols = 18;
        long seed = 20251225L;

        MazeGenerator gen = new KruskalMazeGenerator();
        Maze a = gen.generate(rows, cols, new Random(seed));
        Maze b = gen.generate(rows, cols, new Random(seed));

        assertEquals(signature(a), signature(b));
    }

    @Test
    void differentSeedsUsuallyProduceDifferentStructure() {
        int rows = 18, cols = 18;

        MazeGenerator gen = new KruskalMazeGenerator();
        Maze a = gen.generate(rows, cols, new Random(1));
        Maze b = gen.generate(rows, cols, new Random(2));

        assertNotEquals(signature(a), signature(b));
    }

    private static int countUndirectedEdges(Maze m) {
        int rows = m.rows(), cols = m.cols();
        int edges = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (c + 1 < cols && !m.eastWall(r, c)) edges++;
                if (r + 1 < rows && !m.southWall(r, c)) edges++;
            }
        }
        return edges;
    }

    private static boolean[] bfsReachability(Maze m, int sr, int sc) {
        int rows = m.rows(), cols = m.cols();
        int n = rows * cols;
        boolean[] vis = new boolean[n];

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sr, sc});
        vis[sr * cols + sc] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            int id = r * cols + c;

            if (c + 1 < cols && !m.eastWall(r, c) && !vis[id + 1]) {
                vis[id + 1] = true;
                q.add(new int[]{r, c + 1});
            }
            if (c - 1 >= 0 && !m.westWall(r, c) && !vis[id - 1]) {
                vis[id - 1] = true;
                q.add(new int[]{r, c - 1});
            }
            if (r + 1 < rows && !m.southWall(r, c) && !vis[id + cols]) {
                vis[id + cols] = true;
                q.add(new int[]{r + 1, c});
            }
            if (r - 1 >= 0 && !m.northWall(r, c) && !vis[id - cols]) {
                vis[id - cols] = true;
                q.add(new int[]{r - 1, c});
            }
        }

        return vis;
    }

    private static String signature(Maze m) {
        StringBuilder sb = new StringBuilder(m.rows() * m.cols() * 2);
        for (int r = 0; r < m.rows(); r++) {
            for (int c = 0; c < m.cols(); c++) {
                sb.append(m.northWall(r, c) ? '1' : '0');
                sb.append(m.eastWall(r, c) ? '1' : '0');
                sb.append(m.southWall(r, c) ? '1' : '0');
                sb.append(m.westWall(r, c) ? '1' : '0');
            }
        }
        return sb.toString();
    }
}
