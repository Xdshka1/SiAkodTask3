public final class DSU {
    private final int[] p;
    private final int[] r;

    public DSU(int n) {
        p = new int[n];
        r = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
    }

    public int find(int x) {
        while (p[x] != x) {
            p[x] = p[p[x]];
            x = p[x];
        }
        return x;
    }

    public boolean union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return false;
        if (r[ra] < r[rb]) p[ra] = rb;
        else if (r[ra] > r[rb]) p[rb] = ra;
        else { p[rb] = ra; r[ra]++; }
        return true;
    }
}
