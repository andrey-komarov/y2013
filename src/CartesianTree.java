import java.util.ArrayList;
import java.util.Random;

public class CartesianTree {
    static Random rnd = new Random(3);

    static class Node {
        int x;
        int y;
        int cnt;

        Node left, right;

        Node(int x) {
            this.x = x;
            y = rnd.nextInt();
            cnt = 1;
            left = right = null;
        }
    }

    static int cnt(Node t) {
        return t == null ? 0 : t.cnt;
    }

    static void update(Node t) {
        if (t != null) {
            t.cnt = cnt(t.left) + 1 + cnt(t.right);
        }
    }

    static Node merge(Node t1, Node t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        if (t1.y > t2.y) {
            t1.right = merge(t1.right, t2);
            update(t1);
            return t1;
        } else {
            t2.left = merge(t1, t2.left);
            update(t2);
            return t2;
        }
    }

    static Node[] split(Node t, int x) {
        if (t == null) {
            return new Node[] {null, null};
        }
        if (x <= t.x) {
            Node[] tmp = split(t.left, x);
            t.left = tmp[1];
            update(t);
            tmp[1] = t;
            return tmp;
        } else {
            Node[] tmp = split(t.right, x);
            t.right = tmp[0];
            update(t);
            tmp[0] = t;
            return tmp;
        }
    }

    static void pp(Node t, ArrayList<Integer> a) {
        if (t == null) {
            return;
        }
        pp(t.left, a);
        a.add(t.x);
        pp(t.right, a);
    }

    static String pp(Node t) {
        ArrayList<Integer> a = new ArrayList<>();
        pp(t, a);
        return a.toString();
    }

    public static void main(String[] args) {
        Node root = null;
        for (int i = 0; i < 10; i++) {
            root = merge(root, new Node(i));
        }
        Node[] tmp = split(root, 4);
//        root = merge(tmp[0], tmp[1]);
        System.err.println(pp(tmp[0]) + " " + pp(tmp[1]));
//        System.err.println(pp(root));
    }
}
