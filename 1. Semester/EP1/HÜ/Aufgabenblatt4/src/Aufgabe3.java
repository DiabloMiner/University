/*
    Aufgabe 3) Eindimensionale Arrays und Methoden
*/

import java.util.Arrays;

public class Aufgabe3 {

    private static void replaceValues(int[] workArray) {
        //TODO: Implementieren Sie hier Ihre Lösung für die Angabe
    }

    public static void main(String[] args) {

        int[] array1 = new int[]{12, 3, 15, 18, 22, 9, 5, 8, 16, 21};
        replaceValues(array1);
        System.out.println(Arrays.toString(array1));
        assert (Arrays.equals(array1, new int[]{3, 3, 22, 22, 22, 3, 3, 3, 22, 22}));

        int[] array2 = new int[]{12, 10, 27, 18, 22, 60, 35, 36, 16, 21};
        replaceValues(array2);
        System.out.println(Arrays.toString(array2));
        assert (Arrays.equals(array2, new int[]{10, 10, 10, 10, 10, 60, 10, 60, 10, 10}));

        int[] array3 = new int[]{4, 7, 7, 4};
        replaceValues(array3);
        System.out.println(Arrays.toString(array3));
        assert (Arrays.equals(array3, new int[]{4, 7, 7, 4}));

        int[] array4 = new int[]{-5, -1, 0, 1, 5};
        replaceValues(array4);
        System.out.println(Arrays.toString(array4));
        assert (Arrays.equals(array4, new int[]{-5, -5, -5, 5, 5}));

        int[] array5 = new int[]{1,2,3};
        replaceValues(array5);
        System.out.println(Arrays.toString(array5));
        assert (Arrays.equals(array5, new int[]{1, 1, 3}));

        int[] array6 = new int[]{7};
        replaceValues(array6);
        System.out.println(Arrays.toString(array6));
        assert (Arrays.equals(array6, new int[]{7}));
    }
}
