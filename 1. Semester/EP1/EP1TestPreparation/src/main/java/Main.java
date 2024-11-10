import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        short result;
        String test = "Teststring_Einstufungstest";

        /*System.out.println(countDivisors(1, 28));
        System.out.println(countDivisors(101, 2001));
        System.out.println(countDivisors(8, 8));

        System.out.println(findDoubles(test));
        System.out.println(findDoubles("Haarspangenaal"));
        System.out.println(findDoubles("The Black Beast of Aaaaargh!"));
        System.out.println(findDoubles("Schokoladenkuchen"));

        System.out.println(reverseInsert(test, '.'));
        System.out.println(reverseInsert("qwerty", '-'));
        System.out.println(reverseInsert("Pinkie Pie", '!'));

        printPattern(4, '!');
        printPattern(5, '*');*/

        result = (short) countDivisors(299, 305);


        test = "Blaukraut";
        result = (short) getIntegerRoot(25);
        /*System.out.println(getIntegerRoot(144));
        System.out.println(getIntegerRoot(13));
        System.out.println(getIntegerRoot(1));

        System.out.println(getThird("toss", "a", "coin"));
        System.out.println(getThird("Blaukraut", "bleibt", test));
        System.out.println(getThird("badger", "badger", "badger"));

        System.out.println(replaceA("TU Wien"));
        System.out.println(replaceA("Hubba bubba!"));
        System.out.println(replaceA("aaaa"));

        printBars(2);
        printBars(19);
        printBars(20);
        printBars(21);*/


        test = "all: hallo hall";
        result = (short) sumUp(4, 9, 320_300);
        System.out.println(sumUp(2, 5, 11));
        System.out.println(sumUp(8, 8, 40));
        System.out.println(sumUp(5, 1, 2000));

        System.out.println(addMark(test, "allo", 6));
        System.out.println(addMark(test, "all", 3));
        System.out.println(addMark(test, "hall", 3));
        System.out.println(addMark(test, "@all", 3));

        System.out.println(digitsToDistance("12oder34"));
        System.out.println(digitsToDistance("Ich bin 1 Berliner!11"));
        System.out.println(digitsToDistance("Heute ist der 4.März 2022"));
        System.out.println(digitsToDistance("Vier*mal*vier_=_0"));

        printPattern(6,2);
        printPattern(7,3);
        printPattern(1,1);
        printPattern(8,5);

        horner(105, 2);
    }

    public static void horner(int num, int b) {
        int z = num;
        List<Integer> a = new ArrayList<>();
        while (true) {
            if (z == b) {
                a.add(1);
                break;
            }
            a.add(z % b);
            z = (z - a.get(a.size() - 1)) / b;
        }
        System.out.println(a.toString());
    }

    static void printPattern(int lineLength, int patternLength) {
        for (int i = 0; i < (int) Math.floor((double) lineLength / patternLength); i++) {
            String result = "";

            // Add x's
            for (int j = 0; j < i * patternLength; j++) {
                result += 'x';
            }

            // Add pattern
            for (int j = 0; j < patternLength; j++) {
                result += '?';
            }

            // Add y's
            for (int j = result.length(); j < lineLength; j++) {
                result += 'y';
            }
            System.out.println(result);
        }
    }

    static String digitsToDistance(String text) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                int counter = 0;
                for (int j = i - 1; j >= 0; j--) {
                    if (Character.isDigit(text.charAt(j))) {
                        break;
                    }
                    counter++;
                }

                result += counter;
            } else {
                result += text.charAt(i);
            }
        }

        return result;
    }

    static String addMark(String a, String pattern, int pos) {
        if (a.substring(0, pattern.length()).equals(pattern)) {
            return ("--" + a);
        } else if (a.substring((a.length() - 1) - (pattern.length() - 1)).equals(pattern)) {
            return (a + "--");
        } else if (a.substring(pos, pos + pattern.length()).equals(pattern)) {
            return a.substring(pos);
        } else {
            return "--";
        }
    }

    static int sumUp(int d, int s, int t) {
        int counter = 0;
        while (s < t) {
            int remainder = s % d;
            if (remainder == 0) {
                s++;
                counter++;
            } else {
                s += remainder;
            }
        }
        return counter;
    }

    static void printBars(int i) {
        String firstBar = "", secondBar = "", separator1 = "-", separator2 = "+", plus = "+";

        for (int j = 1; j <= i; j++) {
            if (j % 3 != 0) {
                firstBar += j + separator1;
                separator1 = (separator1.equals(plus)) ? "-" : "+";
            } else {
                secondBar += j + separator2;
                separator2 = (separator2.equals(plus)) ? "-" : "+";
            }
        }

        System.out.print(firstBar + (firstBar.length() != 0 ? "\n": ""));
        System.out.print(secondBar + (secondBar.length() != 0 ? "\n": ""));
    }

    static String replaceA(String s) {
        String result = "";
        int counter = 1;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                result += counter++;
            } else {
                result += s.charAt(i);
            }
        }

        return result;
    }

    static String getThird(String a, String b, String c) {
        if (a.equals(b) && b.equals(c)) {
            return "alle gleich";
        } else if (!a.equals(b) && !b.equals(c) && !c.equals(a)) {
            return "alle unterschiedlich";
        } else if (a.equals(b)) {
            return c;
        } else if (b.equals(c)) {
            return a;
        } else {
            return b;
        }
    }

    static int getIntegerRoot(int k) {
        for (int i = 0; i <= k; i++) {
            if (i * i == k) {
                return i;
            }
        }
        return -1;
    }

    static void printPattern(int n, char character) {
        for (int i = 1; i <= n; i++) {
            String result = "";

            if (i % 2 == 0) {
                for (int j = 0; j < 2*n; j++) {
                    result += character;
                }
            } else {
                for (int j = 0; j < n; j++) {
                    result += character;
                    result += ".";
                }
            }

            result += i;
            System.out.println(result);
        }
    }

    static String reverseInsert(String text, char character) {
        String result = "";
        for (int i = (text.length() - 1); i >= 0; i--) {
            result += text.charAt(i);
            if (i != 0) {
                result += character;
            }
        }
        return result;
    }

    static int findDoubles(String text) {
        int counter = 0;
        for (int i = 1; i < text.length(); i++) {
            char current = text.charAt(i), previous = text.charAt(i - 1);
            if (current == previous) {
                counter++;
            }
        }
        return counter;
    }
    
    static int countDivisors(int x, int y) {
        int counter = 0;
        for (int i = x; i <= y; i++) {
            if ((i % 4 == 0) && (i % 6 != 0)) {
                counter++;
            }
        }
        return counter;
    }

}
