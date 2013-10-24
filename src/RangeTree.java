public class RangeTree {
    private int[] t;
    private int n;

    public RangeTree(int[] a) {
        for (n = 1; n < a.length; n *= 2)
            ;
        t = new int[2 * n];
        for (int i = 0; i < a.length; i++) {
            t[i + n] = a[i];
        }
        for (int i = n - 1; i >= 1; i--) {
            t[i] = t[2 * i] + t[2 * i + 1];
        }
    }

    public void set(int i, int val) {
        int v = n + i;
        t[v] = val;
        v /= 2;
        while (v != 0) {
            t[v] = t[2 * v] + t[2 * v + 1];
            v /= 2;
        }
    }

    public int get(int left, int right, int lBound, int rBound, int v) {
        if (left <= lBound && rBound <= right) {
            return t[v];
        }
        if (right <= lBound || rBound <= left) {
            return 0;
        }
        int mid = (lBound + rBound) / 2;
        return get(left, right, lBound, mid, 2 * v)
                + get(left, right, mid, rBound, 2 * v + 1);
    }

    public int get(int left, int right) {
        return get(left, right, 0, n, 1);
    }
}