import java.io.*;
import java.util.*;

public class Dijkstra {

    static class Edge {
        int to;
        int w;

        Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();

        ArrayList<Edge>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int w = in.nextInt();
            g[a].add(new Edge(b, w));
        }

        int s = 0;
        int t = n - 1;

        final int INF = 1_000_000_000;
        final int[] d = new int[n];
        boolean[] used = new boolean[n];
        Arrays.fill(d, INF);
        d[s] = 0;
        TreeSet<Integer> q = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer v1, Integer v2) {
                if (d[v1] != d[v2]) {
                    return d[v1] - d[v2];
                }
                return v1 - v2;
            }
        });
        q.add(s);
        while (!q.isEmpty()) {
            int v = q.pollFirst();
            for (int i = 0; i < g[v].size(); i++) {
                Edge e = g[v].get(i);
                if (d[e.to] > d[v] + e.w) {
                    q.remove(e.to);
                    d[e.to] = d[v] + e.w;
                    q.add(e.to);
                }
            }
        }
        out.println(d[t]);
    }

    static MyScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new MyScanner(new FileReader("input.txt"));
        out = new PrintWriter("output.txt");
        new Dijkstra().solve();
        out.close();
    }

    static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        MyScanner(Reader r) {
            br = new BufferedReader(r);
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }
}
