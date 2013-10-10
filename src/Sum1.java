import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Sum1 {

    void solve() {
        g();
        a = 1;
        b = 2;
        int a = in.nextInt();
        int b = in.nextInt();
        out.println(a + b);
    }

    static int a;
    int b;

    static Scanner in;
    static PrintWriter out;

    void f() throws Exception {
        throw new Exception("blah-blah");
    }

    void g() {
        try {
            f();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
//      Считывание у строки
//        in = new Scanner("input.txt");
        in = new Scanner(new File("input.txt"));
        out = new PrintWriter("output.txt");
        Sum1 s = new Sum1();
//        new Sum1().solve();
        s.solve();
        a = 3;
//        b = 4;
        out.close();
    }
}
