import java.util.Random;

public interface MazeGenerator {
    Maze generate(int rows, int cols, Random rnd);
}
