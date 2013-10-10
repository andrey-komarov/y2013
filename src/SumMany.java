import java.io.*;
import java.util.StringTokenizer;

public class SumMany {

    void solve() throws IOException {
        int n = in.nextInt();
        int[] a = new int[n];

        int[][] b = new int[10][20];
        int[][] c = new int[10][];
        for (int i = 0; i < c.length; i++) {
            c[i] = new int[i];
        }
        int[] d = b[0];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        out.println(sum);
    }

    static MyScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new MyScanner(new FileReader("input.txt"));
        out = new PrintWriter("output.txt");
        new Sum2().solve();
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
