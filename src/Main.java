import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int rows = args.length > 0 ? Integer.parseInt(args[0]) : 150;
        int cols = args.length > 1 ? Integer.parseInt(args[1]) : 25;
        long seed = args.length > 2 ? Long.parseLong(args[2]) : System.nanoTime();

        MazeGenerator generator = new KruskalMazeGenerator();
        Maze maze = generator.generate(rows, cols, new Random(seed));

        MazeRenderer renderer = new MazeRenderer();
        System.out.print(renderer.render(maze));
    }
}
