/*
    Aufgabe 1) Zweidimensionale Arrays
*/

import java.util.Arrays;

public class Aufgabe1 {

    private static void shiftLines(int[][] workArray) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        int[][] shiftedArray = new int[workArray.length][];

        // Find shortest line
        int min = workArray[0].length, index = 0;
        for (int i = 1; i < shiftedArray.length; i++) {
            if (workArray[i].length < min) {
                min = workArray[i].length;
                index = i;
            }
        }


        // Differentiate between all lines having the same length (min == workArray[0].length)
        // and there being a shortest line
        if (min != workArray[0].length) {
            for (int i = 0; i < workArray.length; i++) {
                if (i == 0) {
                    shiftedArray[i] = workArray[index];
                } else if (i == index) {
                    shiftedArray[i] = workArray[0];
                } else {
                    shiftedArray[i] = workArray[i];
                }
            }
        } else {
            for (int i = 0; i < workArray.length; i++) {
                shiftedArray[i] = (i != (workArray.length - 1)) ? workArray[i + 1] : workArray[0] ;
            }
        }


        // Copy shiftedArray into workArray
        for (int i = 0; i < shiftedArray.length; i++) {
            workArray[i] = shiftedArray[i];
        }
    }

    //Vorgegebene Methode - BITTE NICHT VERÄNDERN!
    private static void printArray(int[][] inputArray) {
        if (inputArray != null) {
            for (int i = 0; i < inputArray.length; i++) {
                for (int j
                     = 0; j < inputArray[i].length; j++) {
                    System.out.print(inputArray[i][j] + "\t");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("Test shiftLines:");
        System.out.println("-----");
        int[][] array1 = new int[][]{{1, 5, 6, 7}, {1, 9, 6}, {4, 3}, {6, 3, 0, 6, 9}, {6, 4, 3}};
        System.out.println("Before:");
        printArray(array1);
        shiftLines(array1);
        assert (Arrays.deepEquals(array1, new int[][]{{4, 3}, {1, 9, 6}, {1, 5, 6, 7}, {6, 3, 0, 6, 9}, {6, 4, 3}}));
        System.out.println("-----");
        System.out.println("After:");
        printArray(array1);
        System.out.println("-----");

        array1 = new int[][]{{3, 2, 4, 1}, {7, 3, 6}, {4}, {5, 6, 2, 4}, {9, 1}, {3}};
        System.out.println("Before:");
        printArray(array1);
        shiftLines(array1);
        assert (Arrays.deepEquals(array1, new int[][]{{4}, {7, 3, 6}, {3, 2, 4, 1}, {5, 6, 2, 4}, {9, 1}, {3}}));
        System.out.println("-----");
        System.out.println("After:");
        printArray(array1);
        System.out.println("-----");

        array1 = new int[][]{{3, 4, 1}, {6, 2, 5}, {9, 7, 8}};
        System.out.println("Before:");
        printArray(array1);
        shiftLines(array1);
        assert (Arrays.deepEquals(array1, new int[][]{{6, 2, 5}, {9, 7, 8}, {3, 4, 1}}));
        System.out.println("-----");
        System.out.println("After:");
        printArray(array1);
        System.out.println("-----");
    }
}

