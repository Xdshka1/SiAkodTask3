import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DSUTest {

    @Test
    void findInitiallyReturnsSelf() {
        DSU dsu = new DSU(10);
        for (int i = 0; i < 10; i++) {
            assertEquals(i, dsu.find(i));
        }
    }

    @Test
    void unionConnectsComponents() {
        DSU dsu = new DSU(6);

        assertTrue(dsu.union(0, 1));
        assertEquals(dsu.find(0), dsu.find(1));

        assertTrue(dsu.union(2, 3));
        assertEquals(dsu.find(2), dsu.find(3));
        assertNotEquals(dsu.find(0), dsu.find(2));

        assertTrue(dsu.union(1, 2));
        int root = dsu.find(0);
        assertEquals(root, dsu.find(1));
        assertEquals(root, dsu.find(2));
        assertEquals(root, dsu.find(3));
    }

    @Test
    void unionOnSameSetReturnsFalse() {
        DSU dsu = new DSU(4);
        assertTrue(dsu.union(0, 1));
        assertFalse(dsu.union(0, 1));
        assertFalse(dsu.union(1, 0));
        assertFalse(dsu.union(0, 0));
    }

    @Test
    void multipleUnionsEndUpSingleComponent() {
        int n = 50;
        DSU dsu = new DSU(n);

        for (int i = 1; i < n; i++) {
            assertTrue(dsu.union(0, i));
        }

        int root = dsu.find(0);
        for (int i = 0; i < n; i++) {
            assertEquals(root, dsu.find(i));
        }
    }
}
