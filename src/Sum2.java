import java.io.*;
import java.util.StringTokenizer;

public class Sum2 {

    int a;

    class A {
        void f() {
            System.out.println(a);
        }
    }

    static class B {
        void f() {
//            System.out.println(a);
        }
    }


    void solve() throws IOException {
        MyScanner s = null;

        s.next();

        int a = in.nextInt();
        int b = in.nextInt();
        out.println(a + b);
    }

    static MyScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new MyScanner(new FileReader("input.txt"));
//        in = new MyScanner(new InputStreamReader(System.in));
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
