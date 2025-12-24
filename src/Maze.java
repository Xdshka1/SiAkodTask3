public final class Maze {
    private final int rows;
    private final int cols;

    private final boolean[][] north;
    private final boolean[][] east;
    private final boolean[][] south;
    private final boolean[][] west;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        north = new boolean[rows][cols];
        east = new boolean[rows][cols];
        south = new boolean[rows][cols];
        west = new boolean[rows][cols];
        fill(true);
    }

    public int rows() { return rows; }
    public int cols() { return cols; }

    public boolean northWall(int r, int c) { return north[r][c]; }
    public boolean eastWall(int r, int c) { return east[r][c]; }
    public boolean southWall(int r, int c) { return south[r][c]; }
    public boolean westWall(int r, int c) { return west[r][c]; }

    public void fill(boolean v) {
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                north[r][c] = east[r][c] = south[r][c] = west[r][c] = v;
    }

    public void openEntranceExit() {
        west[0][0] = false;
        east[rows - 1][cols - 1] = false;
    }

    public void removeWallBetween(int r1, int c1, int r2, int c2) {
        if (r1 == r2 && c1 + 1 == c2) {
            east[r1][c1] = false;
            west[r2][c2] = false;
            return;
        }
        if (r1 == r2 && c2 + 1 == c1) {
            west[r1][c1] = false;
            east[r2][c2] = false;
            return;
        }
        if (c1 == c2 && r1 + 1 == r2) {
            south[r1][c1] = false;
            north[r2][c2] = false;
            return;
        }
        if (c1 == c2 && r2 + 1 == r1) {
            north[r1][c1] = false;
            south[r2][c2] = false;
        }
    }
}
