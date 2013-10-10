class A {

}

public class PointTest {
    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        System.out.println(args.length);
        Point p = new Point(1, 2);
        Point q = p;
        q.x = 3;
        System.out.println(q.x + " " + p.x);
    }
}
