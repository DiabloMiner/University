/*
    Aufgabe 1) Codeanalyse, Codingstyle und Methoden
*/
public class Aufgabe1 {
    //TODO zu Punkt a): Beschreiben Sie hier in 1-2 Sätzen was der Spaghetticode macht
    // Am Ende und Anfang jeder Zeile wird immer ein '#' ausgegeben.
    // Zuerst wird eine Anfangszeile, gefüllt mit '+' herausgegeben, dann eine Zeile gefüllt mit '.' außer einem "Dach" ("/\") in der Mitte,
    // mit jeder weiteren Iteration werden '/' und '\' jeweils nach links/rechts geschoben - der Raum zwischen ihnen bleibt leer.
    // Dies geschieht solange bis die Zeile nur aus '#' und '/' oder '\' an den Rändern besteht, dann wird eine Zeile
    // in der '/' und '\' durch '|' ersetzt werden eingefügt und dann wird das ganze rückwärts abgewickelt, wobei dort
    // wo ursprünglich ein '/' stand, jetzt ein '\' steht, etc.

    //TODO zu Punkt b): Beschreiben Sie in 1-2 Sätzen was Sie ändern würden und warum
    // Zuerst würde ich die Einrückungen zum einen konsistenter machen und zum anderen an die Tiefe ihrer Funktionalität anpassen.
    // Außerdem sollte man sich in Bezug auf die geschwungenen Klammern konsistent für einen Stil entscheiden,
    // zwischen den Codeabschnitten, die für einzelne Ausgabeabschnitte verantwortlich sind, eine passende Anzahl von Leerzeilen freilassen und
    // auch nicht mehrere Anweisungen in eine Zeile schreiben.


    //TODO zu Punkt c und d): Implementieren Sie hier die Methoden Ihrer Lösung

    public static String fillString(Character fillChar, int width) {
        String filledString = "";
        for (int i = 0; i < width; i++) {
            filledString += fillChar;
        }
        return filledString;
    }

    public static String generateRoofLine(Character leftChar, Character rightChar, int adjustedWidth, int i) {
        String points = fillString('.', adjustedWidth / 2 - i);
        String spaces = fillString(' ', 2 * (i - 1));
        return "#" + points + leftChar + spaces + rightChar + points + "#";
    }

    public static void printInitialLine(int width) {
        String line = '#' + fillString('+', width - 2) + '#';
        System.out.println(line);
    }

    public static void printMiddleLine(int width) {
        String line = "#|" + fillString(' ', width - 4) + "|#";
        System.out.println(line);
    }

    public static void printUpwardsRoof(int width) {
        int adjustedWidth = width - 2;

        for (int i = 1; i < width / 2; i++) {
            System.out.println(generateRoofLine('/',  '\\', adjustedWidth, i));
        }
    }

    public static void printDownwardsRoof(int width) {
        int adjustedWidth = width - 2;

        for (int i = width / 2 - 1; i > 0; i--) {
            System.out.println(generateRoofLine('\\',  '/', adjustedWidth, i));
        }
    }

    public static void main(String args[]) {
        //********************************************************
        //TODO zu Punkt c,d und e): Implementieren Sie hier Ihre Lösung für die Angabe
        System.out.println("Ihre Ausgabe:");

        printInitialLine(10);

        printUpwardsRoof(10);
        printMiddleLine(10);
        printDownwardsRoof(10);

        printInitialLine(10);


        //********************************************************

        System.out.println();
        System.out.println("Ausgabe Spaghetticode:");

        System.out.print("#");
        for (int i = 1; i <= 8; i++) {System.out.print("+");}
        System.out.println("#");

        for (int i = 1; i < 5; i++) {
        System.out.print("#");
        for (int j = 2; j <= (8 / 2 - i + 1); j++) {System.out.print(".");
        }
        System.out.print("/");
        for (int j = 1; j <= 2 * (i - 1); j++) {
            System.out.print(" ");
        }
            System.out.print("\\");
            for (int j = 2; j <= (8 / 2 - i + 1); j++) {
            System.out.print(".");
            }System.out.print("#");System.out.println();
        }

        System.out.println("#|      |#");
        for (int i = 1; i < 5; i++) {
        System.out.print("#");
        for (int j = 2; j <= i; j++) {
            System.out.print(".");
        }
            System.out.print("\\");
for (int j = 1; j <= (8 - 2 * i); j++) {
    System.out.print(" ");
}
            System.out.print("/");
            for (int j = 2; j <= i; j++) {
                System.out.print(".");
}
            System.out.print("#");
            System.out.println();
}

        System.out.print("#");
        for (int i = 1; i <= 8; i++) {
        System.out.print("+");}
        System.out.println("#");
    }
}


