import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {

    @Test
    void constructorFillsAllWalls() {
        Maze m = new Maze(3, 4);
        for (int r = 0; r < m.rows(); r++) {
            for (int c = 0; c < m.cols(); c++) {
                assertTrue(m.northWall(r, c));
                assertTrue(m.eastWall(r, c));
                assertTrue(m.southWall(r, c));
                assertTrue(m.westWall(r, c));
            }
        }
    }

    @Test
    void openEntranceExitOpensCorrectWalls() {
        Maze m = new Maze(5, 6);
        m.openEntranceExit();

        assertFalse(m.westWall(0, 0));
        assertFalse(m.eastWall(m.rows() - 1, m.cols() - 1));

        for (int r = 0; r < m.rows(); r++) {
            for (int c = 0; c < m.cols(); c++) {
                if (!(r == 0 && c == 0)) {
                    assertTrue(m.westWall(r, c));
                }
                if (!(r == m.rows() - 1 && c == m.cols() - 1)) {
                    assertTrue(m.eastWall(r, c));
                }
            }
        }
    }

    @Test
    void removeWallBetweenHorizontalRight() {
        Maze m = new Maze(2, 3);
        m.removeWallBetween(0, 0, 0, 1);
        assertFalse(m.eastWall(0, 0));
        assertFalse(m.westWall(0, 1));
        assertTrue(m.westWall(0, 0));
        assertTrue(m.eastWall(0, 1));
    }

    @Test
    void removeWallBetweenHorizontalLeft() {
        Maze m = new Maze(2, 3);
        m.removeWallBetween(0, 2, 0, 1);
        assertFalse(m.westWall(0, 2));
        assertFalse(m.eastWall(0, 1));
    }

    @Test
    void removeWallBetweenVerticalDown() {
        Maze m = new Maze(3, 2);
        m.removeWallBetween(0, 0, 1, 0);
        assertFalse(m.southWall(0, 0));
        assertFalse(m.northWall(1, 0));
    }

    @Test
    void removeWallBetweenVerticalUp() {
        Maze m = new Maze(3, 2);
        m.removeWallBetween(2, 1, 1, 1);
        assertFalse(m.northWall(2, 1));
        assertFalse(m.southWall(1, 1));
    }

    @Test
    void wallSymmetryHoldsAfterRemovals() {
        Maze m = new Maze(4, 5);
        m.removeWallBetween(1, 1, 1, 2);
        m.removeWallBetween(2, 3, 3, 3);
        m.openEntranceExit();

        for (int r = 0; r < m.rows(); r++) {
            for (int c = 0; c < m.cols(); c++) {
                if (c + 1 < m.cols()) {
                    assertEquals(m.eastWall(r, c), m.westWall(r, c + 1));
                }
                if (r + 1 < m.rows()) {
                    assertEquals(m.southWall(r, c), m.northWall(r + 1, c));
                }
            }
        }
    }
}
