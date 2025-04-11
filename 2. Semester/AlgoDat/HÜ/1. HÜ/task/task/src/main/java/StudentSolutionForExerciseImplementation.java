import exercise.StudentSolutionForExercise;
import exercise.StudentInformation;
import exercise.Point;
import exercise.ClosestPair;

/**
 * A class intended for students to implement their solutions in.
 */
public class StudentSolutionForExerciseImplementation implements StudentSolutionForExercise {

    /**
     * Collects and returns information about the student working on solving the instance sets.
     * This method is called automatically.
     * @return First name, last name, and matriculation number collected in a {@link StudentInformation} instance.
     */
    public StudentInformation provideStudentInformation() {
        return new StudentInformation(
                "Jakob", // Vorname
                "Plangger", // Nachname
                "12418810" // Matrikelnummer
        );
    }

    // Implementieren Sie hier Ihre Lösung für die Maximumsuche
    public int findMax(int[] numbers) {
        if (numbers == null || numbers.length <= 0) return 0;
        int max = Integer.MIN_VALUE;
        for (int number : numbers) {
            if (number > max) {
                max = number;
            }
        }
        return max;
    }

    private double dist(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    // Implementieren Sie hier Ihre Lösung für das dichteste Punktepaar
    public void findClosestPair(Point[] points, ClosestPair closestPair) {
        int n = points.length;
        double min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (dist(points[i], points[j]) < min) {
                    min = dist(points[i], points[j]);
                    closestPair.setPoint1(points[i]);
                    closestPair.setPoint2(points[j]);
                }
            }
        }
    }

    private int binomial(int n, int k) {
        if (k > n - k)
            k = n - k;

        int b = 1;
        for (int i = 1, m = n; i <= k; i++, m--)
            b = b * m / i;
        return b;
    }

    private int[] expandArray(int[] array, int number) {
        int[] newArray = new int[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = number;
        return newArray;
    }

    private boolean subsetSum(int[] numbers, int[][] subsets, int n, int i, int sum) {
        // If no match has been found at this point the algorithm can end
        if (n == i) return subsets[0][0] == sum;

        // Test if subsets introduced to function contain the sum
        for (int[] subset : subsets) {
            int subsetSum = 0;
            for (int k = 0; k < subset.length; k++) {
                subsetSum += numbers[subset[k]];
            }
            if (subsetSum == sum) return true;
        }

        // Construct a new level of subsets
        int[][] newSubsets = new int[binomial(n, ++i)][];
        int g = 0;
        for (int[] subset : subsets) {
            int lastIndex = subset[subset.length - 1];
            for (int k = lastIndex + 1; k < n; k++) {
                newSubsets[g++] = expandArray(subset, k);
            }
        }
        return subsetSum(numbers, newSubsets, n, i, sum);
    }

    private boolean recursiveSubsetSum(int[] numbers, int sum) {
        // Search over all subsets of current numbers if they are like sum (only if numbers.length == 1)
        if (numbers.length == 1) {
            return numbers[0] == sum;
        } else {
            // Remove first element from numbers and call recursive subset sum again
            // 1 2 3 4
            // 2 3 4 ->
            int[] newNumbers = new int[numbers.length - 1];
            for (int i = 0; i < newNumbers.length; i++) {
                newNumbers[i] = numbers[i + 1];
            }

            return numbers[0] == sum || recursiveSubsetSum(newNumbers, sum) || recursiveSubsetSum(newNumbers, sum - numbers[0]);
        }
    }

    // Implementieren Sie hier Ihre Lösung für die Teilsummen
    public boolean hasSubsetSum(int sum, int[] numbers) {
        return recursiveSubsetSum(numbers, sum);
        /*
        // Create initial subsets
        int[][] subsets = new int[numbers.length][];
        for (int i = 0; i < numbers.length; i++) {
            subsets[i] = new int[] {i};
        }

        // Call recursive functions
        return subsetSum(numbers, subsets, numbers.length, 1, sum);*/

    }
}
