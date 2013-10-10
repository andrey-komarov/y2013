import java.util.Random;

public class DSU {
    int[] p;
    Random rnd;

    DSU(int n) {
        p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        rnd = new Random(3);
    }

    int get(int v) {
        return p[v] == v ? v : (p[v] = get(p[v]));
    }

    void union(int v1, int v2) {
        v1 = get(v1);
        v2 = get(v2);
        if (v1 == v2) {
            return;
        }
        if (rnd.nextBoolean()) {
            p[v1] = v2;
        } else {
            p[v2] = v1;
        }
    }
}
