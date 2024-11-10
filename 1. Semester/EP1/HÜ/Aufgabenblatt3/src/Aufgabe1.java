/*
    Aufgabe 1) Codeanalyse, Codingstyle und Methoden
*/
public class Aufgabe1 {
    //TODO zu Punkt a): Beschreiben Sie hier in 1-2 Sätzen was der Spaghetticode macht
    //
    //
    //

    //TODO zu Punkt b): Beschreiben Sie in 1-2 Sätzen was Sie ändern würden und warum
    //
    //
    //


    //TODO zu Punkt c und d): Implementieren Sie hier die Methoden Ihrer Lösung

    public static void main(String args[]) {
        //********************************************************
        //TODO zu Punkt c,d und e): Implementieren Sie hier Ihre Lösung für die Angabe
        System.out.println("Ihre Ausgabe:");

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


