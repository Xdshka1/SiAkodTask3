public final class MazeRenderer {
    public String render(Maze maze) {
        int rows = maze.rows();
        int cols = maze.cols();
        StringBuilder out = new StringBuilder();

        out.append('+');
        for (int c = 0; c < cols; c++)
            out.append(maze.northWall(0, c) ? "---+" : "   +");
        out.append('\n');

        for (int r = 0; r < rows; r++) {
            out.append(maze.westWall(r, 0) ? '|' : ' ');
            for (int c = 0; c < cols; c++) {
                out.append("   ");
                out.append(maze.eastWall(r, c) ? '|' : ' ');
            }
            out.append('\n');
            out.append('+');
            for (int c = 0; c < cols; c++)
                out.append(maze.southWall(r, c) ? "---+" : "   +");
            out.append('\n');
        }

        return out.toString();
    }
}
