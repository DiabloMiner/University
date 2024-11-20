/*
    Aufgabe 2) Eindimensionale Arrays
*/
public class Aufgabe2 {

    public static void printArray(short[] shortArray) {
        String toBePrinted = "";
        for (int i = 0; i < shortArray.length; i++) {
            toBePrinted += (i == 0) ? "|" : "";
            toBePrinted += shortArray[i];
            toBePrinted += (i == shortArray.length - 1) ? "|" : ";";
        }
        System.out.println(toBePrinted);
    }

    public static void printArray(char[] charArray) {
        String toBePrinted = "";
        for (int i = 0; i < charArray.length; i++) {
            toBePrinted += charArray[i];
            toBePrinted += (i == charArray.length - 1) ? "" : " ";
        }
        System.out.println(toBePrinted);
    }

    public static void printArrayForLoop(int[] intArray) {
        String toBePrinted = "for-Schleife: ";
        for (int i = intArray.length - 1; i >= 0; i--) {
            toBePrinted += intArray[i];
            toBePrinted += (i == 0) ? "" : "!";
        }
        System.out.println(toBePrinted);
    }

    public static void printArrayWhileLoop(int[] intArray) {
        String toBePrinted = "while-Schleife: ";
        int i = intArray.length - 1;
        while (i >= 0) {
            toBePrinted += intArray[i];
            toBePrinted += (i == 0) ? "" : "!";
            i--;
        }
        System.out.println(toBePrinted);
    }

    public static void main(String[] args) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        short[] shortArray = new short[] {3, 6, 24, 31, 35, 44, 67, 89, 92};
        printArray(shortArray);


        char[] charArray = new char[] {'a', '8', '8', 'G', '2', ':', ':', ':', 'F', '7', 'b', 'b', '-', 'T'};
        int insertions = 0, insertionsSoFar = 0;
        for (int i = 0; i < charArray.length - 1; i++) {
            insertions += (charArray[i] == charArray[i + 1]) ? 1 : 0;
        }
        char[] modifiedCharArray = new char[charArray.length + insertions];
        for (int i = 0; i < modifiedCharArray.length; i++) {
            modifiedCharArray[i] = charArray[i - insertionsSoFar];

            if (i - insertionsSoFar != charArray.length - 1 && charArray[i - insertionsSoFar] == charArray[i -insertionsSoFar + 1]) {
                modifiedCharArray[i + 1] = '+';
                insertionsSoFar++;
                i = i + 1;
            }
        }
        printArray(modifiedCharArray);

        int[] intArray = new int[20];
        for (int i = 19; i >= 0; i--) {
            intArray[(intArray.length - 1) - i] = i;
        }
        printArrayForLoop(intArray);
        printArrayWhileLoop(intArray);
    }
}

