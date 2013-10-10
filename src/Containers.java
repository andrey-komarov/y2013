import java.math.BigInteger;
import java.util.Comparator;

public class Containers {

    static class MyInt implements Comparable<MyInt> {
        int a;

        MyInt(int a) {
            this.a = a;
        }

        @Override
        public int compareTo(MyInt i) {
            return Math.abs(a) - Math.abs(i.a);
        }

        @Override
        public String toString() {
            return "MyInt{" +
                    "a=" + a +
                    '}';
        }
    }

    static class Cmp implements Comparator<MyInt> {
        @Override
        public int compare(MyInt i1, MyInt i2) {
            return i1.a - i2.a;
        }
    }

    static class Point {
        int x, y, z;

        Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            if (y != point.y) return false;
            if (z != point.z) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }

    public static void main(String[] args) {
//
//        Integer a = 1;
//        Integer b = 1;
//        Integer c = 1000;
//        Integer d = 1000;
//        int e = 1000;
//        Integer f = new Integer(1);
//        Integer g = null;
//
//        System.out.println(a == b);
//        System.out.println(c == d);
//        System.out.println(c == e);
//        System.out.println(a == f);
//        System.out.println(c + d);
//        System.out.println(c < d);
//        System.out.println(c.intValue() == d.intValue());

        BigInteger a = BigInteger.ONE;
        BigInteger two = BigInteger.valueOf(2);
        a = a.shiftLeft(1000);
        for (int i = 0; i < 300; i++) {
            a = a.divide(BigInteger.valueOf(3));
        }
        System.out.println(a);


        MyInt i = new MyInt(123);

        System.out.println(i);

//        List<Integer> a = new ArrayList<Integer>(5);
//        a.add(1); // a.push_back
//        a.add(2);
//
//
//        TreeSet<MyInt> sByMod = new TreeSet<MyInt>();
//        TreeSet<MyInt> sByOrder = new TreeSet<MyInt>(new Cmp());
    }
}
