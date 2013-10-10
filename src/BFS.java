import java.io.*;
import java.util.*;

public class BFS {
    void solve() throws IOException {

        int n = in.nextInt();
        int m = in.nextInt();
        ArrayList<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            g[a].add(b);
            g[b].add(a);
        }

        int s = 0;
        int t = n - 1;

        boolean[] used = new boolean[n];
//        Arrays.fill(used, false);
        int[] dist = new int[n];
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.add(s);
        used[s] = true;
//        dist[s] = 0;

        while (!q.isEmpty()) {
            int v = q.poll();
//            int v = q.peek(); q.poll();
            for (int i = 0; i < g[v].size(); i++) {
                int to = g[v].get(i);
                if (!used[to]) {
                    used[to] = true;
                    dist[to] = dist[v] + 1;
                    q.add(to);
                }
            }
        }

    }

    static MyScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new MyScanner(new FileReader("input.txt"));
        out = new PrintWriter("output.txt");
        new BFS().solve();
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
