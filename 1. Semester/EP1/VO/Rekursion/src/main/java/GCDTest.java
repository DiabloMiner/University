public class GCDTest {

    public static int gcdRecursiveNew(int a, int b) {
        if (a % b == 0) {
            return b;
        } else {
            int a1 = b, b1 = a % b;
            return gcdRecursive(a1, b1);
        }

    }

    private static int gcdRecursive(int a, int b) {
        if (a == b) {
            return a;
        } else if (a > b) {
            return gcdRecursive(a - b, b);
        } else {
            return gcdRecursive(a, b - a);
        }
    }
    // a > 0 && b > 0
    private static int gcdIterative(int a, int b) {
        while (a != b) {
            if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }
        return a;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 100; i++) {
            for (int j = 1; j < 100; j++) {
                if (gcdRecursive(i, j) != gcdRecursiveNew(i, j) || gcdRecursiveNew(i, j) != gcdIterative(i, j)) {
                    System.out.println("Error: ");
                    System.out.println(gcdRecursiveNew(i, j));
                    System.out.println(gcdRecursive(i, j));
                    System.out.println(gcdIterative(i, j));
                }
            }
        }
    }

}
