/*
    Aufgabe 3) Zweidimensionale Arrays
*/

import java.util.Random;

public class Aufgabe3 {

    private static boolean[][] genAnimalCompound(int compoundSize, float probability) {
        Random myRand = new Random(5); // Diese Zeile nicht verändern!
        boolean[][] probArray = new boolean[compoundSize][compoundSize];

        for (int i = 0; i < probArray.length; i++) {
            for (int j = 0; j < probArray[i].length; j++) {
                // Check if this position has an animal
                if (myRand.nextFloat() <= probability) {
                    probArray[i][j] = true;
                }
            }
        }

        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        return probArray; //Zeile kann geändert oder entfernt werden.
    }

    private static int[][] calcAnimalDensity(boolean[][] animalCompound) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        int[][] densityArray = new int[animalCompound.length][animalCompound.length];

        // Iterate over every entry
        // and then calculate the density
        for (int i = 0; i < animalCompound.length; i++) {
            for (int j = 0; j < animalCompound.length; j++) {
                int density = 0;

                // Compute density
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        int x = i + k, y = j + l;
                        if (x < 0 || x > animalCompound.length  - 1 || y < 0 || y > animalCompound.length - 1) {
                            density += 0;
                        } else {
                            density += animalCompound[x][y] ? 1 : 0;
                        }
                    }
                }

                densityArray[i][j] = density;
            }
        }

        return densityArray; //Zeile kann geändert oder entfernt werden.
    }

    // helping method for printing the animal compound
    private static void printCompound(boolean[][] animalCompound) {
        for (int y = 0; y < animalCompound.length; y++) {
            for (int x = 0; x < animalCompound[y].length; x++) {
                if (animalCompound[y][x]) {
                    System.out.print("* ");
                } else {
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
    }

    // helping method for printing the number of animals
    private static void printDensity(int[][] animalCompound) {
        for (int y = 0; y < animalCompound.length; y++) {
            for (int x = 0; x < animalCompound[y].length; x++) {
                System.out.print(animalCompound[y][x] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // variables
        int compoundSize = 8;
        float probability = 0.8f;
        boolean[][] animalCompound = genAnimalCompound(compoundSize, probability);
        printCompound(animalCompound);
        System.out.println();
        int[][] animalDensity = calcAnimalDensity(animalCompound);
        printDensity(animalDensity);
    }
}
