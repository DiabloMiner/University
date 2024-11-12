/*
    Aufgabe 3) Rekursion
*/
public class Aufgabe3 {

    private static void printOddNumbersAscending(int start, int end) {
        // Base case
        if (start > end) {
            return;
        }

        // Recursion case
        System.out.print(start % 2 != 0 ? start + " "  : "");
        printOddNumbersAscending(++start, end);
    }

    private static void printEvenNumbersDescending(int end) {
        // Base case
        if (end < 0) {
            return;
        }

        // Recursive case
        System.out.print(end % 2 == 0 ? end + " " : "");
        printEvenNumbersDescending(--end);
    }

    private static int countCharChanges(String text) {
        // Base case
        if (text.length() <= 1) {
            return 0;
        }

        // Recursive case: Check if first and second char are different
        // If so increment counter and check for the remaining string without the first char
        int counter = text.charAt(0) != text.charAt(1) ? 1 : 0;
        return counter + countCharChanges(text.substring(1));
    }

    public static void main(String[] args) {
        printOddNumbersAscending(5, 14);
        System.out.println();
        printEvenNumbersDescending(11);
        System.out.println();

        System.out.println(countCharChanges("A"));
        System.out.println(countCharChanges("AA"));
        System.out.println(countCharChanges("abBc"));
        System.out.println(countCharChanges("XYYYZZAAAB"));
        System.out.println(countCharChanges("satt"));
        System.out.println(countCharChanges("Schifffahrt"));
        System.out.println();

        //DIE NACHFOLGENDEN ZEILEN SIND ZUM TESTEN DER METHODE.
        //**********************************************************************
        assert (countCharChanges("A") == 0);
        assert (countCharChanges("AA") == 0);
		assert (countCharChanges("abBc") == 3);
        assert (countCharChanges("XYYYZZAAAB") == 4);
        assert (countCharChanges("satt") == 2);
        assert (countCharChanges("Schifffahrt") == 8);
        //**********************************************************************
    }
}

