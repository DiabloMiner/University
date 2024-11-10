import java.util.HashMap;

public class FactorialTest {

    public static HashMap<Long, Long> fibNums = new HashMap();

    public static long factorial(int n) {
        return n == 0 ? 1 : n * factorial(n - 1);
    }

    public static long fibonacci(long n) {
        if (fibNums.containsKey(n)) {
            return fibNums.get(n);
        }
        if (n <= 2) {
            return 1;
        } else {
            long num = fibonacci(n - 1) + fibonacci(n - 2);
            fibNums.putIfAbsent(n, num);
            return num;
        }
    }

    public static void main(String[] args) {
        System.out.println(factorial(3));
        System.out.println(fibonacci(59));
    }

}
