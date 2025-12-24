import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class MazeRendererTest {

    @Test
    void renderedDimensionsMatchExpected() {
        int rows = 8, cols = 13;
        Maze maze = new KruskalMazeGenerator().generate(rows, cols, new Random(7));
        String s = new MazeRenderer().render(maze);

        String[] lines = s.split("\n", -1);
        assertTrue(lines.length == 2 * rows + 1 || lines.length == 2 * rows + 2);

        int expectedLineLen = 4 * cols + 1;
        for (int i = 0; i < 2 * rows + 1; i++) {
            assertEquals(expectedLineLen, lines[i].length(), "Line " + i);
        }
    }

    @Test
    void renderedUsesOnlyExpectedCharacters() {
        Maze maze = new KruskalMazeGenerator().generate(10, 10, new Random(1));
        String s = new MazeRenderer().render(maze);

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            boolean ok = (ch == '+') || (ch == '-') || (ch == '|') || (ch == ' ') || (ch == '\n') || (ch == '\r');
            assertTrue(ok, "Unexpected char: '" + ch + "' code=" + (int) ch);
        }
    }

    @Test
    void topBorderContainsPlusAtCorners() {
        Maze maze = new KruskalMazeGenerator().generate(5, 7, new Random(2));
        String firstLine = new MazeRenderer().render(maze).split("\n", -1)[0];
        assertEquals('+', firstLine.charAt(0));
        assertEquals('+', firstLine.charAt(firstLine.length() - 1));
    }
}
