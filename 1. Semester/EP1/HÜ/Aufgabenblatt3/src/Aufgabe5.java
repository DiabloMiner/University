/*
    Aufgabe 5) Rekursion
*/
public class Aufgabe5 {

    private static String orderCharGroups(String text) {
        // Base cases
        if (text.length() <= 1) {
            return text;
        } else if (text.length() == 2) {
            if (text.charAt(1) < text.charAt(0)) {
                return text.substring(1, 2) + text.charAt(0);
            } else {
                return text;
            }
        }

        // Recursive case
        String sortedString;

        // Check if first char should be swapped, then separate the first char of the new string
        // and sort the rest of the input string, then add them up again
        if (text.charAt(0) <= text.charAt(1)) {
            sortedString = text.charAt(0) + orderCharGroups(text.substring(1));
        } else {
            sortedString = text.charAt(1) + orderCharGroups(text.charAt(0) + text.substring(2));
        }

        // Check if first character combined with the input string is already sorted
        // or if the first char needs to be swapped and the rest of the string resorted
        if (sortedString.charAt(0) > sortedString.charAt(1)) {
            sortedString = sortedString.charAt(1) + orderCharGroups(sortedString.charAt(0) + sortedString.substring(2));
        }
        return sortedString;
    }

    private static String order(String text) {
        // Base case: (There is nothing to sort)
        if (text.length() <= 1) {
            return text;
        }

        // Recursive case: Split the first char from text, sort the remaining string,
        // if the char is equal to the first char of the now sorted string add it to the front otherwise to the back
        char firstChar = text.charAt(0);
        String sortedString = order(text.substring(1));
        String result = (sortedString.charAt(0) == firstChar) ? firstChar + sortedString : sortedString + firstChar;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(orderCharGroups(""));
        System.out.println(orderCharGroups("1"));
        System.out.println(orderCharGroups("12"));
        System.out.println(orderCharGroups("1212"));
        System.out.println(orderCharGroups("abbaaababbaa"));
        System.out.println(orderCharGroups("ABBA"));
        System.out.println(orderCharGroups("11221122"));
        System.out.println(orderCharGroups("AAAAAA"));
        System.out.println();

    }
}
