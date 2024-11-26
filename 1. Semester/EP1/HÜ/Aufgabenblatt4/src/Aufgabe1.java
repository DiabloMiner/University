/*
    Aufgabe 1) Code Analyse - Eindimensionale Arrays
*/
public class Aufgabe1 {

    private static void printArray(int[] workArray) {
        for (int i = workArray.length; i >= 1; i--) {
            System.out.print(workArray[i - 1] + " ");
        }
        System.out.println();
    }

    private static void fillArray(int[] filledArray) {
        int number = 3;
        for (int i = 0; i < filledArray.length; i++) {
            filledArray[i] = number;
            number += 3;
        }
    }

    private static void printContentFilteredArray(int[] workArray) {
        int[] copiedArray = workArray;
        for (int i = 0; i < copiedArray.length; i++) {
            if (copiedArray[i] % 9 == 0) {
                copiedArray[i] = -1;
            }
        }
        printArray(copiedArray);
    }

    private static void fillArrayWithNewContent(int[] workArray) {
        int[] helpArray = new int[15];
        int number = 4;
        for (int i = 0; i < helpArray.length; i++) {
            helpArray[i] = number;
            number += 4;
        }
        workArray = helpArray;
        printArray(workArray);
    }

    public static void main(String[] args) {
        int[] filledArray = new int[15];
        fillArray(filledArray);
        printArray(filledArray);

        printContentFilteredArray(filledArray);
        printArray(filledArray);

        filledArray[0] = 123;
        printArray(filledArray);

        fillArrayWithNewContent(filledArray);
        printArray(filledArray);
    }

    //**************************************************************************
    //**** Notizen und Fragebeantwortungen bitte hier unterhalb durchführen! ***
    //**************************************************************************
    //Antwort zu Punkt a:
        // Es kommt bei [i - 1] zu IndexOutOfBoundsException da im for-Loop i >= 0 als Bedingung steht.

    //Antwort zu Punkt b:
        // Die Methode fillArray hat keinen Rückgabewert, weil das als Input gegebene Array in der Methode modifiziert wird
        // und dieses dann außerhalb von der Methode weiterverwendet wird.

    //Antwort zu Punkt c:
        // Die "Kopie" ist eigentlich keine Kopie, sondern eine Referenz zum selben Objekt, das in der Methode modifiziert wird,
        // wodurch das normale printArray außerhalb der Mehtode dann natürlich auch das contentFilteredArray herausgibt.

    //Antwort zu Punkt d:
        // Parameter in Java sind pass-by-Value, das heißt, dass der Wert des Parameters (in diesem Fall die Referenz auf das Array) kopiert wird,
        // aber der Wert des Parameters kann nicht geändert werden, daher können wir mit der Referenz in printContentFilteredArray das eigentliche Array verändern,
        // aber wir können nicht den Wert von workArray auf eine Referenz zu helpArray ändern.

    // Zusatzfragen:
    // 1:
        // Er muss den primitiven Datentyp int haben.

    // 2:
        // Die Länge eines Arrays wird einmal gesetzt und ist ab dann unveränderbar.
        // Es kann aber ein neues Array erstellt werden.

    // 3:
        // Man kann Arrays mit Arrays.copyOf() oder ähnlichen Methoden kopieren oder mit einem for-Loop und einem neuen int-Array.

    // 4:
        // Meist ist der Vergleich mit '==' nicht sinnvoll, da bei diesem Vergleich die Referenzen der beiden Objekte verglichen werden,
        // das heißt es wird geprüft, ob zwei Arrays das gleiche Objekt sind, aber nicht ob sie denselben Inhalt haben.

}