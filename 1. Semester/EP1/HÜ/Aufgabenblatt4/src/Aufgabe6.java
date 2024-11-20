/*
    Aufgabe 6) Zweidimensionale Arrays
*/

import java.util.Arrays;

public class Aufgabe6 {

    private static int[][] generateReformattedArray(int[][] inputArray) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        return null; //Zeile kann geändert oder entfernt werden.
    }

    //Vorgegebene Methode - BITTE NICHT VERÄNDERN!
    private static void printArray(int[][] inputArray) {
        if (inputArray != null) {
            for (int[] arr : inputArray) {
                for (int val : arr) {
                    System.out.print(val + "\t");
                }
                System.out.println();
            }
        }
    }

    //Vorgegebene Methode - BITTE NICHT VERÄNDERN!
    private static void printArray(int[] inputArray) {
        if (inputArray != null) {
            for (int val : inputArray) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int[][] array2 = new int[][]{{1, 1, 1, 0, 2, 1}, {1, 0, 2, 1}, {1, 0, 1, 1, 1, 0, 2, 1},
                {3, 0, 1, 1, 1, 0}, {1, 1, 1, 0}, {5, 1}};
        System.out.println("Before:");
        printArray(array2);
        int[][] array2res = generateReformattedArray(array2);
        System.out.println("-----");
        System.out.println("After:");
        printArray(array2res);
        assert (Arrays.deepEquals(array2res, new int[][]{{1, 0, 1, 1}, {0, 1, 1}, {0, 1, 0, 1, 1}, {0, 0, 0, 1, 0}, {1, 0}, {1, 1, 1, 1, 1}}));
        System.out.println("-----");


        System.out.println();
        array2 = new int[][]{{1, 1, 1, 0, 2, 1, 4, 0}, {1, 0, 5, 1, 2, 0}, {6, 0, 2, 1},
                {1, 1, 7, 0}, {4, 0, 2, 1, 1, 0, 1, 1}, {8, 0}, {7, 0, 1, 1}};
        System.out.println("Before:");
        printArray(array2);
        array2res = generateReformattedArray(array2);
        System.out.println("-----");
        System.out.println("After:");
        printArray(array2res);
        assert (Arrays.deepEquals(array2res, new int[][]{{1, 0, 1, 1, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1}, {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1}}));
        System.out.println("-----");


        System.out.println();
        array2 = new int[][]{{1, 0}, {1, 1}, {2, 0}, {1, 0, 1, 1}, {1, 1, 1, 0}, {2, 1}};
        System.out.println("Before:");
        printArray(array2);
        array2res = generateReformattedArray(array2);
        System.out.println("-----");
        System.out.println("After:");
        printArray(array2res);
        assert (Arrays.deepEquals(array2res, new int[][]{{0}, {1}, {0, 0}, {0, 1}, {1, 0}, {1, 1}}));
        System.out.println("-----");


        System.out.println();
        array2 = new int[][]{{12, 0}};
        System.out.println("Before:");
        printArray(array2);
        array2res = generateReformattedArray(array2);
        System.out.println("-----");
        System.out.println("After:");
        printArray(array2res);
        assert (Arrays.deepEquals(array2res, new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}));
        System.out.println("-----");

    }
}
