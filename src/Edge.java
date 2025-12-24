public final class Edge {
    private final int a;
    private final int b;
    private final int w;

    public Edge(int a, int b, int w) {
        this.a = a;
        this.b = b;
        this.w = w;
    }

    public int a() { return a; }
    public int b() { return b; }
    public int w() { return w; }
}
