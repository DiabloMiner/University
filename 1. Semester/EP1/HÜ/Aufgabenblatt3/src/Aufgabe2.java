/*
    Aufgabe 2) Überladen von Methoden
*/
public class Aufgabe2 {

    private static void addChar(String text, char character) {
        String modifiedText = "";
        boolean doubleChar = true;

        for (int i = 0; i < text.length(); i++) {
            // Check if the index is between two characters i.e. not at the very start
            // If so add the appropriate amount of character
            if (i != 0) {
                if (doubleChar) {
                    modifiedText += character;
                }
                modifiedText += character;
                doubleChar = !doubleChar;
            }
            modifiedText += text.charAt(i);
        }

        System.out.println(modifiedText);
    }

    private static void addChar(int number, char character) {
	    addChar(Integer.toString(number), character);
    }

    private static void addChar(String text, String characters) {
        // Loop through all characters and call addChar for each one
        for (int i = 0; i < characters.length(); i++) {
            addChar(text, characters.charAt(i));
        }
    }

    private static void addChar(String text) {
        addChar(text, '=');
    }

    public static void main(String[] args) {
        String text0 = "";
        String text1 = "A";
        String text2 = "CW";
        String text3 = "EP1";
        String text4 = "Index";

        addChar(text0, '&');
        addChar(text1, '+');
        addChar(text2, '*');
        addChar(text3, '-');
        addChar(text4, '#');
        System.out.println();

        addChar(1, '.');
        addChar(42, ':');
        addChar(148, '$');
        addChar(2048, ')');
        addChar(131719, '%');
        System.out.println();

        addChar(text2, "!O(");
        addChar(text4, "T1#+");
        System.out.println();

        addChar(text1);
        addChar(text2);
        addChar(text3);


    }
}
