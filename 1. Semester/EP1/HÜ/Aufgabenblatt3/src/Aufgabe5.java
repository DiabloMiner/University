/*
    Aufgabe 5) Rekursion
*/
public class Aufgabe5 {

    private static String orderCharGroups(String text) {
        // Base case: (There is nothing to sort)
        if (text.length() <= 1) {
            return text;
        }

        // Recursive case: Split the first char from text, sort the remaining string,
        // If the char is equal to the first char of the now sorted string add it to the front otherwise to the back
        char firstChar = text.charAt(0);
        String sortedString = orderCharGroups(text.substring(1));
        return (sortedString.charAt(0) == firstChar) ? firstChar + sortedString : sortedString + firstChar;
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
