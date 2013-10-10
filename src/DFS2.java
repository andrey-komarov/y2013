import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DFS2 {





    void dfs(int v, boolean[] used, ArrayList<Integer>[] g) {
        used[v] = true;
        for (int i = 0; i < g[v].size(); i++) {
            int to = g[v].get(i);
            if (!used[to]) {
                dfs(to, used, g);
            }
        }
    }

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

        boolean[] used = new boolean[n];
        dfs(0, used, g);

    }

    static MyScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new MyScanner(new FileReader("input.txt"));
        out = new PrintWriter("output.txt");
        new DFS2().solve();
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


 //timofeev.kirill.a@gmail.com